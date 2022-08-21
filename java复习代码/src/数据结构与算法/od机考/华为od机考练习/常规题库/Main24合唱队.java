package 数据结构与算法.od机考.华为od机考练习.常规题库;

/**
 * 24）合唱队--参考【最长上升子序列的长度】
 * 描述：
 * 能找到一个同学，他的两边的同学身高都依次严格降低的队形就是合唱队形。
 * 例子：
 * 123 124 125 123 121 是一个合唱队形
 * 123 123 124 122不是合唱队形，因为前两名同学身高相等，不符合要求
 * 123 122 121 122不是合唱队形，因为找不到一个同学，他的两侧同学身高递减。
 * <p>
 * 你的任务是，已知所有N位同学的身高，计算最少需要几位同学出列，可以使得剩下的同学排成合唱队形。
 * <p>
 * 注意：不允许改变队列元素的先后顺序 且 不要求最高同学左右人数必须相等
 *
 * @author :zoutongkun
 * @date :2022/7/28 9:18 下午
 * @description :
 * @modyified By:
 * <p>
 * <p>
 * /**
 * 思路解析：
 * 方法一：动态规划：参考【最长上升子序列的长度】的做法
 * 具体方法
 * 分析题目可得，其实就是求最长递增子序列的变种题目，只不过加了一个约束条件，需要左边递增右边递减的情况。
 * <p>
 * 1、先找到每一个位置i左侧的最长上升子序列长度left[i]
 * 2、再找到每一个位置i右侧的最长下降子序列长度right[i]
 * 3、然后求出所有位置处的最长序列长度=左侧最长子序列长度+右侧最长子序列长度-1（因为该位置被算了两次，所以减1）
 * 4、然后用数目减去最长序列长度就是答案，需要出队的人数
 */

import java.util.*;

public class Main24合唱队 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        while (sc.hasNext()) {
        //1.录入数组
        int arrLen = sc.nextInt();
        int[] arr = new int[arrLen];
        for (int i = 0; i < arrLen; i++) {
            arr[i] = sc.nextInt();
        }
        //2.定义dp数组：
        //dp[i] 表示 以arr[i] 结尾的左侧序列的最长上升子序列的长度
        //也即： 数组arr前i个数字的最长上升子序列的长度。
        //dpLeft:存储每个数左边小于其的最长上升子序列的长度
        int[] dpLeft = new int[arrLen];
        //dpRight:存储每个数右边小于其的最长上升子序列的长度
        int[] dpRight = new int[arrLen];

        //3.计算每个位置左侧的最长递增--即求最长上升子序列的长度
        for (int i = 0; i < arrLen; i++) {
            //初始化：即当前位置结尾的子序列的长度至少也是1，即就由它本身构成
            dpLeft[i] = 1;
            for (int j = 0; j < i; j++) {
                //动态规划
                if (arr[i] > arr[j]) {
                    dpLeft[i] = Math.max(dpLeft[j] + 1, dpLeft[i]);
                }
            }
        }
        //4.同理，计算每个位置右侧的最长递减
        for (int i = arrLen - 1; i >= 0; i--) {
            //初始化，同理，此时dp[i] 表示 以arr[i] 结尾的右侧序列的最长上升子序列的长度
            dpRight[i] = 1;
            for (int j = arrLen - 1; j > i; j--) {
                //动态规划
                if (arr[i] > arr[j]) {
                    dpRight[i] = Math.max(dpRight[i], dpRight[j] + 1);
                }
            }
        }
        //5.求出每个位置两侧的最长连续上升子序列的最大总长度
        int curTotalLen = 0;
        //定义最大长度，易知最短也是1
        int maxLen = 1;
        for (int i = 0; i < arrLen; i++) {
            //位置 i计算了两次 所以需要－1
            //两个都包含本身
            curTotalLen = dpLeft[i] + dpRight[i] - 1;
            //找到最长的总长度
            maxLen = Math.max(curTotalLen, maxLen);
        }
        //6.用数目减去这个最长的总长度即为需要出队的人数
        System.out.println(arrLen - maxLen);
//        }

    }
}

