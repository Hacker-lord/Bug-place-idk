package me.optix.modules.player;

import org.lwjgl.input.Keyboard;

import me.optix.modules.Module;
import me.optix.util.CEEntityPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

public class RealFreecam extends Module {
	
	public static CEEntityPlayer c;
	double oldXPos;
    double oldYPos;
	double oldZPos;

	public RealFreecam() {
		super("Freecam", "Lets you view rendered chunks as if you are in spectator mode", Category.PLAYER);
		// TODO Auto-generated constructor stub
	}
	public void onEnable() {
		oldXPos = mc.thePlayer.posX;
		oldYPos = mc.thePlayer.posY;
		oldZPos = mc.thePlayer.posZ;
		if(Minecraft.getMinecraft().theWorld != null){
            Minecraft.getMinecraft().theWorld.spawnEntityInWorld(new CEEntityPlayer(Minecraft.getMinecraft().theWorld));
    	}

	}
	public void onUpdate() {
		if(this.isEnabled()) {
			fly(mc.thePlayer);
		}
	}
	public void onDisable() {
		if (c != null) {
        	// optional: if that's enabled, we won't be able to glitch with freecam anymore, but won't be kicked for flying on vanilla servers.
            //Minecraft.getMinecraft().thePlayer.setPosition(c.posX, c.posY, c.posZ);
			mc.thePlayer.setPosition(oldXPos, oldYPos, oldZPos);

            Minecraft.getMinecraft().theWorld.removeEntity(c);
        }
	}
	public void onClientShutdown() {
		this.toggled = false;
	}
	
    public static void fly(EntityPlayerSP s){
    	s.onGround = false;
    	s.motionX = 0.0D;
		s.motionY = 0.0D;
		s.motionZ = 0.0D;
		if (Keyboard.isKeyDown(57) && Minecraft.getMinecraft().inGameHasFocus) {
			s.motionY++;
		} else if (Keyboard.isKeyDown(42) && Minecraft.getMinecraft().inGameHasFocus) {
			s.motionY--;
		}
		double d5 = s.rotationPitch + 90F;
		double d7 = s.rotationYaw + 90F;
		boolean flag4 = Keyboard.isKeyDown(17) && Minecraft.getMinecraft().inGameHasFocus;
		boolean flag6 = Keyboard.isKeyDown(31) && Minecraft.getMinecraft().inGameHasFocus;
		boolean flag8 = Keyboard.isKeyDown(30) && Minecraft.getMinecraft().inGameHasFocus;
		boolean flag10 = Keyboard.isKeyDown(32) && Minecraft.getMinecraft().inGameHasFocus;
		if (flag4) {
			if (flag8) {
				d7 -= 45D;
			} else if (flag10) {
				d7 += 45D;
			}
		} else if (flag6) {
			d7 += 180D;
			if (flag8) {
				d7 += 45D;
			} else if (flag10) {
				d7 -= 45D;
			}
		} else if (flag8) {
			d7 -= 90D;
		} else if (flag10) {
			d7 += 90D;
		}
		if (flag4 || flag8 || flag6 || flag10) {
			s.motionX = Math.cos(Math.toRadians(d7));
			s.motionZ = Math.sin(Math.toRadians(d7));
		}
    }

}
