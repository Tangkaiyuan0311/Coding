import java.util.Scanner;

public class Compress {
    private static String compress(String s) {
        int N = s.length();
        
        StringBuilder bu = new StringBuilder();
        char old = s.charAt(0);
        int len = 1; 
        for (int i = 1; i < N; i++) {
            if (s.charAt(i) != old) {
                bu.append(old);
                bu.append(len);
                if (bu.length() >= N)
                    return s;
                old = s.charAt(i);
                len = 1;
            }
            else {
                len++;
            }
        }
        bu.append(old);
        bu.append(len);
        if (bu.length() >= N)
            return s;
        return bu.toString();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            System.out.println(compress(in.nextLine()));
        }
        in.close();
    }
}
