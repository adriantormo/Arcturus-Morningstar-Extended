package com.eu.habbo.messages.incoming.guilds;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.guilds.Guild;
import com.eu.habbo.habbohotel.users.HabboStats;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.guilds.GuildListComposer;
import gnu.trove.set.hash.THashSet;

public class RequestOwnGuildsEvent extends MessageHandler {
    @Override
    public int getRatelimit() {
        return 500;
    }

    @Override
    public void handle() throws Exception {
        THashSet<Guild> guilds = new THashSet<Guild>();
        HabboStats stats = this.client.getHabbo().getHabboStats();

        stats.guilds.clear();

        for (Guild guild : Emulator.getGameEnvironment().getGuildManager().getGuilds(this.client.getHabbo().getHabboInfo().getId())) {
            if (guild != null) {
                guilds.add(guild);
                stats.addGuild(guild.getId());
            }
        }

        this.client.sendResponse(new GuildListComposer(guilds, this.client.getHabbo()));
    }
}
