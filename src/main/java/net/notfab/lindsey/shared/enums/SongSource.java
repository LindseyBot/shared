package net.notfab.lindsey.shared.enums;

import lombok.Getter;

public enum SongSource {

    Youtube("https://www.youtube.com/watch?v="),
    SoundCloud("https://soundcloud.com/"),
    HTTP(null);

    @Getter
    private final String baseURL;

    SongSource(String baseURL) {
        this.baseURL = baseURL;
    }

}
