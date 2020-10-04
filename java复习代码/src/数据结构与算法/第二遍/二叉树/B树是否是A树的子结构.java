package 数据结构与算法.第二遍.二叉树;

public class B树是否是A树的子结构 {
public boolean HasSubtree(TreeNode rootA,TreeNode rootB){
    //1.若有一颗树为空，则构不成子树
    if (rootA == null || rootB == null){
        return false;
    }
    //2.若当前节点对应相等，则再判断其左右子节点是否对应相等
    if (rootA.val == rootB.val){
        if (isSubtree(rootA,rootB)){
            return true;
        }
    }
    //3.否则，说明以根节点为起点的A树无法找到和B树一样的结构，于是就转战其左右子节点，依次判断即可
        boolean res = HasSubtree(rootA.left, rootB) || HasSubtree(rootA.right, rootB);
        return res;

}
//递归，判断当两个根节点相等时，剩下的节点是否也相等，若都相等，则表示B树为A树的一个子结构
    private boolean isSubtree(TreeNode rootA, TreeNode rootB) {
    if (rootB == null){
        return true;
    }
    if (rootA == null){
        return false;
    }
    //此时若二者的值相等，则再判断其左右子节点是否对应相等即可
        if (rootA.val == rootB.val){
            boolean res = isSubtree(rootA.left, rootB.left) && isSubtree(rootA.right, rootB.right);
            return res;
        }
      return false;
    }
}
