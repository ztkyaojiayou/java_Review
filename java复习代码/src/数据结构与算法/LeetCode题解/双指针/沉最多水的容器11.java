package 数据结构与算法.LeetCode题解.双指针;
/**
 * 11. 盛最多水的容器
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。
 * 在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 *
 * 示例：
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 */

/**
 * 解题思路：使用双指针
 * 直接说思路啦
 *
 * 版本1：
 * 如果选择固定一根柱子，另外一根变化，水的面积会有什么变化吗？稍加思考可得：
 * 当前柱子是最两侧的柱子，水的宽度 d 为最大，其他的组合，水的宽度都比这个小。
 * 左边柱子较短，决定了水的高度为 3。如果移动左边的柱子，新的水面高度不确定，一定不会超过右边的柱子高度 7。
 * 如果移动右边的柱子，新的水面高度一定不会超过左边的柱子高度 3，也就是不会超过现在的水面高度。
 *
 * 由此可见，如果固定左边的柱子，移动右边的柱子，那么水的高度一定不会增加，且宽度一定减少，所以水的面积一定减少。
 * 这个时候，左边的柱子和任意一个其他柱子的组合，其实都可以排除了。也就是我们可以排除掉左边的柱子了。
 * 这个排除掉左边柱子的操作，就是双指针代码里的 i++。i 和 j 两个指针中间的区域都是还未排除掉的区域。
 * 随着不断的排除，i 和 j 都会往中间移动。当 i 和 j 相遇，算法就结束了。
 *
 * 版本2：
 * 算法流程： 设置双指针 i,j 分别位于容器壁两端，根据规则移动指针（后续说明），并且更新面积最大值 res，直到 i == j 时返回 res。
 *
 * 指针移动规则与证明： 每次选定围成水槽两板高度 h[j]h[j] 中的短板，向中间收窄 1 格。以下证明：
 * 设每一状态下水槽面积为 S(i,j),(0 <= i < j < n)，由于水槽的实际高度由两板中的短板决定，则可得面积公式 S(i, j) = min(h[i], h[j]) × (j - i)。
 * 在每一个状态下，无论长板或短板收窄 1 格，都会导致水槽 底边宽度 −1：
 * 若向内移动短板，水槽的短板 min(h[i],h[j]) 可能变大，因此水槽面积 S(i, j)S(i,j) 可能增大。
 * 若向内移动长板，水槽的短板 min(h[i],h[j]) 不变或变小，下个水槽的面积一定小于当前水槽面积。
 * 因此，向内收窄短板可以获取面积最大值。换个角度理解：
 * 若不指定移动规则，所有移动出现的 S(i,j) 的状态数为 C(n,2)，即暴力枚举出所有状态。
 * 在状态 S(i,j) 下向内移动短板至 S(i+1,j)（假设 h[i]<h[j] ），则相当于消去了 {S(i, j - 1), S(i, j - 2), ... , S(i, i + 1)
 * }S(i,j−1),S(i,j−2),...,S(i,i+1) 状态集合。而所有消去状态的面积一定 <= S(i, j)<=S(i,j)：
 * 短板高度：相比 S(i,j) 相同或更短（<= h[i]<=h[i]）；
 * 底边宽度：相比 S(i,j) 更短。
 * 因此所有消去的状态的面积都 < S(i, j)。
 *
 * 通俗的讲，我们每次向内移动短板，所有的消去状态都不会导致丢失面积最大值 。（核心）
 *
 * 复杂度分析：
 * 时间复杂度 O(N)，双指针遍历一次底边宽度 N 。
 * 空间复杂度 O(1)，指针使用常数额外空间。
 */
public class 沉最多水的容器11 {
    public int maxArea(int[] height) {
        int result = 0;//结果变量
        int i = 0;//左指针，从0开始
        int j = height.length - 1;//右指针，从末端开始
        //思路：指针i从左向右遍历，指针j从右向左遍历，
        //但每次移动的是高度较矮者所对应指针，当二者相遇时结束
        while (i < j) {//1.只有i<j时才继续移动，若相遇，则退出循环即可
            int area = (j - i) * Math.min(height[i], height[j]);//2.面积（长度由j-i决定，而高则由较矮者决定）
            result = Math.max(result, area);//3.每次遍历都更新一下面积的最大值，保证result即为所求
            if (height[i] < height[j]) {//4.2若i的高度小于j的高度，则此时矩形的面积的高度就由i的高度决定，而不是由j的高度决定，
                // 于是应当移动左边的柱子，使得高度增加，因此要移动i（即要移动矮者的指针），否则就移动指针j
                i++;
            } else {//4.2否则就移动指针j
                j--;
            }
        }
        //5.返回结果即可
        return result;
    }
}
