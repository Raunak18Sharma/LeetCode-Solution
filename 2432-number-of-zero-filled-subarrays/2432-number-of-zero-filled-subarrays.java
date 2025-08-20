class Solution {
    public long zeroFilledSubarray(int[] nums) {
        long count = 0;          // total subarrays
        long currentZeros = 0;   // length of current zero streak

        for (int num : nums) {
            if (num == 0) {
                currentZeros++;
                count += currentZeros;  // each new zero extends subarrays
            } else {
                currentZeros = 0;       // reset when non-zero found
            }
        }

        return count;
    }
}
