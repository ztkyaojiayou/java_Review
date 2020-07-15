package 多线程与并发.叶子猿代码.多线程高级.Atomoic原子类;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * 4.原子型字段类型AtomicIntegerFieldUpdater的使用
 * （1）如果需要更新对象的某个字段，并在多线程的情况下，能够保证线程安全，atomic同样也提供了相应的原子操作类：
 * AtomicIntegeFieldUpdater：原子更新整型字段类；
 * AtomicLongFieldUpdater：原子更新长整型字段类；
 * AtomicStampedReference：原子更新引用类型，这种更新方式会带有版本号。
 * 更新时会带有版本号，目的是为了解决CAS的ABA问题；
 *
 * （2）要想使用原子更新字段需要两步操作：
 * 原子更新字段类都是抽象类，只能通过静态方法newUpdater来创建一个更新器，并且需要设置想要更新的类和属性；
 * 更新类的属性必须使用public volatile进行修饰；
 */

class User001 {
    private String name;
    public volatile int age;

    public User001(String name, int age) {
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
public class AtomicIntegerFieldUpdaterTest {
    public static void main(String[] args) {
        //先通过静态方法newUpdater来创建一个更新器，并且需要设置想要更新的类和属性；这里设置为age属性
        AtomicIntegerFieldUpdater<User001> a = AtomicIntegerFieldUpdater.newUpdater(User001.class, "age");
        //再创建一个User001对象
        User001 user001 = new User001("Java", 22);
        //传入要修改的对象，并使其加一，返回的是旧值，则为22
        System.out.println(a.getAndIncrement(user001));// 22
        //返回的是旧值，但之前更新了，所以为23
        System.out.println(a.get(user001));// 23
    }
}


