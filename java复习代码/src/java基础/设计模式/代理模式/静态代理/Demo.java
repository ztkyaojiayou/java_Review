package java基础.设计模式.代理模式.静态代理;

/**
 * 静态代理
 * Java中的静态代理要求代理类(ProxySubject)和目标类(RealSubject)都实现同一个接口(Subject)。
 * 静态代理中代理类在编译期就已经确定，而动态代理则是JVM运行时动态生成，
 * 静态代理的效率相对动态代理来说相对高一些，但是静态代理代码冗余大，
 * 一单需要修改接口，代理类和委托类都需要修改。
 * 静态代理：静态代理在使用时,需要定义接口或者父类,
 * 被代理对象(即目标对象)与代理对象一起实现相同的接口或者是继承相同父类。
 *
 * 在使用静态代理时，需要定义接口或者父类,被代理对象(即目标对象)
 * 与代理对象一起实现相同的接口或者是继承相同父类
 */
//1.目标类的接口
interface ITeacherDao {

    void teach(); // 授课的方法
}

//2.目标类的具体实现类，实现ITeacherDao接口
class TeacherDao implements ITeacherDao {
    @Override
    public void teach() {
        System.out.println(" 老师授课中 。。。。。");
    }

}

//3.创建代理类，生成代理对象，也要实现ITeacherDao接口，这就是静态代理的特点（重点）
class TeacherDaoProxy implements ITeacherDao{

    private ITeacherDao target; // 目标对象，通过接口来聚合，目的是使该代理类可以使用原目标类中的方法
    //构造器
    public TeacherDaoProxy(ITeacherDao target) {
        this.target = target;
    }

    //代理类中要对目标类中的方法进行处理，即不止执行原目标类中的方法，
    // 还要加一些其他业务逻辑，比如放广告，抽取提成之类的，比较代理类相当于中间商嘛
    @Override
    public void teach() {
        System.out.println("开始代理，通知老师上课，同时执行某些其他操作，因为我是代理/中间商嘛。。。。。 ");
        target.teach();//这是原方法，由该代理对象去执行，而不是有用户直接调用，这就是代理的精髓
        System.out.println("同学们下课。。。。。");
    }

}

//测试
class StaticProxy_Test {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //创建目标对象(被代理对象)
        TeacherDao teacherDao = new TeacherDao();

        //创建代理对象, 同时将被代理对象传递给代理对象
        TeacherDaoProxy teacherDaoProxy = new TeacherDaoProxy(teacherDao);

        //通过代理对象，调用到被代理对象的方法
        //即：用户执行的是代理对象的方法而不是真正的目标对象的方法，真正的目标对象的方法由代理对象去调用
        teacherDaoProxy.teach();
    }

}
