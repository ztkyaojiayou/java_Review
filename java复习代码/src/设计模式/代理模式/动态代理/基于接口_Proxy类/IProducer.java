package 设计模式.代理模式.动态代理.基于接口_Proxy类;

/**
 * 对生产厂家的要求(接口)
 */
public interface IProducer {

    /**
     * 销售
     *
     * @param money
     */
    public void saleProduct(float money);

    /**
     * 售后
     *
     * @param money
     */
    public void afterService(float money);
}
