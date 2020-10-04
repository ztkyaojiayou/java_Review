package 数据结构与算法.第二遍.二叉树;

import java.util.ArrayList;
import java.util.List;

public class 二叉树的路径和 {
    //（1）入门版：判断该路径是否存在
    //方法：使用递归
    public boolean hashPath01(TreeNode root, int sum){
        //特判
        if (root == null){
            return false;
        }
        //递归结束的条件
        if (root.val == sum && root.left == null && root.right == null){
            return true;
        }
        boolean left = hashPath01(root.left, sum - root.val);
        boolean right = hashPath01(root.right, sum - root.val);
        boolean res = left || right;
        return res;
    }

    //（2）进阶版：拿到这些路径
    //方法：使用递归
    public List<List<Integer>> PathSum02(TreeNode root, int sum){
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        //递归
        recur(res,path,root,sum);
        return res;
    }
//具体的递归方法(很标准的递归步骤）
    private void recur(List<List<Integer>> res, List<Integer> path, TreeNode root, int sum) {
        if (root == null){
            return;
        }
        //做选择
        path.add(root.val);
        //剪枝
        if (root.val == sum && root.left == null && root.right == null){
            res.add(new ArrayList<>(path));
        }
        //递归
        recur(res,path,root.left,sum - root.val);
        recur(res,path,root.right,sum - root.val);
        //回退
        path.remove(path.size() - 1);
    }

    //（3）高阶版：统计符合要求的总路径数（可以不从根节点开始）
    //方法：同样是使用递归
    public int pathSum(TreeNode root, int sum){
        if (root == null){
            return 0;
        }
        int curSum = countPath(root, sum);
        int leftSum = pathSum(root.left, sum);
        int rightSum = pathSum(root.right, sum);
        return curSum + leftSum + rightSum;
    }
//具体的递归方法
    private int countPath(TreeNode root, int sum) {
        int res = 0;
        if (root == null){
            return 0;
        }
     sum = sum - root.val;
        if (sum == 0){
            res = 1;
        }
        int left_node = countPath(root.left, sum);
        int right_node = countPath(root.right, sum);
        //统计结果
        res = res + left_node + right_node;
        return res;
    }
}
