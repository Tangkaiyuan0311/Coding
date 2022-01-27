import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class bin_io {
    public static void main(String[] args) throws IOException {
        // reading and write an array of int value
        int[] a = {0, 1, 2, 3, 4};
        File f = new File("array.bin");
        f.createNewFile();
        var dout = new DataOutputStream(
            new FileOutputStream(f, false)
        );
        dout.writeInt(a.length);
        for (int i = 0; i < a.length; i++)
            dout.writeInt(a[i]);

        File in = new File("array.bin");
        if (in.isFile()) {
            var din = new DataInputStream(
                new FileInputStream(in)
            ); 
            int[] array = new int[din.readInt()];
            for (int i = 0; i < array.length; i++)
                array[i] = din.readInt();
            assert (Arrays.equals(array, a));
            System.out.println("Success!");
        }
    }
}
