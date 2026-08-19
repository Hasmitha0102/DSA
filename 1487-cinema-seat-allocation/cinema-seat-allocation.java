class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rowmasks = new HashMap<>();
        for(int[] seat : reservedSeats){
            int row = seat[0];
            int col = seat[1];
            if(col >= 2 && col <= 9)
                rowmasks.put(row, rowmasks.getOrDefault(row, 0) | (1 << col));
        }

        int lm = (1 << 2) | (1 << 3) | (1 << 4) | (1 << 5);
        int rm = (1 << 6) | (1 << 7) | (1 << 8) | (1 << 9);
        int mm = (1 << 4) | (1 << 5) | (1 << 6) | (1 << 7);
        int max = 2*(n-rowmasks.size());
        for(int mask : rowmasks.values()){
            boolean lf = (mask & lm) == 0;
            boolean rf = (mask & rm) == 0;
            boolean mf = (mask & mm) == 0;
            if(lf && rf)
                max += 2;
            else if(lf || rf || mf)
                max += 1;
        }
        return max;
    }
}