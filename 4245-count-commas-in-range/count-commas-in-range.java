class Solution {
    public int countCommas(int n) {
        long total = 0;
        long nLong = (long)n;
        for(long i=1000;i<=nLong;i*=1000){
            long count = nLong - i +1;
            total += count;
        }
        return (int)total;
    }
}