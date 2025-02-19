package com.autovw.advancednetherite.content.armor;

import com.autovw.advancednetherite.api.annotation.Internal;
import com.autovw.advancednetherite.common.item.AdvancedArmorItem;
import com.autovw.advancednetherite.config.Config;
import net.minecraft.ChatFormatting;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;

/**
 * Author: Autovw
 */
@Internal
public class NetheriteIronArmorItem extends AdvancedArmorItem {
    public NetheriteIronArmorItem(ArmorMaterial material, EquipmentSlot equipmentSlot, Properties properties) {
        super(material, equipmentSlot, properties);
    }

    @Override
    public boolean pacifiesEndermen() {
        return Config.ArmorConfig.ironEndermanPassiveArmor.get();
    }

    @Override
    public boolean pacifiesPiglins() {
        return Config.ArmorConfig.ironPiglinPassiveArmor.get();
    }

    @Override
    public boolean pacifiesPhantoms() {
        return Config.ArmorConfig.ironPhantomPassiveArmor.get();
    }

    @Override
    public ChatFormatting customDurabilityBarColor(ItemStack stack) {
        return ChatFormatting.GRAY;
    }
}
