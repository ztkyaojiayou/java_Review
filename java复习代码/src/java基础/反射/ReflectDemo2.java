package java基础.反射;

import java.lang.reflect.Constructor;

public class ReflectDemo2 {

    /**
     * 主题2：使用反射机制调用构造函数对对象进行初始化并创建该对象的过程：
     * （本来是很简单的事情，在这里只是更深入更细节而已）
     * 注意：易知，这种创建对象的方式和我们之前所使用的的方式的一个最大区别就是：
     * 可以直接使用类名来创建对象，而不需要该类定义在本区域中,常用于spring中
     */
    public static void createNewObject() throws ClassNotFoundException, InstantiationException, IllegalAccessException{

         //1.调用无参构造函数对对象进行初始化的过程：

         //早期：new时候，先根据被new的类的名称找寻该类的字节码文件，并加载进内存，
         //再创建该字节码文件对象，接着创建该字节文件的对应的Person对象.
         //ztk.反射.序列化Person p = new ztk.反射.序列化Person();

        //现在：
        String name = "ztk.反射.序列化Person";
        //1.1找寻该名称类文件，并加载进内存，同时获得该类的字节码对象，即该类的Class对象clazz。
        Class clazz = Class.forName(name);
        //1.2如何产生该类的对象呢？使用newInstance()方法即可,此时会去找对应的构造函数对对象进行初始化
        //注意：对于无参构造函数（即便被我们改写），无需手动获取该构造函数，直接创建对象即可
        //这是有调用有参构造函数所不同的地方，下面会讲到
        Object obj  = clazz.newInstance();//此时已经创建了一个对象
        Person person  = (Person)clazz.newInstance();//再创建一个对象并强转成Person对象
        Object obj01 = Class.forName(name).newInstance();//再创建另外一个对象（两步放一起写了，这是常用的）

        System.out.println(obj);//ztk.反射.序列化Person@610455d6
        System.out.println(person);//ztk.反射.序列化Person@511d50c0
        System.out.println(obj01);//ztk.反射.序列化Person@60e53b93
        System.out.println(obj01 == obj);//false,因为二者不是同一个对象
    }

    public static void createNewObject_2() throws Exception {

        /*
         * 2.调用有参构造函数，对对象进行初始化的过程。这里的重点就是如何获取到这个构造器。
         *即深入研究 ztk.反射.序列化Person p = new ztk.反射.序列化Person("小强",39); 这行代码是怎么执行的？
         *
         * 问题即为：
         * 当使用有参构造方法对指定名称对应的类中的对象进行初始化时是怎么进行的？
         * 答：既然是通过指定的构造函数对对象进行初始化，
         * 那么就应该先获取到该构造函数，方法：通过字节码文件对象即可完成。
         * 该方法是：getConstructor(paramterTypes);
         *
         */
        String name = "ztk.反射.序列化Person";
        //2.1找寻该名称类文件，并加载进内存，并产生Class对象。
        Class clazz = Class.forName(name);
        //2.2再获取到了指定的构造函数对象。（关键）
        Constructor constructor = clazz.getConstructor(String.class,int.class);//因为传入的是String类型和int类型

        //2.3最后通过该构造器对象的newInstance方法进行对象的初始化，此时会去找对应的构造函数对对象进行初始化。
        Object obj = constructor.newInstance("小明",38);

    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, Exception {

        createNewObject();
        //createNewObject_2();

    }

}
