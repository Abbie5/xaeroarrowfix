package cc.abbie.xaeroarrowfix.mixin;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import xaero.common.minimap.MinimapProcessor;
import xaero.common.minimap.radar.MinimapRadar;
import xaero.common.minimap.render.MinimapFBORenderer;
import xaero.common.minimap.render.MinimapRenderer;
import xaero.common.settings.ModSettings;

@Mixin(MinimapRenderer.class)
public abstract class MinimapRendererMixin {
    @WrapWithCondition(
            remap = false,
            method = "renderMinimap",
            at = @At(value = "INVOKE", target = "Lxaero/common/minimap/render/MinimapFBORenderer;renderMainEntityDot(Lnet/minecraft/client/gui/GuiGraphics;Lxaero/common/minimap/MinimapProcessor;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/entity/Entity;DDDDFLxaero/common/minimap/radar/MinimapRadar;ZIZZZDLxaero/common/settings/ModSettings;Lnet/minecraft/client/renderer/MultiBufferSource$BufferSource;F)V")
    )
    private boolean xaeroarrowfix$fixMainEntityDot(MinimapFBORenderer instance, GuiGraphics guiGraphics, MinimapProcessor minimap, Player p, Entity renderEntity, double ps, double pc, double playerX, double playerZ, float partial, MinimapRadar minimapRadar, boolean lockedNorth, int style, boolean smooth, boolean debug, boolean cave, double dotNameScale, ModSettings settings, MultiBufferSource.BufferSource renderTypeBuffers, float minimapScale) {
        return !(settings.mainEntityAs == 2 || lockedNorth);
    }
}
