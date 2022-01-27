import java.util.Scanner;

public class Perm {
    
    private static void exch(char[] a, int i, int j) {
        char tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
    private static int partition(char[] a, int lo, int hi) {
        char v = a[lo];
        int i = lo + 1, j = hi;
        while (i <= j) {
            if (a[i] <= v)
                i++;
            else
                exch(a, i, j--);
        }
        exch(a, lo, j);
        return j;

    }
    private static void qsort(char[] a, int lo, int hi) {
        if (hi <= lo)
            return;
        int par = partition(a, lo, hi);
        qsort(a, lo, par-1);
        qsort(a, par+1, hi);
    }
    // based on sorting
    /*
    private static boolean isPerm(String s, String a) {
        if (s.length() != a.length())
            return false;
        char[] s1 = s.toCharArray();
        qsort(s1, 0, s1.length-1);
        char[] a1 = a.toCharArray();
        qsort(a1, 0, a1.length-1);
        return Arrays.equals(a1, s1);
    }
    */
    // based on counting array
    private static boolean isPerm(String s, String a) {
        if (s.length() != a.length())
            return false;
        int N = s.length();
        int R = 256;
        int[] count = new int[R];
        for (int i = 0; i < N; i++) {
            count[a.charAt(i)]++;
        }
        // trick;
        for (int i = 0; i < N; i++) {
            if (--count[s.charAt(i)] < 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine();
        String s2 = in.nextLine();
        in.close();
        if (isPerm(s1, s2))
            System.out.println("perm");
        else
            System.out.println("not perm");
    }
}
