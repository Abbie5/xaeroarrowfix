package cc.abbie.xaeroarrowfix.mixin;

import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import xaero.common.AXaeroMinimap;
import xaero.common.XaeroMinimapSession;
import xaero.common.graphics.CustomVertexConsumers;
import xaero.common.minimap.MinimapProcessor;
import xaero.common.minimap.render.MinimapRenderer;
import xaero.common.settings.ModSettings;

@Mixin(MinimapRenderer.class)
public abstract class MinimapRendererMixin {
    @Shadow(remap = false)
    protected AXaeroMinimap modMain;

    @ModifyVariable(
            remap = false,
            method = "renderMinimap",
            name = "crosshairDisplayed",
            at = @At(value = "LOAD", ordinal = 1)
    )
    private boolean xaeroarrowfix$fixMainEntityDot(boolean original, XaeroMinimapSession minimapSession, MatrixStack matrixStack, MinimapProcessor minimap, int x, int y, int width, int height, double scale, int size, float partial, CustomVertexConsumers cvc) {
        ModSettings settings = modMain.getSettings();
        int mapSize = minimapSession.getMinimapProcessor().getMinimapSize();
        int shape = settings.minimapShape;

        return original || settings.mainEntityAs == 2 || settings.getLockNorth(mapSize / 2, shape);
    }
}
