package net.lindseybot.discord;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Embed {

    private String url;
    private Message title;
    private Integer color;
    private Message description;
    private Long timestamp;
    private String thumbnail;
    private String image;

    private Author author;
    private Footer footer;

    private List<Field> fields = new ArrayList<>();

    @Data
    public static class Field {

        private Message name;
        private Message value;
        private boolean inline;

    }

    @Data
    public static class Author {

        private Message name;
        private String url;
        private String icon;

    }

    @Data
    public static class Footer {

        private Message text;
        private String icon;

    }

}
