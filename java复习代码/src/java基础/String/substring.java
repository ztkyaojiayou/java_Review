package java基础.String;

public class substring {

        public static void main(String[] args){

            String test = "Hello World !";

            String subTest1 = test.substring(0,3);//从索引的0开始，到索引为2的位置（不包括3）
            System.out.println("subTest:" + subTest1);//subTest:Hel

            String subTest2 = test.substring(0,test.length());//全截取
            System.out.println("subTest:" + subTest2);//subTest:Hello World !
        }
    }


