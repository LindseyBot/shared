package net.lindseybot.commands.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.lindseybot.discord.Embed;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuResponse implements CommandResponse {

    private List<Embed> embeds = new ArrayList<>();

    /**
     * Adds an embed to this response.
     *
     * @param embed Embed.
     * @return PaginatedResponse for convenience.
     */
    public MenuResponse addEmbed(Embed embed) {
        this.embeds.add(embed);
        return this;
    }

}
