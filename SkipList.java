import java.util.Random;

public class SkipList<T> {
    private SkipNode<T> head;
    private int size;
    private int level;
    private final int maxLevel;
    private final double p;
    private final Random rand;

    public SkipList(int maxLevel, double p) {
        this.head = new SkipNode<T>(null, null, null);
        this.size = 0;
        this.maxLevel = maxLevel;
        this.level = 0;
        this.p = p;
        this.rand = new Random();
    }

    private int randomLevel() {
        int tmpLevel = 1;
        while (tmpLevel < maxLevel && rand.nextDouble() < p) {
            tmpLevel++;
        }
        return tmpLevel;
    }

    public int add(T data)
    {
        int tmpLevel = randomLevel();
        SkipNode<T>[] update = new SkipNode[maxLevel];

        // 创建层级
        if (tmpLevel>level)
        {
            SkipNode<T> curHead = head;
            SkipNode<T> cur = new SkipNode<>(null, null, null);
            head = cur;
            for (int i = level; i < tmpLevel - 1; i++) {
                SkipNode<T> node = new SkipNode<>(null, null, null);
                cur.setDown(node);
                cur = node;
            }
            cur.setDown(curHead);
        }

        // 找到插入位置
        SkipNode<T> pre = head;
        for (int i = level - 1; i >= tmpLevel; i--)
            pre = pre.getDown();
        for (int i = tmpLevel - 1; i >= 0; i--) {
            while (pre.getNext() != null && pre.getNext().compareTo(data)<0)
                pre = pre.getNext();
            update[i] = pre;
            pre = pre.getDown();
        }

        // 插入
        SkipNode<T> topNode = null;
        for (int i = 0; i < tmpLevel; i++) {
            SkipNode<T> node = new SkipNode<>(data, null, null);
            if (topNode != null)
                node.setDown(topNode);
            topNode = node;
            if (update[i].getNext() != null)
                node.setNext(update[i].getNext());
            update[i].setNext(node);
        }

        if(tmpLevel>level)
            level = tmpLevel;
        size++;
        return tmpLevel;
    }

    public boolean search(T data) {
        SkipNode<T> cur = head;
        for (int i = level - 1; i >= 0; i--) {
            while (cur.getNext() != null && cur.getNext().compareTo(data)<0)
                cur = cur.getNext();
            if (cur.getNext() != null && cur.getNext().compareTo(data)==0)
                return true;
            cur = cur.getDown();
        }
        return false;
    }

    public boolean remove(T data) {
        SkipNode<T> cur = head;
        boolean flag = false;
        for (int i = level - 1; i >= 0; i--) {
            while (cur.getNext() != null && cur.getNext().compareTo(data)<0)
                cur = cur.getNext();
            if (cur.getNext() != null && cur.getNext().compareTo(data)==0) {
                cur.setNext(cur.getNext().getNext());
                flag = true;
            }
            cur = cur.getDown();
        }
        if (flag)
            size--;
        return flag;
    }
    public void print() {
        SkipNode<T> cur = head;
        while (cur != null) {
            SkipNode<T> tmp = cur.getNext();
            while (tmp != null) {
                System.out.print(tmp.getData() + ",");
                tmp = tmp.getNext();
            }
            System.out.println();
            cur = cur.getDown();
        }

    }
}
