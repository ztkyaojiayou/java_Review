package 设计模式.代理模式.动态代理.基于接口_Proxy类;

/**
 * 模拟一个生产厂商，且实现一个接口（用于演示基于接口的动态代理）
 */
public class Producer implements IProducer{

    /**
     * 销售
     * @param money
     */
    @Override
    public void saleProduct(float money){
        System.out.println("销售了产品，并拿到了钱："+money);
    }

    /**
     * 售后
     * @param money
     */
    @Override
    public void afterService(float money){
        System.out.println("提供了售后服务，并拿到钱："+money);
    }
}
