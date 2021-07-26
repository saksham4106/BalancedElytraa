package io.github.saksham4106.balanced_elytra.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(ElytraItem.class)
public class ElytraItemMixin {

    @Inject(at = @At("HEAD"), method = "elytraFlightTick(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/LivingEntity;I)Z", remap = false)
    private void elytraFlightTick(ItemStack stack, LivingEntity entity, int flightTicks, CallbackInfoReturnable<Boolean> cir){
        Random rand = new Random();
        BlockPos pos = entity.getPosition();
        World world = entity.world;
        int strength = 0;
        if(world.isRainingAt(pos)){
            strength = 15;
            if(world.isThundering()){
                strength = 7;
                if(rand.nextInt(7000) == 0){
                    pos = pos.add(rand.nextInt(10), rand.nextInt(10), rand.nextInt(10));
                    LightningBoltEntity lightningBoltEntity = new LightningBoltEntity(EntityType.LIGHTNING_BOLT, world);
                    lightningBoltEntity.moveForced(Vector3d.copyCenteredHorizontally(pos));
                    world.addEntity(lightningBoltEntity);
                }
            }
        }
        if (strength!= 0 && !world.isRemote && (flightTicks + 1) % strength == 0) {
            stack.damageItem(1, entity, e -> e.sendBreakAnimation(net.minecraft.inventory.EquipmentSlotType.CHEST));
        }
    }

}
