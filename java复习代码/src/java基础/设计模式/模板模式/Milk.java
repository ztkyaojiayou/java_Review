package java基础.设计模式.模板模式;


//抽象类，表示豆浆
public abstract class Milk {

    //模板方法make，可以做成final，不让子类去覆盖，但子类可以直接调用（精髓）
    //这里面的三个方法都是具体的方法，且子类不需要重写
    final void make() {
        select();
        if(customerWantCondiments()) {
            addCondiments();
        }
        soak();
        beat();
    }

    //钩子方法，决定是否需要添加配料（默认加配料，但子类也可以重写该方法将其设置为false）
    boolean customerWantCondiments() {
        return true;
    }
    //添加不同的配料，抽象方法, 由子类具体实现，该方法就决定了子类的种类
    abstract void addCondiments();

    //以下是三个模板方法中三个方法的具体实现（这也是模板模式的精髓，即把一些共用的方法在抽象类中直接实现）
    //1.选材料
    void select() {
        System.out.println("第一步：选择好的新鲜黄豆  ");
    }

    //2.浸泡
    void soak() {
        System.out.println("第三步， 黄豆和配料开始浸泡， 需要3小时 ");
    }

    //3.研磨打碎
    void beat() {
        System.out.println("第四步：黄豆和配料放到豆浆机去打碎  ");
    }

}

