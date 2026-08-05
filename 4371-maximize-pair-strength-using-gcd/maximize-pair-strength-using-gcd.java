class Solution {
    public long maxPairStrength(int[] nums) {
        long max = 0;
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                long a = gcd(nums[i],nums[j]);
                long b = (nums[i]/a)*(long)(nums[j]/a);
                if(b>max)
                    max = b;
            }
        }
        return max;
    }
    private long gcd(long x,long y){
        while(y!=0){
            long temp = x%y;
            x = y;
            y = temp;
        }
        return x;
    }
}