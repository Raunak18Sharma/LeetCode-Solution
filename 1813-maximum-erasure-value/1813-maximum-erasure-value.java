import java.util.HashSet;
import java.util.Set;

public class Solution {
    public int maximumUniqueSubarray(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        int left = 0, currSum = 0, maxSum = 0;

        for (int right = 0; right < nums.length; right++) {
            while (seen.contains(nums[right])) {
                seen.remove(nums[left]);
                currSum -= nums[left];
                left++;
            }
            seen.add(nums[right]);
            currSum += nums[right];
            maxSum = Math.max(maxSum, currSum);
        }

        return maxSum;
    }

    // Example usage
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] nums1 = {4, 2, 4, 5, 6};
        System.out.println(sol.maximumUniqueSubarray(nums1)); // Output: 17

        int[] nums2 = {5, 2, 1, 2, 5, 2, 1, 2, 5};
        System.out.println(sol.maximumUniqueSubarray(nums2)); // Output: 8
    }
}
