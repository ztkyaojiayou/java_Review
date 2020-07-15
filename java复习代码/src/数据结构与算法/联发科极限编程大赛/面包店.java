package 数据结构与算法.联发科极限编程大赛;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 神户开了一家面包店，一个柜台有N排货架，每排货架可以存放K个面包，而且每个面包都有其保鲜期限，比如1、3、4天等等。
 * 科比喜欢从排列最整齐的一排开始，因为这样架子上的面包就不需要再加工了。
 * 整齐的定义:每行货架上的面包日期的倒序对越少，面包就越整齐。如果货架上的面包的新鲜期是完全上升的，那么它是最干净的。
 * 反向对的定义:在数组A中，当i <j时，如果A[i]> A[j]，则(i, j)称为数组A中的反向对。
 * 你能帮Kobe弄清楚整理架子的顺序吗?
 * 输入
 * 在第一行中输入n以指示行数。
 * 在第二行输入k表示每行货架上面包的数量。
 * 后面的N*k表示面包保持新鲜的天数。
 * 数据范围:1≤N, k≤200, 0≤≤10000
 * 输出
 * 输出的是一个新的N*k数组，根据整洁程度对货架的行进行重新排序。
 * 总共有一行输出。有关特定格式，请参考输出示例。
 */
public class 面包店 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[][] count = new int[n][2];//计数数组（n行两列，第一列记录每一行的行号，第二列记录该行的逆序对的数目）
        int[][] nums = new int[n][k];//目标数组（n行k列）
        for (int i = 0; i < n; i++) {//先填充目标数组
            for (int j = 0; j < k; j++) {
                nums[i][j] = sc.nextInt();
            }
            //开始计算，第一列记录每一行的行号，第二列记录该行的逆序对的数目
            count[i][0] = i;//第一列
            count[i][1] = reversePairs(nums[i]);//第二列，使用归并排序的思想进行逆序数的统计

        }
         //只能根据列来比，但传进去的却是一个一个的一维数组行，即一行一行传进去，每次传两个进行比较，
        // 再根据每两个一维数组的第二列（即第二个数）来比较第一列的元素（即第一个数）
         Arrays.sort(count, (o1, o2) -> o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1]);
        // //老式写法：
        //Arrays.sort(count, new Comparator<int[]>() {// 这里的int[]是一个泛型，其只是表示要传入一维数组，
        //    // 但并不是强调只传入一个，实际上是要传两个一维数组来比较的
        //    @Override
        //    public int compare(int[] o1, int[] o2) {
        //        if(o1[1] == o2[1]) return o1[0] - o2[0];
        //        return o1[1]-o2[1];
        //    }
        //});
        //打印
        System.out.print("[");
        for (int i = 0; i < n; i++) {
            System.out.print(Arrays.toString(nums[count[i][0]]));
            if (i != n - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("]");

    }

    public static int reversePairs(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return 0;
        }
        int[] copy = new int[len];
        for (int i = 0; i < len; i++) {
            copy[i] = nums[i];
        }
        int[] temp = new int[len];
        return reversePairs(copy, 0, len - 1, temp);

    }

    private static int reversePairs(int[] nums, int left, int right, int[] temp) {
        if (left == right) {
            return 0;
        }
        int mid = left + (right - left) / 2;
        int leftPairs = reversePairs(nums, left, mid, temp);
        int rightPairs = reversePairs(nums, mid + 1, right, temp);

        // 如果整个数组已经有序，则无需合并， 注意这里使用小于等于
        if (nums[mid] < nums[mid + 1]) {
            return leftPairs + rightPairs;
        }
        int crossPairs = mergeAndCount(nums, left, mid, right, temp);
        return leftPairs + rightPairs + crossPairs;
    }

    private static int mergeAndCount(int[] nums, int left, int mid, int right, int[] temp) {
        for (int i = left; i <= right; i++) {
            temp[i] = nums[i];
        }
        int i = left;
        int j = mid + 1;
        int count = 0;
        for (int k = left; k <= right; k++) {
            if (i == mid + 1) {
                nums[k] = temp[j];
                j++;
            } else if (j == right + 1) {
                nums[k] = temp[i];
                i++;
                count += (right - mid);
            } else if (temp[i] <= temp[j]) {
                nums[k] = temp[i];
                i++;
                count += (j - mid - 1);
            } else {
                nums[k] = temp[j];
                j++;
            }
        }
        return count;
    }

}
