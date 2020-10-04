package 秋招笔试.美团;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class test05 {
    public static void main(String[] args) {
        //输入
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int[] arr = new int[m];
        //队列
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0;i<m;i++){
            int b = sc.nextInt();
            arr[i] = b;
        }
        //处理
for (int i= 0;i< m;i++){
    //如果list中不包含该元素
    if (!list.contains(arr[i])){
        list.add(arr[i]);
    }else {
        //若包含
        list.remove(arr[i]);//remove不对
        list.add(arr[i]);
    }
}
for (int i = 0;i<list.size();i++)
        System.out.println(list.get(i));
    }
}


class Main33 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        //1.如果当前点到的同学不在队列中，则加入队列中的第一个
        Stack<String> stack = new Stack<String>();
        //2.如果当前点到的同学在队列中，则该同学出队，站到队列中第一个，并且不保留他之前的空位。
        // 3 1 2 1
        // 1 2
        for(int i=0;i<m;i++) {
            String index = sc.next();
            if(!stack.contains(index)) {
                stack.push(index);
            }else {
                stack.remove(index);
                stack.push(index);
            }
        }
        while(!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
