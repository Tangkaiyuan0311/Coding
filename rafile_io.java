import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class rafile_io {
    public static void main(String[] args) throws IOException {
        File f = new File("ra_file.text");
        f.createNewFile();
        var out = new PrintWriter(
            new OutputStreamWriter(
                new FileOutputStream(f, false), StandardCharsets.UTF_8), 
                false);
        out.print("This is a random accessed file!");
        out.close();

        var rf = new RandomAccessFile("ra_file.text", "r");
        Scanner in = new Scanner(System.in, StandardCharsets.UTF_8);
        long len = rf.length();
        while (in.hasNextInt()) {
            long pos = in.nextInt();
            if (pos < len)
                rf.seek(pos);
            else
                throw new IllegalArgumentException("too large offset");
            char c = (char)rf.readByte();
            System.out.println(c);
        }
    }
}
