class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        int[] sortedNums = nums.clone();
        Arrays.sort(sortedNums);

        int currentgroup = 0;
        Map<Integer, Integer> numtogroup = new HashMap<>();
        Map<Integer, Queue<Integer>> groupToList = new HashMap<>();
        numtogroup.put(sortedNums[0], currentgroup);
        groupToList.put(currentgroup, new LinkedList<>());
        groupToList.get(currentgroup).offer(sortedNums[0]);
        for(int i=1;i<n;i++){
            if(sortedNums[i] - sortedNums[i-1] > limit){
                currentgroup++;
            }
            numtogroup.put(sortedNums[i], currentgroup);
            groupToList.computeIfAbsent(currentgroup, k -> new LinkedList<>()).offer(sortedNums[i]);
        }
        int[] result = new int[n];
        for(int i=0;i<n;i++){
            int group = numtogroup.get(nums[i]);
            result[i] = groupToList.get(group).poll();
        }
        return result;
    }
}