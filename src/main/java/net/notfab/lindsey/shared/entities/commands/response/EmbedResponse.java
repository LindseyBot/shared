package net.notfab.lindsey.shared.entities.commands.response;

import lombok.Data;
import net.notfab.lindsey.shared.entities.commands.CommandResponse;

@Data
public class EmbedResponse implements CommandResponse {

    private String embed; //TODO: Embed type

}
