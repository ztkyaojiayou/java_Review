package java基础.内部类;

    //外部类0uter
    public class Outer {
        private int a = 99; //外部类的私有属性
        String name = "外部类中的张三"; //外部类和内部类具有相同的成员变量或方法时

        //当内部类Inner定义在成员变量时
        //Inner类可以使用任意访问修饰符，如：public、private、protected等。
        public class Inner {
            int b=2;//内部类的成员属性
            String name = "内部中类的张三"; //外部类和内部类具有相同的成员变量或方法时
            public void test() {//Inner类中定义的test()方法可以访问Outer类中的数据，不受访问控制符的影响
                System. out. println("内部类可以访问内部类中的变量b:"+ b);
                System.out.println( "也可以直接任意访问外部类中的a:" + a);

                //外部类和内部类具有相同的成员变量或方法时：
                //内部类可以直接访问内部类的成员变量或方法
                //但如果内部类访问外部类的成员变量或者方法时，需要使用外部类名.this关键字.外部类变量名进行访问；
                System.out.println("内部类依然可以直接访问内部的该变量，即内部为："+ name);
                System.out.println("外部则需要使用外部类名.this关键字.外部类变量名进行访问，为："+ Outer.this.name);
            }
        }


        //测试成员内部类
        public static void main(String[] args) {
            //定义了成员内部类后，必须使用外部类对象来创建内部类对象

            //方法一：则要先创建外部类对象，再通过外部类对象来创建内部类对象，再用该内部类对象去访问
            Outer outer = new Outer();//先创建外部类对象，对象名为outer
            Inner inner = outer.new Inner();// 使用外部类对象创建内部类对象，对象名为inner
            //方法二：或者使用链式法则创建对象
            new Outer().new Inner().test();
            System.out.println("\n---------------------");
            inner. test();//调用内部类对象的test方法
            System.out.println(inner.b);
        }
    }
