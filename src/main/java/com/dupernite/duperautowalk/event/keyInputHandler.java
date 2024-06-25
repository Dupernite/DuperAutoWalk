package com.dupernite.duperautowalk.event;


import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.MinecartCommandBlockScreen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.network.packet.c2s.play.PlayerInputC2SPacket;
import org.lwjgl.glfw.GLFW;
import net.minecraft.entity.player.PlayerEntity;

public class keyInputHandler {
    public static final String CATEGORY = "key.category";
    public static final String KEY_AUTO_WALK = "key.duperautowalk.autowalk";

    public static KeyBinding autoWalkKey;
    public static boolean isOn = false;
    private static boolean ForwardKeyState = false;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (autoWalkKey.wasPressed()) {
                isOn = !isOn;
            }

            if (isOn) {
                MinecraftClient.getInstance().options.forwardKey.setPressed(true);
                ForwardKeyState = true;
            } else if (!isOn && ForwardKeyState) {
                MinecraftClient.getInstance().options.forwardKey.setPressed(false);
                ForwardKeyState = false;
            }
        });
    }

    public static void register() {
        autoWalkKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_AUTO_WALK,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_Z,
                CATEGORY
                )
        );

        registerKeyInputs();
    }
}
