package 数据结构与算法.LeetCode题解.深度_广度优先算法;

/**
 * 207. 课程表(与图有关，了解即可）
 * 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1。
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，
 * 你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
 * 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
 *
 * 示例 1:
 * 输入: 2, [[1,0]]
 * 输出: true
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
 *
 * 示例 2:
 * 输入: 2, [[1,0],[0,1]]
 * 输出: false
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；
 * 并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
 *
 * 提示：
 * 输入的先决条件是由 边缘列表 表示的图形，而不是 邻接矩阵 。
 * 详情请参见图的表示法。
 * 你可以假定输入的先决条件中没有重复的边。
 * 1 <= numCourses <= 10^5
 */

import java.util.*;

/**
 *（思路懂，但代码没看懂）
 *思路解析：从某门课开始遍历，我们通过 DFS 一条路径一条路径的判断，保证过程中没有遇到环。
 *先把题给的“关系”数组看成图的边，所有的边就构成了一个有向图，
 *那具体怎么把其转化为图呢？利用邻接表的方式即可。
 *对图中的每一个节点使用深度优先遍历搜索，
 *此时，题目的意思即转化为：所遍历出来的路径不存在闭环，否则不符合题意，返回false
 * 比如：对于[[1,3],[1,4],[2,4],[3,5],[3,6],[4,6]] 就可以看做下边的图，箭头指向的是需要先上的课（向下指）。
 *           1    2
 *          / \  /
 *         3   4
 *        / \ /
 *        5  6
 * 深度优先遍历 1，相当于 3 条路径
 * 1 -> 3 -> 5，1 -> 3 -> 6，1 -> 4 -> 6,没有闭环。
 * 深度优先遍历 2，相当于 1 条路径 2 -> 4 -> 6，没有闭环。
 * 深度优先遍历 3，相当于 2 条路径 3 -> 5，3 -> 6，没有闭环。
 * 深度优先遍历 4，相当于 1 条路径 4 -> 6，没有闭环。
 * 深度优先遍历 5，相当于 1 条路径5，没有闭环。
 * 深度优先遍历 6，相当于 1 条路径6，没有闭环。
 * 由于所有的路径都没有闭环，因此符合要求，即可以按照题给要求修完所有课程，返回true。
 *
 * 但若出现如下情况，即3和6之间是双向的，则肯定会出现闭环，此时即修课程3之前要先修课程6，
 * 而修课程6之前又要先修课程3，显然矛盾，故返回false。
 *            1    2
 *           / \  /
 *           3   4
 *          / \\ /
 *          5   6
 *
 * 注解：也可以使用广度优先搜索，思路是：
 * 很简单，要想上完所有的课，一定会有一些课没有先修课，比如上图的 5、6。
 * 然后我们可以把 5 和 6 节点删去。
 * 然后 3 和 4 就可以上了，同样的道理再把 3 和 4 删去。
 * 接下来就可以去学 1 和 2 了。因此可以完成所有的课。
 * 代码的话，用邻接表表示图。此外，我们不需要真的去删除节点，我们可以用 outNum 变量记录所有节点的先修课门数。
 * 当删除一个节点的时候，就将相应节点的先修课个数减一即可。
 * 最后只需要判断所有的节点的先修课门数是否全部是 0 即可。
 */
public class 课程表207 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ///先使用邻接表的方式把数组转化为图（基础知识，见韩顺平讲义）
        HashMap<Integer, ArrayList<Integer>> outNodes = new HashMap<>();
        HashSet<Integer> set = new HashSet<>();
        int rows = prerequisites.length;
        for (int i = 0; i < rows; i++) {
            int key = prerequisites[i][0];
            int value = prerequisites[i][1];
            set.add(key);
            if (!outNodes.containsKey(key)) {
                outNodes.put(key, new ArrayList<>());
            }
            //存储当前节点的所有先修课程
            ArrayList<Integer> list = outNodes.get(key);
            list.add(value);
        }

        HashSet<Integer> visitedFinish = new HashSet<>();
        //判断每一门课
        for (int k : set) {
            if (!dfs(k, outNodes, new HashSet<>(), visitedFinish)) {
                return false;
            }
            visitedFinish.add(k);
        }
        return true;
    }

    private boolean dfs(int start, HashMap<Integer, ArrayList<Integer>> outNodes, HashSet<Integer> visited,
                        HashSet<Integer> visitedFinish) {
        //已经处理过或者到了叶子节点
        if (visitedFinish.contains(start) || !outNodes.containsKey(start)) {
            return true;
        }
        //出现了环
        if (visited.contains(start)) {
            return false;
        }
        //将当前节点加入路径
        visited.add(start);
        ArrayList<Integer> list = outNodes.get(start);
        for (int k : list) {
            if(!dfs(k, outNodes, visited, visitedFinish)){
                return false;
            }
        }
        visited.remove(start);
        return true;
    }
}

