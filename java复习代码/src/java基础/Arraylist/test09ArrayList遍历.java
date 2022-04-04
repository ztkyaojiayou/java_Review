package java基础.Arraylist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;


public class test09ArrayList遍历 {
    /**
     * 五种输出ArrayList的方法
     * 1.使用for循环+get(i)，按照元素获取
     * 2.使用foreach循环，按照对象获取
     * 3.直接使用println方法打印（会调用默认的toString方法（当然已重写））
     * 4.使用迭代器iterator（只能正向遍历，推荐）
     * 5.使用List独有的ListIterator迭代器（可正反遍历）
     */
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        //1.使用for循环+get(i)，按照元素获取：12345
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
        }
        System.out.println("\n----------------------");
        //2.使用foreach循环，按照对象获取：12345
        for (Object object : list) {
            System.out.print(object);
        }
        System.out.println("\n----------------------");
        //3.直接使用println方法打印（会调用默认的toString方法（当然已重写））
        System.out.println(list);//默认调用toString：[1, 2, 3, 4, 5]
        System.out.println("\n----------------------");
        //4.使用迭代器iterator（只能正向遍历，推荐）
        Iterator i = list.iterator();
        while (i.hasNext()) {
            System.out.print(i.next());// 12345
        }
        System.out.println("\n----------------------");
        //5.使用List独有的ListIterator迭代器（可正反遍历）
        //5.1：正着输出
        ListIterator li = list.listIterator();
        while (li.hasNext()) {
            System.out.print(li.next());//12345
        }
        System.out.println("\n----------------------");
        //5.2：反向输出
        while (li.hasPrevious()) {
            System.out.print(li.previous());//54321
        }
        System.out.println("\n----------------------");
    }
}







