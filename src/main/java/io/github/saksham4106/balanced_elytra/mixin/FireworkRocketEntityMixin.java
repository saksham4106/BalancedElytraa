package io.github.saksham4106.balanced_elytra.mixin;

import io.github.saksham4106.balanced_elytra.Config;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.util.math.vector.Vector3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FireworkRocketEntity.class)
public class FireworkRocketEntityMixin {
    @Shadow
    private LivingEntity boostedEntity;

    @Inject(at = @At("TAIL"), method = "tick()V")
    private void tick(CallbackInfo ci){
        if(Config.WEAK_FIREWORKS.get()){
            if (this.boostedEntity != null) {
                if (this.boostedEntity.isElytraFlying() && ((FireworkRocketEntity)(Object)this).world.isRaining()) {
                    Vector3d vector3d = this.boostedEntity.getLookVec();
                    Vector3d vector3d1 = this.boostedEntity.getMotion();
                    this.boostedEntity.setMotion(vector3d1.add(vector3d.x * 0.1D + (vector3d.x * 1.5D - vector3d1.x) * 0.5D, vector3d.y * 0.1D + (vector3d.y * 1.5D - vector3d1.y) * 0.5D, vector3d.z * 0.1D + (vector3d.z * 1.5D - vector3d1.z) * 0.5D).mul(0.8, 0.75, 0.8));
                }
                ((FireworkRocketEntity)(Object)this).setPosition(this.boostedEntity.getPosX(), this.boostedEntity.getPosY(), this.boostedEntity.getPosZ());
                ((FireworkRocketEntity)(Object)this).setMotion(this.boostedEntity.getMotion());
            }
        }
    }
}
