package ru.example.vk_bot_test.models.objects_types.message_event;

import ru.example.vk_bot_test.models.objects_types.VKSideRequestObject;

public abstract class VKSideRequestMessageObject extends VKSideRequestObject {
    public abstract String getMessageText();
    public abstract Integer getFromId();
}
