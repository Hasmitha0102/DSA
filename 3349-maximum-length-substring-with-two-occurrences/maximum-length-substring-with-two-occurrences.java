class Solution {
    public int maximumLengthSubstring(String s) {
        int[] freq = new int[26];
        int maxlength = 0;
        int left = 0;

        for(int right=0; right<s.length(); right++){
            char rc = s.charAt(right);
            freq[rc-'a']++;
            while(freq[rc-'a']>2){
                char lc = s.charAt(left);
                freq[lc-'a']--;
                left++;
            }
            maxlength = Math.max(maxlength, right-left+1);
        }
        return maxlength;
    }
}