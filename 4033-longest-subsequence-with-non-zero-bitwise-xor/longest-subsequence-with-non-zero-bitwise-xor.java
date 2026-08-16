class Solution {
    public int longestSubsequence(int[] nums) {
        int xor = 0;
        boolean x = false;
        for(int n:nums){
            xor ^= n;
            if(n != 0)
                x = true;
        }
        if(xor != 0)
            return nums.length;
        if(x)
            return nums.length-1;
        return 0;
    }
}