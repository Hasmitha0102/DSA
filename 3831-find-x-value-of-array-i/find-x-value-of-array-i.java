class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        long[] count = new long[k];
        for(int x : nums){
            int val = x%k;
            long[] nextcount = new long[k];
            for(int r=0;r<k;r++){
                if(count[r]>0){
                    int next = (r*val)%k;
                    nextcount[next] += count[r];
                }
            }
            nextcount[val]++;
            for(int r=0;r<k;r++){
                result[r] += nextcount[r];
            }
            count = nextcount;
        }
        return result;
    }
}