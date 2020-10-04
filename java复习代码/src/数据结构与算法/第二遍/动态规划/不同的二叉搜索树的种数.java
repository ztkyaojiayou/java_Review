package 数据结构与算法.第二遍.动态规划;

/**
 *  * 综合两个公式可以得到如下递推公式/状态方程（这其实就是所谓的卡特兰数）
 *  * G(n) = G(0)*G(n-1)+G(1)*(n-2)+...+G(n-1)*G(0)
 *  * 即累加，即双重循环
 */
public class 不同的二叉搜索树的种数 {
    public int numTrees(int n){
        //1.定义dp数组，
        // dp[i]就表示i个节点存在的二叉搜索树的个数，则易知dp[n]即为所求。
        int[] dp = new int[n + 1];
        //2.确定初始值
        dp[0] = 1;
        dp[1] = 1;
        //3.一般情况
        for (int i=2;i<n+1;i++){
            for (int j = 1;j<i+1;j++){
                dp[i] += dp[j-1] * dp[i-j];
            }
        }
        //4.返回结果
        return dp[n];
    }
}
