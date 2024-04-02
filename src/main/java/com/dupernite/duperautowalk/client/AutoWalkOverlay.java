package com.dupernite.duperautowalk.client;

import com.dupernite.duperautowalk.DuperAutoWalk;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.util.Identifier;
import com.dupernite.duperautowalk.event.keyInputHandler;

public class AutoWalkOverlay implements HudRenderCallback {
    private static final Identifier TEXTURE = new Identifier(DuperAutoWalk.MOD_ID,
            "textures/gui/autowalk.png");

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        int x = 0;
        int y = 0;
        MinecraftClient client = MinecraftClient.getInstance();

        if(client != null){
            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();

            RenderSystem.setShader(GameRenderer::getPositionTexProgram);
            RenderSystem.setShaderColor(1, 1, 1, 1);

            RenderSystem.setShaderTexture(0, TEXTURE);
            if(keyInputHandler.isAutoWalking){
                drawContext.drawTexture(TEXTURE, x, y, 0,0,16,16,16,16);
            }
        }
    }
}
