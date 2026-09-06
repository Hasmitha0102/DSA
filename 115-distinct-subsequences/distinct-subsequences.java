class Solution {
    public int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();

        if(m>n) return 0;
        double[] dp = new double[m+1];
        dp[0] = 1.0;
        for(int i=0;i<n;i++){
            char ch = s.charAt(i);
            for(int j=m;j>=1;j--){
                if(ch == t.charAt(j-1)){
                    dp[j] += dp[j-1];
                }
            }
        }
        return (int)dp[m];
    }
}