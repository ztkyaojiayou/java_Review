package 数据结构与算法.剑指offer第一版.二叉树;

/**
 * 题目：请实现两个函数，分别用来序列化和反序列化二叉树。
 *
 *【解】：1.首先明确：这其实是两个题目，且二者虽然在思路上有相通性，但在代码上二者并没有关联，各写各的。
 * 2.方法：
 * （1）序列化二叉树：
 * 递归遍历二叉树的节点，空节点使用#代替，节点之间使用逗号隔开，返回字符串
 * （2）反序列化二叉树：
 * 定义一个索引index，将字符串根据逗号分割为数组（要用到String类的split方法），
 * 根据index的值来设置树节点的val，若节点的值为#，则返回 空的树节点。
 *
 */

//模板式代码
 class TreeNode16 {
    int val = 0;
    TreeNode16 left = null;
    TreeNode16 right = null;

    public TreeNode16(int val) {
        this.val = val;

    }

}

public class tree16序列化和反序列化二叉树 {


        /**
         * 1.序列化(使用的是递归，非常简单）
         * 分别递归遍历左节点和右节点，空使用 # 代替，节点之间用 ，隔开
         */
        public String Serialize(TreeNode16 root) {//传入根节点

            //递归结束的标志
            if (root == null) {//如果节点的值为空，，则返回#
                return "#";
            } else {
                return root.val + "," + Serialize(root.left) + "," + Serialize(root.right);//递归处理其他结点
            }
        }

        /**
         * 2.反序列化
         * 使用index来设置树节点的val值，递归遍历左节点和右节点，如果值是#则表示是空节点，直接返回
         */
        TreeNode16 Deserialize(String str) {

            int index = -1;
            //str.split(",")表示：把字符串按照逗号进行分割，取其两边的值
            String[] s = str.split(",");//将序列化之后的序列用 ，分隔符转化为数组（数组只保留逗号两边的值），返回此数组
            index++;//索引每次加一(定义的初始值为-1，-1+1=0，正好为数组的第一个值),可是，这里是怎么个每次+1法？？？？
            int len = s.length;
            if (index > len) {
                return null;
            }
            TreeNode16 treeNode16 = null;
            if (!s[index].equals("#")) {//若不是叶子节点，则通过递归，把其左右节点都安排上（若是叶子节点，则直接返回即可）
                treeNode16 = new TreeNode16(Integer.parseInt(s[index]));//parseInt：把int类型转换为Integer
                treeNode16.left = Deserialize(str);
                treeNode16.right = Deserialize(str);
            }
            return treeNode16;
        }

        //测试代码：
//        public static void main(String[] args) {
//            TreeNode16 treeNode161 = new TreeNode16(1);
//            TreeNode16 treeNode162 = new TreeNode16(2);
//            TreeNode16 treeNode163 = new TreeNode16(3);
//            TreeNode16 treeNode164 = new TreeNode16(4);
//            TreeNode16 treeNode165 = new TreeNode16(5);
//            TreeNode16 treeNode166 = new TreeNode16(6);
//
//            treeNode161.left = treeNode162;
//            treeNode161.right = treeNode163;
//
//            treeNode162.left = treeNode164;
//            treeNode163.left = treeNode165;
//            treeNode163.right = treeNode166;
//
//            tree16序列化二叉树 serializeTree = new tree16序列化二叉树();
//
//            String str = serializeTree.Serialize(treeNode161);
//            TreeNode16 treeNode16 = serializeTree.Deserialize(str);
//        }
    }
