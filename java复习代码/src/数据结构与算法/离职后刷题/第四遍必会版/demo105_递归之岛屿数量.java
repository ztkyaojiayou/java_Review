package 数据结构与算法.离职后刷题.第四遍必会版;

public class demo105_递归之岛屿数量 {
    public int numIslands(char[][] nums) {
        int count = 0;
        int rows = nums.length;
        int cols = nums[0].length;
        //两层循环，表示从原点（0,0）开始搜索
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                //前提，当为岛屿时
                if (nums[i][j] == '1') {
                    //若为真，就表示找到了一块岛屿，于是就加1
                    if (dfs(nums, i, j)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    //深度优先搜索/递归
    private boolean dfs(char[][] nums, int i, int j) {
        //递归出口，即要么越界（要>=）要么搜索到的全是有水区域（即递归回溯到全是水，则说明符合岛屿的定义，返回true）
        if (i < 0 || i >= nums.length || j < 0 || j >= nums[0].length || nums[i][j] == '0') {
            return true;
        }
        //做选择，即把当前位置置为“水”
        nums[i][j] = '0';
        //下一步递归（四个位置同时搜索）
        //四个方向都为水才行，即找到了岛屿（是&&，而不是||，切记！！！）
        boolean res = dfs(nums, i + 1, j)
                && dfs(nums, i, j + 1)
                && dfs(nums, i - 1, j)
                && dfs(nums, i, j - 1);
        //再判断结果
        if (res) {
            return true;
        }
        return false;
        //不用撤销，因为这不是求区域本身，只需判断

    }


    //自写一遍
    public int numIslands02(char[][] nums) {
        //总岛屿数
        int count = 0;
        //行和列
        int row = nums.length;
        int col = nums[0].length;
        //循环遍历，一个一个回溯判断
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (nums[i][j] == '1') {
                    if (dfs02(nums, i, j)) {
                        //找到了岛屿，count+1
                        count++;
                    }
                }

            }
        }
        return count;
    }


    //深度优先搜索/递归
    private boolean dfs02(char[][] nums, int i, int j) {
//递归出口
        if (i < 0 || i >= nums.length || j < 0 || j >= nums[0].length || nums[i][j] == '0') {
            return true;
        }
        //走过的陆地置为0
        nums[i][j] = 0;
        //向四个方向搜索，若全（则用&&，而不是||）为水（即为0），则为岛屿
        boolean res = dfs02(nums, i + 1, j)
                && dfs02(nums, i - 1, j)
                && dfs02(nums, i, j + 1)
                && dfs02(nums, i, j - 1);
        if (res) {
            return true;
        }
        return false;
    }

}
