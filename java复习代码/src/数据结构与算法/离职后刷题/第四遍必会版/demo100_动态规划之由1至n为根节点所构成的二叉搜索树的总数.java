package 数据结构与算法.离职后刷题.第四遍必会版;

public class demo100_动态规划之由1至n为根节点所构成的二叉搜索树的总数 {
    public int numTrees(int n) {
        int[] dp = new int[n];
        dp[0] = 1;//表示只有节点1时的数目，易知只有一种
        dp[1] = 1;//表示只有节点1和2时的数目，易知也只有一种
        for (int i = 2;i<n;i++){
            for (int j = 1;j<i;j++){
                dp[i] += dp[j-1] * dp[i-j];//累加
            }
        }
        return dp[n];
    }
}
