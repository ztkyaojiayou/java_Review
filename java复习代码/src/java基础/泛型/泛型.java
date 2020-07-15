package java基础.泛型;

import java.util.ArrayList;
import java.util.List;

class Fruit{}
class Apple extends Fruit{}
class redApple extends Apple{}
class Orange extends Fruit{}

//简单
public class 泛型 {
    public static void main(String[] args) {
        //1.上界限定，即要接的类是该类（Fruit）及其所有子类，但具体是哪一个不知道，只知道父类是Fruit类
        //因此，可以get，但不可以add；即“上界不存”
        List<? extends Fruit> top = new ArrayList<Apple>();
        /*
        1.1add时：
        add时会编译错误，因为要接的类型不确定具体是哪个子类
        因此，即便你传的是Apple的各种/各级子类，依然可能无法接收，
        毕竟无法确定要接的类型（不定）和传入的类型（固定）的父子关系
        但add（null）没毛病。
        top.add(new Apple());
        top.add(new redApple());
        top.add(new Fruit());
         */
        top.add(null);//add（null）没毛病
//        top.add(new Apple());//编译错误
//        top.add(new redApple());//编译错误
//        top.add(new Fruit());//编译错误
        //1.2get时：当使用父类Fruit去接时没有任何毛病，
        //因为无论get到的是什么子类，其都有一个共同的父类Fruit
        //但不能用其具体子类去接（如Apple），因为get到的类型可能与Apple类无任何关系
        Fruit fruit = top.get(0);//使用父类Fruit类去接get返回的值没任何毛病
        //Apple fruit01 = top.get(0);//编译错误，不能用其具体子类去接（如Apple），
        //因为get到的类型可能与Apple类无任何关系


        //2.下界限定，即要接的类是该类（Apple）及其所有父类，
        //但不知道具体是哪个父类，一直可以追溯到老祖宗Object类
        //因此，可以add，但不可以get，即“下界不取”

        //2.1add时：
        //因此我们不可以add其父类，因为不知道要接的父类到底是哪一个
        //但我们可以add该类（Apple）及其子类，
        //因为至少，不管其子类是什么类型，其都可以用该类（Apple）去接
        //但不可以get，也是同理，不知道具体用哪个父类去接（除了老祖宗类Object）

        List<? super Apple> bottem = new ArrayList<Apple>();
        bottem.add(new Apple());
        bottem.add(new redApple());

        //2.2get时：普通类接不住（包括super后的Apple类），但老祖宗Object类可以
        //Apple apple = bottem.get(0);//编译错误，因为其具体父类未知，Apple类是接不住的

        Object apple01 = bottem.get(0);//此时没毛病，使用老祖宗Object类当然妥妥地接住
    }

}
