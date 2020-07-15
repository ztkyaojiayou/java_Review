package 数据结构与算法.LeetCode题解.二叉树;

import java.util.HashMap;
import java.util.Map;

/**
 * 105. 根据前序与中序遍历序列构造二叉树
 * 根据一棵树的前序遍历与中序遍历来构造出原二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */

 //Definition for a binary tree node.
class TreeNode105 {
      int val;
      TreeNode105 left;
      TreeNode105 right;
      TreeNode105(int x) { val = x; }
  }

/**
 * 解析：使用递归
 * （1）首先明确：
 * 1）前序遍历的形式总是:
 * [ 根节点, [左子树的前序遍历结果], [右子树的前序遍历结果] ]
 * 即根节点总是前序遍历中的第一个节点，于是我们就可以根据前序遍历的序列来找到根节点。
 * 同时，可以看出其位置关系，即左子树的所有节点是排在一起的，同样地，右子树的所有节点也是排在一起的，且在左子树后面。
 *
 * 2）而中序遍历的形式总是：
 * [ [左子树的中序遍历结果], 根节点, [右子树的中序遍历结果] ]
 * 易知，此时，根节点左边的值全是左子树的值，右边则全是右子树的值。
 *
 * （2）于是，只要我们在中序遍历中定位到根节点所在的位置，那么我们就可以分别知道左子树和右子树中的节点数目。
 * 由于同一颗子树的前序遍历和中序遍历的长度显然是相同的，因此我们就可以对应到前序遍历的结果中，（重点）
 * 对上述形式中的所有左右括号进行定位。
 * 这样一来，我们就知道了左子树的前序遍历和中序遍历结果，以及右子树的前序遍历和中序遍历结果，
 * 我们就可以递归地构造出左子树和右子树，再将这两颗子树接到根节点的左右位置。
 * 注意：递归就是不断地更换根节点和左右的边界下标即可（非常重要）
 *
 * （3）下面是其位置关系，先通过前序序列确定根节点的值（即它的第一个值），再找到根节点在中序序列中的位置，
 * 再根据一棵树的左右子树的长度是固定的，而该长度在中序序列中很好确定，
 * 即根节点前面全是左子树的值，其后面则全是右子树的值，于是就可以通过根节点的下标把长度表示出来，
 * 再反推出在前序序列中左子树和右子树的下标表示。（根据是：在前序序列中，所有左子树的值全在根节点后面，所有右子树则又全在左子树的后面，而且是连续排列的，没有交叉）
 *
 * 中序遍历（辅助/基准）
 *      左子树第一个位置   左子树最后一个位置（也是根节点前一个位置）  根节点   右子树第一个位置（也是根节点后一个位置） 右子树最后一个位置
 * 下标：inLeft                       rootIndex - 1              rootIndex     rootIndex + 1                        inRight
 *
 * 前序遍历（主要）
 *        根节点    根后（左前）一个位置          左最后一个位置                     右最前一个位置            右最后一个位置
 *  下标：preLeft       preLeft + 1     rootIndex - inLeft + preLeft    rootIndex - inLeft + preLeft + 1     preRight
 *
 * 细节
 * 在中序遍历中对根节点进行定位时，一种简单的方法是直接扫描整个中序遍历的结果并找出根节点，
 * 但这样做的时间复杂度较高。我们可以考虑使用哈希映射（HashMap）来帮助我们快速地定位根节点。
 * 对于哈希映射中的每个键值对，键表示一个元素（节点的值），值表示其在中序遍历中的出现位置。
 * 在构造二叉树的过程之前，我们可以对中序遍历的列表进行一遍扫描，就可以构造出这个哈希映射。
 * 在此后构造二叉树的过程中，我们就只需要 O(1)O(1) 的时间对根节点进行定位了。
 */
public class 根据前序与中序遍历序列构造二叉树105 {

