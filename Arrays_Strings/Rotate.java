public class Rotate {

    // recursive rotation
    private static void rotate(int[][] m, int len, int i) {
        if (len < 2)
            return;
        int N = m[0].length;
        int off = (N - len) / 2;
        int tmp = m[off][off+i]; // p1
        m[off][off+i] = m[off+len-1-i][off]; // p1 = p4
        m[off+len-1-i][off] = m[off+len-1][off+len-1-i]; // p4 = p3
        m[off+len-1][off+len-1-i] = m[off+i][off+len-1]; // p3 = p2
        m[off+i][off+len-1] = tmp; // p2 = tmp;
    }
    private static void rotate(int[][] m) {
        int N = m[0].length;
        int len;
        for (len = N; len >= 2; len -= 2)
            for (int i = 0; i < len-1; i++)
                rotate(m, len, i);
    }

    /*
    private static int index2row(int i, int N) {
        if (i >= 0 && i <= N-2)
            return 0;
        else if (i >= N-1 && i <= 2*N-3)
            return i+1-N;
        else if (i >= 2*N-2 && i <= 3*N-4)
            return N-1;
        else // 3*N-3 ~ 4N-5
            return 4*N-4-i;
    }
    private static int index2col(int i, int N) {
        if (i >= 0 && i <= N-2)
            return i;
        else if (i >= N-1 && i <= 2*N-3)
            return N-1;
        else if (i >= 2*N-2 && i <= 3*N-4)
            return (3*N-3)-i;
        else
            return 0; 
    }
    */
    private static void printmx(int[][] m) {
        int len = m[0].length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] m = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        printmx(m);
        System.out.println("Rotate");
        rotate(m);
        printmx(m);
    }

}


