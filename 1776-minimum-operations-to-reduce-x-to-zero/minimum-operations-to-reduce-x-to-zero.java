class Solution {
    public int minOperations(int[] nums, int x) {
        int totalsum = 0;
        for(int num : nums){
            totalsum += num;
        }
        int target = totalsum-x;
        if(target < 0) return -1;
        if(target == 0) return nums.length;
        int left = 0;
        int curr = 0;
        int max = -1;
        for(int right=0; right<nums.length; right++){
            curr += nums[right];
            while(left <= right && curr > target){
                curr -= nums[left++];
            }
            if(curr == target){
                max = Math.max(max, right-left+1);
            }
        }
        return max == -1 ? -1 : nums.length - max;
    }
}