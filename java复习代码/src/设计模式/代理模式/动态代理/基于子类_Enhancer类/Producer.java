package 设计模式.代理模式.动态代理.基于子类_Enhancer类;

/**
 * 模拟一个生产厂商，但不实现任何接口，而是直接在该类中编写相关方法（用于演示基于子类的动态代理）
 * @author zoutongkun
 */
public class Producer extends FuProducer{

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
        System.out.println("提供了售后服务，并拿到了钱："+money);
    }
}
