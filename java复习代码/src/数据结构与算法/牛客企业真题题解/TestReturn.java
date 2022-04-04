package 数据结构与算法.牛客企业真题题解;

import java.lang.reflect.Method;

public class TestReturn {
    private void test() {
        int a;
//        return ;
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        B b = new B();
        b.B(1);
        C c = new C(1);

        //反射
        Class<? extends B> aClass = b.getClass();
        Class<B> bClass = B.class;
//        Class<?> aClass1 = aClass.forName("A");
        String name = aClass.getName();
        String className = "数据结构与算法.牛客企业真题题解.B";
        Class<?> aClass1 = Class.forName(className);
        System.out.println(aClass1);
        System.out.println(aClass1 == bClass);
        System.out.println(name);
        B b1 = aClass.newInstance();
        System.out.println(b1);
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
        }
    }
}

abstract class A {
    public void A() {

    }

    public void B() {
        System.out.println("BBBBB");
    }

}

class B extends A {
    void B(int a) {
        super.B();
        System.out.println(a + "--AAAA");
    }
}

class C {

     C(int a) {
         System.out.println("自定义的构造函数");
    }
//若将个构造函数私有化，则该类无法实例化
//    private C() {
//
//    }


}