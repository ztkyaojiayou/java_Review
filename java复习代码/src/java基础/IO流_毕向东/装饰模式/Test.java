package java基础.IO流_毕向东.装饰模式;

//装饰模式
class Person {
    void chifan(){
        System.out.println("原始方法---吃饭");
    }
}
//这个类的出现是为了增强Person而出现的（推荐）
class NewPerson01 {
    private Person p ;
    NewPerson01(Person p){
        this.p = p;
    }

    public void chifan(){
        System.out.println("装饰模式----还有开胃酒");
        p.chifan();
        System.out.println("装饰模式----还有甜点");

    }
}
//使用继承的方法增强原功能（不推荐）
class NewPerson02 extends Person {
    public void chifan(){
        System.out.println("继承模式-----开胃酒");
        super.chifan();
        System.out.println("继承模式-----甜点");
    }
}

//测试
public class Test {

    /**
     * @param args
     */
    public static void main(String[] args) {

        Person p = new Person();
		p.chifan();//原方法
        System.out.println("----------------");
       //装饰模式
        NewPerson01 p1 = new NewPerson01(p);
        p1.chifan();
        System.out.println("----------------");
        //继承的方式
        NewPerson02 p2 = new NewPerson02();
        p2.chifan();
    }

}












