package com.autovw.advancednetherite.content.armor;

import com.autovw.advancednetherite.config.Config;
import com.autovw.advancednetherite.content.ModTooltips;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Author: Autovw
 */
public class EnderMaskArmorItem extends ArmorItem {
    public EnderMaskArmorItem(ArmorMaterial material, EquipmentSlot slot, Properties builder) {
        super(material, slot, builder);
    }

    @Override
    public boolean isEnderMask(ItemStack stack, Player player, EnderMan enderman) {
        return Config.ArmorConfig.emeraldEndermanPassiveArmor.get();
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        // The tooltips are added here, they are removed later if a config option is set to false.
        tooltip.add(ModTooltips.endermaskTooltip);

        // If showTooltips is set to false in the config it removes the tooltips client-side.
        if (!Config.Client.showTooltips.get()) {
            tooltip.remove(ModTooltips.endermaskTooltip);
        } else {
            // If Netherite/Emerald armor is not Endermask Armor (set to false in the config) the game removes the tooltip client-side.
            if (!Config.ArmorConfig.emeraldEndermanPassiveArmor.get()) {
                tooltip.remove(ModTooltips.endermaskTooltip);
            }
        }
    }
}