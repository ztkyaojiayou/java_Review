package 数据结构与算法.离职后刷题.第四遍必会版;

import 数据结构与算法.TreeNode;

import java.util.Map;

/**
 * （思路懂，代码不太懂）题目：根据前序和中序序列（不含有重复的数字），构建一棵二叉树
 * <p>
 * 分析：
 * 根据中序遍历和前序遍历可以确定二叉树，具体过程为：
 * 1.根据前序序列第一个结点确定根结点
 * 2.再根据根结点在中序序列中的位置分割出左右两个子序列
 * 3.最后对左子树和右子树分别递归使用同样的方法继续分解即可（也是先从前序遍历看）
 * 例如：
 * 前序序列{1,2,4,7,3,5,6,8} = pre
 * 中序序列{4,7,2,1,5,3,8,6} = in
 * <p>
 * 根据当前前序序列的第一个结点确定根结点，为 1
 * 找到 1 在中序遍历序列中的位置，为 in[3]
 * 切割左右子树，则 in[3] 前面的为左子树， in[3] 后面的为右子树
 * 则切割后的左子树前序序列为：{2,4,7}，切割后的左子树中序序列为：{4,7,2}；
 * 切割后的右子树前序序列为：{3,5,6,8}，切割后的右子树中序序列为：{5,3,8,6}
 * 对子树分别使用同样的方法分解
 * <p>
 * （前序遍历的第一个值为根节点的值，使用这个值将中序遍历结果分成两部分，
 * 左部分为树的左子树中序遍历结果，右部分为树的右子树中序遍历的结果。）
 */

public class demo05_01根据前序和中序序列重建二叉树 {
    // 声明一个map：用于缓存中序遍历数组每个值对应的索引
    // key：值，value:对应的索引
    private Map<Integer, Integer> indexForInOrders;

    //pre：前序遍历后的数组，in:中序遍历后的数组
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        for (int i = 0; i < in.length; i++) {
            // 存储中序遍历数组每个值对应的索引（即：key=in[i], value=i）
            //用于
            indexForInOrders.put(in[i], i);
        }

        //调用递归方法
        //（核心）要注意的是：在每次递归时，前序序列和中序序列都是会变的，
        //也因此要找到每一次递归时的前序序列和中序序列（方法如上），这样思路就很很清晰了！！！
        //参数说明：preL：前序数组的第一个索引，preR：前序数组的最后一个索引，inL：中序数组的第一个索引（后面要分为左右子树）
        return method(pre, 0, pre.length - 1, 0);
    }

    /**
     * 使用递归，开始真正重新构建二叉树
     * 返回的是
     *
     * @param pre
     * @param preL
     * @param preR
     * @param inL
     * @return
     */
    private TreeNode method(int[] pre, int preL, int preR, int inL) {
        if (preL > preR) {
            return null;
        }
        //因为是建立二叉树，因此首先要建立根节点，那么谁是呢？易知，就是前序序列的第一个结点呀
        //在前序遍历数组中获取根节点的值，易知，其第一个值即为根节点的值
        TreeNode root = new TreeNode(pre[preL]);
        //获取根节点在中序数组中所在的索引，目的是为了求出左右子树的长度
        int inIndex = indexForInOrders.get(root.val);
        //左子树的长度=中序数组中根节点的索引-第一个数的索引
        int leftTreeSize = inIndex - inL;
        //使用递归方法创建二叉树即可
        //即：从原前序序列中找到左右子树的前序序列（利用中序序列）
        //1）创建左子树（根节点就是root）
        //此时的前序序列的起点是preL + 1，终点是preL + leftTreeSize
        //中序序列的起点是：inL
        root.left = method(pre, preL + 1, preL + leftTreeSize, inL);
        //2）创建右子树
        //此时的前序序列的起点是preL + leftTreeSize + 1，终点是preR
        //中序序列的起点是：inL + leftTreeSize + 1
        root.right = method(pre, preL + leftTreeSize + 1, preR, inL + leftTreeSize + 1);
        //最终返回根节点即可
        return root;
    }

    //自写一遍
    //pre：前序遍历后的数组，in:中序遍历后的数组
    // 缓存中序遍历数组每个值对应的索引
    private Map<Integer, Integer> inOrderMap;

    public TreeNode reConstructBinaryTree02(int[] pre, int[] in) {
        //先存储中序遍历的值和对应的下标，用于定位左右子树的位置
        for (int i = 0; i < in.length; i++) {
            inOrderMap.put(in[i], i);
        }
        //开始递归
        return method02(pre, 0, pre.length - 1, 0);
    }

    private TreeNode method02(int[] pre, int preStart, int preEnd, int inStart) {
        if (preStart > preEnd){
            return null;
        }
        TreeNode root = new TreeNode(pre[preStart]);
        Integer curInIndex = inOrderMap.get(root.val);
        int leftTreeLenth = curInIndex - inStart;
        //求出左子树在前序序列中的起止下标和在中序遍历中的起点，用于下一次递归
        int nextPreLeftStart = preStart + 1;
        int nextPreLeftEnd = preStart + leftTreeLenth + 1;
        int nextInLeftStart = inStart;
        //同理，求出右子树的
        int nextPreRightStart = preStart + leftTreeLenth + 1;
        int nextPreRightEnd = preEnd;
        int nextInRightStart = inStart + leftTreeLenth + 1;
        //开始构建左右子树（都是以前序序列为基准）
        root.left = method02(pre, nextPreLeftStart, nextPreLeftEnd, nextInLeftStart);
        root.right = method02(pre, nextPreRightStart, nextPreRightEnd, nextInRightStart);
        return root;
    }
}
