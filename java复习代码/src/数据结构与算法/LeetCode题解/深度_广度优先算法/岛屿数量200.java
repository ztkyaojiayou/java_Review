package 数据结构与算法.LeetCode题解.深度_广度优先算法;

/**
 * 200. 岛屿数量
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 * 示例 1:
 * 输入:
 * 11110
 * 11010
 * 11000
 * 00000
 * 输出: 1
 *
 * 示例 2:
 * 输入:
 * 11000
 * 11000
 * 00100
 * 00011
 * 输出: 3
 * 解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
 */

import java.util.LinkedList;

/**
 * 思路一：深度优先遍历DFS
 * 目标是找到矩阵中 “岛屿的数量” ，上下左右相连的 1 都被认为是连续岛屿。
 * dfs方法： 设目前指针指向一个岛屿中的某一点 (i, j)，寻找包括此点的岛屿边界。
 * 从 (i, j) 向此点的上下左右 (i+1,j),(i-1,j),(i,j+1),(i,j-1) 做深度搜索。
 * 终止条件：
 * (i, j) 越过矩阵边界;
 * grid[i][j] == 0，代表此分支已越过岛屿边界。
 * 搜索岛屿的同时，执行 grid[i][j] = '0'，即将岛屿所有节点删除，以免之后重复搜索相同岛屿。
 * 主循环：
 * 遍历整个矩阵，当遇到 grid[i][j] == '1' 时，从此点开始做深度优先搜索 dfs，
 * 岛屿数 count + 1 且在深度优先搜索中删除此岛屿。
 * 最终返回岛屿数 count 即可。
 *
 * 参考链接：https://leetcode-cn.com/problems/number-of-islands/solution/number-of-islands-shen-du-you-xian-bian-li-dfs-or-/
 */

//这个版本太清晰了，强烈推荐
class 岛屿数量 {
    public int numIslands(char[][] nums) {
        int count = 0;
        //从原点开始，当为岛屿时，才向其四周搜索，因为才有意义嘛
        for(int i = 0; i < nums.length; i++) {
            for(int j = 0; j < nums[0].length; j++) {
                if(nums[i][j] == '1'){
                    dfs(nums, i, j);
                    //搜完一次，count记一次数
                    count++;
                }
            }
        }
        //最后返回结果即可
        return count;
    }

    //深度优先搜索（递归）
    private void dfs(char[][] nums, int i, int j){
        //递归出口，即要么越界要么搜索到了有水区域
        if(i < 0 || j < 0 || i >= nums.length || j >= nums[0].length || nums[i][j] == '0') {
            return;
        }
        //做选择
        nums[i][j] = '0';//即将该岛屿置为“水”，也即删除该岛屿，以免之后重复搜索相同岛屿
        //开始下一层递归，即开始向四周搜索，找到了岛屿就把它删掉/替换
        dfs(nums, i + 1, j);
        dfs(nums, i, j + 1);
        dfs(nums, i - 1, j);
        dfs(nums, i, j - 1);
    }
}

/**
 *补充：关于在java中的队列Queue的说明：
 * 首先，它是一个接口，而不是一个具体的实现类，其下面有三个子接口，
 *即 阻塞双端队列BlockingDeque<E>, 阻塞队列BlockingQueue<E>, 双端队列Deque<E>（注意，并没有所谓的单端/普通队列）
 *根据接口的不同，还还提供了很多具体的实现类，比如在非阻塞队列中，就有：LinkedList（常用）, ArrayDeque, DelayQueue, PriorityQueue
 *要注意的是：在队列的具体实现类中，并没有一个叫queue的普通队列供我们使用，因此当我们需要使用队列时，一般就是使用LinkedList，即链表型list。
 *不用惊讶，LinkedList类既实现了我们熟悉的List<E>，还实现了双端队列Deque<E>，而该类又实现了队列的总接口Queue<E>，
 *因此 LinkedList即是一个链表，又是一个队列（但ArrayList则只是一个list，与队列毫无关系）
 *
 在Java中，LinkedList提供了丰富的方法，可以模拟链式队列，链式堆栈等数据结构，
 为用户带来了极大的方便，下面看看这些方法的用法：
 （1）添加：add,offer 和 push
 1）add
 boolean add(E e)：在链表后添加一个元素，如果成功，返回true，否则返回false；
 void addFirst(E e)：在链表头部插入一个元素；
 addLast(E e)：在链表尾部添加一个元素；
 void add(int index, E element)：在指定位置插入一个元素。
 2）offer
 boolean offer(E e)：在链表尾部插入一个元素；
 boolean offerFirst(E e)：与addFirst一样，实际上它就是addFirst；
 boolean offerLast(E e)：与addLast一样，实际上它就是addLast；
 3）push
 void push(E e)：与addFirst一样，实际上它就是addFirst；

 （2）删除：remove 和 pop
 1）remove
 E remove()；移除链表中第一个元素；
 boolean remove(Object o)：移除链表中指定的元素；
 E remove(int index)：移除链表中指定位置的元素；
 E removeFirst()：移除链表中第一个元素，与remove类似；
 E removeLast()：移除链表中最后一个元素；
 2）pop
 E pop()：与removeFirst一样，实际上它就是removeFirst；

 （3）获取：get,peek 和 poll
 1）get
 E get(int index)：按照下边获取元素；
 E getFirst()：获取第一个元素；
 E getLast()：获取第二个元素；
 2）peek
 E peek()：获取第一个元素，但是不移除；
 E peekFirst()：获取第一个元素，但是不移除；
 E peekLast()：获取最后一个元素，但是不移除；
 3）poll
 E poll()：查询并移除第一个元素；
 */