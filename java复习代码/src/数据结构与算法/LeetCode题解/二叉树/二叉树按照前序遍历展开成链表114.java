package 数据结构与算法.LeetCode题解.二叉树;

/**
 * 114. 二叉树展开为链表
 * 给定一个二叉树，原地将它展开为一个单链表,顺序与前序遍历相同。
 *
 * 例如，给定二叉树
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 *
 * 将其展开为：
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 */
class TreeNode114 {
    int val;
    TreeNode114 left;
    TreeNode114 right;
    TreeNode114(int x) { val = x; }
}

/**
 * 解析：使用递归
 * 其实是分为三步：
 * (1)首先将根节点的左子树变成链表
 * (2)其次将根节点的右子树变成链表
 * (3)最后将变成链表的右子树放在变成链表的左子树的最右边
 *
 * 这就是一个递归的过程，递归的一个非常重要的点就是：
 * 不去管函数的内部细节是如何处理的，我们只看其函数作用以及输入与输出。对于函数flatten来说：
 *
 * 函数作用：将一个二叉树，原地将它展开为链表
 * 输入：树的根节点
 * 输出：无
 * 那我们就直接根据三步来写程序就可以了
 */
public class 二叉树按照前序遍历展开成链表114 {
        public void flatten(TreeNode114 root) {
            //递归结束的条件
            if(root == null){
                return ;
            }
            //先将根节点的左子树变成链表
            flatten(root.left);
            //再将根节点的右子树变成链表，并先保存/挂在一个临时的节点下，
            //等左边的链表挂在树的右边之后，再把该右链表挂在最后面即可
            flatten(root.right);
            TreeNode114 temp = root.right;//临时保存
            //把左边的链表挂在树的右边
            root.right = root.left;
            //记得要将左边置空（切记）
            root.left = null;
            //找到树的最右边的节点
            while(root.right != null){
                //只要不是空节点，就一直向右遍历，直到找到最右边那个节点
                root = root.right;
            }
            //把右边的链表接到刚才树的最右边的节点
            root.right = temp;
        }
    }
