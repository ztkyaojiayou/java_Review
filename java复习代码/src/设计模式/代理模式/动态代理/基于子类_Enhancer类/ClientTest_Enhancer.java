package 设计模式.代理模式.动态代理.基于子类_Enhancer类;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 模拟一个消费者，用于测试
 */
public class ClientTest_Enhancer {

    public static void main(String[] args) {
        final Producer producer = new Producer();
        /**
         *  （2）介绍基于子类的动态代理：
         *  （因此不需要接口，其实就是把实现接口变成了继承父类，
         *  没什么本质区别，对比基于接口的动态代理学习）
         *
         *      涉及的类：Enhancer
         *      提供者：第三方cglib库
         *  1.如何创建代理对象：
         *      使用Enhancer类中的create方法
         *  2.创建代理对象的要求：
         *      被代理类不能是最终类
         *  3.create方法的参数：
         *      3.1.Class：字节码
         *          它是用于指定被代理对象的字节码。
         *
         *      3.2.intercept方法：用于提供增强的代码
         *          它是让我们写如何代理。我们一般都是写一个该接口的实现类，通常情况下都是匿名内部类，但不是必须的。
         *          此接口的实现类都是谁用谁写。
         *          我们一般写的都是该接口的子接口实现类：MethodInterceptor（“方法拦截”）
         */
        //1.创建代理对象（cglibProducer即为创建的代理对象）
        Producer cglibProducer = (Producer) Enhancer.create(producer.getClass(), new MethodInterceptor() {
            /**
             * 执行被代理对象的任何方法都会经过该方法               （核心）
             * @param proxy
             * @param method
             * @param args
             * 以上三个参数和基于接口的动态代理中invoke方法的参数是一样的
             * @param methodProxy ：当前执行方法的代理对象
             * @return
             * @throws Throwable
             */
            //方法拦截，用于写代理的业务
            @Override
            public Object intercept(Object proxy, Method method, Object[] args,
                                    MethodProxy methodProxy) throws Throwable {
                //这个方法体中用于提供增强的代码
                Object returnValue = null;

                //1.获取方法执行的参数
                Float money = (Float) args[0];
                //2.判断当前方法是不是销售
                if ("saleProduct".equals(method.getName())) {
                    returnValue = method.invoke(producer, money * 0.8f);
                }
                return returnValue;
            }
        });
        //2.再使用代理对象帮用户代理
        cglibProducer.saleProduct(12000f);
    }
}
