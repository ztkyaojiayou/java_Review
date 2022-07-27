package 数据结构与算法;

import java.util.LinkedHashMap;
import java.util.Stack;

/**
 * @author :zoutongkun
 * @date :2022/4/27 11:15 上午
 * @description :
 * @modyified By:
 */

class LinkedList{
//    private Stack stack = new Stack();
//    public Integer get(int key){
//        return stack.push(key);
//    }

}


public class LRU {
    int cap;
    LRU(int cap){
        this.cap = cap;
    }
    LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();

    public Integer get(int key){
        if (!map.containsKey(key)){
            return -1;
        }
        method(key);
        return map.get(key);
    }

    public void put(int key,int value){
        if (map.containsKey(key)){
            map.put(key,value);
            method(key);
            return;
        }

        if (map.size() >= cap){
            Integer key1 = map.entrySet().iterator().next().getKey();
            map.remove(key1);
        }
        map.put(key,value);
    }

    private void method(int key){
        Integer val = map.get(key);
        map.remove(key);
        map.put(key,val);
    }
}
