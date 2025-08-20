class Solution {
    public long zeroFilledSubarray(int[] nums) {
        long count = 0;          // total subarrays
        long currentZeros = 0;   // length of current zero streak

       for (int i = 0; i < nums.length; i++) {
    if (nums[i] == 0) {
        currentZeros++;
        count += currentZeros;
    } else {
        currentZeros = 0;
    }
}

        return count;
    }
}
