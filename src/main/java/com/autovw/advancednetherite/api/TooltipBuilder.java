package com.autovw.advancednetherite.api;

import com.autovw.advancednetherite.api.annotation.Internal;
import com.mojang.logging.LogUtils;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

/**
 * Author: Autovw
 * <br/>
 * A flexible builder for translatable tooltips.
 * Replacement of {@link Tooltips} allowing for more control over the created tooltip.
 *
 * @since 1.12.0
 */
public class TooltipBuilder {
    private static final Logger LOGGER = LogUtils.getLogger();

    private TooltipBuilder() {
    }

    /**
     * Used to create a translatable tooltip.
     * Not setting the mod id will result in the tooltip being registered under the default <i>minecraft</i> namespace.
     * If the tooltip name is empty, {@link #build(ResourceLocation, Object...)} will throw a {@link IllegalStateException}.
     *
     * Use {@link #create(ResourceLocation, Object...)} if the tooltip should contain other sub-elements.
     *
     * @param key name of the tooltip
     * @return a MutableComponent
     */
    public static MutableComponent create(ResourceLocation key) {
        return build(key, (Object) null);
    }

    /**
     * Used to create a translatable tooltip.
     * Not setting the mod id will result in the tooltip being registered under the default <i>minecraft</i> namespace.
     * If the tooltip name is empty, {@link #build(ResourceLocation, Object...)} will throw a {@link IllegalStateException}.
     *
     * @param key name of the tooltip
     * @param args sub-elements
     * @return a MutableComponent
     */
    public static MutableComponent create(ResourceLocation key, Object... args) {
        return build(key, args);
    }

    /**
     * Builder used for internal purposes only.
     */
    @Internal
    private static MutableComponent build(ResourceLocation key, @Nullable Object... args) {
        String path = key.getPath();
        String content = "tooltip." + key.getNamespace() + "." + path;
        if (!path.equals("")) {
            if (args != null) {
                return new TranslatableComponent(content, args);
            } else {
                return new TranslatableComponent(content);
            }
        } else {
            LOGGER.error("Cannot build tooltip with empty name");
            throw new IllegalStateException("Tried to build tooltip with incomplete name!");
        }
    }
}
