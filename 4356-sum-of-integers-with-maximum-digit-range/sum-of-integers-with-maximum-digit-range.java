class Solution {
    public int maxDigitRange(int[] nums) {
        int maxRange = -1;
        int sum = 0;
        for(int num : nums){
            int current = getdigitrange(num);
            if(current > maxRange){
                maxRange = current;
                sum = num;
            }
            else if(current == maxRange){
                sum+=num;
            }
        }
        return sum;
    }
    private int getdigitrange(int num){
        if(num==0){
            return 0;
        }
        num = Math.abs(num);
        int max = 0;
        int min = 9;
        while(num>0){
            int digit = num%10;
            if(digit > max) max=digit;
            if(digit < min) min=digit;
            num /= 10;
        }
        return max-min;
    }
}