package 数据结构与算法.第二遍.回溯与递归;

public class 回溯_单词搜索 {
    int m;
    int n;
    boolean[][] visited;
    private int[][] direction = {{-1,0},{0,-1},{0,1},{1,0}};
    private String word;
    private char[] board;

    public boolean exist(char[][] board,String word){
        m= board.length;
        n = board[0].length;
        if (m==0){
            return false;
        }
        visited = new boolean[m][n];
        for (int i = 0;i<m;i++){
            for (int j = 0;j<n;j++){
                if (dfs(i,j,0,board,word)){
                    return true;
                }
            }
        }
        return false;
    }

    //递归方法
    private boolean dfs(int i, int j, int start, char[][] board, String word) {
        if (start == word.length()-1){
            return board[i][j] == word.charAt(start);
        }
        //做选择
        if (board[i][j] == word.charAt(start)){
            visited[i][j] = true;
            for (int k= 0;k<4;k++){
                int newX = i+direction[k][0];
                int newY = j+direction[k][1];
                if (inArea(newX,newY) && !visited[newX][newY]){
                    if (dfs(newX,newY,start+1,board,word)){
                        return true;
                    }
                }
            }
            visited[i][j] = false;
        }
        return false;
    }
//判断当前位置是否合法，即是否在区域内
    private boolean inArea(int x, int y) {
        return x>=0 && x<m && y>=0 && y<n;
    }
}
