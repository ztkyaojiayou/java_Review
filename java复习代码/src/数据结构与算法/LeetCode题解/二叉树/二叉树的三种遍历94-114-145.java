package 数据结构与算法.LeetCode题解.二叉树;

import javax.swing.tree.TreeNode;
import java.util.*;
/**
 * 144. 二叉树的前序遍历
 * 给定一个二叉树，返回它的 前序 遍历。
 *  示例:
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 * 输出: [1,2,3]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */
class 前序遍历114{
    public  List<Integer> preOrderRecur(TreeNode94 root) {
        //结果集
        List<Integer> res = new ArrayList<>();
        //开始递归（带上结果集res）
        dfs(res,root);
        return res;
    }

    void dfs(List<Integer> res, TreeNode94 root) {
        if (root == null) {
            return;
        }
        res.add(root.val);//把根节点存入结果集中//System.out.print(root.val + " ");打印
        dfs(res,root.left);
        dfs(res,root.right);
    }
}

/**
 * 迭代法：同样是使用一个栈来模拟递归即可，
 * 从根节点开始，每次迭代弹出当前栈顶元素，并将其孩子节点压入栈中，但是要先压右孩子再压左孩子，因为栈的特点是先进后出。
 * 在这个算法中，输出到最终结果的顺序按照 Top->Bottom 和 Left->Right，符合前序遍历的顺序。
 */
class solution114{
    public  List<Integer> preOrderIteration(TreeNode94 root) {
        //0.结果集
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        //1.使用一个栈来模拟递归
        Stack<TreeNode94> stack = new Stack<TreeNode94>();
        //1.1先把根节点入栈
        stack.add(root);
        while (!stack.isEmpty()) {//只要栈不为空，就表明还有元素没有遍历完，若为空，则说明已经全部遍历结束，返回结果集即可
            //1.2再弹出根节点并保存到结果集中
            TreeNode94 node = stack.pop();
            res.add(node.val);
            //System.out.print(node.value + " ");//打印出来
            //2.1接着先把其右节点入栈（先）
            if (node.right != null) {
                stack.push(node.right);
            }
            //2.2再把其左节点入栈（后）
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        //3.最终，返回结果集（此时栈中已为空）
        return res;
    }
}

/**
 * 94. 二叉树的中序遍历
 * 给定一个二叉树，返回它的中序 遍历。
 *
 * 示例:
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */



 //Definition for a binary tree node.
 class TreeNode94 {
    int val;
     TreeNode94 left;
     TreeNode94 right;
     TreeNode94(int x) { val = x; }
  }

/**
 * 解析：基本操作，可使用递归和迭代方法实现，且递归较简单，务必掌握迭代算法。
 * （1）解法1：使用递归
 * 递归遍历太简单了
 * 前序遍历:打印-左-右
 * 中序遍历:左-打印-右
 * 后序遍历:左-右-打印
 * 题目要求的是中序遍历，那就按照 左-打印-右这种顺序遍历树就可以了，递归函数实现
 *
 * 终止条件:当前节点为空时
 * 函数内: 递归的调用左节点，打印当前节点，再递归调用右节点
 * 时间复杂度:O(n)
 * 空间复杂度:O(h)，h是树的高度
 */
 class 二叉树的中序遍历94 {
        public List<Integer> inorderTraversal(TreeNode94 root) {
                //结果集
            List<Integer> res = new ArrayList<Integer>();
            //开始递归（带上结果集res）
            dfs(res,root);
            return res;
        }

