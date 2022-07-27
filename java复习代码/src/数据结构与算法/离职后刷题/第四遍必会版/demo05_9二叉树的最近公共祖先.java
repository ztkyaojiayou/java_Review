package 数据结构与算法.离职后刷题.第四遍必会版;

import 数据结构与算法.TreeNode;

/**
 * 这题让求二叉搜索树的最近公共祖先，
 * 而二叉搜索树的特点就是 左子树的所有节点都小于当前节点，
 * 右子树的所有节点都大于当前节点，并且每棵子树都具有上述特点，
 * 所以这题就好办了，从更节点开始遍历
 * 思路：
 * 如果两个节点值都小于根节点，说明他们都在根节点的左子树上，我们往左子树上找
 * 如果两个节点值都大于根节点，说明他们都在根节点的右子树上，我们往右子树上找
 * 如果一个节点值大于根节点，一个节点值小于根节点，说明他们他们一个在根节点的左子树上一个在根节点的右子树上，
 * 那么根节点就是他们的最近公共祖先节点。
 * <p>
 * 简化：
 * 三行代码
 * 1）根结点比两个结点都大 就在左子树找
 * 2）根结点比两个结点都小 就在右子树找
 * 3）否则就返回根结点（其他情况的结果全是根节点）
 */
class demo24_二叉搜索树的最近公共祖先 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //递归结束的条件
        if (root == null || root.val == p.val || root.val == q.val) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        //再判断，分三种情况即可
        //1.若这两个节点都在左边时，则其最近公共祖先也肯定在左边
        if (root.val > p.val && root.val > q.val) {
            return left;
        }
        //2.同理，若这两个节点都在右边时，则其最近公共祖先也肯定在右边
        if (root.val < p.val && root.val < q.val) {
            return right;
        }
        //3.而若处于一左一右时，则其最近公共祖先即为root节点
        return root;
    }

    /**
     * 自写一遍
     * 简化成三行代码也可以：
     * 1）根结点比两个结点都大 就在左子树找
     * 2）根结点比两个结点都小 就在右子树找
     * 3）否则就返回根结点（其他情况的结果全是根节点）
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor02(TreeNode root, TreeNode p, TreeNode q) {

//        可不写，但为了逻辑统一，可以写
//        if (root == null || root.val == p.val || root.val == q.val) {
//            return root;
//        }

        //在左边找
        //对于搜索树，使用root.val>p.val && root.val>q.val这个条件就可以判断出此时的祖先就在左侧
        //但对于普通的二叉树，则需要递归一下以根节点的左结点再作为新的根节点来递归一下，看其是否存在子祖先
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor02(root.left, p, q);
        }
        //在右边找
        if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor02(root.right, p, q);
        }
        //其他情况的结果全是根节点（可验证）
        return root;
    }
}

/**
 * 常考：其实这个题的解法已经涵盖了上一题了，
 * 因为二叉搜索树肯定是二叉树呀！！！
 * 因此只需记住这个题即可！！！
 */
class demo24_二叉树的最近公共祖先 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //递归结束的条件
        if (root == null || root.val == p.val || root.val == q.val) {
            return root;
        }

        //递归找以root的左右结点为根节点时，该两个节点的最近公共祖先（不一定有，也因此可以做判断）
        TreeNode left_Ancestor = lowestCommonAncestor(root.left, p, q);
        TreeNode right_Ancestor = lowestCommonAncestor(root.right, p, q);
        //四种情况
        //1.若都没有，则说明不存在
        if (left_Ancestor == null && right_Ancestor == null) {
            return null;
        }
        //2.若左边没有，则肯定在右边
        if (left_Ancestor == null) {
            return right_Ancestor;
        }
        //3.若右边没有，则肯定在左边
        if (right_Ancestor == null) {
            return left_Ancestor;
        }
        //4.若左右都有，则即为root节点
        return root;
    }

    //自写一遍
    public TreeNode lowestCommonAncestor02(TreeNode root, TreeNode p, TreeNode q) {
        //递归结束的条件
        if (root == null || root.val == q.val || root.val == p.val) {
            return root;
        }
        //递归左右节点看是否有子祖先
        TreeNode leftAncestor = lowestCommonAncestor02(root.left, p, q);
        TreeNode rightAncestor = lowestCommonAncestor02(root.right, p, q);

        //开始判断是否有子祖先
        //情况1
        if (leftAncestor == null || rightAncestor == null) {
            return null;
        }
        //情况2
        if (leftAncestor == null) {
            return rightAncestor;
        }
        //情况3
        if (rightAncestor == null) {
            return leftAncestor;
        }
        //情况4：即在p和q分别在root结点的两侧，此时祖先即为root本身
        return root;
    }
}
