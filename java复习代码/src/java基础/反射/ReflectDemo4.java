package java基础.反射;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ReflectDemo4 {

    /**
     * 主题4：使用反射机制获取指定类中的方法并执行
     * @throws Exception
     */

    //1.获取指定类中的所有方法。
    public static void getMethodDemo() throws Exception {

        //0.先获取该类的字节码对象，用于后面操作
        Class clazz = Class.forName("java基础.反射.Person");

        //1.再获取方法
        //1.1按照下面这样写的话，会获取所有公有的方法(包括Object中的所有方法，因为它是所有类的父类）（存入数组中）
        Method[] methods  = clazz.getMethods();
        //1.2于是要调用getDeclaredMethods()来获取本类中所有方法，
        //包含私有和静态（覆盖掉了上面所获取的Object中的所有方法）。
        methods = clazz.getDeclaredMethods();

        //2.遍历出所有方法名并打印
        for(Method method : methods){
            System.out.println(method);
        }
    }

    //2.获取空参数的普通方法并执行
    public static void getMethodDemo_2() throws Exception {

        //0.先获取该类的字节码对象，用于后面操作
        Class clazz = Class.forName("java基础.反射.Person");
        //1.先获取空参数的普通方法，等会执行。
        Method method = clazz.getMethod("show", null);

//		Object obj = clazz.newInstance();//创建无参的对象
        //2.创建有参的对象
        //2.1先获取有参构造方法
        Constructor constructor = clazz.getConstructor(String.class,int.class);
        //2.2再去初始化，并完成对象的创建
        Object obj = constructor.newInstance("小明",37);
        //3.此时对象创建完成，于是就可以执行此方法了（调用invoke方法即可）
        method.invoke(obj, null);

    }

    //3.获取有参数的普通方法并执行（同2）
    public static void getMethodDemo_3() throws Exception {

        //0.先获取该类的字节码对象，用于后面操作
        Class clazz = Class.forName("java基础.反射.Person");
        //1.再获取一个有参的方法，等会执行
        Method method = clazz.getMethod("paramMethod", String.class,int.class);
        //2.创建一个无参的对象（不需要主动获取无参构造方法，即便已被重写）
        Object obj = clazz.newInstance();
        //3.此时已经创建了一个对象，因此就可以直接调用刚才获取到的有参方法了，
        //也是调用invoke方法，同时传入该方法的参数值即可
        method.invoke(obj, "小强",89);

    }

    public static void main(String[] args) throws Exception {

        getMethodDemo();
//        getMethodDemo_2();
//        getMethodDemo_3();

    }


}
