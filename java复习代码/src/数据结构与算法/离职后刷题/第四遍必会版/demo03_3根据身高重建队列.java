package 数据结构与算法.离职后刷题.第四遍必会版;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * * 具体思路：
 * * （1）先排序，规则：按高度降序排列，当高度相同时，按 k 值的升序排列。
 * * （2）再逐个把它们放在输出队列中，其存放的位置索引值就等于它们的 k 值，
 * *      这里要注意：每次插入时，只管从队列的前面往后面数，要加入的人的索引值k为多少，就往该位置上插入该人。
 * *      若该索引1处已经有人了，那么下次再来一个1怎么办？此时下次来的这个人就替换他，之前的那个人往后移一步即可。
 * * （3）最后，返回输出队列即为所求。
 */
public class demo03_3根据身高重建队列 {
    public int[][] reconstructQueue(int[][] nums) {
        //先按高度降序排序，身高相同时按k升值排序
        Arrays.sort(nums, new Comparator<int[]>() {

            @Override
            //这个比较器的用法要掌握，专门用于多行两列的二维数组的比较
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0];
            }
        });
        //再放入list中，其存放的位置就等于它们的 k 值，其他已存在的元素往后移动即可
        List<int[]> list = new ArrayList<>();
        for (int[] num : nums) {
            //往指定位置放元素
            list.add(num[1], num);
        }
        //最后转化为数组返回即可
        int len = nums.length;
        return list.toArray(new int[len][2]);
    }

    public static int[][] reconstructQueue02(int[][] nums) {
        Arrays.sort(nums, (o1, o2) -> {
            //先按高度降序排序，身高相同时按k升值排序
            //第一位/即[0]表示身高，第二位/即[1]表示k
            return o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0];
        });
        List<int[]> list = new ArrayList<>();
        //二维数组，按行遍历，易知num[1]表示k，单个元素num即为每一行的元素，其实也即
        for (int[] num : nums) {
            list.add(num[1], num);
//            //测试
//            for (int i : num) {
//                System.out.println(i);
//            }
        }
        return list.toArray(new int[nums.length][2]);
    }

    //测试
    public static void main(String[] args) {
        int[][] nums = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        int[][] res = demo03_3根据身高重建队列.reconstructQueue02(nums);
        for (int[] re : res) {
            for (int i : re) {
                System.out.print(i + "\t");
            }
            //换行
            System.out.println();
        }
    }
}
