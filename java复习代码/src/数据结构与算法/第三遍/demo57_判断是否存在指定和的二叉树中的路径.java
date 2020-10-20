package 数据结构与算法.第三遍;
import 数据结构与算法.TreeNode;

import java.util.LinkedList;
import java.util.List;
/**
 * 入门版
 */

public class demo57_判断是否存在指定和的二叉树中的路径 {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null){
            return false;
        }
        if (root.val == sum && root.left == null && root.right == null){
            return true;
        }
        boolean left = hasPathSum(root.left, sum - root.val);
        boolean right = hasPathSum(root.right, sum - root.val);
        return left || right;//只要有一条路径即可
    }
}


/**
 * 进阶版:求这些路径
 */
class 求这些路径 {
    //结果集
    List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        //当前路径
        List<Integer> curPath = new LinkedList<>();
        method(curPath, root, sum);
        return res;
    }
//递归
    private void method(List<Integer> path, TreeNode root, int sum) {
if (root == null){
    return;
}
if (root.val == sum && root.left == null && root.right == null){
    res.add(path);
}
path.add(root.val);
//下一步递归
method(path,root.left,sum-root.val);
method(path,root.right,sum-root.val);
//撤销
        path.remove(path.size()-1);
    }
}
