package 数据结构与算法.离职后刷题.第四遍必会版;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * 146. LRU 缓存
 * 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；
 * 如果不存在，则向缓存中插入该组 key-value 。
 * 如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 * <p>
 * 思路：
 *
 * @author :zoutongkun
 * @date :2022/4/25 4:19 下午
 * @description :
 * @modyified By:
 */

// 思路：使用LinkedHashMap（因为和其相关的源码有类似之处）
// 注意我们实现的双链表 API 只能从尾部插入，
// 也就是说靠尾部的数据是最近使用的，
// 靠头部的数据是最久未使用的。

// 示例：
///* 缓存容量为 2 */
//LRUCache cache = new LRUCache(2);
//// 你可以把 cache 理解成一个队列
//// 假设左边是队头，右边是队尾
//// 最近使用的排在队头，久未使用的排在队尾（注意：这里的示例与我们要使用的linkedHashMap刚好相反）
//// 圆括号表示键值对 (key, val)
//
//cache.put(1, 1);
//// cache = [(1, 1)]
//
//cache.put(2, 2);
//// cache = [(2, 2), (1, 1)]
//
//cache.get(1);       // 返回 1
//// cache = [(1, 1), (2, 2)]
//// 解释：因为最近访问了键 1，所以提前至队头
//// 返回键 1 对应的值 1
//
//cache.put(3, 3);
//// cache = [(3, 3), (1, 1)]
//// 解释：缓存容量已满，需要删除内容空出位置
//// 优先删除久未使用的数据，也就是队尾的数据
//// 然后把新的数据插入队头
//
//cache.get(2);       // 返回 -1 (未找到)
//// cache = [(3, 3), (1, 1)]
//// 解释：cache 中不存在键为 2 的数据
//
//cache.put(1, 4);
//// cache = [(1, 4), (3, 3)]
//// 解释：键 1 已存在，把原始值 1 覆盖为 4
//// 不要忘了也要将键值对提前到队头
//
//作者：labuladong
//链接：https://leetcode-cn.com/problems/lru-cache/solution/lru-ce-lue-xiang-jie-he-shi-xian-by-labuladong/
//来源：力扣（LeetCode）
//著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。


//其实就是借助LinkedHashMap来维护两个方法，即get和put方法
class LRUCache {
    //容量
    int cap;

    //构造方法
    public LRUCache(int capacity) {
        this.cap = capacity;
    }

    //使用一个LinkedHashMap来存储缓存
    LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<>();

    //方法1：get方法
    public int get(int key) {
        //可能不存在该key
        if (!cache.containsKey(key)) {
            return -1;
        }
        // 先要将 key 变为最近使用（只要使用了key，就都要做这一步呀）
        setRecently(key);
        //再取这个“最近使用的”
        return cache.get(key);
    }

    //方法2：put方法
    // 如果我们每次默认从链表尾部添加元素，
    // 那么显然越靠尾部的元素就是最近使用的，
    // 越靠头部的元素就是最久未使用的。
    public void put(int key, int val) {
        //分为三种情况
        //1.若该key已存在，则覆盖即可（此时不用管容量是否满了呀），同时要将该key置为“最近使用”
        //（易知，不管哪个操作，只有使用到了key，都要做这一步）
        if (cache.containsKey(key)) {
            // 修改/覆盖 key 的值
            cache.put(key, val);
            // 将 key 变为最近使用（key和value是一个整体，修改了key就相当于修改了）
            setRecently(key);
            return;
        }

        // 2.如果插入操作导致关键字数量超过 capacity ，
        // 则应该逐出“最久未使用的关键字”，即为最头部的元素。
        if (cache.size() >= this.cap) {
            // 链表头部就是最久未使用的 key
            int oldestKey = cache.keySet().iterator().next();
            cache.remove(oldestKey);
        }
        // 3.常规：即把新的 key 添加链表尾部（此时也刚好是最新元素）
        cache.put(key, val);
        return;
    }

    //将当前元素置为最新元素，插入后就在队尾了（这就是linkedHashMap的特点，即可以指定顺序）
    private void setRecently(int key) {
        //注意：这个key本来就在的，现在只是将其放置到末尾去
        //方法就是：先把这个key对应的value取出来，然后删除原来所在位置的key和value，
        //然后在重新插入一次，此时就在末尾了，此时就是最新的了
        int val = cache.get(key);
        // 删除 key，重新插入到队尾，此时就为最新了
        cache.remove(key);
        cache.put(key, val);
    }
}


//自写一遍
class LRUCacheDemo {
    //容量
    int cap;

    //构造方法
    LRUCacheDemo(int cap) {
        this.cap = cap;
    }

    //有面试官要求这个数据结构要自己写，就离谱！！！
    LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();

    //方法1：get方法
    public int get(int key) {
        //可能为空
        if (!map.containsKey(key)) {
            return -1;
        }
        //由于获取的需是最新的元素，因此要记得将其置于map的末尾（表示最新）
        setRecently(key);
        return map.get(key);
    }

    //方法2：put方法
    public void put(int key, int val) {
        //若有该key，则覆盖即可
        if (map.containsKey(key)) {
            map.put(key, val);
            //同时记得将其置为最新（只要使用了该key就应该维护！！！）
            setRecently(key);
            return;
        }

        //若当前map已经满了，此时若还要加元素的话，
        //就需要先把最近最久未使用的元素剔除掉（这个就是核心！！！）
        if (map.size() >= this.cap) {
            int oldKey = map.keySet().iterator().next();
            map.remove(oldKey);
        }

        //否则，就正常插入，此时就在最末尾了，
        //也刚好是最新元素，无需使用setRecently方法啦
        map.put(key, val);
    }

