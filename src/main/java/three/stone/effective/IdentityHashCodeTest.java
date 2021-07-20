package three.stone.effective;

public class IdentityHashCodeTest {
    private int i;

    public IdentityHashCodeTest(int i) {
        this.i = i;
    }

    @Override
    public int hashCode() {
        return i;
    }

    public static void main(String[] args) {
        IdentityHashCodeTest objectHashCodeTest = new IdentityHashCodeTest(1);
        //1846274136
        System.out.println(objectHashCodeTest.hashCode());
        //1846274136
        // System.identityHashCode方法返回的是默认hashCode方法返回的那个值
        System.out.println(System.identityHashCode(objectHashCodeTest));
    }
}
