package java基础.变量存储位置及传参;

import java.util.Arrays;
//注意：引用即地址
public class test04变量存储位置及传参 {
    //对于一个方法，会在栈中开辟一块内存空间
    public static void main(String[] args) {
        //均为局部变量，其引用均存入栈中
        int i = 1;//基本数据类型，其引用和值均存入栈中
        String str = "hello";//字符串常量，引用存入栈中，值存入常量池中
        /**
         * Integer属于int的包装类，因此num是一个对象，其引用存入栈中，对于值，分为两种情况：
         * （1）若在-128-127之间时，则会被存入常量池中（源码使用了valueOf函数，用于缓存，此时相当于常量，不会二次创建）
         * （2）若不在此区间，则会被临时创建，相当于一个普通对象，其值/对象存入堆中，且不再是常量
         */
        Integer num = 200;
        int[] arr = {1,2,3,4,5};//同样的，引用存入栈中，值存入堆中
        MyData my = new MyData();//基本对象，引用存入栈中，值存入堆中

        /**
         * 由于也是方法，因此会在栈中再创建一块新的内存空间，且方法执行完之后就会被gc
         * 同时把这五个变量传入change方法中一一对应，其中：基本数据类型传的是值，引用类型传的是地址；
         * 操作的是这个方法中的变量，而不是传入的变量
         */

        change(i,str,num,arr,my);
        //务必清楚：打印的是原值或地址
        System.out.println("i = " + i);//1
        System.out.println("str = " + str);//hello
        System.out.println("num = " + num);//200
        System.out.println("arr = " + Arrays.toString(arr));//{2,2,3,4,5}
        System.out.println("my.a = " + my.a);//11
    }
    //该方法的内存空间用完就会被gc
    public static void change(int j, String s, Integer n, int[] a,MyData m){//
        j += 1;//j变为2，但原i不变，还是1
        //对于string，由于在常量池中，字符串对象不可变，因此字符world会被创建并存入常量池中，
        //同时拼接成的helloworld会当做一个新的常量存入常量池中，
        //地址指向这个新常量（即地址已经改变），则原str还是指向hello
        s += "world";
        //因为是Integer类，则相当于创建一个新对象，n变为201，和string一样，存入堆中，
        // 同时地址指向新对象（即地址已经改变），原num还是200
        n += 1;
        a[0] += 1;//改变的是数组的某个元素，地址不变，则原值改变，即arr={2,2,3,4,5}
        m.a += 1;//同理，改变的是值，地址不变，则原值my.a=11
    }
}
class MyData{
    int a = 10;
}
