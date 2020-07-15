package java基础.设计模式.建造者模式;

/**
 * 建造者模式
 * 它也叫作生成器模式，将一个复杂对象的构建和它的表示分离，
 * 使得同样的构建过程可以创建不同的表示。
 * 比如一个User类的属性有name、age、 address、email、job...等，
 * 如果想创建一个User对象，传入全部的属性有点太长了，
 * 这时可以使用建造者模式，传入一个参数就只设置对应属性的值。
 */
//1.要建造的目标产品，这里就为房子
class House {
    private String base;
    private String wall;
    private String roofed;
    public String getBase() {
        return base;
    }
    public void setBase(String base) {
        this.base = base;
    }
    public String getWall() {
        return wall;
    }
    public void setWall(String wall) {
        this.wall = wall;
    }
    public String getRoofed() {
        return roofed;
    }
    public void setRoofed(String roofed) {
        this.roofed = roofed;
    }

}

//2.抽象的建造工人，里面有构造房子的抽象方法
abstract class HouseBuilder {

    protected House house = new House();

    //将建造的流程写好, 抽象的方法
    public abstract void buildBasic();
    public abstract void buildWalls();
    public abstract void roofed();

    //建造房子好， 将产品(房子) 返回，
    //这是一个已经实现了的具体方法（这只是模拟）
    public House buildHouse() {
        return house;
    }

}

//3.再创建建造不同房子的工人，继承抽象类即可
//3.1建高楼的人
class HighBuilder extends HouseBuilder {

    @Override
    public void buildBasic() {
        // TODO Auto-generated method stub
        System.out.println(" 高楼的打地基100米 ");
    }

    @Override
    public void buildWalls() {
        // TODO Auto-generated method stub
        System.out.println(" 高楼的砌墙20cm ");
    }

    @Override
    public void roofed() {
        // TODO Auto-generated method stub
        System.out.println(" 高楼的透明屋顶 ");
    }

}

//3.2建普通房子的人
class CommonBuilder extends HouseBuilder {

    @Override
    public void buildBasic() {
        // TODO Auto-generated method stub
        System.out.println(" 普通房子打地基5米 ");
    }

    @Override
    public void buildWalls() {
        // TODO Auto-generated method stub
        System.out.println(" 普通房子砌墙10cm ");
    }

    @Override
    public void roofed() {
        // TODO Auto-generated method stub
        System.out.println(" 普通房子屋顶 ");
    }

}

//4.指挥者/包工头，用于指定不同的工人来建不同的房子，并返回创建好后的房子成品
class HouseDirector {

    HouseBuilder houseBuilder = null;
    //4.1构造器传入建造工人 houseBuilder
    public HouseDirector(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }

    //4.2通过setter 传入 houseBuilder，用于修改工人的类别，从而创建不同的房子
    public void setHouseBuilder(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }

    //4.3由包工头指导工人造房子的方法，但里面调用的又全是工人自己的方法，
    //相当于包工头真的只是指导一下，自己不输出技术和力量（关键方法）
    public House constructHouse() {
        houseBuilder.buildBasic();
        houseBuilder.buildWalls();
        houseBuilder.roofed();
        return houseBuilder.buildHouse();
    }
}

class test建造者 {
    public static void main(String[] args) {

        //1.先盖个普通房子试试
        //1.1先new一个盖普通房子的工人
        CommonBuilder commonHouse = new CommonBuilder();
        //1.2再new一个包工头，并将该工人交给包工头来管理
        HouseDirector houseDirector = new HouseDirector(commonHouse);

        //1.3包工头开始指挥工人盖房子，盖完后返回房子即可
        House house = houseDirector.constructHouse();

        System.out.println("--------------------------");

        //2.再盖一个高楼，此时只需将工人换成盖盖头的工人即可（使用set方法修改即可，这就是精髓所在）
        //2.1同理，先new一个盖高楼的工人
        HighBuilder highBuilding = new HighBuilder();
        //2.2再重置建造者（使用set方法），将盖高楼的工人交给包工头来管理（核心精髓）
        houseDirector.setHouseBuilder(highBuilding);
        //2.3包工头开始指挥工人盖房子，盖完后返回房子即可
        houseDirector.constructHouse();
    }
}