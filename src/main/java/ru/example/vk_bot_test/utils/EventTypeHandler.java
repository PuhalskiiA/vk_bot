package ru.example.vk_bot_test.utils;

import ru.example.vk_bot_test.models.objects_types.VKSideRequestObject;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Аннотация для обозначения handler'ов
 */
@Retention(value= RetentionPolicy.RUNTIME)
public @interface EventTypeHandler {
    Class<? extends VKSideRequestObject> value();
}
