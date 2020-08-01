package me.optix.util;

import java.util.UUID;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

import me.optix.modules.player.RealFreecam;
import com.mojang.authlib.GameProfile;

public class CEEntityPlayer extends AbstractClientPlayer
{
    public CEEntityPlayer(World p_i45324_1_)
    {
        super(p_i45324_1_, new GameProfile(UUID.randomUUID(), Minecraft.getMinecraft().thePlayer.getName()));
        Minecraft mc = Minecraft.getMinecraft();
        this.setPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ);
        RealFreecam.c = this;
    }

    @Override
    public void addChatMessage(IChatComponent var1)
    {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean canCommandSenderUseCommand(int var1, String var2)
    {
        // TODO Auto-generated method stub
        return false;
    }

}