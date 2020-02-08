package gregapi6.network;

import codechicken.lib.packet.PacketCustom;
import codechicken.lib.packet.PacketCustom.IClientPacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.INetHandlerPlayClient;

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

public class GT6CPH implements IClientPacketHandler
{
    public static final String channel = "GT6Unofficial";

    @Override
    public void handlePacket(PacketCustom packet, Minecraft mc, INetHandlerPlayClient netHandler) {
        switch (packet.getType()) {
            case 100:
                System.out.println("client");
                break;
        }
    }

    public static void sendMultiblockBuild(ItemStack item) {
        PacketCustom packet = new PacketCustom(channel, 100);
        packet.writeItemStack(item, true);
        packet.sendToServer();
    }
}
