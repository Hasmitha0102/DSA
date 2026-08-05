class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        Set<Integer> present = new HashSet<>();
        int min = nums[0];
        int max = nums[0];
        for(int num : nums){
            present.add(num);
            if(num<min) min = num;
            if(num>max) max = num;
        }
        List<Integer> result = new ArrayList<>();
        for(int i=min;i<=max;i++){
            if(!present.contains(i))
                result.add(i);
        }
        return result;
    }
}