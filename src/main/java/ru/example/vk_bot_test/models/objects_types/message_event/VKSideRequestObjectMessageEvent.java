package ru.example.vk_bot_test.models.objects_types.message_event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class VKSideRequestObjectMessageEvent extends VKSideRequestMessageObject {
    @JsonProperty("message")
    private EventTypeMessage eventTypeMessage;

    @Override
    public String getMessageText() {
        return eventTypeMessage.getText();
    }

    @Override
    public Integer getFromId() {
        return eventTypeMessage.getFromId();
    }
}
