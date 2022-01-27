public class Palindrome {

    /*
    // even len : all even count, 0 odd count, cant be 1 odd count
    // odd len : only one odd count, cant be 0 odd count
    // -> at most one odd count, cover both cases
    private static boolean check(String s) {
        char[] a = s.toLowerCase().toCharArray();
        // false: 0, 2, 4...
        // true: 1, 3, 5...
        boolean[] cnt = new boolean[256];
        for (char c : a) {
            if (c != 0x20) // not space character
                cnt[c] = !cnt[c]; // invert
        }
        int num_true = 0;
        for (boolean b : cnt) {
            if (b) {
                if (++num_true > 1)
                    return false;
            }
                
        }
        return true;
    }
    */

    // bit manipulation
    // lower case a~z only
    private static boolean check(String s) {
        char[] a = s.toLowerCase().toCharArray();
        int bitvect = 0; // lower 26 bits are used

        // scan
        for (char c : a) {
            int index = c - 'a';
            if (index > 25 || index < 0)
                continue;
            int mask = 1 << index;
            if ((bitvect & mask) == 0)
                bitvect |= mask;
            else
                bitvect &= ~mask;
        }

        // at most one bit is 1
        boolean zero = (bitvect == 0);
        boolean one = ((bitvect & (bitvect-1)) == 0);
        if (zero || one)
            return true;
        else
            return false;

    }

    public static void main(String[] args) {
        String input = "Tact Coa";
        System.out.println(check(input));
    }
}
