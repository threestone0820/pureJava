package three.stone.jvm;

import org.openjdk.jol.info.ClassLayout;

public class ObjectHeaderTest {
    private int i = 10;

    public static void main(String[] args) {
        ObjectHeaderTest object = new ObjectHeaderTest();
//        System.out.printf("%x\n", object.hashCode());
        String layout = ClassLayout.parseInstance(object).toPrintable();
        System.out.println(layout);
    }
}
