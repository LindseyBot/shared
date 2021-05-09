package net.notfab.lindsey.shared.rpc.services;

import net.notfab.lindsey.shared.rpc.FGuild;

import java.util.List;

public interface RemoteGuildsService {

    default String getRabbitName() {
        return "RemoteGuildsService";
    }

    FGuild getGuild(long id, long userId);

    List<FGuild> getDetails(List<Long> ids, long userId);

}
