class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for(char c : s.toCharArray()){
            count[c-'a']++;
        }
        int i=0;
        while(i<n){
            int ch = target.charAt(i) - 'a';
            if(count[ch] == 0) {
                break;
            }
            count[ch]--;
            i++;
        }
        int start = Math.min(i, n-1);
        for(int j=start;j>=0;j--){
            if(j<i){
                count[target.charAt(j)-'a']++;
            }
            int x = target.charAt(j)-'a';
            for(int c=x+1;c<26;c++){
                if(count[c] > 0){
                    StringBuilder ans = new StringBuilder();
                    ans.append(target.substring(0,j));
                    ans.append((char) ('a' + c));
                    count[c]--;
                    for(int k=0;k<26;k++){
                        while(count[k] > 0){
                            ans.append((char) ('a'+k));
                            count[k]--;
                        }
                    }
                    return ans.toString();
                }
            }
        }
        return "";
    }
}