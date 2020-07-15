package java基础.多态;

public class fu {
        public int fuOnly = 6;
        public String book = "父类的书";
        public static int staticNum =7;
        public static int staticFu = 8;

        public void fuOnly() {
            System.out.println("父类的特有方法");

        }
        public void test() {
            System.out.println("父类的测试方法");
        }
        public static void Static(){
            System.out.println("父类的静态方法");
        }
        public static void fuStaticOnly(){
            System.out.println("父类特有的静态方法");
        }
    }


class zi extends fu {
    public int ziOnly =11;
    public String book = "子类的书";
    public static int staticNum = 12;
    public static int staticZi = 13;

    public void test() {
        System.out.println("子类覆盖之后的测试方法");
    }
    public void ziOnly() {
        System.out.println("子类的特有方法");
    }

    public static void Static(){
        System.out.println("子类覆盖之后的静态方法");
    }


    public static void ziStaticOnly(){
        System.out.println("子类特有的静态方法");
    }
}

/**
 * 结论：
 * 关于多态，分为三种情况：
 * 场景：当创建一个父类引用指向子类的对象时，即fu d1 = new zi();时，d1对象的调用情况如下：
 * （1）当调用非静态成员函数时：编译看左边（父类），运行看右边（子类），
 * 即：此时可以调用父类的特有方法，若有子类有覆盖方法，则会调用子类覆盖了之后的方法（多态的体现），
 * 而调用子类的特有方法会报错
 *
 * （2）当调用非静态的成员变量时：无论编译和运行，也都参考左边（父类），
 * 即：此时不管变量是否有覆盖（不管是否是特有变量），
 * 都是调用的父类的变量，而调用子类的特有变量会出错
 *
 * （3）当调用静态成员函数和变量时（二者规则相同）：无论编译和运行，都参考左边（父类），
 * 即：此时不管静态变量或方法是否有覆盖（不管是否是特有静态变量或方法），
 * 都是调用的父类的变量或方法，调用子类的静态变量或方法会出错
 *
 */
class Test多态 {
    public static void main(String[] args) {
        //0,父类的引用指向了自己的子类对象
        fu d1 = new zi();

        //1.调用非静态/普通成员变量和方法时
        //1.1调用非静态的成员变量
        System.out.println(d1.fuOnly);//6(父类的）
        //System.out.println(d1.ziOnly);//编译错误
        System.out.println(d1.book);//父类的书

        //1.2调用非静态成员函数
        d1.test();//子类覆盖之后的测试方法（因为子类已经覆盖了该方法）
        d1.fuOnly();//父类的特有方法
        //d1.ziOnly();//编译错误

        //2.调用静态static成员变量和方法时
        //2.1调用静态成员函数时（二者规则相同）
        System.out.println(d1.staticNum);//7（父类的）
        System.out.println(d1.staticFu);//8（父类的）
        //System.out.println(d1.staticZi);//编译错误

        //2.2调用静态变量时（二者规则相同）
        d1.Static();//父类的静态方法
        d1.fuStaticOnly();//父类特有的静态方法
        //d1.ziStaticOnly();//编译错误





    }
}


