class Solution {
    public int totalNumbers(int[] digits) {
        int[] freq = new int[10];
        for(int d : digits){
            freq[d]++;
        }
        int count = 0;
        for(int num = 100; num<=998; num+=2){
            int hundreds = num/100;
            int tens = (num/10)%10;
            int units = num%10;
            int[] currfreq = new int[10];
            currfreq[hundreds]++;
            currfreq[tens]++;
            currfreq[units]++;
            if(currfreq[hundreds] <= freq[hundreds] && currfreq[tens] <= freq[tens] && currfreq[units] <= freq[units]){
                count++;
            }
        }
        return count;
    }
}