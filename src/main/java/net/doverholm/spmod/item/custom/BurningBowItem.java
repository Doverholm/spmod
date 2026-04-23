package net.doverholm.spmod.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BurningBowItem extends BowItem {
    private static final int FIREBALL_COOLDOWN_TICKS = 400;
    private static final double FIREBALL_SPREAD_RADIANS = Math.toRadians(6.0D);
    private static final Map<UUID, Long> FIREBALL_COOLDOWNS = new HashMap<>();

    public BurningBowItem(Settings settings) {
        super(settings);
    }

    public static int getFireballCooldownTicks() {
        return FIREBALL_COOLDOWN_TICKS;
    }

    public static int getRemainingFireballCooldownTicks(World world, PlayerEntity player) {
        Long readyAtTick = FIREBALL_COOLDOWNS.get(player.getUuid());
        if (readyAtTick == null) {
            return 0;
        }

        long remaining = readyAtTick - world.getTime();
        if (remaining <= 0L) {
            FIREBALL_COOLDOWNS.remove(player.getUuid());
            return 0;
        }

        return (int) remaining;
    }

    public static void shootFireballVolley(ServerWorld world, PlayerEntity player, ItemStack bowStack) {
        if (getRemainingFireballCooldownTicks(world, player) > 0) {
            return;
        }

        Vec3d forward = player.getRotationVec(1.0F).normalize();
        Vec3d right = new Vec3d(-forward.z, 0.0D, forward.x);
        if (right.lengthSquared() < 1.0E-6D) {
            right = new Vec3d(4.0D, 0.0D, 0.0D);
        } else {
            right = right.normalize();
        }

        Vec3d baseSpawnPos = player.getEyePos().add(forward.multiply(1.1D));
        double[] sideOffsets = {-1.3D, 1.3D};

        for (int i = 0; i < sideOffsets.length; i++) {
            double yawOffset = i == 0 ? -FIREBALL_SPREAD_RADIANS : FIREBALL_SPREAD_RADIANS;
            Vec3d direction = forward.rotateY((float) yawOffset).normalize();
            Vec3d spawnPos = baseSpawnPos.add(right.multiply(sideOffsets[i]));

            FireballEntity fireball = new FireballEntity(world, player, direction, 3);
            fireball.refreshPositionAndAngles(spawnPos.x, spawnPos.y, spawnPos.z, player.getYaw(), player.getPitch());
            world.spawnEntity(fireball);
        }

        world.playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.ENTITY_GHAST_SHOOT,
                SoundCategory.PLAYERS,
                1.0F,
                1.0F
        );

        FIREBALL_COOLDOWNS.put(player.getUuid(), world.getTime() + FIREBALL_COOLDOWN_TICKS);
        EquipmentSlot slot = player.getMainHandStack() == bowStack ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
        bowStack.damage(1, player, slot);
        player.incrementStat(Stats.USED.getOrCreateStat(bowStack.getItem()));
    }

    @Override
    protected ProjectileEntity createArrowEntity(World world, LivingEntity shooter, ItemStack weaponStack, ItemStack projectileStack, boolean critical) {
        ArrowItem arrowItem2 = projectileStack.getItem() instanceof ArrowItem arrowItem ? arrowItem : (ArrowItem) Items.ARROW;
        PersistentProjectileEntity persistentProjectileEntity = arrowItem2.createArrow(world, projectileStack, shooter, weaponStack);
        if (critical) {
            persistentProjectileEntity.setCritical(true);
        }

        persistentProjectileEntity.setOnFireFor(500);

        return persistentProjectileEntity;
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (!(user instanceof PlayerEntity player)) return;

        ItemStack projectile = player.getProjectileType(stack);
        if (projectile.isEmpty()) return;

        int i = this.getMaxUseTime(stack, user) - remainingUseTicks;
        float f = getPullProgress(i);

        if (f < 0.1f) return;

        if (world instanceof ServerWorld serverWorld) {
            List<ItemStack> projectiles = load(stack, projectile, player);
            if (projectiles.isEmpty()) return;

            this.shootAll(
                    serverWorld,
                    player,
                    player.getActiveHand(),
                    stack,
                    projectiles,
                    f * 3.0F,
                    1.0F,
                    f == 1.0F,
                    null
            );

            serverWorld.playSound(
                    null,
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    SoundEvents.ENTITY_ARROW_SHOOT,
                    SoundCategory.PLAYERS,
                    1.0F,
                    1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F
            );

            player.incrementStat(Stats.USED.getOrCreateStat(this));
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.spmod.oath_of_the_burning_vein.arrow"));
        tooltip.add(Text.translatable("tooltip.spmod.oath_of_the_burning_vein.fireball"));
    }
}
