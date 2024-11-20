package ru.example.vk_bot_test.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.example.vk_bot_test.exceptions.EventException;
import ru.example.vk_bot_test.models.VKRequest;
import ru.example.vk_bot_test.services.handlers.Handler;
import ru.example.vk_bot_test.utils.EventTypeHandlerRepository;

/**
 * Сервис для работы c handler'ами
 **/
@Service
@RequiredArgsConstructor
public final class VKBotServiceImpl implements VKBotService {
    private final EventTypeHandlerRepository handlerRepository;

    /**
     * Метод, получающий ответ при вызове метода getResponse() на соответствующем handler'е
     *
     * @param request Тело запроса, полученного от внешнего API
     * @return Возвращаемое значение для Callback API. Возвращается строка "ок" при удачной обработки запроса
     * @throws EventException - в случае, если handler не был найден
     */
    @Override
    public String getResponse(VKRequest request) {
        Handler handler = handlerRepository.getHandler(request.getObject().getClass());

        if (handler == null) throw new EventException("This event is not supported or not found");

        return handler.getResponse(request);
    }
}
