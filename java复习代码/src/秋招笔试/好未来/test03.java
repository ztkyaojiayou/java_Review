package 秋招笔试.好未来;

import java.util.HashSet;

public class test03 {
    public static void main(String[] args) {
        HashSet set = new HashSet();
        set.add(new String("String"));
        set.add(new String("String"));
        set. add(new Object());
        set.add(new Object());
        set.add(new Integer(0));
        set. add(new Integer(0));
        int size = set.size();
        System.out.println(size);
    }
}
