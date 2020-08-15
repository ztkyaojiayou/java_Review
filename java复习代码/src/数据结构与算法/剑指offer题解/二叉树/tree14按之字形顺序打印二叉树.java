package 数据结构与算法.剑指offer题解.二叉树;

//有时候在牛客系统上没有这些包，则应当自己主动添加/导入（这是细节问题，务必注意）
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目：请实现一个函数按照之字形顺序打印二叉树，
 *       即第一行按照从左到右的顺序打印，
 *        第二层按照从右到左的顺序打印，
 *        第三行再按照从左到右的顺序打印，
 *        其他以此类推。
 *
 *【思路】：使用广度优先搜索，配合队列求解（见LeetCode102，讲的很讲清楚啦）
 *  按之字形顺序打印二叉树需要两个队列（先进先出），这里使用ArrayList和LinkedList
 *  易知，奇数层（第一层/根节点就是奇数行）的数要顺序打印，偶数层的数则要逆序打印
 *  方法：我们在打印某一行结点时，把下一层的子结点保存到相应的队列里。
 *   第一行是奇数行，直接从队列中取出即可，即为顺序打印
 *   下一层为偶数层，应当逆序打印，
 *   此时使用集合工具类中的反转操作即可：Collections.reverse(list)
 */

 class TreeNode14 {
    int val = 0;
    TreeNode14 left = null;
    TreeNode14 right = null;

    public TreeNode14(int val) {
        this.val = val;

    }

}

public class tree14按之字形顺序打印二叉树 {

     //注意：这个方法的参数pRoot指的是二叉树的第一个的结点/根节点，其他的结点需要自己处理
     // （也即在牛客系统的后台只会传入根节点）
    public  ArrayList<ArrayList<Integer>> Print(TreeNode15 pRoot) {//传入根节点


        /**
         * 这里涉及到两个list和一个队列queue：
         * 1）ret：最终按照题意要返回的结果集list，里面存储的是又是一个一个的list，
         * 即以一个一个的list为单位存储，其代表的是每一行的元素
         * 2）list：由于顺序存储和反转存储每一行的元素，然后再将该list存入结果集list中
         * 3）queue：用于存储每一行的所有结点
         */

        //LinkedList的add（添加）、poll（删除） 方法提供先进先出的队列操作
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();//最终要返回的，其中包含多个list（每一个list就是每一行的元素）
        Queue<TreeNode15> queue = new LinkedList<>();//把所有结点先存入此队列中（先进先出）
        queue.add(pRoot);//往队列中添加二叉树的第一个结点/根节点，一层一层处理
        boolean reverse = false;//用于逆序操作
        while (!queue.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<>();//用于保存每一行的元素
            int cnt = queue.size();//获取队列的元素个数（队列中保存了每一行的元素）
            while (cnt > 0) {//退出循环时就表示这一层已经处理完了
                TreeNode15 node = queue.poll();//删除并返回队列头部的第一个元素，这里强调返回，且是顺序返回/弹出
                if (node == null)
                    continue;//continue时，跳出本次循环，继续执行下次循环;Break时，跳出循环（结束循环），执行循环体下面的语句。
                list.add(node.val);//把从queue中顺序返回/弹出的元素依次存入到另一个队列list中
                //再通过循环，处理其左右结点（即每处理一个节点，就同时把该节点的左右节点（属于下一层的结点）加入到队列中））
                //先添加该节点的左节点，再添加其右节点，此时为顺序，等会使用reverse方法反转即可
                queue.add(node.left);
                queue.add(node.right);
                cnt--;//每处理完一个元素就减一，直到在这一层全处理完毕。
            }
            if (reverse) {//第一行为顺序打印，因此这段代码要先写到真正要反转的前面
                //反转list，用于逆序打印
                Collections.reverse(list);//此时list已经反转
            }
            //由之前的分析知，奇数行顺序打印，偶数行逆序打印
            // 又因为第一行为奇数行，则顺序打印，打印完之后通过语句reverse = !reverse反转一次，则第二行就变成了逆序打印啦
            reverse = !reverse;//设置为true，使得下一行变成逆序打印（执行line68实现反转）
            if (list.size() != 0)
                ret.add(list);//把list存入ret中，用于返回
        }
        return ret;
    }

}
