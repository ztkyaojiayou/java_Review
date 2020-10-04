package 数据结构与算法.第二遍.回溯与递归;

public class 机器人的运动范围 {
    public static int MoveCount(int k,int rows,int cols){
        boolean[] visited = new boolean[rows * cols];//默认为false，表示未访问过
        return dps(k,rows,cols,0,0,visited);
    }

    private static int dps(int k, int rows, int cols, int cur_row, int cur_col, boolean[] visited) {
        int i = cur_row * cols + cur_col;//当前行*总列数+当前列
        if (cur_col < 0 || cur_col > cols || cur_row < 0 || cur_row > rows || visited[i] || !checkSum(k,cur_row,cur_col)){
            return 0;
        }
        //结果上面的if判断后，说明该位置是可以走的，因此要把该位置标记为已访问，同时使用递归，开始进行下一个位置的探索
        visited[i] = true;
        int res = dps(k,rows,cols,cur_row+1,cur_col,visited)
                + dps(k,rows,cols,cur_row -1,cur_col,visited)
                + dps(k,rows,cols,cur_row,cur_col+1,visited)
                + dps(k,rows,cols,cur_row,cur_col -1,visited) + 1;
        return res;
    }

    private static boolean checkSum(int k, int cur_row, int cur_col) {
        int sum = 0;
        //行的各位相加
        while (cur_row !=0){
            sum+=cur_row%10;
            cur_row = cur_row/10;
        }
        //再在行的各位和上加上列的各位相加
        while (cur_col != 0){
            sum+=cur_col%10;
            cur_col = cur_col/10;
        }
        if (sum>k){
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
       int res = 机器人的运动范围.MoveCount(5,12,2);
        System.out.println(res);
    }
}
