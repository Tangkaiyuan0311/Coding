import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.stream.Stream;

public class text_io {
    public static void main(String[] args) throws IOException {
        /*
        var in = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        int c;
        while ((c = in.read()) != -1) {
            System.out.print((char)c);
        }
        System.out.println("End of file");
        */

        // text output
        File f = new File("out.txt");
        f.createNewFile();
        var out = new PrintWriter(
            new OutputStreamWriter(
                new FileOutputStream(f, false), StandardCharsets.UTF_8), 
                false);
        out.println("This is an apple");
        out.close();

        // text input
        File file = new File("input.txt");
        Scanner in = new Scanner(file, StandardCharsets.UTF_8);
        in.useDelimiter("\s|\n"); // white space + new line
        // use stream
        Stream<String> s = in.tokens();
        s.forEach((e)-> {
            System.out.print(e + " ");
        });
        // alternative
        StringBuilder bu = new StringBuilder();
        while (in.hasNext()) {
            bu.append(in.next());
        }
        String words = bu.toString();
        System.out.println(words);

    }
}
