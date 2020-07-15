package java基础.序列化;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
 * 这个demo是毕向东老师的IO流里面的内容，也涉及到了序列化，就一并汇总于此。
 * Serializable:用于给被序列化的类加入ID号。
 * 用于判断类和对象是否是同一个版本。
 */
class Person01 implements Serializable/*标记接口*/ {

    /**
     * transient:非静态数据不想被序列化可以使用这个关键字修饰。
     */
    private static final long serialVersionUID = 9527l;
    private transient String name;
    private static int age;


    public Person01(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

}

public class ObjectStreamDemo {

    /**
     * @param args
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {

//		writeObj();
        readObj();
    }

    public static void readObj() throws IOException, ClassNotFoundException {

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("obj.object"));
        //对象的反序列化。
        Person01 p = (Person01)ois.readObject();

        System.out.println(p.getName()+":"+p.getAge());

        ois.close();

    }

    public static void writeObj() throws IOException, IOException {

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("obj.object"));
        //对象序列化。  被序列化的对象必须实现Serializable接口。
        oos.writeObject(new Person01("小强",30));

        oos.close();
    }

}

