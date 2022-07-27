package 数据结构与算法.剑指offer第一版.二叉树;

import java.util.LinkedList;

/**
 * 题目：请实现一个函数来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 *
 *【解】：
 *  (思路一，这里不采用，思路很容易理解，但实现较复杂）通常我们有三种不同的二叉树遍历算法，
 *  即前序遍历、中序遍历和后序遍历。在这三种遍历算法中，都是先遍历左子结点再遍历右子结点。
 *  我们是否可以定义一种遍历算法，先遍历右子结点再遍历左子结点？
 *  比如我们针对前序遍历定义一种对称的遍历算法，即先遍历父结点，再遍历它的右子结点，最后遍历它的左子结点。
 *  我们发现可以用过比较二叉树的前序遍历序列和对称前序遍历序列来判断二叉树是不是对称的。
 *  如果两个序列一样，那么二叉树就是对称的。
 *
 *【注意】：
 *   判断二叉树是否对称，序列化遍历时，叶子结点下面的空指针也要遍历出来，
 *   不然如果二叉树所有结点的value都一样的时候，不管咋遍历，序列化的结果都一样了。
 *
 *（思路2，思路也简单，实现也容易，很妙，推荐）
 * 按树的一层一层比较，从根节点开始比较，比较每一层的对称位置的值是否相等即可（画图更直观）
 */

class TreeNode {//这是每个tree类都需要的，为了不重复，这里使用其对应的行号进行区分
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
public class tree13判断二叉树是否对称 {

    /**
     * 这是系统提供的函数，不能修改，但我们需要一个额外的方法进行判断,怎么办呢？
     * 答曰:在此方法的return返回我们自定义的方法的相关调用即可,很简单，很多题目都需要这样用；
     * 这个方法的方法名可以和原方法同名，但不要理解为方法重载或递归
     * @param root
     * @return
     */
    public boolean isSymmetrical(TreeNode root) {//输入一个结点，把其看成是根节点
        //特判，即若树为空，也是一种特殊的对称
        if (root == null) {
            return true;
        }
        //开始递归
        return isSymmetrical(root, root);
    }

    public boolean isSymmetrical(TreeNode root1, TreeNode root2) {
        //递归结束的条件
        if (root1 == null && root2 == null) {//与运算，若这两个结点都为空，则也是一种对称
            return true;
        }
        if (root1 == null || root2 == null) {//或运算，若这两个结点之中只有一个为空，则肯定不对称
            return false;
        }
        if (root1.val != root2.val ) {//若这两个结点（的值）都不相等，则也肯定不对称
            return false;
        }
        //否则，就说明当前节点相等，于是就继续使用递归继续分别比较其左右结点（也即下一层的各个对称位置的值）即可
        //但要注意:root1的左子节点是和root2的右子节点比较，
        //同理，root1的右子节点是和root2的左子节点比较，
        //当每一个结点比较之后都相等的话，则肯定对称了
        return isSymmetrical(root1.left, root2.right) && isSymmetrical(root1.right, root2.left);
    }
}

/**
 * 方法2：使用队列进行迭代实现（面试常问，要掌握）
 * （1）回想下递归的实现：
 * 当两个子树的根节点相等时，就比较:
 * 左子树的left 和 右子树的right，这个比较是用递归实现的。
 *
 * （2）现在我们改用队列来实现，思路如下：
 * 首先从队列中拿出两个节点(left和right)比较
 * 将left的left节点和right的right节点放入队列，再比较
 * 将left的right节点和right的left节点放入队列，再比较
 * 时间复杂度是O(n)，空间复杂度是O(n)
 */
class Solution101 {
    public boolean isSymmetric(TreeNode root) {
        if(root==null || (root.left==null && root.right==null)) {
            return true;
        }
        //用队列保存节点（list本来就是一个特殊的队列，不要大惊小怪）
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        //将根节点的左右孩子放到队列中
        queue.add(root.left);
        queue.add(root.right);
        while(queue.size()>0) {
            //从队列中取出两个节点，再比较这两个节点
            TreeNode left = queue.removeFirst();
            TreeNode right = queue.removeFirst();
            //如果两个节点都为空就继续循环，两者有一个为空就返回false
            if(left==null && right==null) {
                continue;
            }
            if(left==null || right==null) {
                return false;
            }
            if(left.val!=right.val) {
                return false;
            }
            //将左节点的左孩子， 右节点的右孩子放入队列,
            //它们两个在队列中是相邻的，因为队列是先进先出的，再拿出来比较
            queue.add(left.left);
            queue.add(right.right);
            //再将左节点的右孩子，右节点的左孩子放入队列，同理。
            queue.add(left.right);
            queue.add(right.left);
        }
        return true;
    }
}
