package three.stone.effective;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HashCodeShouldImmutable {
    public static void main(String[] args) {
        Email email = new Email("123.com");
        Map<Email, String> map = new HashMap<>();
        map.put(email, "123");
        System.out.println(map.get(email));
        email.setEmail("123.com");
        System.out.println(map.get(email));
        email.setEmail("1234.com");
        System.out.println(map.get(email));
    }

    private static class Email {
        private String email;

        public Email(String email) {
            this.email = email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Email email1 = (Email) o;
            return email.equals(email1.email);
        }

        @Override
        public int hashCode() {
            return Objects.hash(email);
        }
    }
}
