package ru.example.vk_bot_test.services.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import ru.example.vk_bot_test.exceptions.EventException;
import ru.example.vk_bot_test.models.VKRequest;
import ru.example.vk_bot_test.models.objects_types.message_event.VKSideRequestMessageObject;
import ru.example.vk_bot_test.models.objects_types.message_event.VKSideRequestObjectMessageEvent;
import ru.example.vk_bot_test.utils.EventTypeHandler;

import java.net.URI;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Класс - handler, описывающий работу с новыми сообщениями
 */
@Service
@RequiredArgsConstructor
@EventTypeHandler(VKSideRequestObjectMessageEvent.class)
public final class MessageEventTypeHandler extends Handler {
    private final RestTemplate restTemplate;

    /**
     * Метод, выполняющий запрос к VK API, для ответа пользователю на его сообщение
     *
     * @param request Тело запроса, полученного от внешнего API
     * @return Возвращаемое значение для Callback API. Возвращается строка "ок" при удачной обработки запроса
     * @throws EventException - в случае, если при выполнении запроса произошла ошибка
     */
    @Override
    public String getResponse(VKRequest request) {
        HttpEntity<String> entity = new HttpEntity<>(composeHeaders());

        ResponseEntity<String> response = restTemplate
                .exchange(composeURI(request), HttpMethod.GET, entity, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) throw new EventException("Couldn't send the message back");

        return "ok";
    }

    /**
     * Метод, собирающий заголовок запроса
     *
     * @return Собранный заголовок
     */
    private static HttpHeaders composeHeaders() {
        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization", "Bearer " + Handler.getToken());

        return headers;
    }

    /**
     * Метод, собирающий URI
     *
     * @return Собранный URI
     */
    private static URI composeURI(VKRequest request) {
        VKSideRequestMessageObject object = (VKSideRequestMessageObject) request.getObject();

        UriComponents builder = UriComponentsBuilder.newInstance()
                .scheme("https").host("api.vk.com").path("/method/messages.send")
                .queryParam("user_id", object.getFromId())
                .queryParam("random_id", ThreadLocalRandom.current().nextInt())
                .queryParam("message", composeMessageFromInputObject(object))
                .queryParam("v", request.getV())
                .build();

        return builder.toUri();
    }

    /**
     * Метод, собирающий ответ пользователю на его сообщение
     *
     * @return Собранный ответ
     */
    private static String composeMessageFromInputObject(VKSideRequestMessageObject object) {
        String message = object.getMessageText().replace(" ", "+");

        return "You+said+" + message;
    }
}
