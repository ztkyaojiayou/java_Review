package 数据结构与算法.od机考.华为od机考练习.常规题库;

import java.util.PriorityQueue;
import java.util.*;

/**
 * 58） 输入n个整数，输出其中最小的k个
 * 方法：使用堆排序--大顶堆--使用现成的优先队列实现即可
 * 注意：堆排序常用于解决topK问题，务必掌握！！！
 *
 * @author :zoutongkun
 * @date :2022/7/28 1:33 下午
 * @description :
 * @modyified By:
 */
class Main58 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int k = sc.nextInt();
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(((o1, o2) -> o2 - o1));
        for (int i = 0; i < num; i++) {
            int cuNum = sc.nextInt();
            priorityQueue.add(cuNum);
            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        }


        //此时优先队列中的元素就是最小的k个数，
        //但无法像list一样按照索引输出，只能使用增强for循环或通过poll方法输出
        //此时：
        //1）若使用增强for循环：无序
//        for (Integer integer : priorityQueue) {
//            System.out.print(integer+" ");//无序，不符合题目要求--3747 3113 2448 1386 2847 2163 60 713
//        }

        //2）若通过poll方法：则是从大到小输出，不符合题意，--3747 3113 2847 2448 2163 1386 713 60
        // 因为是最大堆嘛，所以最上面的元素是这个堆中最大的数，那能不能改成最小堆呢？
        // 答：不能，因为使用最大堆是为了得到最小的k个数，若改成最小堆，则得到的是最大的k个数了，则明显不对了。
//        int size = priorityQueue.size();//需要先计算出来，因为在poll时就是删除元素，size会变
//        for (int i = 0; i < size; i++) {
//            if (priorityQueue.size() == 1) {
//                System.out.print(priorityQueue.poll());
//            } else {
//                System.out.print(priorityQueue.poll() + " ");
//            }
//        }
        //3)最终方案：先转为list再排序再输出
        // （不过既然有用到了list和排序，那似乎就没有必要使用priorityQueue了，但这这是针对这个题而言啦）
        ArrayList<Integer> list = new ArrayList<>(priorityQueue);
        Collections.sort(list);

        for (int i = 0; i < list.size(); i++) {
//            if (i == list.size() - 1) {
//                System.out.println(list.get(i));
//            } else {
//                System.out.print(list.get(i) + " ");
//            }
            //其实不用刻意区分是否是最后一个元素，直接全部拼接空格就行
            System.out.print(list.get(i) + " ");
        }
    }
}

/**
 * 方法2：最原始的方法
 */

class Main580 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            int[] arr = new int[n];
            //加入数组中
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            //排序
            Arrays.sort(arr);
            //打印输出
            for (int i = 0; i < k; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }
}

