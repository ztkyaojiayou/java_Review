package 数据结构与算法.LeetCode题解.二叉树;

import javax.swing.tree.TreeNode;

/**
 * 538. 把二叉搜索树转换为累加树
 * 给定一个二叉搜索树（Binary Search Tree），
 * 把它转换成为累加树（Greater Tree)，
 * 使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
 *
 * 例如：
 * 输入: 原始二叉搜索树:
 *               5
 *             /   \
 *            2     13
 * 输出: 转换为累加树:
 *              18
 *             /   \
 *           20     13
 */
class TreeNode538 {
    int val;
    TreeNode538 left;
    TreeNode538 right;
    TreeNode538(int x) { val = x; }
}
/**
 * 思路解析：
 * 通过观察累加前的中序遍历与累加后中序遍历，我们会发现，其实后者的每一个元素就是前者的每一个元素的向后累加的结果。（真的妙啊）
 * 那问题就迎刃而解了，我们只需反向中序遍历即可,并把每次的节点值进行累加，就能得到最终的累加树。
 * 而且这样保证了我们对每个节点只访问了一次。
 */
class 把二叉搜索树转化为累加树538 {
        int sum = 0;
        public TreeNode538 convertBST(TreeNode538 root) {
            if(root != null){
                convertBST(root.right);
                sum = sum + root.val;
                root.val = sum;
                convertBST(root.left);
            }
            return root;
        }
    }

    //写法2：递归的标准写法如下：
class solution538{
    private int sum;
    public TreeNode538 convertBST(TreeNode538 root) {
        sum = 0;
        //调用递归函数
        convert(root);
        //返回新二叉树的根节点即可
        return root;
    }
    //递归函数
    private void convert(TreeNode538 root){
        if(root == null) {
            return;
        }
        convert(root.right);
        sum = sum + root.val;
        root.val = sum;
        convert(root.left);
    }
}
