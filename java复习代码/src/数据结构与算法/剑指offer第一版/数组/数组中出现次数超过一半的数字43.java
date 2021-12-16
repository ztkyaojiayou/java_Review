package 数据结构与算法.剑指offer第一版.数组;

/**
 * 题目描述
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。
 * 如果不存在则输出0。
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 思路解析：该题同LeetCode的169题
 * 可以使用哈希字典法，排序法，摩尔投票法等
 */

/**
 * 方法1：哈希字典法
 */
class 数组中出现次数超过一半的数字43_1 {
    public int MoreThanHalfNum_Solution(int [] array) {
            int length = array.length;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < array.length; i++) {
            if (map.containsKey(array[i])) {
                map.put(array[i], map.get(array[i])+1);

            } else {
                map.put(array[i], 1);
            }
            if (map.get(array[i]) > length/2) {
                return array[i];
            }
        }
        return 0;
    }
}

/**
 * 方法2：排序，找中间值即可
 */
class Solution43_2 {
    public int MoreThanHalfNum_Solution(int [] array) {
        //0.特判
        if ( array == null || array.length == 0) {
            return 0;
        }
        //1.先对数组排序（默认为升序）
        Arrays.sort(array);
        int count=0;
        //2.记录数组中等于该数组中间索引的元素（若题目明确说肯定存在该数的话，就不用做这一步，见LeetCode第169题）
        for(int i=0;i<array.length;i++){
            if(array[i]==array[array.length/2]){
                count++;
            }
        }
        //3.再判断该元素的数量是否超过一半，
        //3.1若是，则说明符合条件，返回中间索引值即可
        if(count>array.length/2){
            return array[array.length/2];
        }else{//3.2否则说明不存在超过一半的数，返回0即可
            return 0;
        }
    }
}

/**
 * 方法3：摩尔投票法（推荐）
 */
class Solution43_3 {
    public int MoreThanHalfNum_Solution(int [] array) {
        //0.特判
        if ( array == null || array.length == 0) {
            return 0;
        }
        //1.先初始化：
        //候选人:设为第一个元素array[0]，其票数vote_count置为1
        int candi_num = array[0];
        int vote_count = 1;
        //2.开始遍历数组中的各个元素
        for (int i = 1; i < array.length; i++) {//从第二个元素开始遍历即可
            if (array[i] == candi_num){//2.1若当前元素与候选人相等，则使其票数加一
                vote_count++;
            }
            else{//2.2而若不相等，则令其票数减1
                vote_count--;
            }
            if (vote_count == 0) {//2.3若票数为0了，而数组元素还没遍历完毕，则说明该候选人不是超过一半的那个数，
                //于是，把候选人换为当前值nums[i],同时将它的票数也重置为1，继续进行上述投票动作，
                //直到最后，最后的那种候选人即为所求。
                candi_num = array[i];
                vote_count = 1;
            }
        }

        //3.此时已经选出了候选人candi_num，但它还不一定就是我们想要的结果，因为其个数可能并没有过半数，因此还要判断一下
        //（这同样是因为题目说了该数可能不存在，这也是和leetcode第169题所不同的地方）
        vote_count = 0;
        for (int arr : array) {//3.0遍历数组，统计候选人candi_num的个数是否大于了一半，若是，则其确实就是我们所求的，
            //否则，说明该元素并不是超过一半的元素，也说明该数并不存在，返回0即可
            if (arr == candi_num)
                vote_count++;
        }
        if (vote_count > array.length/2){//3.1若个数超过一半，则其即为所求
            return candi_num;
        }
        else{//3.2否则，说明该数不存在
            return 0;
        }
    }
}