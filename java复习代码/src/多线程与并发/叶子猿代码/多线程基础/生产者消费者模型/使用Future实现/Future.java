package 多线程与并发.叶子猿代码.多线程基础.生产者消费者模型.使用Future实现;


public class Future {

    private Product product;

    private boolean down;

    public synchronized void setProduct (Product product) {
        if(down) {
            return;
        }

        this.product = product;
        this.down = true;
        notifyAll();
    }

    public synchronized Product get () {
        while(!down) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return product;
    }

}

