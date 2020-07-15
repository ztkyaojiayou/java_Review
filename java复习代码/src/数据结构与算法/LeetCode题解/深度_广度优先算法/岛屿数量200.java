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
 * 这道题是可以使用一个经典的算法来解决的，那就是 Flood fill，以下的定义来自 维基百科：Flood fill 词条。
 * Flood fill 算法是从一个区域中提取若干个连通的点与其他相邻区域区分开（或分别染成不同颜色）的经典 算法。
 * 因为其思路类似洪水从一个区域扩散到所有能到达的区域而得名。在 GNU Go 和 扫雷 中，Flood Fill算法被用来计算需要被清除的区域。
 * “Flood” 我查了一下，作为动词是 “淹没；充满” 的意思，作为名词是 “洪水” 的意思。
 * 下面我们简单解释一下这个算法：
 * 从一个区域中提取若干个连通的点与其他相邻区域区分开
 * 从一个点扩散开，找到与其连通的点，这不是什么高深的算法，其实就是从一个点开始，进行一次 “深度优先遍历” 或者 “广度优先遍历”，
 * 通过 “深度优先遍历” 或者 “广度优先遍历” 发现一片连着的区域，
 * 对于这道题来说，就是从一个是 “陆地” 的格子开始进行一次 “深度优先遍历” 或者 “广度优先遍历”，
 * 把与之相连的所有的格子都标记上，视为发现了一个 “岛屿”。
 *
 * 说明：这里做 “标记” 的意思是，通过 “深度优先遍历” 或者 “广度优先遍历” 操作，
 * 我发现了一个新的格子，与起始点的那个格子是连通的，我们视为 “标记” 过，也可以说 “被访问过”。
 *
 * 那么每一次进行 “深度优先遍历” 或者 “广度优先遍历” 的条件就是：
 * 1、这个格子是陆地 1，如果是水域 0 就无从谈论 “岛屿”；
 * 2、这个格子不能是之前发现 “岛屿” 的过程中执行了 “深度优先遍历” 或者 “广度优先遍历” 操作，
 * 而被标记的格子（这句话说得太拗口了，大家意会即可，意会不了不是您的问题，是我表达的问题，直接看代码会清楚很多）。
 */
public class 岛屿数量200 {
    /**
     * 方法一：深度优先遍历：遍历所有点（陆地和水），把与一块陆地的所有相邻的陆地全部找到，就构成一片岛屿啦
     * 原理：每一片相邻的陆地肯定是一个岛屿
     */
        //           x-1,y
        //  x,y-1    x,y      x,y+1
        //           x+1,y
        // 方向数组，它表示了相对于当前位置的 4 个方向的横、纵坐标的偏移量，这是一个常见的技巧
        private static final int[][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        // 标记数组，标记了 grid 的坐标对应的格子是否被访问过
        private boolean[][] marked;
        // grid 的行数
        private int rows;
        // grid 的列数
        private int cols;
        private char[][] grid;

        public int numIslands(char[][] grid) {
            rows = grid.length;//行数
            if (rows == 0) {
                return 0;
            }
            cols = grid[0].length;//列数
            this.grid = grid;
            //“标记是否已经访问过了”的数组，默认全为false，表示没有被访问过
            marked = new boolean[rows][cols];
            int count = 0;//用于计数，每一次递归结束就相当于找到了一个岛屿
            //开始遍历每一个点，从原点开始
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    // 如果是陆地（1），并且没有被访问过，就进行深度优先遍历（此时才有意义嘛）
                    //同时结果值count加一（因为每一次深度遍历会把所有与之相邻的陆地全找出来，
                    //肯定就是一片岛屿，则有几次深度遍历就会找到几座岛屿）
                    if (!marked[i][j] && grid[i][j] == '1') {
                        count++;//结果值加1
                        dfs(i, j);//开始深度遍历，找到与之相邻的所有陆地
                    }
                }
            }
            //当所有的坐标都访问完之后，返回结果即可
            return count;
        }

        // 深度遍历的具体方法（也是回溯/递归）
        // 从坐标为 (i,j) 的点开始进行深度优先遍历
        private void dfs(int i, int j) {
            marked[i][j] = true;//遍历过了的点要标记为true，表示已经访问过
            // 得到 4 个方向的坐标（固定写法）
            for (int k = 0; k < 4; k++) {
                int newX = i + directions[k][0];
                int newY = j + directions[k][1];
                // 递归的条件（其实也是递归终止的条件），有三个：
                // （1）如果不越界、（2）没有被访问过、（3）并且还要是陆地
                //  则继续递归，进行深度优先遍历
                if (inArea(newX, newY) && grid[newX][newY] == '1' && !marked[newX][newY]) {
                    //开始递归，深度优先遍历
                    dfs(newX, newY);
                }
            }
        }

        // 判断当前点是否越界的方法（返回true则表示没有越界，false则表示越界）
        // （单独封装成了一个方法而已）
        private boolean inArea(int x, int y) {
            //等于号不要忘了
            return x >= 0 && x < rows && y >= 0 && y < cols;
        }


        //测试
        //案例1
        public static void main(String[] args) {
            岛屿数量200 solution = new 岛屿数量200();
            char[][] grid1 = {
                    {'1', '1', '1', '1', '0'},
                    {'1', '1', '0', '1', '0'},
                    {'1', '1', '0', '0', '0'},
                    {'0', '0', '0', '0', '0'}};
            int numIslands1 = solution.numIslands(grid1);
            System.out.println(numIslands1);
            //案例2:
            char[][] grid2 = {
                    {'1', '1', '0', '0', '0'},
                    {'1', '1', '0', '0', '0'},
                    {'0', '0', '1', '0', '0'},
                    {'0', '0', '0', '1', '1'}};
            int numIslands2 = solution.numIslands(grid2);
            System.out.println(numIslands2);
        }
    }

