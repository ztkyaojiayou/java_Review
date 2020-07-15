package 多线程与并发.叶子猿代码.多线程基础.生产者消费者模型.使用Future实现;


import java.util.Random;

public class ProductFactory {

    public Future createProduct(String name) {
        Future f = new Future(); // 创建一个订单
        System.out.println("下单成功，你可以去上班了》。。");
        // 生产产品
        new Thread(new Runnable() {
            @Override
            public void run() {
                Product p = new Product(new Random().nextInt(), name);
                f.setProduct(p);
            }
        }).start();
        return f;
    }

}

