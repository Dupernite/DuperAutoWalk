package com.dupernite.duperautowalk.client;

import com.dupernite.duperautowalk.DuperAutoWalk;
import com.dupernite.duperautowalk.compat.MidnightLibAPI;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.Identifier;
import com.dupernite.duperautowalk.event.keyInputHandler;

public class AutoWalkOverlay implements HudRenderCallback {
    private static final Identifier TEXTURE = Identifier.of(DuperAutoWalk.MOD_ID,
            "textures/gui/autowalk.png");

    @Override
    public void onHudRender(DrawContext drawContext, RenderTickCounter tickCounter) {
        MinecraftClient client = MinecraftClient.getInstance();

        if(client != null){
            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();
            int x = 0;
            int y = 0;

            RenderSystem.setShader(GameRenderer::getPositionTexProgram);
            RenderSystem.setShaderColor(1, 1, 1, 1);

            RenderSystem.setShaderTexture(0, TEXTURE);
            if(keyInputHandler.isOn && MidnightLibAPI.isIconEnabled){
                switch (MidnightLibAPI.positionEnum) {
                    case TOP_LEFT -> {
                        x = 0;
                        y = 0;
                    }
                    case TOP_RIGHT -> {
                        x = width - 16;
                        y = 0;
                    }
                    case MIDDLE_LEFT -> {
                        x = 0;
                        y = height / 2 - 8;
                    }
                    case MIDDLE_RIGHT -> {
                        x = width - 16;
                        y = height / 2 - 8;
                    }
                    case BOTTOM_LEFT -> {
                        x = 0;
                        y = height - 16;
                    }
                    case BOTTOM_RIGHT -> {
                        x = width - 16;
                        y = height - 16;
                    }
                }
                drawContext.drawTexture(TEXTURE, x, y, 0,0,16,16,16,16);
            }
        }
    }
}
