package 数据结构与算法.第三遍;

public class demo105_递归之岛屿数量 {
    public int numIslands(char[][] nums) {
        int count = 0;
        int rows = nums.length;
        int cols = nums[0].length;
        for (int i = 0;i<rows;i++){
            for (int j = 0;j<cols;j++){
                if (nums[i][j] == '1'){//前提
                    dfs(nums,i,j);
                    count++;
                }
            }
        }
        return count;
    }
//深度优先搜索/递归
    private void dfs(char[][] nums, int i, int j) {
        if (i<0 || i> nums.length ||j<0 || j>nums[0].length || nums[i][j] == '0'){
            return;
        }
        nums[i][j] = '0';
        dfs(nums,i+1,j);
        dfs(nums,i,j+1);
        dfs(nums,i-1,j);
        dfs(nums,i,j-1);
    }
}
