package 数据结构与算法.离职后刷题.第四遍必会版;


import 数据结构与算法.TreeNode;

import java.util.LinkedList;

/**
 * 类比最小深度，这里的深度也可以叫最大深度
 */
class 二叉树的深度 {
    /**
     * 方法1：递归
     * 首先明确：
     * 树的深度：二叉树的深度为根节点到最远叶子节点的最长路径上的节点数（易知，要比层数多1）。
     *
     * @param root
     * @return
     */
    public int TreeDepth(TreeNode root) {
        //递归结束的出口
        if (root == null) {
            return 0;
        }
        //左子树的高度/层数
        int left = TreeDepth(root.left);
        //右子树的高度/层数
        int right = TreeDepth(root.right);

        //注意特殊情况的处理（可以不写，也能通过，但为了和最小深度的逻辑保持一致，因此这里也写上）
        if (root.left == null || root.right == null) {
            return left + right + 1;
        }
        //加1是深度的定义（即左右子树的深度的较大者+1）
        int res = Math.max(left, right) + 1;
        return res;
    }

    //自写一遍
    public int TreeDepth01(TreeNode root) {
        //递归出口
        if (root == null) {
            return 0;
        }

        //左右子树的高度/层数
        int left_height = TreeDepth01(root.left);
        int right_height = TreeDepth01(root.right);

        //注意特殊情况的处理（可以不写，也能通过，但为了和最小深度的逻辑保持一致，因此这里也写上）
        if (root.left == null || root.right == null) {
            return left_height + right_height + 1;
        }
        //返回
        int res = Math.max(left_height, right_height) + 1;
        return res;

    }

    /**
     * 方法2：非递归/迭代（面试推荐）
     * 其实和层序遍历思路相同
     * （大部分二叉树的非递归算法都和层序遍历相同，因此务必掌握）
     */
    public int TreeDepth02(TreeNode root) {
//        if (root == null) {
//            return 0;
//        }
        LinkedList<TreeNode> singleLine = new LinkedList<>();
        singleLine.add(root);
        int depth = 0;
        while (!singleLine.isEmpty()) {
            int size = singleLine.size();
            for (int i = 0; i < size; i++) {
                ///处理逻辑：由于只求深度，因此只需弹出即可，无需其他逻辑
                // 处理掉的就删掉
                TreeNode node = singleLine.poll();
                //新增逻辑（可能不需要？为了统一，先写着）
                if (node == null) {
                    continue;
                }
                //再放下一层节点
                if (node.left != null) {
                    singleLine.add(node.left);
                }
                if (node.right != null) {
                    singleLine.add(node.right);
                }
            }
            //遍历完一层，深度就加1--就这一行代码是核心，其他代码都和层序遍历相同
            //不用额外加1了！！！
            depth++;
        }
        return depth;
    }

    //自写一遍
    public int TreeDepth002(TreeNode root) {
        //主要就是处理这一个list！！！
        LinkedList<TreeNode> singleLine = new LinkedList<>();
        singleLine.add(root);
        //记录深度
        int depth = 0;
        while (!singleLine.isEmpty()) {
            int size = singleLine.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur_node = singleLine.poll();
                if (cur_node == null) {
                    continue;
                }
                //再放下一层节点（而不是值val）
                if (cur_node.left != null) {
                    singleLine.add(cur_node.left);
                }
                if (cur_node.right != null) {
                    singleLine.add(cur_node.right);
                }
            }
            //每遍历一层就加1（已包含了根节点那一层，因此无需额外加1）
            depth++;
        }
        return depth;
    }
}


/**
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * 说明：叶子节点是指没有子节点的节点。
 * 思路一模一样：相比求深度，只需改为min即可
 */
class 最小深度 {
    public int Min_Depth(TreeNode root) {
        //递归结束的条件
        if (root == null) {
            return 0;
        }
        //左右最小高度/层数
        int left = Min_Depth(root.left);
        int right = Min_Depth(root.right);

        //注意特殊情况
        // 如果左右子树都为空或者有一方为空，则直接返回两者的最小高度 + 1即可
        if (root.left == null || root.right == null) {
            return left + right + 1;
        }
        //选较小值加1即为所求（即相比求深度，只需将这里改为min即可）
        int res = Math.min(left, right) + 1;
        return res;
    }

    //自写一遍
    public int Min_Depth02(TreeNode root) {
        //递归出口
        if (root == null) {
            return 0;
        }
        int left_minHeight = Min_Depth02(root.left);
        int right_minHeight = Min_Depth02(root.right);
        //注意特殊情况的处理
        if (root.left == null || root.right == null) {
            return left_minHeight + right_minHeight + 1;
        }
        //返回：较小者（最大深度/深度则是较大者，就这个区别！）加1即为所求
        int res = Math.min(left_minHeight, right_minHeight) + 1;
        return res;
    }

}

