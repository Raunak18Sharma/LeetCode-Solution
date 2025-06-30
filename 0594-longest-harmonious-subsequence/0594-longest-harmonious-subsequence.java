import java.util.*;

public class Solution {
    public int findLHS(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        int maxLength = 0;

        // Count frequency of each number
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Check for each key if (key + 1) exists
        for (int key : freqMap.keySet()) {
            if (freqMap.containsKey(key + 1)) {
                int length = freqMap.get(key) + freqMap.get(key + 1);
                maxLength = Math.max(maxLength, length);
            }
        }

        return maxLength;
    }
}
