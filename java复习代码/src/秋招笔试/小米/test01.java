package 秋招笔试.小米;


public class test01 {
    static boolean foo(char c) {
        System.out.print(c);
        return true;
    }

    public static void main(String[] args) {
        int i = 0;
        for (foo('B'); foo('A') && (i < 2); foo('C')) ;
        i++;
        foo('D');

    }
}
