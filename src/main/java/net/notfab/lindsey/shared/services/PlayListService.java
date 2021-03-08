package net.notfab.lindsey.shared.services;

import lombok.extern.slf4j.Slf4j;
import net.notfab.lindsey.shared.entities.music.Track;
import net.notfab.lindsey.shared.entities.playlist.PlayList;
import net.notfab.lindsey.shared.enums.PlayListSecurity;
import net.notfab.lindsey.shared.repositories.sql.CuratorRepository;
import net.notfab.lindsey.shared.repositories.sql.TrackRepository;
import org.simmetrics.StringMetric;
import org.simmetrics.builders.StringMetricBuilder;
import org.simmetrics.metrics.CosineSimilarity;
import org.simmetrics.simplifiers.Simplifiers;
import org.simmetrics.tokenizers.Tokenizers;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Slf4j
public class PlayListService {

    private static final String PLAYLIST_PREFIX = "Lindsey:PlayList:";
    private static final StringMetric metric = StringMetricBuilder.with(new CosineSimilarity<>())
            .simplify(Simplifiers.toLowerCase(Locale.ENGLISH))
            .simplify(Simplifiers.replaceNonWord())
            .tokenize(Tokenizers.whitespace())
            .build();

    private final TrackRepository repository;
    private final ListOperations<String, String> redis;
    private final CuratorRepository curatorRepository;

    public PlayListService(TrackRepository repository, StringRedisTemplate redis, CuratorRepository curatorRepository) {
        this.repository = repository;
        this.redis = redis.opsForList();
        this.curatorRepository = curatorRepository;
    }

    public Optional<Track> getNext(long playlist, int last) {
        Optional<Track> oTrack = this.getByPos(playlist, last + 1);
        if (oTrack.isPresent()) {
            Track track = oTrack.get();
            track.setPosition(last + 1);
            return Optional.of(track);
        } else if (last == -1) {
            return Optional.empty();
        } else {
            return getNext(playlist, -1);
        }
    }

    public Optional<Track> getByPos(long playlist, int pos) {
        String code = redis.index(PLAYLIST_PREFIX + playlist, pos);
        if (code == null) {
            return Optional.empty();
        }
        Optional<Track> oTrack = this.repository.findById(code);
        if (oTrack.isEmpty()) {
            return Optional.empty();
        }
        Track track = oTrack.get();
        track.setPosition(pos);
        return Optional.of(track);
    }

    public Optional<Track> findByName(long playlist, String name) {
        double score = 0;
        Track best = null;
        List<Track> allTracks = this.getAllTracks(playlist);
        for (int i = 0, allTracksSize = allTracks.size(); i < allTracksSize; i++) {
            Track track = allTracks.get(i);
            double s = metric.compare(track.getTitle(), name);
            if (s > score) {
                score = s;
                best = track;
                best.setPosition(i);
            }
        }
        return Optional.ofNullable(best);
    }

    public Optional<Track> findByCode(long playlist, String code) {
        List<String> range = redis.range(PLAYLIST_PREFIX + playlist, 0, this.size(playlist));
        if (range == null || range.isEmpty()) {
            return Optional.empty();
        }
        for (int i = 0; i < range.size(); i++) {
            String c = range.get(i);
            if (c.equals(code)) {
                Optional<Track> oTrack = repository.findById(code);
                if (oTrack.isEmpty()) {
                    return Optional.empty();
                }
                Track track = oTrack.get();
                track.setPosition(i);
                return Optional.of(track);
            }
        }
        return Optional.empty();
    }

    public Long size(long playlist) {
        return redis.size(PLAYLIST_PREFIX + playlist);
    }

    public List<Track> getAllTracks(long playlist) {
        List<String> range = redis.range(PLAYLIST_PREFIX + playlist, 0, this.size(playlist));
        if (range == null) {
            return new ArrayList<>();
        }
        return this.repository.findAllById(range);
    }

    public int add(long playlist, Track... tracks) {
        List<String> inPlayList = redis.range(PLAYLIST_PREFIX + playlist, 0, this.size(playlist));
        if (inPlayList == null) {
            inPlayList = new ArrayList<>();
        }
        List<Track> toAdd = new ArrayList<>();
        for (Track track : tracks) {
            boolean isPresent = inPlayList.stream()
                    .anyMatch(old -> old.equals(track.getCode()));
            if (isPresent) {
                continue;
            }
            toAdd.add(track);
        }
        for (Track track : toAdd) {
            redis.rightPush(PLAYLIST_PREFIX + playlist, track.getCode());
        }
        return toAdd.size();
    }

    public Long remove(long playlist, Track track) {
        return this.redis.remove(PLAYLIST_PREFIX + playlist, 0, track.getCode());
    }

    public boolean canRead(PlayList playList, long userId) {
        if (playList.getSecurity() == PlayListSecurity.PUBLIC
                || playList.getSecurity() == PlayListSecurity.SHARED) {
            return true;
        }
        if (playList.getOwner() == userId) {
            return true;
        }
        return this.curatorRepository.findByPlayListAndUserId(playList, userId)
                .isPresent();
    }

    public boolean canModify(PlayList playList, long userId) {
        if (playList.getSecurity() == PlayListSecurity.PUBLIC) {
            return true;
        }
        if (playList.getOwner() == userId) {
            return true;
        }
        return this.curatorRepository.findByPlayListAndUserId(playList, userId)
                .isPresent();
    }

}
