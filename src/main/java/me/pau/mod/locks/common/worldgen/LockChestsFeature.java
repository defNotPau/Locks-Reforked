package me.pau.mod.locks.common.worldgen;


import com.mojang.serialization.Codec;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class LockChestsFeature extends Feature<NoneFeatureConfiguration> {
	public LockChestsFeature(Codec<NoneFeatureConfiguration> codec)
	{
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> p_159749_) {
		return true;
	}
}