package net.lindseybot.discord;

import lombok.Data;
import net.lindseybot.enums.DiscordButtonStyle;

@Data
public class Button implements MessageComponent {

    private String idOrUrl;
    private String data;
    private Message label;
    private DiscordButtonStyle style;
    private boolean disabled;
    private Emote emote;
    private long userFilter;

}
