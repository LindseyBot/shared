package net.notfab.lindsey.shared.rpc.services;

import net.notfab.lindsey.shared.rpc.DGuild;

public interface RemoteGuilds {

    DGuild getGuild(long id, long userId);

}
