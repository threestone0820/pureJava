package three.stone.jvm;

public class HashCode {

    public static void main(String[] args) {
        // HashCode类没有覆盖hashCode()方法，
        // 所以hashCode()和System.identityHashCode(hc)返回值相同
        HashCode hc = new HashCode();
        System.out.println("hc hashCode(): " + hc.hashCode());
        System.out.println("hc identityHashCode(): " + System.identityHashCode(hc));

        // User类覆盖了hashCode()方法，
        // 所以hashCode()和System.identityHashCode(user)返回值不同
        User user = new User("hello", 10);
        System.out.println("user hashCode(): " + user.hashCode());
        System.out.println("user identityHashCode(): " + System.identityHashCode(user));
        // 修改了name，hashCode()的返回值改变了，但System.identityHashCode(user)返回值不会变
        user.name = "hello2";
        System.out.println("user hashCode(): " + user.hashCode());
        System.out.println("user identityHashCode(): " + System.identityHashCode(user));
    }

    private static class User{
        public String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public int hashCode() {
            return name.hashCode() + age;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof User)) {
                return false;
            }

            User user = (User) obj;
            return this.name.equals(user.name) && this.age == user.age;
        }
    }
}
