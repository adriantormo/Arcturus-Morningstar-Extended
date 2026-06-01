package com.eu.habbo.messages.incoming.guilds;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.guilds.Guild;
import com.eu.habbo.habbohotel.users.HabboStats;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.guilds.GuildListComposer;
import gnu.trove.set.hash.THashSet;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RequestOwnGuildsEvent extends MessageHandler {
    @Override
    public int getRatelimit() {
        return 500;
    }

    @Override
    public void handle() throws Exception {
        THashSet<Guild> guilds = new THashSet<Guild>();
        Set<Integer> guildIds = new HashSet<>();
        HabboStats stats = this.client.getHabbo().getHabboStats();

        for (int guildId : stats.guilds) {
            if (guildId == 0)
                continue;

            Guild guild = Emulator.getGameEnvironment().getGuildManager().getGuild(guildId);

            if (guild != null && guildIds.add(guild.getId())) {
                guilds.add(guild);
            }
        }

        List<Guild> persistedGuilds = Emulator.getGameEnvironment().getGuildManager().getGuilds(this.client.getHabbo().getHabboInfo().getId());
        persistedGuilds.addAll(Emulator.getGameEnvironment().getGuildManager().getOwnedGuilds(this.client.getHabbo().getHabboInfo().getId()));

        for (Guild guild : persistedGuilds) {
            if (guild != null && guildIds.add(guild.getId())) {
                guilds.add(guild);
            }
        }

        if (!guilds.isEmpty()) {
            stats.guilds.clear();

            for (Guild guild : guilds) {
                stats.addGuild(guild.getId());
            }
        }

        this.client.sendResponse(new GuildListComposer(guilds, this.client.getHabbo()));
    }
}
