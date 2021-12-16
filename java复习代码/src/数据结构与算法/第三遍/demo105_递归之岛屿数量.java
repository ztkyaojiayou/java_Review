package 数据结构与算法.第三遍;

public class demo105_递归之岛屿数量 {
    public int numIslands(char[][] nums) {
        int count = 0;
        int rows = nums.length;
        int cols = nums[0].length;
        //两层循环，表示从原点（0,0）开始搜索
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (nums[i][j] == '1') {//前提
                    if(dfs(nums, i, j));//若为真，就表示找到了一块岛屿，于是就加1
                    count++;
                }
            }
        }
        return count;
    }

    //深度优先搜索/递归
    private boolean dfs(char[][] nums, int i, int j) {
        //递归出口
        if (i < 0 || i > nums.length || j < 0 || j > nums[0].length || nums[i][j] == '0') {
            return true;
        }
        //做选择，即把当前位置置为“水”
        nums[i][j] = '0';
        //下一步递归（四个位置同时搜索）
        //有一个方向成功就行，即找到了岛屿
        boolean res = dfs(nums, i + 1, j)
                || dfs(nums, i, j + 1)
                || dfs(nums, i - 1, j)
                || dfs(nums, i, j - 1);
        //再判断结果
        if (res){
            return true;
        }
        return false;
        //不用撤销，因为这不是求区域本身，只需判断
    }
}
