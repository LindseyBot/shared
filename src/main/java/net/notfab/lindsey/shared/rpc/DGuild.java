package net.notfab.lindsey.shared.rpc;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DGuild implements Serializable {

    static final long serialVersionUID = 1L;

    private long id;
    private String name;
    private String iconUrl;

    private List<DTextChannel> textChannels;
    private List<DVoiceChannel> voiceChannels;

    public DTextChannel getTextChannelById(long id) {
        return this.textChannels
                .stream()
                .filter(channel -> channel.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public DVoiceChannel getVoiceChannelById(long id) {
        return this.voiceChannels
                .stream()
                .filter(channel -> channel.getId() == id)
                .findFirst()
                .orElse(null);
    }

}