//定义节点
class Node207 {
    int val;
    Node207 next;

    Node207(int val) {
        this.val = val;
    }

    Node207(int val, Node207 next) {
        this.val = val;
        this.next = next;
    }
}

//写法2：
class Solution207 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) return true;

        // 构建图
        Node207[] graph = new Node207[numCourses];//图的节点数
        for (int[] plan : prerequisites) {
            // 头插法
            graph[plan[0]] = new Node207(plan[1], graph[plan[0]]);
        }
        // 判断是否存在环
        int[] visited = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            // 未遍历则遍历，如果存在环，则不符合要求
            if(visited[i] != -1 && !isDAG(i, graph, visited)) {
                return false;
            }
            // 遍历完成，以i出发不存在环路，置为访问且无环
            visited[i] = -1;
        }
        return true;
    }

//使用深度优先搜索算法，判断是否存在环
    private boolean isDAG(int start, Node207[] graph, int[] visited) {
        // 置为正在遍历
        visited[start] = 1;
        Node207 temp = graph[start];
        while (temp != null) {
            // 之前已经遍历过，无环
            if (visited[temp.val] == -1) {
                temp = temp.next;
                continue;
            }
            // 存在环路
            if(visited[temp.val] == 1 || !isDAG(temp.val, graph, visited)) return false;
            // 遍历结束，置为已访问且无环
            visited[temp.val] = -1;
            temp = temp.next;
        }
        return true;
    }
}

//广度优先搜索的代码
class solutions2071{
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //保存每个节点的先修课个数，也就是出度
        HashMap<Integer, Integer> outNum = new HashMap<>();
        //保存以 key 为先修课的列表，也就是入度的节点
        HashMap<Integer, ArrayList<Integer>> inNodes = new HashMap<>();
        //保存所有节点
        HashSet<Integer> set = new HashSet<>();
        int rows = prerequisites.length;
        for (int i = 0; i < rows; i++) {
            int key = prerequisites[i][0];
            int value = prerequisites[i][1];
            set.add(key);
            set.add(value);
            if (!outNum.containsKey(key)) {
                outNum.put(key, 0);
            }
            if (!outNum.containsKey(value)) {
                outNum.put(value, 0);
            }
            //当前节点先修课个数加一
            int num = outNum.get(key);
            outNum.put(key, num + 1);

            if (!inNodes.containsKey(value)) {
                inNodes.put(value, new ArrayList<>());
            }
            //更新以 value 为先修课的列表
            ArrayList<Integer> list = inNodes.get(value);
            list.add(key);
        }

        //将当前先修课个数为 0 的课加入到队列中
        Queue<Integer> queue = new LinkedList<>();
        for (int k : set) {
            if (outNum.get(k) == 0) {
                queue.offer(k);
            }
        }
        while (!queue.isEmpty()) {
            //队列拿出来的课代表要删除的节点
            //要删除的节点的 list 中所有课的先修课个数减一
            int v = queue.poll();
            ArrayList<Integer> list = inNodes.getOrDefault(v, new ArrayList<>());

            for (int k : list) {
                int num = outNum.get(k);
                //当前课的先修课要变成 0, 加入队列
                if (num == 1) {
                    queue.offer(k);
                }
                //当前课的先修课个数减一
                outNum.put(k, num - 1);
            }
        }

        //判断所有课的先修课的个数是否为 0
        for (int k : set) {
            if (outNum.get(k) != 0) {
                return false;
            }
        }
        return true;
    }
}
