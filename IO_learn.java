import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PushbackInputStream;

public class IO_learn {
    public static void main(String[] args) throws IOException {
        int data = 1999;

        File file = new File("int.dat");
        file.createNewFile(); // if file already exists will do nothing 
        FileOutputStream fout = new FileOutputStream(file, false);
        var dout = new DataOutputStream(fout);
        dout.writeInt(data);
        dout.close();

        //var din = new DataInputStream(new FileInputStream("int.dat"));
        var din = new DataInputStream(
            new PushbackInputStream(
                new BufferedInputStream(
                    new FileInputStream("int.dat")
                )
            )
        );
        assert (din.readInt() == data);
        din.close();

        System.out.println("Success");


    }
}