class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int left=0, ones=0;
        int bestlen = Integer.MAX_VALUE;
        String ans = "";
        for(int right=0;right<s.length();right++){
            if(s.charAt(right) == '1') ones++;
            while(ones == k){
                String curr = s.substring(left,right+1);
                if(curr.length() < bestlen || (curr.length() == bestlen && curr.compareTo(ans) < 0)){
                    bestlen = curr.length();
                    ans = curr;
                }
                if(s.charAt(left) == '1') ones--;
                left++;
            }
        }
        return ans;
    }
}