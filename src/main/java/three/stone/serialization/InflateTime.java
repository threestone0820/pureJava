package three.stone.serialization;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class InflateTime {
    public static void main(String[] args) {
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("time.ser"));
            PersistentTime time = (PersistentTime) is.readObject();
            System.out.println("Flattened time: " + time.getTime());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
