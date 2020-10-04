package 秋招笔试.其他杂七杂八;

class Base01{
        void method(int a){
System.out.println("Base class method called with a ="+a);
    }
    }

    class Derived extends Base01{
    @Override
    void method(int a){
System.out.println("Derived class method called with a ="+a);
}
}
public class Main095{
    public static void main(String[] args){
        Base01 base = new Derived();
        base.method(10);
    }

}


