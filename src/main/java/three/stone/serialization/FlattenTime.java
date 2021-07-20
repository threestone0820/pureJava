package three.stone.serialization;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class FlattenTime {
    public static void main(String[] args) {
        PersistentTime time = new PersistentTime();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("time.ser"));
            out.writeObject(time);
            out.close();
        } catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
