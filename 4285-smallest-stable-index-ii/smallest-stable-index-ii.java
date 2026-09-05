class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] suffixmin = new int[n];

        suffixmin[n-1] = nums[n-1];
        for(int i=n-2;i>=0;i--){
            suffixmin[i] = Math.min(nums[i], suffixmin[i+1]);
        }

        int prefmax = Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            prefmax = Math.max(prefmax, nums[i]);
            if(prefmax - suffixmin[i] <= k){
                return i;
            }
        }
        return -1;
    }
}