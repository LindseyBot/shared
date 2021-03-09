package net.notfab.lindsey.shared.rpc;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class FGuild implements Serializable {

    static final long serialVersionUID = 1L;

    private long id;
    private String name;
    private String iconUrl;

    private List<FChannel> channels;
    private List<FRole> roles;

    @JsonIgnore
    public List<FChannel> getAllChannels() {
        List<FChannel> channels = new ArrayList<>();
        for (FChannel channel : this.channels) {
            if (channel instanceof FCategory) {
                channels.addAll(((FCategory) channel).getChannels());
            } else {
                channels.add(channel);
            }
        }
        return channels;
    }

    @JsonIgnore
    public FChannel getTextChannelById(long id) {
        return this.getAllChannels()
                .stream()
                .filter(channel -> channel.getType() == FChannelType.TEXT)
                .filter(channel -> channel.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @JsonIgnore
    public FChannel getVoiceChannelById(long id) {
        return this.getAllChannels()
                .stream()
                .filter(channel -> channel.getType() == FChannelType.VOICE)
                .filter(channel -> channel.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @JsonIgnore
    public FRole getRoleById(long id) {
        return this.roles.stream()
                .filter(role -> role.getId() == id)
                .findFirst()
                .orElse(null);
    }

}
