package 数据结构与算法.第三遍;


import 数据结构与算法.TreeNode;

public class demo21_判断树B是否是树A的子结构 {
    public boolean HasSubtree(TreeNode rootA, TreeNode rootB) {
        if (rootA == null ||rootB == null){
            return false;
        }
        if (rootA.val == rootB.val){
            if (isSame(rootA,rootB)){
                return true;
            }
        }
        return HasSubtree(rootA.left,rootB) || HasSubtree(rootA.right,rootB);
    }

    private boolean isSame(TreeNode rootA, TreeNode rootB) {
        if (rootA == null){
            return false;
        }
        if (rootB == null){
            return true;
        }
        if (rootA.val == rootB.val){
            //递归地，左节点和左节点比较，右节点和右节点比较
            return isSame(rootA.left,rootB.left) && isSame(rootA.right,rootB.right);
        }
        return false;
    }
}
