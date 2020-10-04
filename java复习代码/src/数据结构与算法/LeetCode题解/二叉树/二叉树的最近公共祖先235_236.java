package 数据结构与算法.LeetCode题解.二叉树;


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */


/**
 * 235. 二叉搜索树的最近公共祖先（入门版）
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
 * 示例 1:
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * 输出: 6
 * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
 *
 * 示例 2:
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * 输出: 2
 * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
 *
 * 说明:
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉搜索树中。
 */

/**
 * 解析：这道题和236题是相似的，只是上一题的二叉树是一颗普通的二叉树，
 * 而这里的二叉树是一颗搜索树，其性质更明显，因此更简单。
 * 先回忆一下二叉搜索树（BST)的基本性质：
 * （1）节点 N 左子树上的所有节点的值都小于等于节点 N 的值
 * （2）节点 N 右子树上的所有节点的值都大于等于节点 N 的值
 * （3）左子树和右子树也都是 二叉搜索树(BST)
 *
 * 思路：
 * 从根节点开始遍历树
 * 如果节点 p 和节点 q 都在右子树上，那么以右孩子为根节点继续 1 的操作
 * 如果节点 p 和节点 q 都在左子树上，那么以左孩子为根节点继续 1 的操作
 * 如果条件 2 和条件 3 都不成立，这就意味着我们已经找到节 p 和节点 q 的 最近公共祖先 了
 */
class 二叉搜索树的最近公共祖先235 {
    public TreeNode236 lowestCommonAncestor(TreeNode236 root, TreeNode236 p, TreeNode236 q) {
        //1.递归结束的条件
        if(root == null || root.val == p.val || root.val == q.val){
            return root;
        }
        //2.通过递归求出其左子树和右子树的最近公共祖先left和right
        TreeNode236 left = lowestCommonAncestor(root.left,p,q);
        TreeNode236 right = lowestCommonAncestor(root.right,p,q);
        //3.开始对递归结果进行讨论,由于是二叉搜索树，因此其结果只有三种情况，即：
        // 3.1要么根节点比p和q都大，此时说明p和q都分布在根节点的左边，则最近公共祖先肯定在左边
        // 3.2要么比他们俩小，此时说明p和q都分布在根节点的右边，则最近公共祖先肯定在右边
        // 3.3再要么比其中一个大，比另外一个小，此时说明p和q分布在根节点的两侧，则最近公共祖先肯定就是根节点自己嘛。
        //3.1 当都分布在左侧时
        if(root.val>p.val && root.val>q.val) return left;
        //3.2 当都分布在右侧时
        if(root.val<p.val && root.val<q.val) return right;
        //3.3 当分布在两侧时
        return root;
    }
}



/**
 * 236. 二叉树的最近公共祖先(较难）
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，
 * 最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]

 * 示例 1:
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出: 3
 * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 *
 * 示例 2:
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出: 5
 * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。
 * 因为根据定义最近公共祖先节点可以为节点本身。
 */
class TreeNode236 {
    int val;
    TreeNode236 left;
    TreeNode236 right;
    TreeNode236(int x) { val = x; }
}

/**
 * 思路解析：使用递归，最近公共祖先就是在他们的公共祖先节点中找一个最近的节点。
 * 首先把概念理清楚：
 * 祖先的定义： 若节点 p 在节点 root 的左（右）子树中，或 p = root，则称 root 是 p 的祖先。
 *  最近公共祖先的定义： 设节点 root 为节点 p,q 的某公共祖先，
 *  若其左子节点 root.left 和右子节点 root.right 都不是 p,q 的公共祖先，则称 root 是 “最近的公共祖先” 。
 *
 * 根据以上定义，若 root 是 p,q 的 最近公共祖先 ，则只可能为以下情况之一：
 * p 和 q 在 root 的子树中，且分列 root 的 异侧（即分别在左、右子树中）；
 * p = root ，且 q 在 root 的左或右子树中；
 * q = root ，且 p 在 root 的左或右子树中；
 考虑通过递归对二叉树进行后序遍历，当遇到节点 p 或 q 时返回。
 从底至顶回溯，当节点 p,q 在节点 root 的异侧时，节点 root 即为最近公共祖先，则向上返回 root 。

 （1）解析版本1：
 递归解析：
 终止条件：
 当越过叶节点，则直接返回 null ；
 当 root 等于 p,q ，则直接返回 root ；
 递推工作：
 开启递归左子节点，返回值记为 left ；
 开启递归右子节点，返回值记为 right ；
 返回值： 根据 left 和 right ，可展开为四种情况；
 当 left 和 right 同时为空 ：说明 root 的左 / 右子树中都不包含 p,q ，返回 null ；
 当 left 和 right 同时不为空 ：说明 p,q 分列在 root 的 异侧 （分别在 左 / 右子树），因此 root 为最近公共祖先，返回 root ；
 当 left 为空 ，right 不为空 ：p,q 都不在 root 的左子树中，直接返回 right 。具体可分为两种情况：
 p,q 其中一个在 root 的 右子树 中，此时 right 指向 pp（假设为 pp ）；
 p,q 两节点都在 root 的 右子树 中，此时的 right 指向 最近公共祖先节点 ；
 当 left 不为空 ， right 为空 ：与情况 3. 同理；

 * （2）解析版本2：
 * 对于递归函数lowestCommonAncestor：
 * 函数作用：找到该树中两个指定节点的最近公共祖先
 * 输入：该树的根节点，两个指定节点
 * 输出：两个指定节点的最近公共祖先
 *
 * 这里对于一棵树中，两个指定节点必定分别分布在其最近公共祖先的左右子树中。
 * 由于题目中说一个节点也可以是它自己的祖先。
 * 那我们先可以求出其左子树的最近公共祖先和右子树的最近公共祖先。
 *
 * TreeNode left = lowestCommonAncestor(root.left,p,q);
 * TreeNode right = lowestCommonAncestor(root.right,p,q);
 * 如果left或right为null，说明p和q并不存在其左或右子树中。所以我们可以分为以下4种情况：
 *
 * left == null 并且 right == null 说明，p与q根本不存在公共祖先。那直接返回null；
 * left == null 并且 right ！= null 。说明其公共祖先就是 right；
 * right == null 并且 left ！= null 。说明其公共祖先就是 left；
 * right ！= null 并且 left ！=null 。说明right == left = root，return root。
 * 根据上述逻辑即可解决问题。
 */
class 二叉树的最近公共祖先236 {
        public TreeNode236 lowestCommonAncestor(TreeNode236 root, TreeNode236 p, TreeNode236 q) {
            //1.递归结束的条件，如果根节点为空，或者其中一个节点的值和根节点相等，就返回根节点
            if(root == null || root.val == p.val || root.val == q.val){
                return root;
            }
            //2.通过递归求出这两个节点的左子树和右子树的最近公共祖先left和right
            TreeNode236 left = lowestCommonAncestor(root.left,p,q);
            TreeNode236 right = lowestCommonAncestor(root.right,p,q);
            //3.再对递归结果进行分类讨论：
            //3.1都没有找到，则说明根本不存在公共祖先，直接返回null
            if(left == null && right == null) return null;
            //3.2若在左子树中没有找到，那一定在右子树中
            if(left == null) return right;
            //3.3若在右子树中没有找到，那一定在左子树中
            if(right == null) return left;
            //3.4对于其他情况，即当left和right分布在根节点的异侧时，那就说明其最近公共祖先就是根节点root
            return root;
        }
    }







