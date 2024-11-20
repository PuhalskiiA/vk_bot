package ru.example.vk_bot_test.models.objects_types.message_event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EventTypeMessage {
    private Integer id;

    @JsonProperty("peer_id")
    private Integer peerId;

    @JsonProperty("from_id")
    private Integer fromId;

    private String text;
}
