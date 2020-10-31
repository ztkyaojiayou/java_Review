package 数据结构与算法.剑指offer题解.字符串;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 题目：请实现一个函数用来找出字符流中第一个只出现一次的字符。
 * 例如，当从字符流中只读出前两个字符“go”时，第一个只出现一次的字符是‘g’。
 *      当从该字符流中读出前六个字符“google”时，第一个只出现 1 次的字符是”l”。
 *
 * 【自然想法】：
 *      用LinkedHashMap，
 *          以这个字符做为key
 *          当这个字符第一次出现时，将其value置为1，
 *          当这个字符出现了第二次的时候，直接将其value设为-1，
 *      最后遍历map.values()，找到第一个是1的那个Entry，返回key √
 *
 * 【书上的做法】：new了一个 CharStatistics
 *      里面有一个occurrence[256]这么大的数组：相当于HashMap中的key（以字符的ASCII码为index）
 *                                   书上是以该字符在字符串中的位置作为其 val。
 *      然后先将occurrence[]中的所有元素置为-1（代表从未出现过）
 *      然后开始遍历字符串，index = 0
 *              第一次出现-->occurrence[ch] = index++;
 *              第二次出现(occurrence[ch]!=-1)   -->occurrence[ch] = -2;
 *      最后遍历occurrence[]数组，
 *          找到第一个occurrence[i] != -1的字符，返回。
 *
 */
public class string07字符流中第一个不重复的字符 {
    //1.先创建一个map，用于存储
        HashMap<Character, Integer> map = new LinkedHashMap<>();

        //Insert one char from stringstream
    //2.把目标字符传入，把每个字符当做map的key，若此字符不存在，则把其value置为1，否则置为-1，依次按照此规则放入到map中
        public void Insert(char ch) {
            //containsKey(ch)表示map中是否存在有以字符ch为key的value
            // 若对应的值value为空，则说明此字符还没有出现过，把其value设置为1
            if (!map.containsKey(ch)) {
                map.put(ch, 1);
            } else {//否则说明此字符已经出现过，把其value置为-1
                //至此，易知，只要是重复出现过的字符，其值必为-1，只出现过一次的则为1，此key（即此字符）即为所求
                map.put(ch, -1);
            }

//            char[] chars = ch.toCharArray();//把字符串转换为字符数组
//            for(char ch : chars){//遍历chars
//                //以chars数组中的字符作为map中的key，get(ch)表示获取其对应的value
//                //若对应的值为空，则说明此字符还没有出现过，把其value设置为1
//                if (map.get(ch) == null){
//                    map.put(ch, 1);
//                } else {//否则说明此字符已经出现过，把其value置为-1
//                    map.put(ch, -1);
//                }
//            }
        }

    //return the first appearence once char in current stringstream
    //3.开始判断，取目标值，即entry.getValue() == 1的值
    public char FirstAppearingOnce() {

        // Map是java中的一个接口，Map.Entry是Map的一个内部接口。
        // map.keySet()方法：返回值是Map中key值的集合；
        // map.entrySet()方法：返回值是一个Set集合，此集合的类型为Map.Entry。
        // Map.Entry是Map声明的一个内部接口，此接口为泛型，定义为Entry<K,V>。它表示的是Map中的一个实体（key-value）。
        // 接口中有我们常用的getKey(),getValue方法。

        //这是增强for循环，要遍历的东西是map.entryset()，也就是说把这个map拆开，然后在for循环里遍历，
        //每次遍历都可以得到一个Entry<String,Integer> entry对象（key+value）
        for(Map.Entry<Character, Integer> entry : map.entrySet()){//遍历map的entrySet（key+value）
            //获取value，若为1，则说明是第一次出现的字符，即为所求，获取其key（也就是其对应的字符
            if (entry.getValue() == 1){
                return entry.getKey();
            }
        }
        return '#';
    }
}
