package 数据结构与算法.剑指offer题解.二叉树;

/**
 * 题目：输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则返回 true。否则返回 false。
 * 假设输入的数组的任意两个数字都互不相同。
 *
 * 【解】：
 *      递归
 *  *      盯准根结点所在的位置！
 *  *
 * 这道题的解题突破点就在于二叉树的后序遍历数组有着什么的特点？
 * 特点：（1）对于每一棵子树，它的根结点（root）总是对应该子树的后序序列的最后一个数（索引为 end=squence.length-1）
 *          树的右子树的的根节点总是对应数组的倒数第二个数（索引为 end-1=squence.length-2）
 *      （2）若传入的数组是后序遍历数组，则遍历数组时，若遇到比最后一个元素大的结点，则应当有：
 *          1.它前面的元素都要比最后一个元素小，
 *          2.该元素后面的所有值都必须大于最后一个值，
 *      这两个条件必须都要满足。否则就说明该序列不是二叉树后序遍历。
 *
 * 例子： 如：数组arr[]={2 4 3 6 8 7 5} 就是一个正确的后序遍历后的数组
 *       因为：最后一个元素是 5 ，首先遍历数组，当遍历到6的时候，6前面的值都小于5，
 *       如果在6后面的值有一个小于5就不是后序遍历，但此数组没有，因此是一个符合要求的数组
 *
 * 方法： 对于每一棵子树，它的根结点总是对应该子树的后序序列的最后一个数
 *       只需要不断地确定出左子树区间和右子树区间，并且判断：左子树区间的所有结点值 < 根结点值 < 右子树区间所有结点值，这个条件是否满足即可
 */

public class tree38二叉搜索树的后序遍历序列 {
    private static boolean verifySequenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length<=0){
            return false;
        }
        return verifySequenceOfBST(sequence, 0, sequence.length-1);
    }

    private static boolean verifySequenceOfBST(int[] squence, int start, int end) {
        //说明递归结束，且每一轮判断都符合要求
        if (start >= end){
            return true;
        }
        int root = squence[end]; //end位置为根结点
        int index = 0;
        //找到第一个比根结点大的数的位置/索引
        while (squence[index]<root && index<end-1){//只需从第一个数到倒数第二个数中遍历（因为倒数第一个数就是根节点）
            index++;
        }
        int mid = index;//即第一个比根结点大的数的位置
        //从第一个大于root值的地方开始遍历，判断该元素之后的所有值是否都大于root值
        while (squence[index]>root && index<end-1){
            //若中途有小于root的数，则会退出循环，则index不可能达到end-1（倒数第二个位置），则说明肯定不是后序遍历数组
            index++;
        }
        //根据上面的分析，该元素（index处）后面的所有值都必须大于最后一个值
        //因此若是后序遍历数组，则index最终结果应该是end-1，也即倒数第二个位置，
        if (index != end-1){ //若二者不相等，说明此数组肯定不是后序遍历之后的数组
            return false;
        }
        //通过递归不断地判断左子树区间和右子树区间是否也满足上述条件即可
        return verifySequenceOfBST(squence,start,mid-1) && verifySequenceOfBST(squence,mid,end-1);
    }
}
