package 数据结构与算法.数据结构.二叉树.arrayBinaryTree;

/**
 * 本Demo演示的是顺序存储二叉树的遍历方法
 * （注解：顺序存储二叉树即是把一个数组中的元素依次按顺序放置到二叉树的各个节点上所形成的二叉树，且一般只考虑完全二叉树）
 * 特点：所转换成的二叉树中的第n个元素（n从0开始，且指的不是数组中的值，而是其下标）在二叉树中的位置的左子节点为2*n + 1
 * 右子节点为2*n + 2，父节点为（n-1）/2
 *
 * 需求：把数组arr = { 1, 2, 3, 4, 5, 6, 7 }转变为二叉树，
 * 要求以前序遍历的方式遍历之后的结果为：1，2，4，5，3，6，7
 */

//1.编写一个ArrayBinaryTree类, 用于实现顺序存储二叉树遍历
class ArrBinaryTree {
    private int[] arr;//存储数据结点的数组

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //重载preOrder
    public void preOrder() {
        this.preOrder(0);
    }

    //2.编写一个方法，完成顺序存储二叉树的前序遍历
    /**
     * @param index 数组的下标（刚好对应二叉树的结点值）
     */
    public void preOrder(int index) {
        //2.1如果数组为空，或者 arr.length = 0
        if(arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        //2.2输出当前这个元素
        System.out.println(arr[index]);//即父节点
        //向左递归遍历
        if((index * 2 + 1) < arr.length) {//即若有左节点，则遍历左节点（这个判断方式务必要理解）
            preOrder(2 * index + 1 );
        }
        //向右递归遍历
        if((index * 2 + 2) < arr.length) {//即若有右节点，则遍历右节点
            preOrder(2 * index + 2);
        }
    }

}

//2.测试
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7 };
        //创建一个 ArrBinaryTree
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder(); // 1,2,4,5,3,6,7
    }

}