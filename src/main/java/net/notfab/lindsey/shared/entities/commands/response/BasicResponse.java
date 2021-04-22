package net.notfab.lindsey.shared.entities.commands.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.notfab.lindsey.shared.entities.commands.CommandResponse;

@Data
@AllArgsConstructor
public class BasicResponse implements CommandResponse {

    private final String message;

}
