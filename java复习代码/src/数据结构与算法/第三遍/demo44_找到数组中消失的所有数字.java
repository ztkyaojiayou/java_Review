package 数据结构与算法.第三遍;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//易知，很容易想到用hashMap
public class demo44_找到数组中消失的所有数字 {
    //先来简单解法：
    public List<Integer> findDisappearedNumbers01(int[] nums) {
        HashMap<Integer, Boolean> map = new HashMap<>();
        //先把每个元素都标记为true
        for (int i = 0;i<nums.length;i++){
            map.put(nums[i],true);
        }
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 1;i<nums.length;i++){
            if (!map.containsKey(i)){//若不存在，则该下标即为所求
                res.add(i);
            }
        }
        return res;
    }
}
