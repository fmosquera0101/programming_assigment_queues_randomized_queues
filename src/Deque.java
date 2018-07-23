import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {
    
    private Node head;
    private Node tail;
    private int size;
    
    // construct an empty deque
    public Deque() {
        this.head = null;
        this.tail = null;
        size = 0;
    }
    // is the deque empty?
    public boolean isEmpty() {
        return this.size == 0;
    }
    // return the number of items on the deque
    public int size() {
        return this.size;
    }
    // add the item to the front
    public void addFirst(Item item) {
        if (null == item) {
            throw new IllegalArgumentException();
        }
        Node newNode = new Node();
        newNode.setItem(item);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;
        }
        
        size++;
        
    }
    // add the item to the end
    public void addLast(Item item) {

        if (null == item) {
            throw new IllegalArgumentException();
        }
        Node newNode = new Node();
        newNode.setItem(item);
        if (tail == null) {
            tail = head = newNode;
        } else {
            newNode.setPrev(tail);
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
        
    

    }
    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node nodeTemp = head;
        head = head.getNext();
        if (head == null) {
            tail = null;
        } else {
            head.setPrev(null);
        }
        size--;
        
        
        return nodeTemp.getItem();
    }
    // remove and return the item from the end
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node newNode = tail;
        tail = tail.getPrev();
        if (tail == null) {
            head = null;
        } else {
            tail.setNext(null);
        }
        size--;
        
        
        return newNode.getItem();
    }
    // return an iterator over items in order from front to end
    @Override
    public Iterator<Item> iterator() {
        return new IteratorQueue(head);
    }
    
    private class Node {
        private Item item;
        private Node next;
        private Node prev;

        public Item getItem() {
            return item;
        }
        public void setItem(Item item) {
            this.item = item;
        }
        public Node getNext() {
            return next;
        }
        public void setNext(Node next) {
            this.next = next;
        }
        public Node getPrev() {
            return prev;
        }
        public void setPrev(Node prev) {
            this.prev = prev;
        }
        
    }  
    private class IteratorQueue implements Iterator<Item> {
        private Node node;
        public IteratorQueue(Node head) {
            this.node = head;
        }
        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public Item next() {
            if (node == null) {
                throw new NoSuchElementException();
            }
            Item item = node.getItem();
            node = node.getNext();         
            return item;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
        
    }
    


}
