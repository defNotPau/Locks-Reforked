package me.pau.mod.locks.mixin;

import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.culling.Frustum;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import javax.annotation.Nullable;

@Mixin(LevelRenderer.class)
public abstract class LevelRendererMixin {
    @Shadow @Nullable private Frustum capturedFrustum;

    @Shadow public abstract void captureFrustum();

    @Unique
    public Frustum getFrustum() {
        captureFrustum();
        return capturedFrustum;
    }
}
