class Solution {
    static class Interval{
        int l, r, weight, id;
        Interval(int l, int r, int weight, int id){
            this.l = l;
            this.r = r;
            this.weight = weight;
            this.id = id;
        }
    }
    static class Result{
        long weight;
        List<Integer> indices;
        Result(long weight, List<Integer> indices){
            this.weight = weight;
            this.indices = indices;
        }
        boolean isbetterthan(Result other){
            if(other == null) return true;
            if(this.weight != other.weight){
                return this.weight > other.weight;
            }
            int len = Math.min(this.indices.size(), other.indices.size());
            for(int i=0;i<len;i++){
                if(!this.indices.get(i).equals(other.indices.get(i))){
                    return this.indices.get(i) < other.indices.get(i);
                }
            }
            return this.indices.size() < other.indices.size();
        }
    }
    public int[] maximumWeight(List<List<Integer>> intervalsList) {
        int n = intervalsList.size();
        Interval[] intervals = new Interval[n];
        for(int i=0;i<n;i++){
            List<Integer> it = intervalsList.get(i);
            intervals[i] = new Interval(it.get(0), it.get(1), it.get(2), i);
        }
        Arrays.sort(intervals, (a,b) -> Integer.compare(a.r, b.r));
        int[] prev = new int[n];
        for(int i=0;i<n;i++){
            int low=0, high=i-1;
            int target = -1;
            while(low<=high){
                int mid = low+(high-low)/2;
                if(intervals[mid].r < intervals[i].l){
                    target = mid;
                    low = mid+1;
                }
                else{
                    high = mid-1;
                }
            }
            prev[i] = target;
        }
        Result[][] dp = new Result[5][n+1];
        for(int c=0;c<=4;c++){
            for(int i=0;i<=n;i++){
                dp[c][i] = new Result(0, new ArrayList<>());
            }
        }
        for(int c=1;c<=4;c++){
            for(int i=0;i<n;i++){
                Result best = dp[c][i];
                int p = prev[i];
                Result prevres = dp[c-1][p+1];
                List<Integer> newindices = new ArrayList<>(prevres.indices);
                newindices.add(intervals[i].id);
                Collections.sort(newindices);
                Result candidate = new Result(prevres.weight + intervals[i].weight, newindices);
                if(candidate.isbetterthan(best)){
                    dp[c][i+1] = candidate;
                }
                else{
                    dp[c][i+1] = best;
                }
            }
        }
        List<Integer> ansList = dp[4][n].indices;
        int[] ans = new int[ansList.size()];
        for(int i=0;i<ansList.size();i++){
            ans[i] = ansList.get(i);
        }
        return ans;
    }
}