package 设计模式.装饰者模式;

/*
装饰设计模式：
当想要对已有的对象进行功能增强时，
可以定义类，将已有对象传入，基于已有的功能，并提供加强功能。
那么自定义的该类称为装饰类。

装饰类通常会通过构造方法接收被装饰的对象。
并基于被装饰的对象的功能，提供更强的功能。
*/

//普通人
class Person {
    public void chifan() {
        System.out.println("吃饭");
    }
}

//超人（里面包含普通人对象）
class SuperPerson {
    private Person p;//普通人对象

    // 构造方法
    // 即在超人类中传入一个原普通人对象，这样就即可以使用原普通人对象中的方法，
    // 同时又可以自定义其他逻辑了
    SuperPerson(Person p) {
        this.p = p;
    }

    //超人吃饭的方法
    public void superChifan() {
        System.out.println("开胃酒");
        //包含了普通人吃饭的方法
        p.chifan();
        System.out.println("甜点");
        System.out.println("来一根");
    }
}

class PersonDemo {
    public static void main(String[] args) {
        Person p = new Person();

        //p.chifan();

        SuperPerson sp = new SuperPerson(p);
        sp.superChifan();

    }
}

