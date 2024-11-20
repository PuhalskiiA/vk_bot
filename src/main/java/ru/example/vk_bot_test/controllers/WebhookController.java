package ru.example.vk_bot_test.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.example.vk_bot_test.models.VKRequest;
import ru.example.vk_bot_test.services.VKBotService;

/**
 * Класс реализует контроллер для работы с Callback API
 */
@RestController
@RequiredArgsConstructor
public final class WebhookController {
   private final VKBotService vkBotService;

    /**
     * Метод, обрабатывающий запросы от Callback API
     *
     * @param request тело запроса, полученное от Callback API
     * @return Ответ на полученный запрос
     */
    @PostMapping(value = "/webhook")
    public ResponseEntity<String> receiveWebhook(@RequestBody VKRequest request) {
        return new ResponseEntity<>(vkBotService.getResponse(request), HttpStatus.OK);
    }
}
