package com.autovw.advancednetherite.common.item;

import com.autovw.advancednetherite.Reference;
import com.autovw.advancednetherite.api.annotation.Internal;
import com.autovw.advancednetherite.config.Config;
import com.autovw.advancednetherite.content.ModTooltips;
import com.autovw.advancednetherite.core.ModToolTiers;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

/**
 * Author: Autovw
 */
public class AdvancedSwordItem extends SwordItem {
    private final Tier tier;

    public AdvancedSwordItem(Tier tier, int attackDamage, float attackSpeed, Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
        this.tier = tier;
    }

    /**
     * Netherite items do not burn by default.
     * {@link Override} if you want to disable this feature.
     *
     * @return If true, item does not burn when on fire
     */
    @Override
    public boolean isFireResistant() {
        return true;
    }

    /**
     * {@link Override} this method if you want to add your own custom tooltips.
     *
     * @param stack     The item stack
     * @param level     The world/level
     * @param tooltips  List of tooltips
     * @param flag      Used to determine if a tooltip is only visible when debug mode (F3 + H) is enabled
     */
    public void addTooltips(ItemStack stack, Level level, List<Component> tooltips, TooltipFlag flag) {
    }

    /**
     * Use {@link AdvancedSwordItem#addTooltips(ItemStack, Level, List, TooltipFlag)} instead
     */
    @Deprecated(forRemoval = true, since = "1.11.0") // TODO remove in MC 1.19
    public void addTooltips(ItemStack stack, List<Component> tooltip, TooltipFlag flag) {
    }

    /**
     * {@link Override} this method if you want to give your item a custom durability bar color.
     * Feature is disabled by default, can be enabled in Advanced Netherite's Client config.
     *
     * @param stack The item stack
     * @return The custom durability bar color
     */
    @Nullable
    public ChatFormatting customDurabilityBarColor(ItemStack stack) {
        return null;
    }

    /* ================ INTERNAL, use alternatives linked in javadoc ================ */

    /**
     * Don't override this method, use {@link AdvancedSwordItem#addTooltips(ItemStack, List, TooltipFlag)} if you want to add your own custom tooltips.
     */
    @Internal
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        if (Config.Client.showTooltips.get()) {
            if (stack.getItem().getRegistryName().getNamespace().equals(Reference.MOD_ID) && Config.AdditionalDropsConfig.enableAdditionalMobDrops.get()) {
                if (Screen.hasShiftDown()) {
                    if (tier == ModToolTiers.NETHERITE_IRON) tooltip.add(ModTooltips.phantomMobDropTooltip);
                    if (tier == ModToolTiers.NETHERITE_GOLD || tier == ModToolTiers.NETHERITE_DIAMOND) {
                        tooltip.add(ModTooltips.zombifiedPiglinMobDropTooltip);
                        tooltip.add(ModTooltips.piglinMobDropTooltip);
                    }
                    if (tier == ModToolTiers.NETHERITE_EMERALD || tier == ModToolTiers.NETHERITE_DIAMOND) {
                        tooltip.add(ModTooltips.endermanMobDropTooltip);
                    }
                } else {
                    tooltip.add(ModTooltips.SHIFT_KEY_TOOLTIP);
                }
            }

            addTooltips(stack, world, tooltip, flag); // Add tooltips from add-ons
            addTooltips(stack, tooltip, flag); // TODO remove in MC 1.19
        }
    }

    /**
     * Don't override this method, use {@link AdvancedSwordItem#customDurabilityBarColor(ItemStack)} to change the custom durability bar color.
     */
    @Internal
    @Override
    public int getBarColor(ItemStack stack) {
        if (Config.Client.matchingDurabilityBars.get()) {
            if (tier == ModToolTiers.NETHERITE_IRON) {
                return Objects.requireNonNull(ChatFormatting.GRAY.getColor());
            }
            if (tier == ModToolTiers.NETHERITE_GOLD) {
                return Objects.requireNonNull(ChatFormatting.GOLD.getColor());
            }
            if (tier == ModToolTiers.NETHERITE_EMERALD) {
                return Objects.requireNonNull(ChatFormatting.DARK_GREEN.getColor());
            }
            if (tier == ModToolTiers.NETHERITE_DIAMOND) {
                return Objects.requireNonNull(ChatFormatting.AQUA.getColor());
            }
        }

        return customDurabilityBarColor(stack) != null && Config.Client.matchingDurabilityBars.get() ? Objects.requireNonNull(customDurabilityBarColor(stack).getColor()) : super.getBarColor(stack);
    }
}
