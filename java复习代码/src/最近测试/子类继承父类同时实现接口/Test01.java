package 最近测试.子类继承父类同时实现接口;

import 最近测试.子类继承父类同时实现接口.People;
import 最近测试.子类继承父类同时实现接口.Simple2People;
import 最近测试.子类继承父类同时实现接口.SimplePeople;

/**
 * @author :zoutongkun
 * @date :2022/8/16 1:27 下午
 * @description :
 * @modyified By:
 */
public class Test01 {
    public static void main(String[] args) {
//        //接口不能被实例化
//        People people = new People();
//        People simplePeople = new SimplePeople();
//        simplePeople.love();
//        People simple2People = new Simple2People();
//        simple2People.love();
        for (int i = 0; i < 5; i++) {
            try {
                int a = 10 / 0;
            } catch (Exception e) {
                //执行5次
                e.printStackTrace();
            }finally {
                //只执行一次！！！
                System.out.println("必须执行");
            }
            }
        }

//        //        int a  = 10/0;
//    //若有finally，则其后不能再有代码，否则报错
//        System.out.println("1111111");
    }
