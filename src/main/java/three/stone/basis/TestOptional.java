package three.stone.basis;

import java.util.Optional;

public class TestOptional {
    private Boolean isClientActive = null;

    public Boolean getClientActive() {
        return isClientActive;
    }

    public static void main(String[] args) throws InterruptedException {
        TestOptional object = new TestOptional();
        System.out.println(Optional.ofNullable(object)
                .map(TestOptional::getClientActive)
                .orElse(false));
    }

}
