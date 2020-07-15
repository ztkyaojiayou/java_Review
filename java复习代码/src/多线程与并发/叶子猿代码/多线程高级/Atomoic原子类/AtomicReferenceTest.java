package 多线程与并发.叶子猿代码.多线程高级.Atomoic原子类;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 3.原子引用类型AtomicReference的使用：采用CAS操作修改原子类对象
 * 该代码首先创建了一个Person对象，然后把Person对象设置进AtomicReference对象中，
 * 然后调用compareAndSet方法，该方法就是通过CAS操作设置ar。
 * 原理与AtomicInteger类中的compareAndSet方法相同。
 */
class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
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
public class AtomicReferenceTest {

    public static void main(String[] args) {
        //先创建两个person对象，一个代表旧值，一个代表要更新的值
        Person Oldperson = new Person("SnailClimb", 22);
        Person Newperson = new Person("Daisy", 20);
        //接着创建一个原子类对象ar
        AtomicReference<Person> ar = new AtomicReference<Person>();
        //把person对象塞到该原子类对象中
        ar.set(Oldperson);
        //再采用CAS操作设置新值
        ar.compareAndSet(Oldperson, Newperson);
        //测试，获取其属性值，看是否为修改后的对象的值（ok）
        System.out.println(ar.get().getName());//Daisy
        System.out.println(ar.get().getAge());//20
    }
}


