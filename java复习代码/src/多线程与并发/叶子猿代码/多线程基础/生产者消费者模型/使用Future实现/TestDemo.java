package 多线程与并发.叶子猿代码.多线程基础.生产者消费者模型.使用Future实现;


public class TestDemo {

    public static void main(String[] args) {

        ProductFactory pf = new ProductFactory();

        // 下单，交钱
        Future f = pf.createProduct("蛋糕");

        System.out.println("我去上班了，下了班我来取蛋糕...");

        // 拿着订单获取产品
        System.out.println("我拿着蛋糕回家." + f.get());
    }

}

