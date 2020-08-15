package 设计模式.模板模式;


public class PureMilk extends Milk {

    @Override
    void addCondiments() {
        // TODO Auto-generated method stub
        System.out.println(" 纯豆浆，啥也不加 ");
    }

    @Override
    boolean customerWantCondiments() {
        // TODO Auto-generated method stub
        return false;
    }

}


