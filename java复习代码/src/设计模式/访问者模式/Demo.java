package 设计模式.访问者模式;

import java.util.LinkedList;
import java.util.List;

//获得男人和女人的评价结果，抽象类
abstract class Multi_Result {

    //得到男性 的测评
    public abstract void getManResult(Man_singer manSinger);

    //得到女的 测评
    public abstract void getWomanResult(Woman_singer womanSinger);
}

//男人和女人成功的评价类
class Success_res extends Multi_Result {

    @Override
    public void getManResult(Man_singer manSinger) {
        // TODO Auto-generated method stub
        System.out.println(" 男人给的评价该歌手很成功 !");
    }

    @Override
    public void getWomanResult(Woman_singer womanSinger) {
        // TODO Auto-generated method stub
        System.out.println(" 女人给的评价该歌手很成功 !");
    }

}
//男人和女人失败的评价类
class Fail_res extends Multi_Result {

    @Override
    public void getManResult(Man_singer manSinger) {
        // TODO Auto-generated method stub
        System.out.println(" 男人给的评价该歌手失败 !");
    }

    @Override
    public void getWomanResult(Woman_singer womanSinger) {
        // TODO Auto-generated method stub
        System.out.println(" 女人给的评价该歌手失败 !");
    }

}
//男人和女人待定的评价类
class Wait_res extends Multi_Result {

    @Override
    public void getManResult(Man_singer manSinger) {
        // TODO Auto-generated method stub
        System.out.println(" 男人给的评价是该歌手待定 ..");
    }

    @Override
    public void getWomanResult(Woman_singer womanSinger) {
        // TODO Auto-generated method stub
        System.out.println(" 女人给的评价是该歌手待定 ..");
    }

}

//选手的抽象类，其提供一个方法，让访问者可以访问
abstract class Singer {

    //提供一个方法，让访问者可以访问
    public abstract void accept(Multi_Result multiResult);
}
//男选手
class Man_singer extends Singer {

    @Override
    public void accept(Multi_Result multiResult) {
        // TODO Auto-generated method stub
        multiResult.getManResult(this);
    }

}

//说明
//1. 这里我们使用到了双分派, 即首先在客户端程序中，将具体状态作为参数传递Woman中(第一次分派)
//2. 然后Woman 类调用作为参数的 "具体方法" 中方法getWomanResult, 同时将自己(this)作为参数
//   传入，完成第二次的分派
//女选手
class Woman_singer extends Singer {

    @Override
    public void accept(Multi_Result multiResult) {
        // TODO Auto-generated method stub
        multiResult.getWomanResult(this);
    }
}

    //数据结构，管理很多人（Man , Woman）
    class Singer_lists {

        //维护了一个集合
        private List<Singer> singers = new LinkedList<>();

        //增加到list
        public void add_person(Singer p) {
            singers.add(p);
        }
        //移除
        public void del_person(Singer p) {
            singers.remove(p);
        }

        //显示测评情况
        public void display(Multi_Result multiResult) {
            for(Singer p: singers) {
                p.accept(multiResult);
            }
        }
    }

    //测试类
    class test访问者 {
        public static void main(String[] args) {
            // TODO Auto-generated method stub
            //创建ObjectStructure
            Singer_lists singerLists = new Singer_lists();

            singerLists.add_person(new Man_singer());
            singerLists.add_person(new Woman_singer());


            //成功
            Success_res successRes = new Success_res();
            singerLists.display(successRes);

            System.out.println("===============");
            Fail_res failRes = new Fail_res();
            singerLists.display(failRes);

            System.out.println("=======给的是待定的测评========");

            Wait_res wait = new Wait_res();
            singerLists.display(wait);
        }
    }