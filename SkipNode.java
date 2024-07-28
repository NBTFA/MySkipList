public class SkipNode<T> {
    private T data;
    private SkipNode<T> next;
    private SkipNode<T> down;

    public SkipNode(T data, SkipNode<T> next, SkipNode<T> down) {
        this.data = data;
        this.next = next;
        this.down = down;
    }

    public T getData() {
        return data;
    }

    public SkipNode<T> getNext() {
        return next;
    }

    public SkipNode<T> getDown() {
        return down;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setNext(SkipNode<T> next) {
        this.next = next;
    }

    public void setDown(SkipNode<T> down) {
        this.down = down;
    }

    public int compareTo(T data) {
        return ((Comparable<T>) this.data).compareTo(data);
    }
}
