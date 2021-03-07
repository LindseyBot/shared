package net.notfab.lindsey.shared.rpc;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FGuild implements Serializable {

    static final long serialVersionUID = 1L;

    private long id;
    private String name;
    private String iconUrl;

    private List<FTextChannel> textChannels;
    private List<FVoiceChannel> voiceChannels;

    public FTextChannel getTextChannelById(long id) {
        return this.textChannels
                .stream()
                .filter(channel -> channel.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public FVoiceChannel getVoiceChannelById(long id) {
        return this.voiceChannels
                .stream()
                .filter(channel -> channel.getId() == id)
                .findFirst()
                .orElse(null);
    }

}
