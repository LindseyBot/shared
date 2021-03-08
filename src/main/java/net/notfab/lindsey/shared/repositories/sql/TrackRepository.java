package net.notfab.lindsey.shared.repositories.sql;

import net.notfab.lindsey.shared.entities.music.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRepository extends JpaRepository<Track, String> {
}
