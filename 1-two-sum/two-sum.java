class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] r = new int[2]; 
        int n = nums.length;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(nums[i]+nums[j]==target){
                    r[0] = i;
                    r[1] = j;
                    return r;
                }
            }
        }
        return r;
    }
}