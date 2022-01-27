import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Str_rotate {
    private static boolean isSubstring (String s1, String s2) {
        if (s1.length() == s2.length())
            return s1.equals(s2);

        String lr, sr;
        if (s1.length() > s2.length()) {
            sr = s2;
            lr = s1;
        }
        else {
            sr = s2;
            lr = s1;
        }
        // check is sr is the substring of lr
        Pattern pattern = Pattern.compile(sr);
        Matcher matcher = pattern.matcher(lr);
        if (matcher.find())
            return true;
        else
            return false;
        
    }

    private static boolean isRotate(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;
        String cat = s1 + s2;
        if (isSubstring(cat, s2))
            return true;
        else
            return false;
    }
     

    public static void main(String[] args) {
        assert (isRotate("waterbottle", "erbottlewat"));

    }
}
