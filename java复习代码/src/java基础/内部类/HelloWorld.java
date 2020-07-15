package java基础.内部类;

    public class HelloWorld {
        //定义外部类的成员变量
        String name = "张三";
        static int i = 8;
        static String nickname = "张三--外";//重名的静态变量
        String nick = "张三--外";//重名的非静态变量

        //定义静态内部类
        public static class Inner {
            String name01 = "内部类中的非static成员---李四";
            static String name = "内部类中的static成员---李四";
            static String nickname = "张三--内";//重名的静态变量
            String nick = "张三--外";//重名的非静态变量
            //1.静态内部类中的static方法
            public static void Show() {
                //1.1不管是外部类还是内部类，访问非static成员时，都必须使用new.类名.静态成员
                System.out.println("static--外部类的name为: " + new HelloWorld().name);
                System.out.println("static--内部类的name为: " + new Inner().name01);
                //1.2同样地，不管是外部类还是内部类，访问static成员时，直接访问即可
                System.out.println(i);//外部类中的
                System.out.println(name);//内部类中的
                //1.3如果外部类的静态成员与内部类的静态成员相同， 则可以通过"外部类名.静态成员"来访问外部类的静态成员；
                //如果不同，可以直接调用外部类的静态成员名。
                //易知，其实重名不重名的规则是一样的
                System.out.println(HelloWorld.nickname);
                System.out.println(nickname);
                //1.4如果外部类的非静态成员与内部类的非静态成员相同时，其重名不重名的规则也是一样的
                System.out.println("static--外部类的name为: " + new HelloWorld().nick);
                System.out.println("static--内部类的name为: " + new Inner().nick);
            }
            //2.静态内部类中的非static方法（为主）
            public void show01(){
                //2.1对于外部类，则由于本方法在静态类中，则对于非静态成员，就必须使用new出该类的对象才可以访问
                System.out.println("非static--外部类的name为: " + new HelloWorld().name);
                //2.2.1对于本类，则可以直接访问静态成员和非静态成员
                System.out.println(name01);
                System.out.println(name);
                //2.2.2而对于本类的静态成员，则可直接访问
                System.out.println(i);
            }
        }

        public static void main(String[] args) {
            //1.对于非static成员，需要new一个对应的对象
            //且在创建静态内部类的对象时，不需要外部类的对象，可以直接创建；
            //相当于new HelloWorld.Inner().show01();
            //或Inner inner = new Inner();inner.show01();
            new Inner().show01();

            System.out.println(new Inner().name01);

            //2.对于static成员，不需要new对象 ，直接使用类名调用就可以访问static方法了,
            //且也不需要外部类，直接用内部类名调用即可
            Inner.Show();//相当于HelloWorld.Inner.Show();
            System.out.println(Inner.name);//相当于System.out.println(HelloWorld.Inner.name);
        }
    }

