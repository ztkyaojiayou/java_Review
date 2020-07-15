package 多线程与并发.叶子猿代码.多线程基础.生产者消费者模型.使用Future实现;


public class Product {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + "]";
    }

    public Product(int id, String name) {
        System.out.println("开始生产 " + name);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.id = id;
        this.name = name;
        System.out.println(name + " 生产完毕");
    }

}

