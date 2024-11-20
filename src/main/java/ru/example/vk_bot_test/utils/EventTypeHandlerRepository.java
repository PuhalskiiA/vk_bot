package ru.example.vk_bot_test.utils;

import org.springframework.stereotype.Component;
import ru.example.vk_bot_test.models.objects_types.VKSideRequestObject;
import ru.example.vk_bot_test.services.handlers.Handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс, определяющий набор обработчиков (Handler'ов)
 */
@Component
public final class EventTypeHandlerRepository {
    private final Map<Class<? extends VKSideRequestObject>, Handler> handlers = new HashMap<>();

    public EventTypeHandlerRepository(List<Handler> handlers) {
        handlers.forEach(eventTypeHandler -> {
            if (eventTypeHandler.getClass().isAnnotationPresent(EventTypeHandler.class)) {
                Class<? extends VKSideRequestObject> objectTypeClass = eventTypeHandler.getClass()
                        .getAnnotation(EventTypeHandler.class).value();

                this.handlers.put(objectTypeClass, eventTypeHandler);
            }
        });
    }

    /**
     * Метод для получения нужного handler'a, на основе класса объекта, полученного при десериализации
     *
     * @param sideRequestObjectClass - класс объекта
     * @return Соответствующий классу handler
     */
    public Handler getHandler(Class<? extends VKSideRequestObject> sideRequestObjectClass) {
        return handlers.get(sideRequestObjectClass);
    }
}
