package 数据结构与算法.算法.递归;

/**
 * 程序员必备的算法之（1）递归算法（Recursion）：即一个方法自己调用自己，但每次调用时传入的变量不同
 *
 * 个人认为，递归的代码表面上看似乎就是一种思路或伪代码，不涉及具体的解决方法
 * 但其实它就是一种具体的方法，把递归过程写出来就能理解了
 *
 * 特点：
 * 1.当程序执行到一个方法时（任何方法），就会开辟一个受保护的独立的空间(栈)用于执行相应的代码
 * 2.每个空间都会复制一份与该方法有关的数据(局部变量)，且都是独立的，不会相互影响，比如n变量
 *   但若方法中使用的是引用类型变量(比如数组)，就会共享该引用类型的数据.
 * 3.递归必须向退出递归的条件逼近，否则就会无限递归，最终出现StackOverflowErro错误)
 * 4.当一个方法执行完毕，或者遇到return, 就会返回，遵守“谁调用，就将结果返回给谁”的规则
 *   同时该方法也就执行完毕，开始执行它的上一级方法
 */
public class RecursionDemo {

        public static void main(String[] args) {
            // TODO Auto-generated method stub
            //通过打印问题，理解递归调用机制
            test(4);

            //int res = factorial(3);
            //System.out.println("res=" + res);
        }
        //1.打印问题.
        public static void test(int n) {
            if (n > 2) {
                test(n - 1);
            } //else {
            System.out.println("n=" + n);
            // }
        }
        //2.阶乘问题
        public static int factorial(int n) {
            if (n == 1) {
                return 1;
            } else {
                return factorial(n - 1) * n; // 1 * 2 * 3
            }
        }


    }

