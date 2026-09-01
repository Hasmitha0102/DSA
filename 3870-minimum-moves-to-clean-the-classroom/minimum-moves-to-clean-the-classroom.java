class Solution {
    static class State{
        int r, c, energy, mask, moves;
        State(int r, int c, int energy, int mask, int moves){
            this.r = r;
            this.c = c;
            this.energy = energy;
            this.mask = mask;
            this.moves = moves;
        }
    }
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        int sr=0, sc=0;
        int littercount = 0;
        int[][] litterId = new int[m][n];
        for(int[] row : litterId){
            Arrays.fill(row, -1);
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                char ch = classroom[i].charAt(j);
                if(ch == 'S'){
                    sr = i;
                    sc = j;
                }
                else if(ch == 'L'){
                    litterId[i][j] = littercount++;
                }
            }
        }
        if(littercount == 0){
            return 0;
        }

        int allcollected = (1 << littercount)-1;

        boolean[][][][] visited = new boolean[m][n][energy+1][1 << littercount];
        Queue<State> q = new LinkedList<>();
        q.offer(new State(sr, sc, energy, 0, 0));
        visited[sr][sc][energy][0] = true;
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};
        while(!q.isEmpty()){
            State cur = q.poll();
            if(cur.mask == allcollected){
                return cur.moves;
            }
            for(int d=0;d<4;d++){
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if(nr < 0 || nr >= m || nc < 0 || nc >= n){
                    continue;
                }
                if(classroom[nr].charAt(nc) == 'X'){
                    continue;
                }
                if(cur.energy == 0){
                    continue;
                }
                int newenergy = cur.energy - 1;
                int newmask = cur.mask;
                if(classroom[nr].charAt(nc) == 'L'){
                    int id = litterId[nr][nc];
                    newmask |= (1 << id);
                }
                if(classroom[nr].charAt(nc) == 'R'){
                    newenergy = energy;
                }
                if(!visited[nr][nc][newenergy][newmask]){
                    visited[nr][nc][newenergy][newmask] = true;
                    q.offer(new State(nr, nc, newenergy, newmask, cur.moves+1));
                }
            }
        }
        return -1;
    }
}