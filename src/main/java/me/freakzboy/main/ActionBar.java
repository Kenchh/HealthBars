package me.freakzboy.main;

import net.minecraft.server.v1_15_R1.IChatBaseComponent;
import net.minecraft.server.v1_15_R1.PacketPlayOutTitle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class ActionBar extends Packets {

    private String text;

    public ActionBar(String text) {
        this.text = text;
    }

    @Override
    public void send(LivingEntity entity) {
        final IChatBaseComponent cbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + text + "                         " + "\"}");
        final PacketPlayOutTitle ppoc = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.ACTIONBAR, cbc);
        this.sendPacket((Player) entity, ppoc);
    }


}
