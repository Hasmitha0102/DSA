class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] lastseen = new int[128];
        int left = 0;
        int maxl = 0;
        for(int right = 0; right < s.length(); right++){
            char c = s.charAt(right);
            left = Math.max(left, lastseen[c]);
            maxl = Math.max(maxl, right-left+1);
            lastseen[c] = right + 1;
        }
        return maxl;
    }
}