class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int m = n/2;
        int[] count = new int[26];
        for(int i=0;i<n;i++){
            count[s.charAt(i) - 'a']++;
        }
        int oddcount = 0;
        char midchar = 0;
        for(int i=0;i<26;i++){
            if(count[i] % 2 != 0){
                oddcount++;
                midchar = (char) ('a' + i);
            }
        }
        if(oddcount > (n%2)){
            return "";
        }
        int[] half = new int[26];
        for(int i=0;i<26;i++){
            half[i] = count[i]/2;
        }
        int[] curhalf = half.clone();
        boolean canmatchprefix = true;
        for(int i=0;i<m;i++){
            int c = target.charAt(i) - 'a';
            if(curhalf[c] > 0){
                curhalf[c]--;
            }
            else{
                canmatchprefix = false;
                break;
            }
        }
        if(canmatchprefix){
            String candidate = buildpalindrome(target.substring(0, m), midchar, n);
            if(candidate.compareTo(target) > 0){
                return candidate;
            }
        }
        for(int i=m-1;i>=0;i--){
            int[] req = new int[26];
            boolean possible = true;
            for(int j=0;j<i;j++){
                int c = target.charAt(j) - 'a';
                req[c]++;
                if(req[c] > half[c]){
                    possible = false;
                    break;
                }
            }
            if(!possible) continue;

            int[] rem = new int[26];
            for(int j=0;j<26;j++){
                rem[j] = half[j] - req[j];
            }
            int pick = -1;
            int targetcharidx = target.charAt(i) - 'a';
            for(int c=targetcharidx+1;c<26;c++){
                if(rem[c] > 0){
                    pick = c;
                    break;
                }
            }
            if(pick != -1){
                rem[pick]--;
                StringBuilder firsthalf = new StringBuilder(target.substring(0, i));
                firsthalf.append((char)('a'+pick));
                for(int c=0;c<26;c++){
                    while(rem[c] > 0){
                        firsthalf.append((char)('a'+c));
                        rem[c]--;
                    }
                }
                return buildpalindrome(firsthalf.toString(), midchar, n);
            }
        }
        return "";
    }
    private String buildpalindrome(String firsthalf, char midchar, int totallen){
        StringBuilder sb = new StringBuilder(firsthalf);
        if(totallen % 2 == 1){
            sb.append(midchar);
        }
        for(int i=firsthalf.length()-1;i>=0;i--){
            sb.append(firsthalf.charAt(i));
        }
        return sb.toString();
    }
}