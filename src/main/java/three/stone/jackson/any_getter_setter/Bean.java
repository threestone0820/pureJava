package three.stone.jackson.any_getter_setter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import three.stone.jackson.MapEntity;

import java.util.List;
import java.util.Map;

class Bean extends MapEntity {
    public enum Type {
        @JsonProperty("abc")
        ABC,
        @JsonProperty("xyz")
        XYZ;
    }

    public static class AnotherBean extends MapEntity {
        @JsonProperty("another_bean_field")
        public String anotherBeanField;

        @JsonCreator
        public AnotherBean() {

        }
    }

    @JsonProperty("string_field")
    public String stringField;
    @JsonProperty("boolean_field")
    public Boolean booleanField;
    @JsonProperty("long_field")
    public Long longField;
    @JsonProperty("double_field")
    public Double doubleField;
    @JsonProperty("int_field")
    public Integer intField;
    @JsonProperty("list_field")
    public List<String> listField;
    @JsonProperty("map_field")
    public Map<String, Object> mapField;
    @JsonProperty("enum_field")
    public Type enumField;
    @JsonProperty("another_bean_field")
    public AnotherBean anotherBean;
//    @IgnoreDB
    @JsonProperty("ignore_field")
    public String ignoreField;

    @JsonCreator
    public Bean() {
    }


    public static void main(String[] args) throws JsonProcessingException {

    }
}
