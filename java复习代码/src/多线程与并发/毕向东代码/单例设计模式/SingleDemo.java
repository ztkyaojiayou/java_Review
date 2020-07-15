package 多线程与并发.毕向东代码.单例设计模式;


/*
多线程下的单例

*/

//饿汉式
class Single
{
    private static final Single s = new Single();
    private Single(){}
    public static Single getInstance()
    {
        return s;
    }
}



//懒汉式

//加入同步为了解决多线程安全问题。

//加入双重判断是为了解决效率问题。




class Single01
{
    private static Single01 s = null;

    public Single01(){}

    public static Single01 getInstance()
    {
        if(s==null)
        {
            synchronized(Single01.class)
            {
                if(s==null)
                    //				-->0 -->1
                    s = new Single01();
            }
        }
        return s;
    }
}
public class  SingleDemo
{
    public static void main(String[] args)
    {
        System.out.println("Hello World!");
        Single01 single01 = new Single01();
        Single01 single011 = single01.getInstance();
        Single01 single012 = single01.getInstance();
        System.out.println(single011);
        System.out.println(single012);
        System.out.println(single011 == single012);
        //输入结果为：
//        ztk.多线程与并发.毕向东代码.单例设计模式.Single01@610455d6
//        ztk.多线程与并发.毕向东代码.单例设计模式.Single01@610455d6
//        true
    }
}