        private Map<Integer, Integer> indexMap;
        //preorder：前序遍历的序列，inorder：中序遍历的序列
        public TreeNode105 buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;//前序序列的长度
        // 构造中序序列（而不是前序序列）的哈希映射，即把中序序列中的值作为key，其下标作为value存入hashMap中，
            // 于是就可以通过前序序列中的第一个值（即根节点的值）来快速定位到该根节点在中序序列中的位置啦
        indexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        //开始递归
        return BuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }
    /**
     * 具体的递归函数
     * @param preorder 前序遍历的序列
     * @param inorder 中序遍历的序列
     * @param preorder_left 前序遍历序列子区间的左边界，可以取到
     * @param preorder_right 前序遍历序列子区间的右边界，可以取到
     * @param inorder_left 中序遍历序列子区间的左边界，可以取到
     * @param inorder_right 前序遍历序列子区间的右边界，可以取到
     * @return
     */
        public TreeNode105 BuildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
                //1.递归结束的条件，即前序序列不再构成区间时
            if (preorder_left > preorder_right) {
                return null;
            }

            // 2.在前序遍历中获取到根节点的值（前序遍历中的第一个节点就是根节点）
            int preorder_root = preorder_left;
            // 3.再到中序序列中找到它所对应的位置（下标）
            int inorder_root = indexMap.get(preorder[preorder_root]);

            // 4.开始构建二叉树
            // 4.1先把根节点建立出来（即建立一个节点）
            TreeNode105 root = new TreeNode105(preorder[preorder_root]);

            // 4.2再开始构建左子树
            // 4.2.1先在中序序列中得到左子树中的节点数目（性质：中序序列的根节点左边的值全是左子树的值，右边则全是右子树的值）
            int size_left_subtree = inorder_root - inorder_left;

            // 4.2.2再根据前序序列来递归地构造左子树（但下标值则是由中序序列的下标值表示出来），并连接到根节点
            // 前序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
            //于是更新前序序列中左子树的右边界，以及中序遍历中左子树的右边界；即以中序序列的左子树作为一颗新树开始递归
            root.left = BuildTree(preorder, inorder, preorder_left + 1, preorder_left + size_left_subtree, inorder_left, inorder_root - 1);

            // 4.3同样地，再递归地构造右子树，并连接到根节点
            // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
            // 于是更新前序序列中右子树的左边界，以及中序遍历右子树的左边界；即以中序序列的右子树作为一颗新树开始递归
            root.right = BuildTree(preorder, inorder, preorder_left + size_left_subtree + 1, preorder_right, inorder_root + 1, inorder_right);

            // 5.最后返回根节点即可，因为后台就可以根据根节点去遍历出该树啦
            return root;
        }
    }

/**
 * 106. 从中序与后序遍历序列构造二叉树
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如,给出:
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */

/**
 * 解析：与上题的思路完全一样
 */
class Solution106{
private Map<Integer, Integer> map;

    public TreeNode105 buildTree(int[] inorder, int[] postorder) {
        //1.特判
        if (inorder == null || postorder == null || inorder.length != postorder.length) {
            return null;
        }
        //2. 跟105题很像，主要思路就是从根节点入手，然后分为左右子树递归即可
        int n = inorder.length;
        if (n == 1) {
            return new TreeNode105(inorder[0]);
        }
        //2.1 将中序序列的索引存入map中，key为val，value为index
        map = new HashMap<>();
        int i = 0;
        for (int in : inorder) {
            map.put(in, i++);
        }
        //2.2 开始递归
        return buildTree(0, n - 1, n - 1,postorder);
    }

    /**
     * @param start     当前节点在中序数组中左索引
     * @param end       当前节点在中序数组中右
     * @param rootIndex 当前节点在后序数组中的根索引
     * @return tree
     */
    private TreeNode105 buildTree(int start, int end, int rootIndex,int[] postorder) {
        if (start > end || rootIndex < 0) {
            return null;
        }
        //1. 根节点
        TreeNode105 root = new TreeNode105(postorder[rootIndex]);
        if (start == end || rootIndex == 0) {
            return root;
        }
        //2. 获取根节点在中序数组中索引
        int index = map.get(postorder[rootIndex]);
        //3. 递归取左右子树即可
        //左子树的根节点在后序数组中的索引：rootIndex-(右子树的节点数)-1
        root.left = buildTree(start, index - 1, rootIndex - (end - index - 1 + 1) - 1,postorder);
        root.right = buildTree(index + 1, end, rootIndex - 1,postorder);
        return root;
    }
}