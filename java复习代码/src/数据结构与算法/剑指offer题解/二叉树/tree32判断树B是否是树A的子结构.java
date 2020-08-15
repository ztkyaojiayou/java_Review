package 数据结构与算法.剑指offer题解.二叉树;

/**
 * 题目：输入两棵二叉树 A 和 B，判断 B 是不是 A 的子结构。
 * （注意：我们约定，空数不是任意一颗树的子结构；
 * 子结构不等于子树，子结构的判定没有那么严格，即只要A中有B的结构就行）
 *
 * 【解】：
 *  要找到A树中是否存在和B树结构一样的子树，我们可以分为两步来求解：
 * （1）先在树A中找到和B的根节点的值相同的结点R
 * （2）再判树A中以R为根节点的子树是不是包含和树B一样的结构
 *
 * 1.首先需要判断A,B的根节点是否一样。
 * 2.如果不一样，再判断A的左孩子和B的根节点是否一样，
 * 同理可再判断A的右孩子和B的根节点是否一样，依次找下去
 * 如果上述情况都不满足则说明不包含
 * 1.如果找到了A中有值和B中的根节点相同，则比较左右子树是否相同。
 * 2.如果B为空了，则说明包含
 * 3.如果A为空了，则说明不包含
 */
class TreeNode32 {
    int val = 0;
    TreeNode32 left = null;
    TreeNode32 right = null;

    public TreeNode32(int val) {
        this.val = val;

    }
}
public class tree32判断树B是否是树A的子结构 {

    //遍历大树
    //1.先使用HasSubtree方法：用于在树A中找到和B的根节点的值相同的结点
    //（1）首先需要判断A,B的根节点是否一样。
    //（2）若不一样，则通过递归思想再判断A的左孩子和B的根节点是否一样，同理可判断A的右孩子和B的根节点是否一样。依次找下去
    //（3）如果上述情况都不满足则说明不包含
    public boolean HasSubtree(TreeNode32 rootA,TreeNode32 rootB) {
        //（1）如果左右子树都为空，则说明不包含
        if(rootA == null || rootB == null){
            return false;
        }
        //（2）如果找到与子树B相同根的值，则进一步判断是否存在与此根节点相同的子结构，走isSubtreeWithRoot（）判断方法
        if(rootA.val == rootB.val){
            if(isSubtreeWithRoot(rootA,rootB)){
                return true;
            }
        }
        //（3）若二者不相等，则通过递归思想再判断A的左孩子和B的根节点是否一样
        //     同理再判断A的右孩子和B的根节点是否一样，依次递归找下去即可
        return HasSubtree(rootA.left, rootB) || HasSubtree(rootA.right, rootB);
    }

    //2.再使用isSubtreeWithRoot方法：用于判断当在大树A中找到了一个和子树B相同的根节点后判断是否存在与此根节点相同的子结构
    // （1）若子结构B为空了，则说明包含
    // （2）若大树A为空了，则说明不包含
    //  (3)如果找到了A中有值和B中的根节点相同，则使用递归比较左右子树是否相同。
    public boolean isSubtreeWithRoot(TreeNode32 rootA, TreeNode32 rootB) {
        // （1）若子结构B为空了，则说明包含
        if(rootB == null){
            return true;
        }
        // （2）若大树A为空了，则说明不包含
        if(rootA == null){
            return false;
        }
        //（3）相等后判断左右孩子
        if(rootA.val == rootB.val){
            return isSubtreeWithRoot(rootA.left, rootB.left) && isSubtreeWithRoot(rootA.right, rootB.right);
        }
        return false;
    }
}
