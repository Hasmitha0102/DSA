class Solution {
    public int[] countTasks(int[] tasks, int[] shifts) {
        int n = tasks.length;
        int m = shifts.length;
        long[] prefix = new long[n+1];
        for(int i=0;i<n;i++){
            prefix[i+1] = prefix[i] + tasks[i];
        }
        int[] ans = new int[m];
        int idx = 0;
        long remaining = tasks[0];
        for(int i=0;i<m;i++){
            long time = shifts[i];
            if(time<remaining){
                remaining -= time;
                ans[i] = n-idx;
                continue;
            }
            time -= remaining;
            idx++;
            if(idx == n){
                ans[i] = 0;
                idx = 0;
                remaining = tasks[0];
                continue;
            }
            long completed = prefix[idx];
            long target = completed + time;
            int completedtasks = upperBound(prefix, target)-1;
            if(completedtasks >= n){
                ans[i] = 0;
                idx = 0;
                remaining = tasks[0];
            }
            else{
                idx = completedtasks;
                remaining = tasks[idx] - (target - prefix[idx]);
                ans[i] = n-idx;
            }
        }
        return ans;
    }
    private int upperBound(long[] prefix, long value){
        int low = 0;
        int high = prefix.length;
        while(low<high){
            int mid = low + (high-low)/2;
            if(prefix[mid] <= value){
                low = mid+1;
            }
            else{
                high = mid;
            }
        }
        return low;
    }
}