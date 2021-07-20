package three.stone.effective.system_property;

public class SystemPropertyTest {
    public static void main(String[] args) {
        System.out.println(Boolean.getBoolean("boolean.value"));
        System.out.println(Integer.getInteger("int.value"));
        System.out.println(Long.getLong("long.value"));
    }
}
