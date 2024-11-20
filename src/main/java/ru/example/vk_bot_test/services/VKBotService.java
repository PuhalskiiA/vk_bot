package ru.example.vk_bot_test.services;

import ru.example.vk_bot_test.models.VKRequest;

/**
 * Интерфейс представляет метод для получения ответа на запрос
 *
 * В случае Callback API, предоставляемого VK, мы должны возвращать строку "ок" и статус 200,
 * при удачной обработки запроса
 */
public interface VKBotService {
    String getResponse(VKRequest request);
}
