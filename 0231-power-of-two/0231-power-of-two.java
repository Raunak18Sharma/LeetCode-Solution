public class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        double logVal = Math.log(n) / Math.log(2);
        return Math.abs(logVal - Math.round(logVal)) < 1e-10;
    }
}