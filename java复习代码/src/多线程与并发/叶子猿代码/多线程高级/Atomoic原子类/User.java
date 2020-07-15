package 多线程与并发.叶子猿代码.多线程高级.Atomoic原子类;


//3.具有原子性的对象的创建方法:先创建此对象，再使用AtomicReference类创建
public class User {

    private String name;

    public volatile int old;//需要被更新/修改的字段必须由volatile修饰（默认是old = 0）

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOld() {
        return old;
    }

    public void setOld(int old) {
        this.old = old;
    }

}

