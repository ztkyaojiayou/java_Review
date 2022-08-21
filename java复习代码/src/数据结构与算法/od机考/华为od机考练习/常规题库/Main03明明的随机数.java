package 数据结构与算法.od机考.华为od机考练习.常规题库;

/**
 * 03）明明的随机数
 * 描述
 * 明明生成了NN个1到500之间的随机整数。
 * 请你删去其中重复的数字，即相同的数字只保留一个，
 * 把其余相同的数去掉，然后再把这些数从小到大排序，按照排好的顺序输出。
 *
 * 参考：80）整型数组合并--使用treeSet
 * <p>
 * 知识点：TreeSet是依靠TreeMap来实现的
 * TreeSet是一个有序集合，
 * 它的元素会默认按照自然顺序/升序排列（而不是指排列/输出顺序和插入顺序一致），而不像哈希表那样毫无规律。
 * 也就是说TreeSet中的对象元素需要实现Comparable接口。
 * 当然，你也可以在创建TreeSet对象时传递一个比较器来实现你自己的排序方式
 * TreeSet类中跟HashSet类一样也没有get()方法来获取列表中的元素，
 * 所以也只能通过迭代器或增强for循环方法来获取。
 *
 * @author :zoutongkun
 * @date :2022/7/28 10:34 上午
 * @description :
 * @modyified By:
 */

import java.util.Scanner;
import java.util.TreeSet;

/**
 * 方法1：使用treeSet：既可以去重，又可以排序（默认自然顺序/升序）
 */
class Main03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //获取个数
        int num = sc.nextInt();
        //创建TreeSet进行去重排序
        TreeSet set = new TreeSet();
        //输入
        for (int i = 0; i < num; i++) {
            set.add(sc.nextInt());
        }
        //输出
        //方式1：使用增强for循环输出
        //会默认排序
        for (Object o : set) {
            System.out.println(o);
        }
        //方式2：使用迭代器遍历输出
//         Iterator iterator = set.iterator();
//         while (iterator.hasNext()){
//             System.out.println(iterator.next());
//         }

    }
}

/**
 * 方法2：主要的思路就是空间换时间，还有利用数组的下标。
 * 但一般来讲，使用数组是很常见的，不太算利用空间换时间
 * <p>
 * 具体做法：
 * 1）创建一个1001个数字的数组，在输入一个1-1000的数字时将该数组对应下标的值设置为1。
 * 2）然后再从小到大循环数组中值为1的下标输出，因为下标本身有序的因此就不用排序。
 * 优化：
 * 1）字节数组更省空间，因为数组存的只是个标识1，所以能用就行。
 * 2）数组长度只需要501即可（因为题干说了，输入的值到范围<=500），因为数据个数不影响数组长度，
 * 影响数组长度的只有数据的范围因为是按照输入数据对应的索引修改数据的，
 * 即使有1000个500，它也只是在500这个位置修改/覆盖数据1000次而已
 */
class Main030 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        while (sc.hasNext()) {
        int num = sc.nextInt();
//        int[] arr = new int[1001];
        //优化后
        byte[] arr = new byte[501];
        for (int i = 0; i < num; i++) {
            //注意：即便是多行，也可以直接使用nextInt一个一个录入
            int n = sc.nextInt();
            //易知，当有重复的数时，会覆盖，相当于去重，完美！
            arr[n] = 1;
        }
        for (int i = 1; i < arr.length; i++) {
            //按照索引输出值为1的元素即可，因为索引天然有序
            if (arr[i] == 1) {
                System.out.println(i);
            }
        }
//        }
    }

}
