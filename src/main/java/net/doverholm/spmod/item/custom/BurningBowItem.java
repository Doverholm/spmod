package net.doverholm.spmod.item.custom;

import net.doverholm.spmod.component.ModDataComponentTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
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
import net.minecraft.world.World;

import java.util.List;

public class BurningBowItem extends BowItem {

    public BurningBowItem(Settings settings) {
        super(settings);
    }

    @Override
    protected ProjectileEntity createArrowEntity(World world, LivingEntity shooter, ItemStack weaponStack, ItemStack projectileStack, boolean critical) {
        ArrowItem arrowItem2 = projectileStack.getItem() instanceof ArrowItem arrowItem ? arrowItem : (ArrowItem)Items.ARROW;
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
            if(projectiles.isEmpty()) return;

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

            int stacks = stack.getOrDefault(ModDataComponentTypes.BURNING_STACKS, 0);
            stack.set(ModDataComponentTypes.BURNING_STACKS, stacks + 1);
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {

        int stacks = stack.getOrDefault(ModDataComponentTypes.BURNING_STACKS, 0);

        tooltip.add(Text.literal("stack: " + stacks));
    }
}
