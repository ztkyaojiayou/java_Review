package java基础.泛型;

import java.util.ArrayList;
import java.util.List;

public class GenericTest{
        public static void main(String[] args) {

//List list = new ArrayList() ;
//list. add("qqyumidi") ;
//list. add("corn") ;
//list. add(100);
List<String> list = new ArrayList<String>();//右边的泛型可以省略，但左边的必须写
list.add("电子科技大学");
list.add("bejjing");
list.add("chengdu");
list.add("100");
//list.add(100);//当指定了泛型为String类型时，则只可操作字符串类型，操作其他类型会报错

            for (String str:list) {
                System.out.println(str);//使用foreach循环进行遍历
            }
            for (int i = 0;i< list.size();i++){
                String name = list.get(i);
                System.out.println("name ="+ name);
            }
        }
    }

