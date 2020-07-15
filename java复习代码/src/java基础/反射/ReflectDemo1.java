package java基础.反射;


/*
 * JAVA反射机制是在运行状态中，对于任意一个类 (class字节码文件)，都能够知道这个类的所有属性和方法；
 * 对于任意一个实例对象，都能够调用它的任意一个方法和属性；
 * 这种动态获取信息以及动态调用对象的方法的功能称为java语言的反射机制。
 *
 * 动态获取类中信息，就是java反射 。
 * 可以理解为对类的解剖。
 *
 * 要想要对字节码文件进行解剖，必须要有字节码文件对象.
 * 字节码文件在反射中非常重要，只要拿到了它，则一切都好办！！！
 *
 * 如何获取字节码文件对象呢？
 * 有三种方式
 *
 */

public class ReflectDemo1 {

    /**
     * 主题1：获取一个类的字节码对象
     * 字节码对象指的就是：
     * Java把一个xxx.java文件经过编译后形成的xxx.class文件
     * （jvm真正执行的是这个文件，也称之为该类的字节码文件，
     * 这个文件中就包含了这个类的所有信息，包括成员变量，成员方法以及构造方法）
     * 也抽象成了一个类，这个类就叫Class类
     *这个类的对象就是我们所说的“一个类的字节码对象”，
     * 只要拿个这个对象，就可以操作该类中的所有信息了，
     * 包括获取、修改和执行成员变量，成员方法以及通过构造方法创建对象啦
     *
     */

    /*
     * 获取一个类的字节码对象（即Class对象,格式为：class 全类名）的方式有如下三种：
     * 1、方式一：
     * 可以直接使用Object类中的getClass()方法的。
     * 想要用这种方式，必须要明确具体的类，并创建对象。
     * 麻烦，不推荐.
     *
     */
    public static void getClassObject_1(){

        Person p = new Person();
        Class clazz = p.getClass();

        Person p1 = new Person();
        Class clazz1 = p1.getClass();
        System.out.println(clazz);//class ztk.反射.序列化Person

        System.out.println(clazz==clazz1);//true 即一个类的字节码对象是确定的，不管是通过哪个实例对象获取
    }

    /*
     * 2、方式二：
     * 任何数据类型都具备一个静态的属性.class来获取其对应的Class对象。
     * 相对简单，但是还是要明确用到类中的静态成员。
     * 还是不够扩展。
     *
     */
    public static void getClassObject_2() {

        Class clazz = Person.class;

        Class clazz1 = Person.class;

        System.out.println(clazz);//class ztk.反射.序列化Person
        System.out.println(clazz==clazz1);//true

    }

    /*
     * 3、方式三：（推荐）
     * （首先明确，Class本身就是一个类，它主要用于操作我们所定义的类，它里面定义了很多方法，
     * 我们现在说的“要获取一个类的字节码对象”，就是该类的一个对象，就需要用到这个类的中的forName方法来获取）
     *
     * 只要通过给定的类的字符串名称就可以获取该类，更为扩展。
     * 可是用Class类中的方法完成。
     * 该方法就是forName.
     * 这种方式只要有名称即可，更为方便，扩展性更强。
     */

    public static void getClassObject_3() throws ClassNotFoundException {

        String className = "ztk.反射.序列化Person";

        Class clazz = Class.forName(className);

        System.out.println(clazz);//class ztk.反射.序列化Person
    }

    //4、测试类：
    public static void main(String[] args) throws ClassNotFoundException {

        //getClassObject_1();
        //getClassObject_2();
        getClassObject_3();

    }

}

