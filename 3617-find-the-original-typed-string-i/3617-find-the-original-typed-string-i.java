class Solution {
    public int possibleStringCount(String word) {
        int count = 1;  // Start with 1. This means "no mistake" is always possible.

        for(int i = 0; i < word.length() - 1; i++) {
            // Check if the current letter is the same as the next letter
            if(word.charAt(i) == word.charAt(i + 1)) {
                count++;  // If yes, it could be the mistake place. Add 1 possibility.
            }
        }

        return count;  // Return how many total possible real words.
    }
}
