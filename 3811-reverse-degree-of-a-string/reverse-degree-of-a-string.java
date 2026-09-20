class Solution {
    public int reverseDegree(String s) {
        int total = 0;
        for(int i=0;i<s.length();i++){
            int rev = 'z' - s.charAt(i)+1;
            int stringposition = i+1;
            total += stringposition*rev;
        }
        return total;
    }
}