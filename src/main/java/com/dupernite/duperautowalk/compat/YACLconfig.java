package com.dupernite.duperautowalk.compat;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import dev.isxander.yacl3.gui.controllers.cycling.EnumController;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.dupernite.duperautowalk.DuperAutoWalk.MOD_ID;
import static dev.isxander.yacl3.platform.YACLPlatform.getConfigDir;

public final class YACLconfig {
    public static final ConfigClassHandler<YACLconfig> GSON = ConfigClassHandler.createBuilder(YACLconfig.class)
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(getConfigDir().resolve("duperautowalk.json"))
                    .setJson5(true)
                    .build())
            .build();

    public enum positionEnum {
        TOP_LEFT,
        TOP_RIGHT,
        MIDDLE_LEFT,
        MIDDLE_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT
    }

    public enum feedbackEnum {
        NONE,
        HUD,
        CHAT
    }

    @SerialEntry
    public static positionEnum position = positionEnum.TOP_LEFT;

    @SerialEntry
    public static feedbackEnum feedback = feedbackEnum.HUD;

    public static Screen createScreen(Screen parent) {
        return YetAnotherConfigLib.create(YACLconfig.GSON, ((defaults, config, builder) -> {
            var defaultCategoryBuilder = ConfigCategory.createBuilder()
                    .name(Text.translatable("category.duperautowalk.general"));

            var feedback = createOption(
                    "option.duperautowalk.feedback",
                    "option.duperautowalk.feedback.description",
                    "feedback",
                    YACLconfig.feedback,
                    () -> YACLconfig.feedback,
                    val -> YACLconfig.feedback = val,
                    enumOption -> new EnumController<>(enumOption, v -> Text.translatable(MOD_ID + ".feedback." + v.toString().toLowerCase()), feedbackEnum.values())
            );

            var position = createOption(
                    "option.duperautowalk.position",
                    "option.duperautowalk.enabled.position",
                    "position",
                    YACLconfig.position,
                    () -> YACLconfig.position,
                    val -> YACLconfig.position = val,
                    enumOption -> new EnumController<>(enumOption, v -> Text.translatable(MOD_ID + ".position." + v.toString().toLowerCase()), positionEnum.values())
            );

            return builder
                    .title(Text.translatable("config.duperautowalk.title"))
                    .category(
                            defaultCategoryBuilder
                                    .options(List.of(feedback, position))
                                    .build()
                    );
        })).generateScreen(parent);
    }

    private static <T> Option<T> createOption(
            String name,
            String description,
            String type,
            T defaultValue,
            Supplier<T> currentValue,
            Consumer<T> newValue,
            Function<Option<T>, Controller<T>> customController
    ) {
        return Option.<T>createBuilder()
                .name(Text.translatable(name))
                .description(
                        OptionDescription.createBuilder()
                                .webpImage(screenshot(type))
                                .text(Text.translatable(description))
                                .build()
                )
                .binding(defaultValue, currentValue, newValue)
                .customController(customController)
                .build();
    }

    public static feedbackEnum getFeedback(){
        return feedback;
    }

    public static positionEnum getPosition(){
        return position;
    }
    public static Identifier screenshot(String type) {
            if (Objects.equals(type, "position")) {
                return Identifier.of(MOD_ID, "textures/screenshots/position/" + position.toString().toLowerCase() + ".webp");
            } else if (Objects.equals(type, "feedback")) {
                return Identifier.of(MOD_ID, "textures/screenshots/feedback/" + feedback.toString().toLowerCase() + ".webp");
            }
        return null;
    }
}