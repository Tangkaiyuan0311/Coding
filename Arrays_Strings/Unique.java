import java.util.Scanner;

public class Unique {
    /*
    public static boolean IsUnique(String s) {
        // assuming ascii
        int R = 256;
        boolean[] vec = new boolean[R];
        for (int i = 0; i < s.length(); i++) {
            if (vec[s.charAt(i)])
                return false;
            else
                vec[s.charAt(i)] = true;
        }
        return true;
    }
    */
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
    public static boolean IsUnique(String s) {
        char[] a = s.toCharArray();
        qsort(a, 0, a.length-1);
        char old = '\0';
        for (int i = 0; i < a.length; i++) {
            if (a[i] == old)
                return false;
            old = a[i];
        }
        return true;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        in.close();
        if (IsUnique(input))
            System.out.println("Unique");
        else
            System.out.println("Not Unique");
    }
}