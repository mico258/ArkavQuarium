package linkedlist;

/**
 *Class Linked List .
 */
public class LinkedList<T> {
    /**
     *head .
     */
    Node<T> head;

    /**
     *constructor LinkedList .
     */
    public LinkedList() {
        head = null;
    }

    /**
     *getter isEmpty .
     * @return boolean .
     */
    public boolean isEmpty() {
        if (head == null) {
            return true;
        } else {
            return false;
        }

    }

    /**
     *getter size .
     * @return int .
     */
    public int size() {
        int count = 0;
        Node<T> now = head;
        while (now != null) {
            count++;
            now = now.getNext();
        }
        return count;
    }

    /**
     *method add .
     * @param element .
     * @return boolean .
     */
    public boolean add(T element) {
        if (head == null) {
            head = new Node<T>(element);
        } else {
            Node<T> temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(new Node<T>(element));
        }
        return true;
    }

    //remove element in list with value 'element'
    /**
     *method remove .
     * @param element .
     * @return boolean .
     */
    public boolean remove(T element) {
        Node<T> prev = null;
        Node<T> now = head;

        while (now != null && now.getData() != element) {
            prev = now;
            now = now.getNext();
        }

        if (now != null) {
            if (prev != null) {
                prev.setNext(now.getNext());
            } else {
                head = now.getNext();
            }
            now.setNext(null);
            return true;
        } else {
            return false;
        }

    }

    /**
     *method remove .
     * @param idx .
     * @return boolean .
     */
    public boolean remove(int idx) {
        Node<T> prev = null;
        Node<T> now = head;

        int i = 0;
        while (i < idx) {
            prev = now;
            now = now.getNext();
            i++;
        }

        if (now != null) {
            if (prev != null) {
                prev.setNext(now.getNext());
            } else {
                head = now.getNext();
            }
            now.setNext(null);
            return true;
        } else {
            return false;
        }

    }
    /**
     *method get .
     * @param index .
     * @return T .
     */

    //return value (type = T)
    public T get(int index) {
        if (index < size()) {
            Node<T> now = head;
            int i = 0;
            while (i < index) {
                now = now.getNext();
                i++;
            }
            return now.getData();
        } else {
            return null;
        }
    }

    /**
     *method get .
     * @param index .
     * @return Node<T>.
     */
    public Node<T> getNode(int index) {
        if (index < size()) {
            Node<T> now = head;
            int i = 0;
            while (i < index) {
                now = now.getNext();
                i++;
            }
            return now;
        } else {
            return null;
        }
    }
}

