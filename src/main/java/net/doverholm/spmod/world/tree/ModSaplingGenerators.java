package net.doverholm.spmod.world.tree;

import net.doverholm.spmod.SPMod;
import net.doverholm.spmod.world.ModConfiguredFeatures;
import net.minecraft.block.SaplingGenerator;

import java.util.Optional;

public class ModSaplingGenerators {
    public static final SaplingGenerator BLOODWOOD = new SaplingGenerator(SPMod.MOD_ID + ":bloodwood",
            Optional.empty(), Optional.of(ModConfiguredFeatures.BLOODWOOD_KEY), Optional.empty());
}
