class Solution {
    private int[] maxlen;
    private int[] preflen;
    private int[] sufflen;
    private char[] prefchar;
    private char[] suffchar;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        int k = queryIndices.length;
        char[] cha = s.toCharArray();
        maxlen = new int[4*n];
        preflen = new int[4*n];
        sufflen = new int[4*n];
        prefchar = new char[4*n];
        suffchar = new char[4*n];

        build(1, 0, n-1, cha);
        int[] ans = new int[k];
        for(int i=0;i<k;i++){
            int idx = queryIndices[i];
            char ch = queryCharacters.charAt(i);
            update(1, 0, n-1, idx, ch);
            ans[i] = maxlen[1];
        }
        return ans;
    }
    private void build(int node, int start, int end, char[] cha){
        if(start == end){
            maxlen[node] = 1;
            preflen[node] = 1;
            sufflen[node] = 1;
            prefchar[node] = cha[start];
            suffchar[node] = cha[start];
            return;
        }
        int mid = start+(end-start)/2;
        int left = 2*node;
        int right = 2*node+1;

        build(left, start, mid, cha);
        build(right, mid+1, end, cha);

        combine(node, left, right, mid-start+1, end-mid);
    }

    private void update(int node, int start, int end, int idx, char ch){
        if(start == end){
            prefchar[node] = ch;
            suffchar[node] = ch;
            return;
        }
        int mid = start + (end-start)/2;
        int left = 2*node;
        int right = 2*node+1;
        if(idx <= mid)
            update(left, start, mid, idx, ch);
        else
            update(right, mid+1, end, idx, ch);
        combine(node, left, right, mid-start+1, end-mid);
    }

    private void combine(int node, int left, int right, int lenL, int lenR){
        prefchar[node] = prefchar[left];
        preflen[node] = preflen[left];
        if(preflen[left] == lenL && prefchar[left] == prefchar[right])
            preflen[node] += preflen[right];

        suffchar[node] = suffchar[right];
        sufflen[node] = sufflen[right];
        if(sufflen[right] == lenR && suffchar[right] == suffchar[left])
            sufflen[node] += sufflen[left];

        maxlen[node] = Math.max(maxlen[left], maxlen[right]);
        if(suffchar[left] == prefchar[right])
            maxlen[node] = Math.max(maxlen[node], sufflen[left]+preflen[right]);
    }
}