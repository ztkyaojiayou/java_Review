package 最近测试.子类继承父类同时实现接口;

/**
 * @author :zoutongkun
 * @date :2022/8/16 1:28 下午
 * @description :
 * @modyified By:
 */
/**
 *
 * @author ljf
 *定义一个抽象类animal  关键字abstract
 */
public abstract class Animal {
    //将动物共有属性进行封装    名字  年龄   颜色 性别 说话
    private String name;
    private int age;
    private String color;
    private char sex;
    private String speak;
    //设置set/get方法给封装属性进行赋值
    //set外界调用进行赋值     get取出值使用
    public void setname(String name){
        this.name = name;
    }
    public String getname(){
        return name;
    }

    public void setage(int age){
        this.age = age;
    }
    public int getage(){
        return age;
    }

    public void setcolor(String color){
        this.color = color;
    }
    public String color(){
        return color;
    }

    public void setsex(char sex){
        this.sex = sex;
    }
    public char getsex(){
        return sex;
    }

    public void setspeak(String speak){
        this.speak = speak;
    }
    public String getspeak(){
        return speak;
    }
    public void shuchu(){
        System.out.println("这只动物是"+this.color+this.name+"它今年"+this.age+"岁了"+"是一只"
                +this.sex+this.name+"它会陪你"+this.speak+"的聊天");
    }



    public void testSpeak(){
        System.out.println("吃饭啦");
    }
}
