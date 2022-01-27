import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class fs_io {
    public static void main(String[] args) throws IOException {
        // Paths
        Path re = Paths.get("fs.txt");
        // Path ab = Paths.get("/home", "coding", "fs.txt");
        Path parent = re.getParent();
        Scanner in = new Scanner(re, StandardCharsets.UTF_8);
        in.useDelimiter("\s|\n");
        Stream<String> words = in.tokens();
        words.forEach((w)->{
            System.out.print(w);
        });
        

        //Files
        byte[] bytes = Files.readAllBytes(re);
        var content = Files.readString(re, StandardCharsets.UTF_8);
        assert (Arrays.equals(bytes, content.getBytes(StandardCharsets.UTF_8)));
        
        // create file/dir
        Path dir = Paths.get("new_dir");
        Files.createDirectories(dir);
        Path newfile = Paths.get("new_dir", "newfile");
        Files.createFile(newfile);
        String c = "Hello world!";
        Files.write(newfile, c.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);

        // IO for large file
        InputStream is = Files.newInputStream(newfile);
        Scanner s = new Scanner(is, StandardCharsets.UTF_8);
        assert (s.nextLine().equals("Hello world!"));
        s.close();
        OutputStream os = Files.newOutputStream(newfile, StandardOpenOption.APPEND);
        var out = new PrintWriter(
            new OutputStreamWriter(os, StandardCharsets.UTF_8), true);
        out.print("\nThanks");
        out.close();


        

    }
}
