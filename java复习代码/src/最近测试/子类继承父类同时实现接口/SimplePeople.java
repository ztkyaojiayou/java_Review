package 最近测试.子类继承父类同时实现接口;

/**
 * @author :zoutongkun
 * @date :2022/8/16 1:30 下午
 * @description :
 * @modyified By:
 */
/**
 *
 * @author ljf
 *定义一个Extends_implements类
 *继承抽象类的同时实现接口
 */
public class SimplePeople implements People {

    @Override
    public void love() {
        // TODO Auto-generated method stub
        System.out.println("照顾小动物");
    }

    @Override
    public void money() {
        // TODO Auto-generated method stub
        System.out.println("赚钱");
    }


}
