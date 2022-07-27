package 数据结构与算法.离职后刷题;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author :zoutongkun
 * @date :2022/5/3 3:04 下午
 * @description :
 * @modyified By:
 */

/**
 * 为什么必须要用双向链表」呢？因为我们需要删除操作。
 * 删除一个节点不光要得到该节点本身的指针，也需要操作其前驱节点的指针，
 * 而双向链表才能支持直接查找前驱，保证操作的时间复杂度为 O(1)。
 */
//先定义结点
class DoubleListNode {
    public int key;
    public int val;
    public DoubleListNode pre;
    public DoubleListNode next;

    //构造函数，不用管pre和next
    public DoubleListNode(int key, int val) {
        this.key = key;
        this.val = val;
    }
}

/**
 * 注意:我们实现的双链表 API 只能从尾部插入，
 * 也就是说靠尾部的数据是最近使用的，
 * 靠头部的数据是最久为使用的。
 */
public class LRUDemo {
    //1.定义容量
    public int cap;
    //2.定义双向链表的头尾结点（均属于哑结点，即只是用于方便操作双向链表）
    public DoubleListNode head = new DoubleListNode(-1, -1);
    public DoubleListNode tail = new DoubleListNode(-1, -1);
    //3.定义map（key+Node）
    public HashMap<Integer, DoubleListNode> map = new HashMap<>();

    //构造函数（构造容量和双向链表）
    public LRUDemo(int cap) {
        //初始化容量
        this.cap = cap;

        //构建双向链表
        head.next = tail;
        tail.pre = head;

    }

    //开始写主体
    //1.get方法：获取指定元素
    public int get(int key) {
        //判空
        if (!map.containsKey(key)) {
            return -1;
        }

//        //若为获取前10个元素，则可以参考如下写法
//        int cnt = 0;
//        for (Integer curKey : map.keySet()) {
//            List<Integer> list = new ArrayList<>();
//            while (cnt <10){
//                DoubleListNode node = map.get(curKey);
//                //将其置为最新使用过的结点
//                setRecentLy(node, node.val);
//                list.add(map.get(curKey).val);
//            }
//        }

        DoubleListNode curNode = map.get(key);
        //将其置为最新使用过的结点
        setRecentLy(curNode, curNode.val);
        //同时返回
        return curNode.val;
    }

    //2.put方法
    public void put(int key, int val) {
        //1.若该key存在，则覆盖即可，同时更新该key为最新使用过的元素
        if (map.containsKey(key)) {
            //得到老结点
            DoubleListNode oldNode = map.get(key);
            //覆盖并将其置为最新使用的元素
            setRecentLy(oldNode, val);
            return;
        } else if (map.size() >= cap) {
            //2.若容量已满
            //2.1则要删除最头部的元素（head.next即为头部元素）
            DoubleListNode firstNode = head.next;
            deleteNode(firstNode);
            //2.2同时也要在map中删除
            map.remove(firstNode.key);
        }
        //3.常规：插入元素：map+双向链表（末尾）
        //3.1先创建该节点
        DoubleListNode newNode = new DoubleListNode(key, val);
        //3.2插入双向链表尾部
        insertTail(newNode);
        //3.3同时存入到map中
        map.put(key, newNode);
    }

    //将当前元素置为最新使用的元素
    private void setRecentLy(DoubleListNode curNode, int val) {
        //1.先将此节点从链表中删去（但map中不动）
        deleteNode(curNode);
        //2.再将其移至链尾，也即重新在链表尾部插入该元素
        insertTail(curNode);
        //3.若为put方法，则可能由于key存在，此时则只需要更新val即可，
        //而若为get方法，则不用更新，传入原值即可，相当于没更新
        curNode.val = val;
    }

    //删除一个结点
    public void deleteNode(DoubleListNode curNode) {
        curNode.pre.next = curNode.next;
        curNode.next.pre = curNode.pre;
    }

    //将当前结点插入到双向链表的尾部
    public void insertTail(DoubleListNode curNode) {
        curNode.pre = tail.pre;
        curNode.next = tail;
        tail.pre.next = curNode;
        tail.pre = curNode;
    }

}

class Test01 {
    public static void main(String[] args) {
        LRUDemo lruDemo = new LRUDemo(3);
        lruDemo.put(1, 2);
        lruDemo.put(2, 3);
        lruDemo.put(3, 4);
        lruDemo.put(4, 6);
        lruDemo.put(5, 4);
        lruDemo.put(6, 6);
        System.out.println(lruDemo.get(3));
    }
}
