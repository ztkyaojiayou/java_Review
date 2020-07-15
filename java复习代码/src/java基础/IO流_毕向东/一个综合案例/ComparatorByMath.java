package java基础.IO流_毕向东.一个综合案例;

import java.util.Comparator;

//自定义一个比较规则
//方法：实现Comparator接口，再实现其compare方法即可
public class ComparatorByMath implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {

        int temp = o1.getMa() - o2.getMa();

        return temp==0?o1.getName().compareTo(o2.getName()):temp;
    }

}

