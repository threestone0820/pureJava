package three.stone.jvm;

import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.GenerateMicroBenchmark;

public class HelloWorld {
    @GenerateMicroBenchmark
    public void helloWorld() {

    }

    public static void main(String[] args) {
        Main.main(args);
    }
}
