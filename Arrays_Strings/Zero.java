public class Zero {
    private static void zero(int[][] m) {
        int col = m[0].length;
        int row = m.length;

        boolean[] r = new boolean[row];
        boolean[] c = new boolean[col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (m[i][j] == 0) {
                    r[i] = true;
                    c[j] = true;
                }
            }
        }
        for (int i = 0; i < r.length; i++)
            if (r[i])
                zeroRow(m, i);
        for (int j = 0; j < c.length; j++)
            if (c[j])
                zeroCol(m, j);

    }

    private static void zeroRow(int[][] m, int r) {
        for (int j = 0; j < m[0].length; j++)
            m[r][j] = 0;
    }
    private static void zeroCol(int[][] m, int c) {
        for (int i = 0; i < m.length; i++)
            m[i][c] = 0;
    }
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
        int[][] m = {{0, 1, 2}, {3, 4, 5}, {8, 0, 9}};
        printmx(m);
        zero(m);
        printmx(m);
    }
}
