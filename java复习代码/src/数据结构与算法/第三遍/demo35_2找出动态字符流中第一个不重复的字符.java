package 数据结构与算法.第三遍;


import java.util.HashMap;
import java.util.Map;

public class demo35_2找出动态字符流中第一个不重复的字符 {
    //0.使用一个map做标记
    HashMap<Character, Integer> map = new HashMap<>();
    //1.先插入字符
    public void Insert(char ch){
        if (!map.containsKey(ch)){
            map.put(ch,1);
        }
        map.put(ch,-1);
    }

    //2.再查找即可
    public char findMethod(){
   for (Map.Entry<Character,Integer> entry : map.entrySet()){//这是遍历map的一种常用方式，记住即可
       if (entry.getValue() == 1){//找到value为1的字符即为所求
           return entry.getKey();
       }
   }
   return '#';//否则返回#（可自定义）
    }

}
