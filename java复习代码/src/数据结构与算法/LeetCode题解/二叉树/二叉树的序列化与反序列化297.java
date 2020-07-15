package 数据结构与算法.LeetCode题解.二叉树;

import javax.swing.tree.TreeNode;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * 297. 二叉树的序列化与反序列化
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，
 * 进而可以将转换后的数据存储在一个文件或者内存中，
 * 同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 *
 * 请设计一个算法来实现二叉树的序列化与反序列化。
 * 这里不限定你的序列 / 反序列化算法执行逻辑，
 * 你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 *
 * 示例:
 * 你可以将以下二叉树：
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 * 序列化为 "[1,2,3,null,null,4,5]"
 * 提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。
 * 你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 *
 * 说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，
 * 你的序列化和反序列化算法应该是无状态的。
 */

class TreeNode297 {
    int val;
    TreeNode297 left;
    TreeNode297 right;
    TreeNode297(int x) { val = x; }
}

/**
 * 本题要进行二叉树的序列化与反序列化，其中，序列化我们很容易就想到策略，直接遍历一遍我们的序列即可，
 * 我们可以按照先序遍历的方式进行，这里的关键地方在于如果遇到空的时候也要记录下来，
 * 可以选择任何标志符，这里我们选用“null”，对于每个字符转之间，我们选择“，”。
 *
 * 对于反序列化我们先要解析序列化的字符串，之后每当生成一个数的结点的时候，
 * 需要删除这个点，之后在此基础上去构建这棵树。
 */
public class 二叉树的序列化与反序列化297 {
        //（1）序列化：即把二叉树中的节点转化为字符串
        public String serialize(TreeNode297 root) {
            //使用递归，调用递归函数
            return getSerialize(root,"");
        }

        //序列化的具体实现
        public String getSerialize(TreeNode297 root, String str){
            //0.递归结束的条件：空节点用null表示（细节，勿忘）
            //不过这里是使用if else语句嵌套使用的，
            //因此把递归结束的条件写到if语句里，而把递归的操作放在else语句里（这样写也不错）
            if(root == null){//递归结束的条件
                str = str + "null,";
            }
            else{//1.采用前序遍历转化成字符串，使用递归
                str = str + root.val+",";//先访问根节点（每个字符串之间，使用“，”分隔）
                str =  getSerialize(root.left,str);//再向左递归访问其左节点
                str =  getSerialize(root.right,str);//向右递归访问其右节点
            }
            //2.返回最终的结果即可
            return str;
        }

        //（2）反序列化：即把字符串还原成二叉树
        public TreeNode297 deserialize(String data) {
            if(data == null){
                return null;
            }
            //把字符串按照“，”来转化为字符串数组
            String[] strArr = data.split(",");
            //再把字符串数组转化为数字数组，并存入到list中，再处理该list（不懂）
            LinkedList<String> list = new LinkedList<String>(Arrays.asList(strArr));
            return getDeserialize(list);
        }
        //反序列化的具体实现
        public TreeNode297 getDeserialize(LinkedList<String> list){
            //0.递归结束的条件，有两个
            //1）因为在递归代码中，每用完一个节点就会把其删除吗，因此当list的长度为0时，递归肯定就要结束
            if(list.size() == 0){
                return null;
            }
            //2）当递归遍历到一个节点的空子节点时，也可以结束递归啦
            // 同时，因为 TreeNode 的默认值就是 null。所以末尾的 null 是没有必要人为地挂在节点上的，
            // 也因此在递归结束/返回之前，我们可以把末尾的 null 去掉（remove）。
            if(list.get(0).equals("null")){
                list.remove(0);
                return null;
            }

            //1.开始构建二叉树， 先要解析序列化的字符串，之后每当生成一个数的结点的时候，就需要删除这个点，
            //之后在此基础上去构建这棵树，即我们每次都只需要取下标为0的数（即第一个数）即可，这样可以省去遍历list所需要的的开销。
            //1.1先构建根节点root
            TreeNode297 root = new TreeNode297(Integer.parseInt(list.get(0)));//把int型转为Integer型
            list.remove(0);//用完了就删除，于是此时list中第一个元素就是下一个元素啦~
            //1.2再链接它的左右节点，使用递归即可
            root.left = getDeserialize(list);
            root.right = getDeserialize(list);

            //2.当字符串中的所有字符都递归地使用完之后，就表示二叉树已经构建完毕，于是返回其根节点即可
            return root;
        }
    }
