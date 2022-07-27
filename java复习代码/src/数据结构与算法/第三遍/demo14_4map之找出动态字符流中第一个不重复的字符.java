package 数据结构与算法.第三遍;

import java.util.HashMap;
import java.util.Map;

public class demo14_4map之找出动态字符流中第一个不重复的字符 {
    //0.使用一个map做标记
    //具体为：若该字符不存在时，就设为1，否则设为-1
    HashMap<Character, Integer> map = new HashMap<>();

    //1.先插入字符
    public void Insert(char ch) {
        if (!map.containsKey(ch)) {
            map.put(ch, 1);
        }
        //即表示该元素已重复，不是所求
        map.put(ch, -1);
    }

    //2.再查找即可
    public char findMethod() {
        //这是遍历map的一种常用方式，记住即可
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            //找到value为1的字符即为所求
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        //否则返回#（可自定义）
        return '#';
    }

}
