package com.dupernite.duperautowalk.client;

import com.dupernite.duperautowalk.DuperAutoWalk;
import com.dupernite.duperautowalk.compat.YACLconfig;
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
            int size_x = 0;
            int size_y = 0;

            RenderSystem.setShader(GameRenderer::getPositionTexProgram);
            RenderSystem.setShaderColor(1, 1, 1, 1);

            RenderSystem.setShaderTexture(0, TEXTURE);
            if(keyInputHandler.isOn && YACLconfig.getFeedback() == YACLconfig.feedbackEnum.HUD){
                switch (YACLconfig.getPosition()) {
                    case BOTTOM_LEFT-> {
                        y = height - 17;
                    }
                    case BOTTOM_RIGHT -> {
                        x = width - 16;
                        y = height - 17;
                    }
                    case MIDDLE_LEFT -> {
                        y = height / 2 - 8;
                    }
                    case MIDDLE_RIGHT -> {
                        x = width - 16;
                        y = height / 2 - 8;
                    }
                    case TOP_LEFT -> {
                        y = 1;
                    }
                    case TOP_RIGHT -> {
                        x = width - 16;
                        y = 1;
                    }
                    case CUSTOM -> {
                        x = YACLconfig.getCoords_x();
                        y = YACLconfig.getCoords_y();
                    }
                }

                switch (YACLconfig.getSize()) {
                    case EIGHT -> {
                        size_x = 8;
                        size_y = 8;
                    }
                    case SIXTEEN -> {
                        size_x = 16;
                        size_y = 16;
                    }
                    case THIRTY_TWO -> {
                        size_x = 32;
                        size_y = 32;
                    }
                    case FORTY_EIGHT -> {
                        size_x = 48;
                        size_y = 48;
                    }
                    case SIXTY_FOUR -> {
                        size_x = 64;
                        size_y = 64;
                    }
                }

                drawContext.drawTexture(TEXTURE, x, y, 0,0,size_x,size_y,size_x,size_y);
            }
        }
    }
}
