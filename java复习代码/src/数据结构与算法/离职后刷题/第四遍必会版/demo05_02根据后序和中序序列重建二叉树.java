package 数据结构与算法.离职后刷题.第四遍必会版;

import 数据结构与算法.TreeNode;

import java.util.Map;

/**
 * 递归过程就是二叉树的建立过程。对二叉树的建立过程有了大致了解之后，接下来就是确定左右子树在中序和后序数组的边界。
 * 如何确定子树的左右边界？
 * 根据二叉树的性质，我们可以依次采取下述步骤：
 * 1、先利用后序遍历找根节点：后序遍历的最后一个数，就是根节点的值；
 * 2、在中序遍历中找到根节点的位置 k，则 k 左边是左子树的中序遍历，右边是右子树的中序遍历;
 * 3、假设il,ir对应子树中序遍历区间的左右端点， pl,pr对应子树后序遍历区间的左右端点。
 * 那么左子树的中序遍历的区间为 [il, k - 1]，右子树的中序遍历的区间为[k + 1, ir];
 * 4、由步骤3可知左子树中序遍历的长度为k - 1 - il + 1，由于一棵树的中序遍历和后序遍历的长度相等，因此后序遍历的长度也为k - 1 - il + 1。
 * 这样，根据后序遍历的长度，我们可以推导出左子树后序遍历的区间为[pl, pl + k - 1 - il]，
 * 右子树的后序遍历的区间为[pl + k - 1 - il + 1, pr - 1];
 * @author :zoutongkun
 * @date :2022/4/8 10:00 下午
 * @description :思路和前序遍历和中序遍历构建二叉树几乎相同，主要也是找边界
 * @modyified By:
 */
public class demo05_02根据后序和中序序列重建二叉树 {

        private Map<Integer,Integer> map;
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            int n = inorder.length;
            for(int i = 0; i < n; i++) {
                map.put(inorder[i], i);  //记录中序遍历的根节点位置
            }
            return dfs(postorder, 0, n - 1, 0, n - 1);
        }

        public TreeNode dfs(int[] postorder, int il, int ir,int pl, int pr)
        {
            if(pl > pr ) {
                return null;
            }
            int k = map.get(postorder[pr]); //中序遍历根节点位置
            TreeNode root = new TreeNode(postorder[pr]); //创建根节点
            root.left  = dfs(postorder, il, k - 1, pl, pl + k - 1 - il);
            root.right = dfs(postorder, k + 1, ir, pl + k - 1 - il + 1, pr - 1);
            return root;
        }
}