        void dfs(List<Integer> res, TreeNode94 root) {
            //递归的终止条件，即当根节点（随时在变，每一个子树也有根节点哦）为空时
            if(root==null) {
                return;
            }
            //按照 左-打印-右的方式遍历
            dfs(res,root.left);//先向左递归
            res.add(root.val);//把根节点存入结果集中
            dfs(res,root.right);//再向右递归
        }
    }

/**
 * (2)解法2：使用迭代法（重点，掌握）
 * 先回忆一下使用递归的过程：
 * 它是不断往左边走，当左边走不下去了，就打印节点，并转向右边，然后右边继续这个过程。
 * 于是,我们在迭代实现时，就可以用栈来模拟上面的调用过程。
 *
 */
class Solution94 {
    public List<Integer> inorderTraversal(TreeNode94 root) {
        //0.结果集
        List<Integer> res = new ArrayList<Integer>();
        //1.使用一个栈来模拟递归的过程（这就是迭代的精髓）
        Stack<TreeNode94> stack = new Stack<TreeNode94>();

        //2.模拟递归的调用
        //2.1若当前节点不为空，则不断往左子树方向走，每走一次就将当前节点保存到栈中
        while(stack.size()>0 || root!=null) {
            if(root!=null) {
                stack.add(root);
                root = root.left;

            } else {//2.2否则，当当前节点为空时，即说明左边走到头了，此时做两件事：
                //（1）先从栈中弹出节点并保存到结果集res中
                //（2）然后转向右边节点，继续上面的整个过程
                TreeNode94 tmp = stack.pop();
                res.add(tmp.val);
                root = tmp.right;
            }
        }
        return res;
    }
}
//另一种写法：
class solution941{
    public void preOrderIteration(TreeNode94 root) {
        //0.结果集
        List<Integer> res = new ArrayList<Integer>();
            if (root == null) {
                return;
            }
            //TreeNode94 root = root;
            Stack<TreeNode94> stack = new Stack<>();
            while (!stack.isEmpty() || root != null) {
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                TreeNode94 node = stack.pop();
                //System.out.print(node.val + " ");
                res.add(node.val);
                if (node.right != null) {
                    root = node.right;
                }
            }
        }
    }




/**
 * 145. 二叉树的后序遍历(其迭代法较难）
 * 给定一个二叉树，返回它的 后序 遍历。
 *
 * 示例:
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 * 输出: [3,2,1]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */

/**
 * 解析：
 * 思路相同，也是有两种方法，递归和迭代，重点掌握迭代
 * （1）递归法，与上面的思路完全相同，简单
 */
class 后序遍历145 {
    public List<Integer> postorderTraversal(TreeNode94 root) {
        //结果集
        List<Integer> res = new ArrayList<>();
        //开始递归（带上结果集res）
        dfs(res,root);
        return res;
    }

     void dfs(List<Integer> res, TreeNode94 root) {

        //递归终止的条件
        if (root == null) {
            return;
        }
        //开始遍历(注意吗，这里是递归dfs方法，而不是postorderTraversal方法，且要带上结果集res，犯了三次错啦，草踏马）
        dfs(res,root.left);
        dfs(res,root.right);
        res.add(root.val);//把根节点存入结果集中
        //System.out.print(root.val + " ");//常规的就是打印出来，这里只是把结果存入list中而已
    }
}
/**
 * （2）迭代法
 我们使用转换的思想，使用两个栈来解决
 具体思路：
 由于后序遍历的顺序是：左右根，而前序遍历的过程 是 根左右。
 因此可以先将前序遍历的结果转化成 根右左，存入另一个栈中。
 （具体做法就是在压栈的过程中优先压入左子树，在压入右子树（因为栈是先进后出的））。
 然后再将这个结果弹出，即变成了左右根啦。
 这里是利用栈的先进后出的性质。
 */
class Solution迭代145 {
    public List<Integer> postorderTraversal(TreeNode94 root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        //使用两个栈来解决
        //stack1用于保存“（顶）根右左（底）”顺序，stack2用于保存“（顶）左右根（底）”顺序，
        //于是从stack2中弹出的顺序就为“左右根”了，即为后序遍历的顺序。
        Stack<TreeNode94> stack1 = new Stack<>();
        Stack<TreeNode94> stack2 = new Stack<>();
        stack1.push(root);//先根
        while (!stack1.isEmpty()) {
            TreeNode94 node = stack1.pop();//把stack1中的根节点弹出，存入stack2中
            stack2.push(node);//把根节点存入stack2中
            //接着向左，把左节点先保存到stack1中
            if (node.left != null) {
                stack1.push(node.left);
            }
            //再向右，把右节点后保存到stack1中
            if (node.right != null) {
                stack1.push(node.right);
            }
            //注解：先向左，后向右保存节点到stack1中的目的是为了弹出时为先右后左
        }
        //再弹出stack2的元素，其顺序即为后序遍历的顺序
        //因为此时stack2中存的顺序即为“中右左”了，于是弹出后的顺序就变成了“左右中”，刚好是后序遍历的顺序，完美~
        while (!stack2.isEmpty()) {
            //System.out.print(stack2.pop().value + " ");
            TreeNode94 node = stack2.pop();
            res.add(node.val);//添加到结果集中
        }
        //最终返回结果集即可
        return res;
    }

}
