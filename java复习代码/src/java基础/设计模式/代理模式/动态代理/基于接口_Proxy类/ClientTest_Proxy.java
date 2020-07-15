package java基础.设计模式.代理模式.动态代理.基于接口_Proxy类;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 模拟一个消费者，用于测试
 */
public class ClientTest_Proxy {

    public static void main(String[] args) {

        ////1.当没有代理商（动态代理技术）时，消费者买东西的操作如下：直接找生产厂商购买
        //Producer producer = new Producer();
        //producer.saleProduct(10000f);
        ////运行结果如下：
        ////销售了产品，并拿到了钱：10000.0


        /**
         * 这里开始讲解动态代理：
         * 特点：字节码随用随创建，随用随加载（因此采用的是匿名内部类）
         * 作用：不修改源码的基础上对方法增强
         * 分类：
         *   （1）基于接口的动态代理
         *   （2）基于子类的动态代理
         *
         *  (1)先介绍基于接口的动态代理：（为主）
         *      涉及的类：Proxy
         *      提供者：JDK官方
         *  1.如何创建代理对象：
         *      使用Proxy类中的newProxyInstance方法
         *  2.创建代理对象的要求：
         *      被代理类最少实现一个接口，如果没有则不能使用（弊端）
         *  3.newProxyInstance方法的参数：
         *      3.1.ClassLoader：类加载器
         *          它是用于加载代理对象字节码的。和被代理对象使用相同的类加载器。写法是固定的。
         *      3.2.Class[]：字节码数组
         *          它是用于让代理对象和被代理对象有相同方法。写法是固定的。
         *      3.3.匿名内部类InvocationHandler中的invoke方法：用于提供增强的代码
         *          它是让我们写“如何代理”的部分，属于关键部分。（关键）
         *          我们一般都是写一个该接口的实现类，通常情况下都是匿名内部类，但不是必须的。
         *          此接口的实现类都是谁用谁写。
         */
        //2.当有了代理商（动态代理技术）时，消费者买东西的操作如下：通过找代理商购买

        final Producer producer = new Producer();//在匿名内部类中访问局部变量时，这个局部变量必须用final修饰符修饰
        // （但从jdk1.8开始，可以不用了）

        // 2.1 创建代理对象，同时加上该代理对象要执行的额外逻辑（如赚差价等）
        // （注意）这里返回的proxyProducer就是代理（Producer的）对象，Producer则是被代理的对象，
       IProducer proxyProducer = (IProducer) Proxy.newProxyInstance(producer.getClass().getClassLoader(),
                producer.getClass().getInterfaces(),
                new InvocationHandler() {//匿名内部类，随用随加载
                    /**
                     * 作用：执行被代理对象（这里就是Producer的对象）的任何接口方法都会经过该方法（核心）
                     * 方法参数的含义
                     * @param proxy   代理对象的引用
                     * @param method  当前执行的方法
                     * @param args    当前执行方法所需的参数
                     * @return        和被代理对象方法有相同的返回值
                     * @throws Throwable
                     */

                    //这个方法中就是用于提供增强的代码（invoke：调用）
                    //规则：所有要调用目标类的操作都会被拦截下来强行通过代理对象来执行
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        Object returnValue = null;

                        //1.获取方法执行的参数(money)
                        Float money = (Float)args[0];
                        //2.判断当前方法是不是销售
                        if("saleProduct".equals(method.getName())) {
                            returnValue = method.invoke(producer, money*0.8f);
                        }
                        return returnValue;
                    }
                });

        // 2.2 然后就可以使用该代理对象来调用目标类中的方法啦
        proxyProducer.saleProduct(10000f);

        //此时的运行结果如下：（即消费者花了10000元，但市场厂商只拿到了8000元，差价2000块给了代理商。
        //销售了产品，并拿到了钱：8000.0
        //易知：通过使用动态代理技术之后，使方法增强了。
    }
}
