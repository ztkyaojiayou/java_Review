package 数据结构与算法.LeetCode题解.数组;

/**
 * 238. 除自身以外数组的乘积
 * 给你一个长度为 n 的整数数组 nums，其中 n > 1，
 * 返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 *
 * 示例:
 * 输入: [1,2,3,4]
 * 输出: [24,12,8,6]
 *
 * 提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。
 * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 * 进阶：
 * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 */

/**
 * 思路解析：可以使用两个数组来分别存储某个数组位置左边的所有元素乘积以及其右边的所有元素乘积，
 * 最后将两个数组各个位置的元素相乘，就能够得到最终的答案
 * （即：乘积 = 当前数左边的乘积 * 当前数右边的乘积）。
 * 对于进阶所提到的在常数空间复杂度内完成题目，可以直接将答案存储在输出数组中。
 * 即首先循环数组，得到图中的left数组，只不过此时是使用res数组来存的。
 * 本代码就是使用的原数组result来保存left数组的，很巧妙呀~
 */
public class 除自身以外的数组元素的乘积238 {
        public int[] productExceptSelf(int[] nums) {
            int len = nums.length;
            int[] res = new int[len];

            //1.先求各个元素的左边乘积和，并把其先临时保存在result数组中，而不去开设一个新的数组，可以达到节省空间的作用
            int leftSum = 1;//1.0约定，第一个元素的左乘积和为1
            for(int i=0;i<len;i++){//遍历，求和
                res[i] = leftSum;//1.1先求第一个位置的左乘积和，并放入result数组中
                leftSum*=nums[i];//2.再把该“左数组和”变量累乘即可，这样的话，下一个位置的左乘积和刚好是使用的“累乘到上一个位置的和”
            }
            /**
             *  此时数组result中存放的是每个位置的左乘积（非常关键）
             */
            //2.再开始由后往前遍历，方法也一样，主要是要注意一点，此时存入result数组中的值即为最终结果
            int rightSum = 1;//2.0约定，最后一个元素的右乘积和也为1
            for(int j=len - 1;j>=0;j--){
                res[j]*=rightSum;//2.1即当前位置的最终乘积和为：原result[i](即该元素的左边乘积和）乘以 该元素的右边乘积和rightSum
                rightSum*=nums[j];//2.2同时更新右边乘积和即可，用于下一个位置的计算
            }

            //3.最终，返回该result数组即可
            return res;
        }
    }
