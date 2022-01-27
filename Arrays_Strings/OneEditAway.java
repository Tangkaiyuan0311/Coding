import java.util.Scanner;

public class OneEditAway {
    private static boolean check(String a, String b) {
        int len_a = a.length();
        int len_b = b.length();
        
        if (len_a == len_b) {
            // check replacement
            int dif = 0;
            for (int i = 0; i < len_a; i++)
                if (a.charAt(i) != b.charAt(i))
                    if (++dif > 1)
                        return false;
            return true;
                    
        }
        else if (len_a - len_b == -1 || len_a - len_b == 1) {
            // check removal/addition
            String lr, sr;
            if (len_a > len_b) {
                lr = a;
                sr = b;
            }
            else {
                lr = b;
                sr = a;
            }
            
            int i = 0, j = 0;
            int cnt = 0;
            while (i < lr.length() && j < sr.length()){
                // invariant: still possibly one edit away
                if (lr.charAt(i) != sr.charAt(j)) {
                    i++;
                    if (++cnt > 1)
                        return false; 
                    continue;
                }
                i++;
                j++;
            }
            return true;
        }
        else
            return false;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String a = in.nextLine();
        String b = in.nextLine();
        if (check(a, b))
            System.out.println("One edit away");
        else
        System.out.println("Not one edit away");
    }
}
