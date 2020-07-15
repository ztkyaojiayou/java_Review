package java基础.反射;

import java.lang.reflect.Field;

public class ReflectDemo3 {

    /*
     * 主题3：从反射的角度看，获取指定类中的字段/成员变量。
     *
     */
    public static void getFieldDemo() throws Exception {

        Class clazz = Class.forName("java基础.反射.Person");

        Field field = null;//只能获取公有的（该方法必须有参数，也即必须获取指定的字段，而不是获取所有）
        field = clazz.getDeclaredField("age");//只获取本类，但包含私有。（可以读）

        //要对私有字段的访问取消权限检查才可以写。相当于进行暴力访问（即默认不可以写，因为person类不在本类中）
        //否则无法访问，会报错：java.lang.IllegalAccessException。
        field.setAccessible(true);

        Object obj = clazz.newInstance();//创建一个无参对象（会去调用无参构造方法）

        field.set(obj, 89);//给获取到的age字段赋值89(若该类中已经赋了初值，则会被覆盖）

        Object o = field.get(obj);//再获取该字段的值

        System.out.println(o);//打印出来该字段的值，89

//		ztk.反射.序列化Person p = new ztk.反射.序列化Person();
//		p.age = 30;


    }

    public static void main(String[] args) throws Exception {

        getFieldDemo();
//        //平时的访问方法：
//        序列化Person p = new 序列化Person();
//        System.out.println(p.age01);//但由于person类不再该类中，因此只可以访问其public字段
    }
}