/**
 * 方法二：广度优先遍历：思路完全相同，纯粹只是遍历的方式不同而已
 * 具体过程：先把每一个点都转化为一个数（方式随意），比如：下面这个矩阵，其中，令尾部带1的表示陆地，其他的代表水，
 * 11   21  31  41   5
 * 61   71  8   91  10
 * 111 121  13  14  15
 * 16  17   18  19  20
 *
 * 对每一个点（陆地或水）都进行遍历（两层循环即可），在其四周寻找陆地，找到了就把其标记为“已被访问过”，同时继续从找到的陆地中任选一个点继续找，否则，撤回到上一级。
 * 先从第一个点11（0,0）开始，11先入队列，此时该点能遍历到的陆地为21和61，把其加入到队列的末尾，此时11的使命完成，出队，于是队列中现在存的就是21和61；
 * 此时用21再去遍历，此时可以遍历到71和31这两块陆地，于是也把其入队，此时陆地21的使命完成，出队，此时队列中的数为61,71,31；
 * 再从61开始寻找，可以找到11这块新陆地（71由于已经找过了，则此时会跳过，不再入队，会被标记为“之前已经被访问过”），
 * 将其入队，以此类推，直到遍历结束，由于所有找到的陆地构成了一个连通的区域，因此可以构成一个岛屿，于是结果加1。
 * 此时还只是从11这个点的遍历结束，接下来，要按照同样地方法和过程继续遍历其他的所有点，
 * 易知，每一次遍历所得到的连通区域都可以构成岛屿，于是，只需对结果进行累加即可。
 */
class Solution2 {
    private int rows;
    private int cols;
    private boolean[][] marked;
    //代表一个点的前后左右四个位置
    private static final int[][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    char[][] grid;
    public int numIslands(char[][] grid) {
        //           x-1,y
        //  x,y-1    x,y      x,y+1
        //           x+1,y
        rows = grid.length;//行数
        if (rows == 0) {
            return 0;
        }
        cols = grid[0].length;//列数
        this.grid = grid;
        marked = new boolean[rows][cols];
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 如果是岛屿中的一个点，并且没有被访问过
                // 从坐标为 (i,j) 的点开始进行广度优先遍历
                if (!marked[i][j] && grid[i][j] == '1') {
                    count++;
                    bfs(i,j);
                }

                }
            }
        //返回最终结果即可
        return count;
        }

        //具体的广度遍历算法
    private void bfs(int i, int j) {
        //广度优先搜索需要一个额外的队列（这是与深度优先遍历不同的地方），使用LinkedList（勿大惊小怪，它就是队列的一个实现，且常用）
        LinkedList<Integer> queue = new LinkedList<>();
        // 把该坐标所对应的值存入队列中
        // 小技巧：把坐标转换为一个数字
        // 否则，得用一个数组存
        queue.addLast(i * cols + j);
        // 注意：这里要把该位置标记成已经访问过
        //【特别注意】在放入队列以后，要马上标记成已经访问过，语义也是十分清楚的：反正只要进入了队列，你迟早都会遍历到它
        // 而不是在出队列的时候再标记
        //【特别注意】如果是出队列的时候再标记，会造成很多重复的结点进入队列，造成重复的操作，这句话如果你没有写对地方，代码会严重超时的
        marked[i][j] = true;
        while (!queue.isEmpty()) {
            int cur = queue.removeFirst();//第一个元素（也就是当前用于遍历的元素，因为它已经完成了使命）出队
            // 得到 4 个方向的坐标
            for (int k = 0; k < 4; k++) {
                int newX = i + directions[k][0];
                int newY = j + directions[k][1];
                // 如果不越界、没有被访问过、并且还要是陆地，我就继续放入队列，放入队列的同时，要记得标记已经访问过
                if (inArea(newX, newY) && grid[newX][newY] == '1' && !marked[newX][newY]) {
                    //queue.addLast(newX * cols + newY);

                    //marked[newX][newY] = true;
                    bfs(newX,newY);
                }
            }
        }
    }

    private boolean inArea(int x, int y) {
        // 等于号这些细节不要忘了
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        char[][] grid1 = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}};
        int numIslands1 = solution2.numIslands(grid1);
        System.out.println(numIslands1);

        char[][] grid2 = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}};
        int numIslands2 = solution2.numIslands(grid2);
        System.out.println(numIslands2);
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