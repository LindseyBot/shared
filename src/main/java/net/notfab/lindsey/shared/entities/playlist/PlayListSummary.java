package net.notfab.lindsey.shared.entities.playlist;

import net.notfab.lindsey.shared.enums.PlayListGenre;

public interface PlayListSummary {

    String getName();

    String getLogo();

    PlayListGenre getGenre();

    long getId();

    long getVotes();

}
