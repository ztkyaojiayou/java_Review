package 数据结构与算法.第三遍;

import 数据结构与算法.TreeNode;

import java.util.*;

/**
 * @author :zoutongkun
 * @date :2022/4/7 1:35 上午
 * @description :
 * @modyified By:
 */
public class demo05_18按之字形顺序打印二叉树 {
    /**
     * 这里涉及到两个list和一个队列queue：
     * 1）res：最终按照题意要返回的结果集list，里面存储的是又是一个一个的list，
     * 即以一个一个的list为单位存储，其代表的是每一行的元素
     * 2）temp：由于顺序存储和反转存储每一行的元素的值，然后再将该list存入结果集list中
     * 3）singleLine：用于存储每一行的所有结点(而不是值）
     */
    //注意：这个方法的参数root指的是二叉树的第一个的结点/根节点，其他的结点需要自己处理
    // （也即在牛客系统的后台只会传入根节点）
    public List<List<Integer>> PrintByZhiType(TreeNode root) {//传入根节点
        //LinkedList的add（添加）、poll（删除） 方法提供先进先出的队列操作
        List<List<Integer>> res = new ArrayList<>();//最终要返回的，其中包含多个list（每一个list就是每一行的元素）
        LinkedList<TreeNode> singleLine = new LinkedList<>();//把所有结点先存入此队列中（先进先出）
        singleLine.add(root);//往队列中添加二叉树的第一个结点/根节点，一层一层处理
        boolean reverse = false;//用于逆序操作
        while (!singleLine.isEmpty()) {
            List<Integer> temp = new ArrayList<>();//用于保存每一行的元素
            int size = singleLine.size();//获取队列的元素个数（队列中保存了每一行的元素）
            for (int i = 0; i < size; i++) {
                TreeNode cur_node = singleLine.poll();
                if (cur_node == null) {
                    continue;//continue时，跳出本次循环，继续执行下次循环;Break时，跳出循环（结束循环），执行循环体下面的语句。
                }
                temp.add(cur_node.val);//把从queue中顺序返回/弹出的元素依次存入到另一个队列list中
                //再通过循环，处理其左右结点（即每处理一个节点，就同时把该节点的左右节点（属于下一层的结点）加入到队列中））
                //先添加该节点的左节点，再添加其右节点，此时为顺序，等会使用reverse方法反转即可
                if (cur_node.left != null) {
                    singleLine.add(cur_node.left);
                }

                if (cur_node.right != null) {
                    singleLine.add(cur_node.right);
                }

            }
            //以下列两处为关键代码
            if (reverse) {//第一行为顺序打印，因此这段代码要先写到真正要反转的前面
                //反转list，用于逆序打印
                Collections.reverse(temp);//此时list已经反转
            }
            //由之前的分析知，奇数行顺序打印，偶数行逆序打印
            // 又因为第一行为奇数行，则顺序打印，打印完之后通过语句reverse = !reverse反转一次，则第二行就变成了逆序打印啦
            reverse = !reverse;//设置为true，使得下一行变成逆序打印（执行line68实现反转）
            res.add(temp);//把list存入ret中，用于返回
        }
        return res;
    }

    //自写一遍
    public List<List<Integer>> PrintByZhiType01(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<TreeNode> singleLine = new LinkedList<>();
        singleLine.add(root);
        boolean flag = false;
        while (!singleLine.isEmpty()) {
            int size = singleLine.size();
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur_node = singleLine.poll();
                if (cur_node == null) {
                    continue;
                }
                temp.add(cur_node.val);
                if (cur_node.left != null) {
                    singleLine.add(cur_node.left);
                }
                if (cur_node.right != null) {
                    singleLine.add(cur_node.right);
                }
            }
            //反转
            if (flag) {
                Collections.reverse(temp);
            }
            //flag= true是不对的
            flag = !flag;
            res.add(temp);
        }
        return res;
    }


}
