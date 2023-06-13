package cc.abbie.xaeroarrowfix.mixin;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xaero.common.minimap.MinimapProcessor;
import xaero.common.minimap.radar.MinimapRadar;
import xaero.common.minimap.render.MinimapFBORenderer;
import xaero.common.settings.ModSettings;

@Mixin(MinimapFBORenderer.class)
public abstract class MinimapFBORendererMixin {
    @Inject(
            remap = false,
            method = "renderMainEntityDot",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void xaeroarrowfix$fixMainEntityDot(GuiGraphics guiGraphics, MinimapProcessor minimap, Player p, Entity renderEntity, double ps, double pc, double playerX, double playerZ, float partial, MinimapRadar minimapRadar, boolean lockedNorth, int style, boolean smooth, boolean debug, boolean cave, double dotNameScale, ModSettings settings, MultiBufferSource.BufferSource renderTypeBuffers, float minimapScale, CallbackInfo ci) {
        if (settings.mainEntityAs == 2 || lockedNorth) ci.cancel();
    }
}
