package ru.example.vk_bot_test.services.handlers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.example.vk_bot_test.models.VKRequest;

/**
 * Абстрактный класс, описывающий handler'ы
 */
@Component
public abstract class Handler {
    private static String TOKEN;

    public abstract String getResponse(VKRequest request);

    public static String getToken() {
        return TOKEN;
    }

    @Value("${vk.token}")
    public void setTOKEN(String TOKEN) {
        Handler.TOKEN = TOKEN;
    }
}
