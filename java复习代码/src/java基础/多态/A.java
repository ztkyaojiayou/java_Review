package java基础.多态;


    public class A {
        public String show(D obj) {
            return ("A类的特有方法");
        }

        public String show(A obj) {
            return ("被覆盖的方法");
        }

    }

    class B extends A{
        public String show(B obj){
            return ("B类特有方法");
        }

        public String show(A obj){
            return ("覆盖的方法");
        }
    }

    class C extends B{

    }

    class D extends B{

    }

    class Test02 {
        public static void main(String[] args) {
            A a1 = new A();//父类特有对象
            A a2 = new B();//指向子类对象的父类引用
            B b = new B();//子类特有对象

            C c = new C();
            D d = new D();

            //父类特有对象a1
            System.out.println("1--" + a1.show(b));
            System.out.println("2--" + a1.show(c));
            System.out.println("3--" + a1.show(d));

            //指向子类对象的父类引用a2
            System.out.println("4--" + a2.show(b));
            System.out.println("5--" + a2.show(c));
            System.out.println("6--" + a2.show(d));

            //子类特有对象b
            System.out.println("7--" + b.show(b));
            System.out.println("8--" + b.show(c));
            System.out.println("9--" + b.show(d));
        }
    }
