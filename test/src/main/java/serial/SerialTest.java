package serial;

import org.junit.Test;

import javax.xml.crypto.dsig.spec.HMACParameterSpec;
import java.io.*;
import java.util.HashMap;

public class SerialTest {
    @Test
    public void encode(){
        Persion p = new Persion(1L, "123");
        System.out.println("person Seria:" + p);
        try {
            FileOutputStream fos = new FileOutputStream("Persion.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(p);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void decode(){
        Persion p;
        try {
            FileInputStream fis = new FileInputStream("Persion.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            p = (Persion) ois.readObject();
            ois.close();
            System.out.println(p.toString());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
