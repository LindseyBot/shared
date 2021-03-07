package net.notfab.lindsey.shared.rpc.services;

import net.notfab.lindsey.shared.rpc.FGuild;

public interface RemoteGuilds {

    FGuild getGuild(long id, long userId);

}
