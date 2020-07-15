package java基础.Collections工具类;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
collections工具类中的二分查找方法：BinarySearch（）
返回：
（1）若找到，则返回第一个出现的值的索引
（2）若没找到，则返回这个指定对象应该在的索引的负数再减1。
 */
public class CollectionsBinarySearch {
    public static void main(String[] args) {
        List<String>  list = new ArrayList<String>();
        list.add("abc");
        list.add("fcd");
        list.add("bde");
        list.add("def");
        System.out.println("原list为：" + list);//[abc, fcd, bde, def]
        //System.out.println(list.get(0));//可以获取到第一个元素：abc

        //在进行二分查找时，必须先进行排序，否则结果是不确定的，是不准确的
        //1.不排序时：结果是错的（且该方法自己不会主动排序，必须手动排序）
        int index = Collections.binarySearch(list,"bde");//
        int index01 = Collections.binarySearch(list,"fd");//
        System.out.println("不排序时（错误），bde所在的位置为：" + index);//-2，不正确
        System.out.println("不排序时（错误），fd所在的位置为："+ index01);//-5,不正确

        //2.先（手动）排序，再查找，正确
        Collections.sort(list);

        System.out.println("排序后为：" + list);//[abc, bde, def, fcd]
        int index02 = Collections.binarySearch(list,"bde");
        int index021 = Collections.binarySearch(list,"cde");
        System.out.println("bde所在的位置为（正确）：" + index02);//1,正确
        System.out.println("cde所在的位置为（正确）：" + index021);//-3,正确（因为不存在）


    }


}
