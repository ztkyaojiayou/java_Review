package java基础.类型强转;

public class test02类型强转 {
    int b1=1,b2=2,b3,b6;
    int  b4=4,b5=6;
public int add(int a,int b){
    b3= a + b;
    return b3;
}

}
    class test{
    public static void main(String[] args) {
        int x;
        test02类型强转 t2 = new test02类型强转();
        x= t2.add(3,7);
        System.out.println(x);
        }
    }
