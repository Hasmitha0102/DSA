class Solution {
    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;
        long mincoin = coins[0];
        for(int coin : coins){
            mincoin = Math.min(mincoin, coin);
        }
        long low = 1;
        long high = mincoin * (long)k;
        long ans = high;
        while(low <= high){
            long mid = low + (high - low)/2;
            if(countmultiples(coins, mid) >= k){
                ans = mid;
                high = mid-1;
            }
            else{
                low = mid+1;
            }
        }
        return ans;
    }

    private long countmultiples(int[] coins, long target){
        int n = coins.length;
        long count = 0;
        int total = 1<<n;
        for(int mask=1;mask<total;mask++){
            long curr = 1;
            int bitcount = 0;
            boolean overflow = false;
            for(int i=0;i<n;i++){
                if((mask & (1 << i)) != 0){
                    bitcount++;
                    curr = lcm(curr, coins[i]);
                    if(curr > target){
                        overflow = true;
                        break;
                    }
                }
            }
            if(overflow) continue;
            if(bitcount % 2 == 0)
                count -= target/curr;
            else
                count += target/curr;
        }
        return count;
    }
    private long gcd(long a, long b){
        while(b != 0){
            long temp = b;
            b = a%b;
            a = temp;
        }
        return a;
    }
    private long lcm(long a, long b){
        return(a/gcd(a,b))*b;
    }
}