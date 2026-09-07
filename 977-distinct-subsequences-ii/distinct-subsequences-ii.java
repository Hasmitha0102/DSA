class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1000000007;
        long[] endswith = new long[26];
        for(int i=0;i<s.length();i++){
            int charidx = s.charAt(i)-'a';
            long current = 1;
            for(int j=0;j<26;j++){
                current = (current + endswith[j])%MOD;
            }
            endswith[charidx] = current;
        }
        long total = 0;
        for(int j=0;j<26;j++){
            total = (total + endswith[j]) % MOD;
        }
        return (int)total;
    }
}