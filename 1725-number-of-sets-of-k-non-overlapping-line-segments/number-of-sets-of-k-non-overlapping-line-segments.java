class Solution {
    public int numberOfSets(int n, int k) {
        int MOD = 1000000007;
        int N = n+k-1;
        int R = 2*k;
        if(R > N) return 0;
        long num = 1;
        long den = 1;
        for(int i=1;i<=R;i++){
            num = (num*(N-i+1))%MOD;
            den = (den*i)%MOD;
        }
        return (int) ((num * power(den, MOD-2, MOD))%MOD);
    }
    private long power(long base, long exp, int mod){
        long res = 1;
        base %= mod;
        while(exp > 0){
            if((exp & 1) == 1) res = (res*base)%mod;
            base = (base*base)%mod;
            exp >>= 1;
        }
        return res;
    }
}