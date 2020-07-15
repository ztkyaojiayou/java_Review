package 数据结构与算法.LeetCode题解.二叉树;

import javax.swing.tree.TreeNode;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 226. 翻转二叉树
 * 翻转一棵二叉树。
 *
 * 示例：
 * 输入：
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 输出：
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 */
class TreeNode226 {
    int val;
    TreeNode226 left;
    TreeNode226 right;
    TreeNode226(int x) { val = x; }
}

/**
 * 思路解析：很明显，使用递归，对每一个（根）节点的左右子树（节点）进行互换即可。
 * 当然，也可以使用迭代法，使用一个栈辅助即可。
 *
 * 终止条件：当前节点为null时返回
 * 交换当前节点的左右节点，再递归的交换当前节点的左节点，递归的交换当前节点的右节点
 *
 * 时间复杂度：每个元素都必须访问一次，所以是O(n)
 * 空间复杂度：最坏的情况下，需要存放O(h)个函数调用(h是树的高度)，所以是O(h)
 */
//方法1：使用递归
public class 翻转二叉树226 {
        public TreeNode226 invertTree(TreeNode226 root) {
            //递归函数的终止条件，节点为空时返回
            if(root==null) {
                return null;
            }
            //下面三句是将当前节点的左右子树交换
            TreeNode226 tmp = root.right;//和交换两个数一样，也是选一个中间量temp即可，只是这个中间量为一个节点而已
            root.right = root.left;
            root.left = tmp;
            //递归交换当前节点的 左子树
            invertTree(root.left);
            //递归交换当前节点的 右子树
            invertTree(root.right);
            //函数返回时就表示当前这个节点，以及它的左右子树
            //都已经交换完了
            return root;
        }
    }
//写法2：（每太懂）
class solution {
    public TreeNode226 invertTree(TreeNode226 root) {
        //递归的终止条件
        if (root == null) {
            return null;
        }
        TreeNode226 right = invertTree(root.right);
        TreeNode226 left = invertTree(root.left);
        root.left = right;
        root.right = left;
        return root;
    }
}

/**
 * 方法2：使用迭代：
 * 我们也可以用迭代方法来解决这个问题，这种做法和深度优先搜索（Breadth-fist Search, BFS）很接近。
 *
 * 算法
 * 这个方法的思路就是，我们需要交换树中所有节点的左孩子和右孩子。
 * 因此可以创一个队列来存储所有左孩子和右孩子还没有被交换过的节点。
 * 开始的时候，只有根节点在这个队列里面。只要这个队列不空，就一直从队列中出队节点，
 * 然后互换这个节点的左右孩子节点，接着再把孩子节点入队到队列，对于其中的空节点不需要加入队列。
 * 最终队列一定会空，这时候所有节点的孩子节点都被互换过了，直接返回最初的根节点就可以了。

 * 递归实现也就是深度优先遍历的方式，那么对应的就是广度优先遍历。
 * 广度优先遍历需要额外的数据结构--队列或栈，来存放临时遍历到的元素。
 * 深度优先遍历的特点是一竿子插到底，不行了再退回来继续；而广度优先遍历的特点是层层扫荡。
 * 所以，我们需要先将根节点放入到队列中，然后不断的迭代队列中的元素。
 * 对当前元素调换其左右子树的位置，然后：
 *
 * 判断其左子树是否为空，不为空就放入队列中
 * 判断其右子树是否为空，不为空就放入队列中
 */

class Solution226 {
    public TreeNode226 invertTree(TreeNode226 root) {
        //0.特判
        if(root==null) {
            return null;
        }
        //1.使用一个辅助队列
        //将二叉树中的节点逐层放入队列中，再迭代处理队列中的元素
        LinkedList<TreeNode226> queue = new LinkedList<TreeNode226>();
        //1.1先把根节点放入队列进行处理
        queue.add(root);
        while(!queue.isEmpty()) {
            //2.再每次都从队列中拿一个节点，并交换这个节点的左右子树
            //2.1先取（易知，第一次取的就是根节点）
            TreeNode226 cur = queue.poll();
            //2.2再互换当前节点的左右子节点，使用一个辅助节点变量temp即可
            TreeNode226 temp = cur.left;
            cur.left = cur.right;
            cur.right = temp;

            //3.再把当前节点的左右子节点入队，按顺序进行如上的处理即可
            //3.1如果当前节点的左子树不为空，则把它放入队列等待后续处理
            if(cur.left!=null) {
                queue.add(cur.left);
            }
            //3.2如果当前节点的右子树不为空，则把它放入队列等待后续处理
            if(cur.right!=null) {
                queue.add(cur.right);
            }

        }
        //4.最后返回处理完的根节点即可
        return root;
    }
}

//写法2：使用栈，思路完全相同
class solutions{
    public TreeNode226 invertTree(TreeNode226 root) {
        Stack<TreeNode226> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode226 cur = stack.pop();
            if (cur == null) {
                continue;
            }
            //互换当前节点的左右子节点
            TreeNode226 temp = cur.left;
            cur.left = cur.right;
            cur.right = temp;

            //再把其左右子节点入队，依次进行如上相同的处理即可
            stack.push(cur.right);
            stack.push(cur.left);
        }
        return root;
    }
}
