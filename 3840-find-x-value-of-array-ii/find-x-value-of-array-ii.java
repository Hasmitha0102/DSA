class Solution {
    static class Node{
        int prod;
        int[] cnt;
        Node(int k){
            this.prod = 1;
            this.cnt = new int[k];
        }
    }
    private int n;
    private int k;
    private Node[] tree;
    private Node merge(Node left, Node right){
        Node res = new Node(k);
        res.prod = (int) ((1L * left.prod * right.prod)%k);
        for(int r=0;r<k;r++){
            res.cnt[r] = left.cnt[r];
        }
        for(int r=0;r<k;r++){
            if(right.cnt[r] > 0){
                int combinedmod = (int) ((1L * left.prod * r)%k);
                res.cnt[combinedmod] += right.cnt[r];
            }
        }
        return res;
    }
    private void build(int node, int l, int r, int[] nums){
        if(l==r){
            tree[node] = new Node(k);
            int valmod = nums[l]%k;
            tree[node].prod = valmod;
            tree[node].cnt[valmod] = 1;
            return;
        }
        int mid = l+(r-l)/2;
        build(2*node, l, mid, nums);
        build(2*node+1, mid+1, r, nums);
        tree[node] = merge(tree[2*node], tree[2*node+1]);
    }
    private void update(int node, int l, int r, int idx, int val){
        if(l == r){
            int valmod = val%k;
            tree[node].prod = valmod;
            java.util.Arrays.fill(tree[node].cnt, 0);
            tree[node].cnt[valmod] = 1;
            return;
        }
        int mid = l+(r-l)/2;
        if(idx <= mid){
            update(2*node, l, mid, idx, val);
        }
        else{
            update(2*node+1, mid+1, r, idx, val);
        }
        tree[node] = merge(tree[2*node], tree[2*node+1]);
    }
    private Node query(int node, int l, int r, int ql, int qr){
        if(ql <= l && r <= qr){
            return tree[node];
        }
        int mid = l+(r-l)/2;
        if(qr <= mid){
            return query(2*node, l, mid, ql, qr);
        }
        if(ql > mid){
            return query(2*node+1, mid+1, r, ql, qr);
        }
        Node leftres = query(2*node, l, mid, ql, qr);
        Node rightres = query(2*node+1, mid+1, r, ql, qr);
        return merge(leftres, rightres);
    }
    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;
        this.tree = new Node[4*n];
        build(1, 0, n-1, nums);
        int[] ans = new int[queries.length];
        for(int i=0;i<queries.length;i++){
            int idx = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];
            update(1, 0, n-1, idx, val);
            Node res = query(1, 0, n-1, start, n-1);
            ans[i] = res.cnt[x];
        }
        return ans;
    }
}