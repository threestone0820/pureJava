package three.stone.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Push {

    @JsonProperty("type")
    private String type;
    @JsonProperty("name")
    private String name;
    @JsonProperty("push_type")
    private PushType pushType;

    @JsonCreator
    private Push() {
    }

    public static enum PushType {
        @JsonProperty("gcm")
        GCM,
        @JsonProperty("apns")
        APNS
    }

    public Push(String type, String name, PushType pushType) {
        this.type = type;
        this.name = name;
        this.pushType = pushType;
    }
}
