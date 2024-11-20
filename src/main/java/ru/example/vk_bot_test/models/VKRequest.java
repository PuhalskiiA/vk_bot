package ru.example.vk_bot_test.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import ru.example.vk_bot_test.models.objects_types.VKSideRequestObject;
import ru.example.vk_bot_test.models.objects_types.message_event.VKSideRequestObjectMessageEvent;

@Data
public class VKRequest {
    private String type;

    @JsonProperty("group_id")
    private String groupId;

    @JsonProperty("event_id")
    private String eventId;

    private String v;

    @JsonTypeInfo(
            use = JsonTypeInfo.Id.NAME,
            include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
            property = "type"
    )
    @JsonSubTypes(value = {
            @JsonSubTypes.Type(value = VKSideRequestObjectMessageEvent.class, name = "message_new")
    })
    private VKSideRequestObject object;
}
