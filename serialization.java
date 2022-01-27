import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class serialization {
    
    private static class fruit implements Serializable{
        String name;
        int weight;
        fruit(String name, int weight) {
            this.name = name;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        fruit apple = new fruit("apple", 400);
        File f = new File("apple.dat");
        f.createNewFile();
        var out = new ObjectOutputStream(
            new FileOutputStream(f, false)
        );
        out.writeObject(apple);

        var in = new ObjectInputStream(
            new FileInputStream("apple.dat")
        );
        var fruit = (fruit)in.readObject();
        assert (fruit.name.equals(apple.name));

    }
}
