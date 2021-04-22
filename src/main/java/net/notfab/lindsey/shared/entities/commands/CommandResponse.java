package net.notfab.lindsey.shared.entities.commands;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import net.notfab.lindsey.shared.entities.commands.response.ErrorResponse;
import net.notfab.lindsey.shared.entities.commands.response.InvalidResponse;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public interface CommandResponse {

    default boolean isError() {
        return this instanceof ErrorResponse;
    }

    default boolean isInvalid() {
        return this instanceof InvalidResponse;
    }

}
