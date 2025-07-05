import java.util.HashMap;

class Solution {
    public int findLucky(int[] arr) {
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        for (int num : arr) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        int result = -1;
        for (int key : freqMap.keySet()) {
            if (key == freqMap.get(key)) {
                result = Math.max(result, key);
            }
        }
        return result;
    }
}
