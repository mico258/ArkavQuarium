package linkedlist;

/**
 *Class Node<T>.
 */
public class Node<T> {
    T data;
    Node<T> next;

    /**
     *Constructor node .
     */
    public Node() {
        data = null;
        next = null;
    }

    /**
     *Constructor node .
     * @param data .
     */
    public Node(T data) {
        this.data = data;
        this.next = null;
    }

    /**
     *getter data .
     * @return T .
     */
    public T getData() {
        return data;
    }

    /**
     *setter Data .
     * @param data .
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     *getter Next .
     * @return Node<T> .
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     *setter Next .
     * @param next .
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }
}
