class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        int[] suffixSum = new int[n];
        suffixSum[n-1] = piles[n-1];
        for(int i=n-2;i>=0;i--){
            suffixSum[i] = suffixSum[i+1]+piles[i];
        }
        int[][] memo = new int[n][n+1];
        return helper(piles, suffixSum, 0, 1, memo);
    }
    private int helper(int[] piles, int[] suffixSum, int i, int M, int[][] memo){
        if(i >= piles.length){
            return 0;
        }
        if(i+2 * M >= piles.length){
            return suffixSum[i];
        }
        if(memo[i][M] != 0){
            return memo[i][M];
        }
        int max = 0;
        for(int j=1;j<=2*M;j++){
            int next = Math.max(M,j);
            int curr = suffixSum[i] - helper(piles, suffixSum, i+j, next, memo);
            max = Math.max(max, curr);
        }
        memo[i][M] = max;
        return max;
    }
}