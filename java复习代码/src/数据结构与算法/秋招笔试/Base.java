package 数据结构与算法.秋招笔试;

public class Base {
    public static final String FOO = "foo";
    public static void main(String[] args) {
        Base b = new Base();
        Sub s = new Sub();
        System.out.print(Base.FOO);
        System.out.print(Sub.FOO);
        System.out.print(b.FOO);
        System.out.print(s.FOO);
        System.out.print(((Base) s).FOO);
    }
}
class Sub extends Base {
    public static final String FOO = "bar";
}

