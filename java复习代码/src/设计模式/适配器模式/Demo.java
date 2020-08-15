package 设计模式.适配器模式;

/**
 * 适配器模式
 * 基本介绍: Adapter 类，通过继承src类，实现dst类接口，完成src->dst的适配。
 *
 * 应用实例说明
 * 以生活中充电器的例子来讲解适配器，充电器本身相当于Adapter,220V交流电相当于sIc(即被适配者)，我们
 * 的目dst(即 目标)是5V直流电
 */
//1.适配接口（5v）
interface IVoltage5V {
    public int output5V();
}

//2.被适配的类（220v）
class Voltage220V {
    //输出220V的电压
    public int output220V() {
        int src = 220;
        System.out.println("原电压=" + src + "伏");
        return src;
    }
}

//3.适配器类（实现从220V到5v的转化）
class VoltageAdapter extends Voltage220V implements IVoltage5V {
    @Override
    public int output5V() {
        // TODO Auto-generated method stub
        //3.1先获取到220V电压
        int srcV = output220V();
        //3.2再将其转成 5v，便于给手机充电
        int dstV = srcV / 44 ;
        return dstV;
    }
}

//手机充电
class Phone {
    public void charging(IVoltage5V iVoltage5V) {
        if(iVoltage5V.output5V() == 5) {
            System.out.println("现在的电压为5V, 可以充电~~");
        } else if (iVoltage5V.output5V() > 5) {
            System.out.println("现在的电压大于5V, 不能充电~~");
        }
    }
}

//测试
class test {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(" === 类适配器模式 ====");
        Phone phone = new Phone();
        phone.charging(new VoltageAdapter());
    }

}