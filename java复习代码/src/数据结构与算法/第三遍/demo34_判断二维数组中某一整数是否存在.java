package 数据结构与算法.第三遍;

public class demo34_判断二维数组中某一整数是否存在 {
    public boolean Find(int target,int[][] matrix) {//array 待查找的数组,target 要查找的数
if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
    return false;
}
int cow = matrix.length;
int cols = matrix[0].length;
//从右上角开始找
int i = 0;
int j = cols-1;
while (i<=cow && j>=0){
    int cur = matrix[i][j];
    if (cur == target){
        return true;
    }else if (cur < target){
        i++;
    }else {
        j++;
    }
}
return false;
    }
}
