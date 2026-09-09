class Solution {
    public long countCommas(long n) {
        long total = 0;
        for(long i=1000; i<=n; i*=1000){
            total += (n-i+1);
            if(i>Long.MAX_VALUE/1000)
                break;
        }
        return total;
    }
}