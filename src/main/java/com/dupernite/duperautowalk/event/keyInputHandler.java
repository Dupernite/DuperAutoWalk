package com.dupernite.duperautowalk.event;


import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class keyInputHandler {
    public static final String CATEGORY = "key.category.duperautowalk";
    public static final String KEY_AUTO_WALK = "key.duperautowalk.autowalk";
    public static KeyBinding autoWalk;
    public static boolean isAutoWalking = false;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            KeyBinding forwardKey = MinecraftClient.getInstance().options.forwardKey;
            if (autoWalk.wasPressed()) {
                isAutoWalking = !isAutoWalking;
            }
            if (isAutoWalking) {
                if (client.player != null) {
                    KeyBinding.setKeyPressed(InputUtil.fromTranslationKey(forwardKey.getBoundKeyTranslationKey()), true);
                }
            }
        });
    }

    public static void register() {
        autoWalk = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_AUTO_WALK,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_Z,
                CATEGORY
                )
        );

        registerKeyInputs();
    }
}
