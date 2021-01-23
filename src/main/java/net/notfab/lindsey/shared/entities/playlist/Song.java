package net.notfab.lindsey.shared.entities.playlist;

import lombok.Data;
import net.notfab.lindsey.shared.enums.SongSource;

@Data
public class Song {

    private String url;
    private String name;
    private String author;
    private SongSource source;

    private long length;
    private boolean stream;
    private String identifier;

    public boolean isSimilar(Song song) {
        if (url.equals(song.getUrl())) {
            return true;
        }
        if (name.equalsIgnoreCase(song.getName())) {
            return true;
        }
        if (author.equalsIgnoreCase(song.getAuthor())) {
            return true;
        }
        if (name.toLowerCase().contains(song.getName().toLowerCase())) {
            return true;
        }
        return author.toLowerCase().contains(song.getAuthor().toLowerCase());
    }

}
