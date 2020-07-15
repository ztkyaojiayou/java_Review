package 数据结构与算法.联发科极限编程大赛;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

class Node4 {
    int cur, left, right;

    public Node4(int cur, int left, int right) {
        this.cur = cur;
        this.left = left;
        this.right = right;
    }
}
class self_Comparator implements Comparator<Node4> {
    @Override
    public int compare(Node4 o1, Node4 o2) {
        if(o1.cur <o2.cur) return -1;
        else if(o1.cur >o2.cur) return 1;
        else if(o1.left <o2.left) return -1;
        return 1;
    }

}

public class Main_4 {
        static long result = 0;
        //行list
        static ArrayList<Node4> row = new ArrayList<>();
        //列list
        static ArrayList<Node4> col = new ArrayList<>();
        public static void main(String[] args) throws IOException {
            //输入
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            while(n-->0) {
                int x1 = scanner.nextInt();
                int y1 = scanner.nextInt();
                int x2 = scanner.nextInt();
                int y2 = scanner.nextInt();
                if(x1==x2) row.add(new Node4(x1,Math.min(y1, y2),Math.max(y1, y2)));
                else col.add(new Node4(y1,Math.min(x1, x2),Math.max(x1, x2)));
            }
            //按自定义的比较器排序
            row.sort(new self_Comparator());
            col.sort(new self_Comparator());
            //归并排序
            row = merge(row);
            col = merge(col);
            //遍历
            for(Node4 r : row) {
                for(Node4 c : col) {
                    if(c.left <=r.cur &&r.cur <=c.right &&r.left <=c.cur &&c.cur <=r.right) {
                        result--;
                    }
                }
            }
            //打印结果
            System.out.println(result);
        }
        //归并的具体实现，把排序后的结果存入list中
        private static ArrayList<Node4> merge(ArrayList<Node4> list) {
            ArrayList<Node4> res = new ArrayList<>();
            Node4 last = new Node4(-0x7fffffff,-0x7fffffff,-0x7fffffff);
            for(Node4 node : list) {
                if(last.cur ==node.cur) {
                    if(last.right <node.left) {//不相邻时
                        res.add(last);
                        result +=last.right -last.left +1;
                        last = node;
                    }else {//相邻时
                        last.right = Math.max(last.right, node.right);
                    }
                }else {//不在一列或一行时
                    if(last.cur !=-0x7fffffff) {//排除第一次
                        res.add(last);
                        result +=last.right -last.left +1;
                    }
                    last = node;
                }
            }
            res.add(last);
            result +=last.right -last.left +1;
            return res;
        }
    }
