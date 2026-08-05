class Solution {
    public int countRatioSubarrays(int[] nums, int a, int b) {
        int n = nums.length;
        long[] prefsum = new long[n+1];
        int[] lastodd = new int[n+1];
        lastodd[0] = -1;
        for(int i=0;i<n;i++){
            boolean iseven = (nums[i] % 2 == 0);
            prefsum[i+1] = prefsum[i] + (iseven ? b:-a);
            lastodd[i+1] = iseven ? lastodd[i] :i;
        }
        Set<Long> set = new HashSet<>();
        for(long val : prefsum)
            set.add(val);
        List<Long> sortedvals = new ArrayList<>(set);
        Collections.sort(sortedvals);

        Map<Long, Integer> rankmap = new HashMap<>();
        for(int i=0;i<sortedvals.size();i++)
            rankmap.put(sortedvals.get(i),i+1);
        int m = sortedvals.size();
        FenwickTree bit = new FenwickTree(m);
        long totalcount = 0;
        int left = 0;
        for(int right = 1; right <= n;right++){
            int validBound = lastodd[right];
            while(left<=validBound){
                bit.add(rankmap.get(prefsum[left]),1);
                left++;
            }
            if(validBound != -1){
                int rank = rankmap.get(prefsum[right]);
                totalcount += bit.queryFrom(rank);
            }
        }
        return(int) totalcount;
    }
    private static class FenwickTree{
        private final int[] tree;
        public FenwickTree(int size){
            tree = new int[size+1];
        }
        public void add(int index,int delta){
            for(;index < tree.length; index += index & -index){
                tree[index] += delta;
            }
        }
        public int query(int index){
            int sum = 0;
            for(; index>0;index-=index & -index){
                sum += tree[index];
            }
            return sum;
        }
        public int queryFrom(int rank){
            return query(tree.length - 1) - query(rank-1);
        }
    }
}