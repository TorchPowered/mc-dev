package net.minecraft.world.gen.layer;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenJungle;
import net.minecraft.world.biome.BiomeGenMesa;

public class GenLayerShore extends GenLayer
{
    public GenLayerShore(long p_i2130_1_, GenLayer p_i2130_3_)
    {
        super(p_i2130_1_);
        this.parent = p_i2130_3_;
    }

    /**
     * Returns a list of integer values generated by this layer. These may be interpreted as temperatures, rainfall
     * amounts, or biomeList[] indices based on the particular GenLayer subclass.
     */
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int[] aint = this.parent.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
        int[] aint1 = IntCache.getIntCache(areaWidth * areaHeight);

        for (int i = 0; i < areaHeight; ++i)
        {
            for (int j = 0; j < areaWidth; ++j)
            {
                this.initChunkSeed((long)(j + areaX), (long)(i + areaY));
                int k = aint[j + 1 + (i + 1) * (areaWidth + 2)];
                BiomeGenBase biomegenbase = BiomeGenBase.getBiome(k);

                if (k == BiomeGenBase.getIdForBiome(Biomes.mushroomIsland))
                {
                    int j2 = aint[j + 1 + (i + 1 - 1) * (areaWidth + 2)];
                    int i3 = aint[j + 1 + 1 + (i + 1) * (areaWidth + 2)];
                    int l3 = aint[j + 1 - 1 + (i + 1) * (areaWidth + 2)];
                    int k4 = aint[j + 1 + (i + 1 + 1) * (areaWidth + 2)];

                    if (j2 != BiomeGenBase.getIdForBiome(Biomes.ocean) && i3 != BiomeGenBase.getIdForBiome(Biomes.ocean) && l3 != BiomeGenBase.getIdForBiome(Biomes.ocean) && k4 != BiomeGenBase.getIdForBiome(Biomes.ocean))
                    {
                        aint1[j + i * areaWidth] = k;
                    }
                    else
                    {
                        aint1[j + i * areaWidth] = BiomeGenBase.getIdForBiome(Biomes.mushroomIslandShore);
                    }
                }
                else if (biomegenbase != null && biomegenbase.getBiomeClass() == BiomeGenJungle.class)
                {
                    int i2 = aint[j + 1 + (i + 1 - 1) * (areaWidth + 2)];
                    int l2 = aint[j + 1 + 1 + (i + 1) * (areaWidth + 2)];
                    int k3 = aint[j + 1 - 1 + (i + 1) * (areaWidth + 2)];
                    int j4 = aint[j + 1 + (i + 1 + 1) * (areaWidth + 2)];

                    if (this.func_151631_c(i2) && this.func_151631_c(l2) && this.func_151631_c(k3) && this.func_151631_c(j4))
                    {
                        if (!isBiomeOceanic(i2) && !isBiomeOceanic(l2) && !isBiomeOceanic(k3) && !isBiomeOceanic(j4))
                        {
                            aint1[j + i * areaWidth] = k;
                        }
                        else
                        {
                            aint1[j + i * areaWidth] = BiomeGenBase.getIdForBiome(Biomes.beach);
                        }
                    }
                    else
                    {
                        aint1[j + i * areaWidth] = BiomeGenBase.getIdForBiome(Biomes.jungleEdge);
                    }
                }
                else if (k != BiomeGenBase.getIdForBiome(Biomes.extremeHills) && k != BiomeGenBase.getIdForBiome(Biomes.extremeHillsPlus) && k != BiomeGenBase.getIdForBiome(Biomes.extremeHillsEdge))
                {
                    if (biomegenbase != null && biomegenbase.isSnowyBiome())
                    {
                        this.func_151632_a(aint, aint1, j, i, areaWidth, k, BiomeGenBase.getIdForBiome(Biomes.coldBeach));
                    }
                    else if (k != BiomeGenBase.getIdForBiome(Biomes.mesa) && k != BiomeGenBase.getIdForBiome(Biomes.mesaPlateau_F))
                    {
                        if (k != BiomeGenBase.getIdForBiome(Biomes.ocean) && k != BiomeGenBase.getIdForBiome(Biomes.deepOcean) && k != BiomeGenBase.getIdForBiome(Biomes.river) && k != BiomeGenBase.getIdForBiome(Biomes.swampland))
                        {
                            int l1 = aint[j + 1 + (i + 1 - 1) * (areaWidth + 2)];
                            int k2 = aint[j + 1 + 1 + (i + 1) * (areaWidth + 2)];
                            int j3 = aint[j + 1 - 1 + (i + 1) * (areaWidth + 2)];
                            int i4 = aint[j + 1 + (i + 1 + 1) * (areaWidth + 2)];

                            if (!isBiomeOceanic(l1) && !isBiomeOceanic(k2) && !isBiomeOceanic(j3) && !isBiomeOceanic(i4))
                            {
                                aint1[j + i * areaWidth] = k;
                            }
                            else
                            {
                                aint1[j + i * areaWidth] = BiomeGenBase.getIdForBiome(Biomes.beach);
                            }
                        }
                        else
                        {
                            aint1[j + i * areaWidth] = k;
                        }
                    }
                    else
                    {
                        int l = aint[j + 1 + (i + 1 - 1) * (areaWidth + 2)];
                        int i1 = aint[j + 1 + 1 + (i + 1) * (areaWidth + 2)];
                        int j1 = aint[j + 1 - 1 + (i + 1) * (areaWidth + 2)];
                        int k1 = aint[j + 1 + (i + 1 + 1) * (areaWidth + 2)];

                        if (!isBiomeOceanic(l) && !isBiomeOceanic(i1) && !isBiomeOceanic(j1) && !isBiomeOceanic(k1))
                        {
                            if (this.func_151633_d(l) && this.func_151633_d(i1) && this.func_151633_d(j1) && this.func_151633_d(k1))
                            {
                                aint1[j + i * areaWidth] = k;
                            }
                            else
                            {
                                aint1[j + i * areaWidth] = BiomeGenBase.getIdForBiome(Biomes.desert);
                            }
                        }
                        else
                        {
                            aint1[j + i * areaWidth] = k;
                        }
                    }
                }
                else
                {
                    this.func_151632_a(aint, aint1, j, i, areaWidth, k, BiomeGenBase.getIdForBiome(Biomes.stoneBeach));
                }
            }
        }

