package java基础.多态;

//这里应用了接口interface PCI ,在mainBorad类里应用了多态ues PCI。增强了程序的扩展性！
interface PCI{//定义一个设备接口
    public void open() ;
    public void close() ;
}

class NetCard implements PCI{//实现接口，相当于重写父类方法
    public void open() {
        System.out.println("网卡 open");
    }
    public void close (){
        System.out.println("网卡 close");
    }
}

class SoundCard implements PCI{//实现接口，相当于重写父类方法
    public void open (){
        System. out . println("声卡 open") ;
    }

    public void close (){
        System. out.println ("声卡 close") ;
    }

}


class MainBoard {
    public void run (){
        System. out. println ("主板运行啦------- ") ;
    }

    //PCI p = new NetCard()//接口型引用指向自己的子类对象
    public void usePCI(PCI p)//多态体现：传入不同的子类对象就可以实现不同的功能
    {
        if(p!=null)
            p.open() ;
        p.close() ;
    }
}

public class testDuoTai {
    public static void main(String[] args) {
        MainBoard mb = new MainBoard() ;
        mb. run() ;
        mb. usePCI (new NetCard());//传入接口的子类对象NetCard,就可以调用它的方法了
        mb. usePCI (new SoundCard());//传入接口的子类对象SoundCard，就可以调用它的方法了
    }
}





