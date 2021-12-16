package 数据结构与算法.第三遍;

import 数据结构与算法.TreeNode;

public class demo05_7判断树B是否是树A的子结构 {
    public boolean HasSubtree(TreeNode rootA, TreeNode rootB) {
        //先以rootA为根节点，去判断是否存在和树B一样的子结构
        if (rootA.val == rootB.val){//前提，即只要当二者的根节点相等时，才有继续判断的必要
            if (isSame(rootA,rootB)){
                return true;
            }
        }
        //再以rootA.left或right为根节点，继续去判断，只要存在就行
        return HasSubtree(rootA.left,rootB) || HasSubtree(rootA.right,rootB);
    }

    private boolean isSame(TreeNode rootA, TreeNode rootB) {
        //即若比到最后，发现B树还有剩余，则说明肯定不能找到该子树
        if (rootA == null){
            return false;
        }
        //同理，即若比到最后，发现A树还有剩余，则说明肯定能找到一个子树
        if (rootB == null){
            return true;
        }
        if (rootA.val == rootB.val){//这是前提
            //递归地，左节点和左节点比较，右节点和右节点比较
            return isSame(rootA.left,rootB.left) && isSame(rootA.right,rootB.right);
        }
        return false;
    }
}