        return aint1;
    }

    private void func_151632_a(int[] p_151632_1_, int[] p_151632_2_, int p_151632_3_, int p_151632_4_, int p_151632_5_, int p_151632_6_, int p_151632_7_)
    {
        if (isBiomeOceanic(p_151632_6_))
        {
            p_151632_2_[p_151632_3_ + p_151632_4_ * p_151632_5_] = p_151632_6_;
        }
        else
        {
            int i = p_151632_1_[p_151632_3_ + 1 + (p_151632_4_ + 1 - 1) * (p_151632_5_ + 2)];
            int j = p_151632_1_[p_151632_3_ + 1 + 1 + (p_151632_4_ + 1) * (p_151632_5_ + 2)];
            int k = p_151632_1_[p_151632_3_ + 1 - 1 + (p_151632_4_ + 1) * (p_151632_5_ + 2)];
            int l = p_151632_1_[p_151632_3_ + 1 + (p_151632_4_ + 1 + 1) * (p_151632_5_ + 2)];

            if (!isBiomeOceanic(i) && !isBiomeOceanic(j) && !isBiomeOceanic(k) && !isBiomeOceanic(l))
            {
                p_151632_2_[p_151632_3_ + p_151632_4_ * p_151632_5_] = p_151632_6_;
            }
            else
            {
                p_151632_2_[p_151632_3_ + p_151632_4_ * p_151632_5_] = p_151632_7_;
            }
        }
    }

    private boolean func_151631_c(int p_151631_1_)
    {
        return BiomeGenBase.getBiome(p_151631_1_) != null && BiomeGenBase.getBiome(p_151631_1_).getBiomeClass() == BiomeGenJungle.class ? true : p_151631_1_ == BiomeGenBase.getIdForBiome(Biomes.jungleEdge) || p_151631_1_ == BiomeGenBase.getIdForBiome(Biomes.jungle) || p_151631_1_ == BiomeGenBase.getIdForBiome(Biomes.jungleHills) || p_151631_1_ == BiomeGenBase.getIdForBiome(Biomes.forest) || p_151631_1_ == BiomeGenBase.getIdForBiome(Biomes.taiga) || isBiomeOceanic(p_151631_1_);
    }

    private boolean func_151633_d(int p_151633_1_)
    {
        return BiomeGenBase.getBiome(p_151633_1_) instanceof BiomeGenMesa;
    }
}
