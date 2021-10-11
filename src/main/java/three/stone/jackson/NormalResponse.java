package three.stone.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NormalResponse {
    @JsonProperty
    private long errorCode;
    @JsonProperty
    private String message;
    @JsonProperty
    private Boolean success;
    @JsonProperty
    private List<String> ids;
    @JsonProperty
    private Set<String> names;
    @JsonProperty
    private Map<String, Object> properties;
    @JsonProperty
    private List<MessageType> messageTypes;
    @JsonProperty
    private Push push;
    @JsonProperty
    private List<Push> pushes;

    @JsonCreator
    public NormalResponse() {
    }

    public NormalResponse(long errorCode, String message,
                          Boolean success, List<String> ids,
                          Set<String> names, Map<String, Object> properties,
                          List<MessageType> messageTypes, Push push,
                          List<Push> pushes) {
        this.errorCode = errorCode;
        this.message = message;
        this.success = success;
        this.ids = ids;
        this.names = names;
        this.properties = properties;
        this.messageTypes = messageTypes;
        this.push = push;
        this.pushes = pushes;
    }

    public static enum MessageType {
        @JsonProperty("im")
        IM,
        @JsonProperty("big_group")
        BIG_GROUP
    }
}
