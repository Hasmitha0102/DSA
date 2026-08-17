class Solution {
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        int[] prefix = new int[n+1];
        for(int i=0;i<n;i++){
            prefix[i+1] = prefix[i]+stoneValue[i];
        }
        int[][] dp = new int[n][n];
        for(int i=2;i<=n;i++){
            for(int j=0;j+i-1<n;j++){
                int x = j+i-1;
                for(int k=j;k<x;k++){
                    int left = prefix[k+1]-prefix[j];
                    int right = prefix[x+1]-prefix[k+1];
                    if(left < right){
                        dp[j][x] = Math.max(dp[j][x], left+dp[j][k]);
                    }
                    else if(left > right){
                        dp[j][x] = Math.max(dp[j][x], right+dp[k+1][x]);
                    }
                    else{
                        dp[j][x] = Math.max(dp[j][x], left+Math.max(dp[j][k], dp[k+1][x]));
                    }
                }
            }
        }
        return dp[0][n-1];
    }
}