import java.util.*;

public class Solution {
    public boolean reorderedPowerOf2(int n) {
        String target = sortDigits(n);

        // Generate all powers of two up to 1e9
        for (int i = 0; i < 31; i++) { // 2^0 to 2^30
            int pow = 1 << i;
            if (target.equals(sortDigits(pow))) {
                return true;
            }
        }
        return false;
    }

    // Helper: returns sorted string of digits
    private String sortDigits(int num) {
        char[] chars = String.valueOf(num).toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
