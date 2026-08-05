class Solution {
    public int dominantIndices(int[] nums) {
        int n = nums.length;
        int count = 0;
        long suffixsum = 0;
        suffixsum += nums[n-1];
        for(int i=n-2; i>=0; i--){
            int elements = n-1-i;
            if(elements>0){
                if((long) nums[i] * elements > suffixsum)
                    count++;
            }
            suffixsum += nums[i];
        }
        return count;
    }
}