    //将当前正在使用的key置为最新
    //（注意：易知这个key肯定是已经存在map中的）
    private void setRecently(int key) {
        Integer val = map.get(key);
        map.remove(key);
        map.put(key, val);
    }

    public static void main(String[] args) {
//        //测试泛型的擦除
//        ArrayList list = new ArrayList();
//        list.add(111);
//        list.add("111");
        LRUCacheDemo lruCacheDemo = new LRUCacheDemo(3);
        lruCacheDemo.put(1, 3);
        lruCacheDemo.put(2, 4);
        lruCacheDemo.put(3, 51);
        lruCacheDemo.put(4, 5);
        lruCacheDemo.put(5, 12);
        int res = lruCacheDemo.get(2);
        System.out.println(res);
    }
}


/**
 * 方法2：还是使用linkedhashMap的思想，只是这个双向链表自己实现（TMD面试好恶心，艹）
 * https://leetcode-cn.com/problems/lru-cache/solution/shou-si-lruzhe-yi-pian-zu-gou-liao-shuan-zf3a/
 */

//1.先定义双向链表的结点
class Node {
    //记录key与val的同时，还记录当前节点的pre节点与next节点--双向链表
    public int key;//即map中的那个key，用于映射，使用的value则是val
    public int val;//key对应的实际的值

    public Node pre;
    public Node next;

    //构造方法，初始化节点（不用管pre和next结点）
    public Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
}

//2.再就可以写主体了
class LRUCache02 {
    //缓存容量大小
    public int cap;
    //头结点与尾结点
    public Node head = new Node(-1, -1);
    public Node tail = new Node(-1, -1);

    //使用一个map--用于记录缓存中存储的数据（key+Node，这个node中就有对应的key和value）
    //（注意：map还是可以使用现成的的！！！）
    public HashMap<Integer, Node> map = new HashMap<>();

    //LRU缓存的构造（包括map，双向链表，容量）
    public LRUCache02(int capacity) {
//        //1.map
//        map = new HashMap<>();
        //2.构造双向链表
        //2.1先定义头结点和尾结点
//        head = new Node(-1, -1);
//        tail = new Node(-1, -1);
        //2.2再具体构造双向链表
        head.next = tail;
        tail.pre = head;
        // 3.容量
        this.cap = capacity;
    }

    //2.1get方法
    public int get(int key) {
        //先尝试从缓存map中获取对应key的节点（其中就有key和value）
        Node node = map.get(key);
        //如果不存在就返回-1
        if (node == null) {
            return -1;
        }
        //将其移至链尾，代表最近使用过
        setRecently(node, node.val);
        //若有，则同时返回节点的val
        return node.val;
    }

    //2.2put方法
    public void put(int key, int value) {
        //如果当前map缓存中存在这个key,就覆盖，
        //并去map中找到并且将其节点移至链尾 表示最近使用过
        if (map.containsKey(key)) {
            Node node = map.get(key);
            setRecently(node, value);
        } else {
            //如果当前缓存数据已满 则需要删除最开始的节点数据 清除其在map缓存中的位置
            if (map.size() >= this.cap) {
                //删除第一个结点，也即最近最少使用的那个结点
                Node node = head.next;
                //这里的删除分为两步：删除map中的该key以及在链表中对应的该结点
                //1.删除其在链表中对应的该结点
                delete(node);
                //2.同时删除map中的该key
                map.remove(node.key);
            }
            //常规：创建新的节点数据，
            //并且添加到链尾表示最近访问过的同时将其加入到map缓存中
            Node newNode = new Node(key, value);
            //即：对于一个新节点，也是也是分为两步，在map中存储，同时插入到链表的尾部
            //1.插到尾部
            insertTail(newNode);
            //2.同时也存入map
            map.put(key, newNode);
        }
    }

    //即上面的setRecently方法，这里的val参数主要是为put方法服务的~
    public void setRecently(Node node, int val) {
        //1.先将此节点删去
        delete(node);
        //2.再将其移至链尾，也即重新在链表尾部插入该元素
        insertTail(node);
        //3.为其赋值
        node.val = val;
    }

    //删除双向链表中的该节点（该节点在任意位置，但对于链表而言，时间复杂度就是O(1)哦！！！）
    //非常好理解，就是把当前节点前后节点的指针换一下指向即可！！！
    public void delete(Node node) {
        //要删除的节点的pre节点的next节点 为当前节点的next节点
        node.pre.next = node.next;
        //要删除节点的next节点的pre节点 为当前节点的pre节点
        node.next.pre = node.pre;
    }

    //（看似简单，但好像还是没太懂）
    // 即此时的当前结点要位于tail结点和其前一个结点的中间，
    // tail结点只是一个象征性的结点，需要一直在最尾部（因此不用管tail.next的情况啦），
    // 我们插入到链表尾部的所谓尾结点依旧需要在tail结点的前面！！！
    public void insertTail(Node node) {
        //都使用tail结点来做桥梁！！！
        //1.先安置当前结点
        //1.1当前节点的pre指针为尾结点的pre节点
        node.pre = tail.pre;
        //1.2当前节点的next节点为node节点
        node.next = tail;
        //2.再安置tail结点
        //2.1然后更改尾结点的位置，此时尾结点的pre节点的next节点应该指向node
        tail.pre.next = node;
        //2.2尾结点的pre节点就是node节点
        tail.pre = node;
    }
}



