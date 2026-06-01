package com.eu.habbo.messages.incoming.users;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.habbohotel.users.HabboManager;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.users.UserProfileComposer;

public class RequestUserProfileEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        int habboId = this.packet.readInt();
        boolean openProfileWindow = this.packet.bytesAvailable() > 0 ? this.packet.readBoolean() : true;
        Habbo habbo = Emulator.getGameEnvironment().getHabboManager().getHabbo(habboId);

        if (habbo != null)
            this.client.sendResponse(new UserProfileComposer(habbo, this.client, openProfileWindow));
        else
            this.client.sendResponse(new UserProfileComposer(HabboManager.getOfflineHabboInfo(habboId), this.client, openProfileWindow));
    }
}
