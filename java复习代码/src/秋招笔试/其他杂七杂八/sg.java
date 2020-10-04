package 秋招笔试.其他杂七杂八;

abstract class BaseCls {
    static int i = 1;
    abstract void method();
}
public class sg extends BaseCls {
    public static void main(String[] args) {
        int[] ar = new int[3];
        for (int i = 0; i < ar.length; i++) {
            System.out.println(ar[i]);
        }
    }

    @Override
    void method() {

    }
}


