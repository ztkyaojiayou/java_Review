package 数据结构与算法.剑指offer题解.双指针;
import java.lang.reflect.Array;
import java.util.*;

/**
 * 题目描述
 * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，
 * 如果有多对数字的和等于S，输出两个数的乘积最小的。
 * 输出描述:
 * 对应每个测试案例，输出两个数，小的先输出。
 */

/**
 * 思路解析：这种题，很明显就可以找到两种方法，
 * 即使用哈希字典映射法和双指针法，且都是很不错的方法，务必掌握
 *
 * （1）方法1：使用双指针
 * 使用双指针，一个指针指向元素较小的值，一个指针指向元素较大的值。
 * 指向较小元素的指针从头向尾遍历，指向较大元素的指针从尾向头遍历。
 * 要特别注意：最外层的两个数的乘积就是最小，千万别被题目误导！！！
 *
 * - 如果两个指针指向元素的和 sum == target，那么得到要求的结果；
 * - 如果 sum > target，移动较大的元素，使 sum 变小一些；
 * - 如果 sum < target，移动较小的元素，使 sum 变大一些。
 *
 */
class 和为S的两个数57_1 {
    public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        //0.结果集(list型）
        ArrayList<Integer> ans = new ArrayList<>();

        //1.定义双指针，一头一尾
        int i = 0, j = array.length - 1;
        //2.开始遍历（只要i<j，就一直遍历）
        while (i < j) {
            //当前的和
            int cur = array[i] + array[j];
            if (cur == sum)//2.1若该和等于目标值sum，则把这两个数加入结果集list中。
                return new ArrayList<>(Arrays.asList(array[i], array[j]));//Arrays.asList：把数组变为list（务必记住）
            if (cur < sum)//2.2若小于sum，则左指针右移
                i++;
            else//2.3若大于sum，则右指针左移
                j--;
        }
        //3.最后，返回结果集list即可
        return new ArrayList<>();
    }
}

/**
 * 方法2：使用hashMap作字典映射
 * 要求a + b = sum, 如果已知a， 那么b = sum - a
 * 所以可以先将b添加入哈希中（因为数组是有序的，因此b是唯一的），
 * 然后遍历一遍数组设为a，在哈希中寻找是否存在b，然后再更新乘积最小值
 */
class Solution57_2 {
    public ArrayList<Integer> FindNumbersWithSum(int [] array, int sum) {
        //0.结果集(list型）
        ArrayList<Integer> ans = new ArrayList<>();
        //1.特判，即若数组为空或长度小于2，则返回空的list即可
        if (array==null || array.length<2)
            return ans;
        //2.使用一个hashMap作字典映射（sum-array[i]作为key，array[i]作为value）
        Map<Integer, Integer> map = new HashMap<>();
        //3.开始遍历数组，若存在array[i]，就把sum-array[i]和array[i]作为结果加到结果集中，
        //若不存在，就把（sum- array[i],array[i]）存入map中
        for (int i=0;i<array.length;i++){
                if(map.containsKey(array[i])){
                    ans = new ArrayList<>();//为什么又要重新建立此list？？？
                    ans.add(sum-array[i]);
                    ans.add( array[i]);
            }else {
                    map.put(sum- array[i],array[i]);
            }
        }
        //4.返回最终的结果list即可
        return ans;
    }
}