package gregapi6.network;

import codechicken.lib.packet.PacketCustom;
import codechicken.lib.packet.PacketCustom.IServerPacketHandler;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.INetHandlerPlayServer;

/*
 * Original NotEnoughItems
 * Copyright (c) 2014 ChickenBones under the MIT License
 * https://github.com/Chicken-Bones/NotEnoughItems/blob/master/LICENSE.txt
 *
 * NotEnoughItems Unofficial
 * Copyright (c) 2019 monatann
 * The code where I have written is All Rights Reserved.
 * You can personal use/modify/fork/pull request this, but you may not re-upload without my permission.
 * It's possible to chnage License again, please see here about more detail.
 * https://github.com/CatastropheModpack/NotEnoughItems/blob/1.7.10-master/LICENSE.txt
 */

public class GT6SPH implements IServerPacketHandler
{

    public static final String channel = "NEI";

    @Override
    public void handlePacket(PacketCustom packet, EntityPlayerMP sender, INetHandlerPlayServer netHandler) {
        switch (packet.getType()) {
            case 100:
            	ItemStack item = packet.readItemStack(true);
            	System.out.println(item.getDisplayName());
                break;
        }
    }

}
