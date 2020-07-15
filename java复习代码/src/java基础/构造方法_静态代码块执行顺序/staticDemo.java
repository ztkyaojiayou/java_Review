package java基础.构造方法_静态代码块执行顺序;

//本demo用于在没有继承的情况下，各种代码块和构造函数的执行顺序
public class staticDemo {

    static
    {
        System. out.println("a静态代码块，先执行，用于初始化类，但只执行一次") ;
    }


    {
        System. out.println("b构造代码块，要优于构造函数，另外，我在c构造代码块前面，" +
                "所以要优先c执行,且每new一个对象都要重新执行一次") ;
    }


    {
        System. out.println("c构造代码块，要优于构造函数，在b之后执行（按顺序执行），" +
                "同样地，每new一个对象都要重新执行一次") ;
    }

     staticDemo (){
        System. out.println("d自定义的无参构造方法，不是默认的哦，" +
                "说明new对象时没有传参哦，每new一个对象都可能要重新执行一次") ;
    }
      staticDemo(int x){
        System.out.println("e有参构造方法，说明new对象时传参了哦，" +
                "每new一个对象都可能要重新执行一次");
    }

    /**
     * 当类中的成员变量或方法被静态修饰后，就多了一个调用方式：
     * 除了可以被对象调用外，还可以直接被类名调用。
     * 写法是：类名.静态成员
     */
    public static void staticShow (){//静态方法，对象可以直接调用，这一点毫无疑问，
        //只是还多了一个调用方法而已（即被类直接调用）
        //要明确的另外一点是：在静态方法中，只能访问静态成员（方法和变量），
        //而非静态方法则既可以访问静态也可以访问非静态。
        System. out.println("staticShow run,静态方法（可以被类名直接调用哦）" +
                "当然是每调用一次就执行一次呀") ;
    }
    public void show(){//普通方法，只能被对象调用
        System. out.println("show run普通方法，当然也是每调用一次就执行一次呀") ;
    }

}

class TestStatic{
    static{
        System. out.println("主函数所在类中的静态代码块（不能放在main函数里面），" +
                "最先执行，谁也别拦我，但也只执行一次") ;
    }

    public static void main(String[] args) {

        System.out.println("\n------创建一个对象，先要进行类和对象的初始化----------");
        staticDemo staticDemo01 = new staticDemo();
        System.out.println("\n-----初始化完毕，开始真正调用方法执行啦-----------");
        staticDemo01.staticShow();
        staticDemo01.show();
        System.out.println("\n------再创建一个对象，但传了参数，则再一次进行对象初始化，" +
                "但不需要类初始化----------");
        staticDemo staticDemo02 = new staticDemo(4);//若对应的构造方法被私有时，则无法创建此对象
        System.out.println("\n-----初始化完毕，开始真正调用方法执行啦-----------");
        staticDemo02.show();
        staticDemo02.staticShow();
        System.out.println("\n--------使用类名直接调用satic方法玩玩" +
                "（单独使用了，静态代码块也需要执行，因为它是对类初始化，" +
                "即只要使用了该类，就必须先初始化该类）--------");
        staticDemo.staticShow();
    }
}
