package three.stone.jvm;

import java.util.ArrayList;
import java.util.List;

public class StackOverFlowTest {
    private static int _10K = 10 * 1024;
    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(new byte[_10K]);
            System.out.println(i);
        }
    }
}
