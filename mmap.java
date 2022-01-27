import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.IOException;

public class mmap {
    public static void main(String[] args) throws IOException {
        Path input = Paths.get("input.txt");
        // FileChannel channel = FileChannel.open(input, FileChannel.MapMode.READ_ONLY);
        FileChannel channel = FileChannel.open(input, StandardOpenOption.READ);
        int filelen = (int)channel.size();
        ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, filelen);

        // viewed as byte stream
        StringBuilder bu = new StringBuilder();
        while (buf.hasRemaining()){
            bu.append((char)buf.get());
        }
        System.out.println(bu.toString());

        // viewed as random accessed array
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < buf.limit(); i++)
            b.append((char)buf.get(i));
        System.out.println(b.toString());
    }
}
