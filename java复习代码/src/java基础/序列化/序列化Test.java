package java基础.序列化;

import java.io.*;

class Teacher implements Serializable {
        private String name;
        private int age;
        //我不提供无参构造器
        public Teacher(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Teacher{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    public class 序列化Test {
        public static void main(String[] args) {
            //1.先序列化成字节序列
            try (//1.1先创建一个ObjectOutputStream输出流
                 ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("目标文件.txt"))) {
                //1.2再将对象序列化到文件object.txt中

                //1.2.1先创建一个对象，用于序列化
                Teacher teacher = new Teacher("电子科技大学", 65);
                //1.2.2再序列化到文件中
                oos.writeObject(teacher);
                System.out.println("序列化完成");
            } catch (Exception e) {
                e.printStackTrace();
            }

            //2.再反序列化成对象
            try (//2.1创建一个ObjectInputStream输入流
                 ObjectInputStream ois = new ObjectInputStream(new FileInputStream("目标文件.txt"))) {
                //2.2读取到该对象并返回，即反序列化完成
                Teacher brady = (Teacher) ois.readObject();
                System.out.println(brady);
                System.out.println("反序列化也完成啦");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        }

