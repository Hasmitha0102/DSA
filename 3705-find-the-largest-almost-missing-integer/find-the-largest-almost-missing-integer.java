class Solution {
    public int largestInteger(int[] nums, int k) {
        Map<Integer, Integer> subarrayCount = new HashMap<>();
        int n = nums.length;

        for(int i=0;i<=n-k;i++){
            Set<Integer> uniqueInWindow = new HashSet<>();
            for(int j=i;j<i+k;j++){
                uniqueInWindow.add(nums[j]);
            }
            for(int num : uniqueInWindow){
                subarrayCount.put(num, subarrayCount.getOrDefault(num, 0) + 1);
            }
        }
        int max = -1;
        for(Map.Entry<Integer, Integer> entry : subarrayCount.entrySet()){
            if(entry.getValue() == 1){
                max = Math.max(max, entry.getKey());
            }
        }
        return max;
    }
}