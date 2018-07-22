import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item>{
    
    private Node head;
    private Node tail;
    private int size;
    
    // construct an empty deque
    public Deque(){
        this.head = null;
        this.tail = null;
        size = 0;
    }
    // is the deque empty?
    public boolean isEmpty(){
        return this.size == 0;
    }
    // return the number of items on the deque
    public int size(){
        return this.size;
    }
    // add the item to the front
    public void addFirst(Item item){
        if(null == item){
            throw new IllegalArgumentException();
        }
        Node prevHead = head;
        head = new Node();
        head.setItem(item);
        head.setNext(prevHead);
        
        if(isEmpty()){
            tail = head;
        }
        if(null != prevHead){
            prevHead.setPrev(head);
        }
        size++;
        
    }
    // add the item to the end
    public void addLast(Item item){

        if(null == item){
            throw new IllegalArgumentException();
        }
        Node prevTail = tail;
        tail = new Node();
        tail.setItem(item);
        tail.setPrev(prevTail);
        
        if(isEmpty()){
            tail = head;
        }
        if(null != prevTail){
            prevTail.setNext(tail);
        }
        size++;
        
    

    }
    // remove and return the item from the front
    public Item removeFirst(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        Node prevHead = head;
        head.setNext(head.getNext());
        if(null != this.head){
            this.head.setPrev(null);
        }
        if (isEmpty()) {
            this.tail = null;
        }
        size--;
        
        
        return prevHead.getItem();
    }
    // remove and return the item from the end
    public Item removeLast(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        Node prevLast = tail;
        tail.setPrev(tail.getPrev());
        if(null != this.tail){
            this.head.setNext(null);
        }
        if (isEmpty()) {
            this.head = null;
        }
        size--;
        
        
        return prevLast.getItem();
    }
    // return an iterator over items in order from front to end
    @Override
    public Iterator<Item> iterator() {
        return new IteratorQueue(head);
    }


    private class Node{
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
    private class IteratorQueue implements Iterator<Item>{
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
            if(node == null){
                throw new NoSuchElementException();
            }
            Item item = node.getItem();
            node = node.getNext();         
            return item;
        }
        public void remove(){
            throw new UnsupportedOperationException();
        }
        
    }

}
