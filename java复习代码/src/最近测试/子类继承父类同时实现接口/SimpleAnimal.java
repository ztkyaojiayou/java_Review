package 最近测试.子类继承父类同时实现接口;

/**
 * @author :zoutongkun
 * @date :2022/8/21 3:27 下午
 * @description :
 * @modyified By:
 */
public class SimpleAnimal extends Animal {
    /**
     * @Override 不管是继承父类（此时为重写），还是实现接口（此时为实现），
     * 当需要重写或实现方法时，都是使用的这个注解！！！
     */
    @Override
    public void testSpeak() {
        System.out.println("吃饭啦");
    }
}
