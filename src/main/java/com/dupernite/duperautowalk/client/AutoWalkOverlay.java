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

            RenderSystem.setShader(GameRenderer::getPositionTexProgram);
            RenderSystem.setShaderColor(1, 1, 1, 1);

            RenderSystem.setShaderTexture(0, TEXTURE);
            if(keyInputHandler.isOn && YACLconfig.getFeedback() == YACLconfig.feedbackEnum.HUD){
                if(YACLconfig.getPosition() == YACLconfig.positionEnum.BOTTOM_LEFT){
                    x = 0;
                    y = height - 17;
                } else if(YACLconfig.getPosition() == YACLconfig.positionEnum.BOTTOM_RIGHT){
                    x = width - 16;
                    y = height - 17;
                } else if(YACLconfig.getPosition() == YACLconfig.positionEnum.MIDDLE_LEFT){
                    x = 0;
                    y = height / 2 - 8;
                } else if(YACLconfig.getPosition() == YACLconfig.positionEnum.MIDDLE_RIGHT){
                    x = width - 16;
                    y = height / 2 - 8;
                } else if(YACLconfig.getPosition() == YACLconfig.positionEnum.TOP_LEFT){
                    x = 0;
                    y = 1;
                } else if(YACLconfig.getPosition() == YACLconfig.positionEnum.TOP_RIGHT){
                    x = width - 16;
                    y = 1;
                }
                drawContext.drawTexture(TEXTURE, x, y, 0,0,16,16,16,16);
            }
        }
    }
}
