package 数据结构与算法.第二遍.回溯与递归;

public class 矩阵中的路径 {
public static boolean hashPath(char[] arr,int rows,int cols,char[] str){
    if (arr == null || arr.length != rows * cols || str == null || str.length == 0){
        return false;
    }
    boolean[] visited = new boolean[rows * cols];//默认为false
    int[] pathLength = {0};
    for (int i = 0;i < rows;i++){
        for (int j = 0;j < cols;j++){
            if (hashPathCore(arr,rows,cols,str,visited,i,j,pathLength)){
                return true;
            }
        }
    }

    return false;
}

    private static boolean hashPathCore(char[] arr,int rows,int cols,char[] str,boolean[] visited,int cur_row,
                                        int cur_col, int[] pathLength){
    boolean hashPath = false;

    int cur = cur_row * cols + cur_col;//将当前位置“拉直”，这是常用伎俩，即为“当前行*总列数+当前列”

    if (pathLength[0] == str.length){
        return true;
    }
if (cur_row > 0 && cur_row < rows || cur_col > 0 && cur_col < cols && arr[cur] == str[pathLength[0]] && ! visited[cur]){
    visited[cur] = true;
    pathLength[0]++;
    hashPath = hashPathCore(arr,rows,cols,str,visited,cur_row,cur_col-1,pathLength)
            || hashPathCore(arr,rows,cols,str,visited,cur_row,cur_col+1,pathLength)
            || hashPathCore(arr,rows,cols,str,visited,cur_row -1,cur_col,pathLength)
            || hashPathCore(arr,rows,cols,str,visited,cur_row +1,cur_col,pathLength);
    //若该位置的上下左右都没有找到，则回溯，即把这个位置重新标记为未访问过，同时将满足条件的字符串减1即可
    if (!hashPath){
        pathLength[0]--;
        visited[cur] = false;
    }
}
//否则，就表示至少有一条路径合适，于是返回hashPath或true
    return hashPath;
    }
}
