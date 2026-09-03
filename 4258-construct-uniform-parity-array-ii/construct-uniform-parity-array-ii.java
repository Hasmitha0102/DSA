class Solution {
    public boolean uniformArray(int[] nums1) {
        int minval = Integer.MAX_VALUE;
        boolean hasodd = false;
        for(int x : nums1){
            if(x < minval){
                minval = x;
            }
            if(x % 2 != 0){
                hasodd = true;
            }
        }
        return !hasodd || (minval % 2 != 0);
    }
}