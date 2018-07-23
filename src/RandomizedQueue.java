import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;


public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queueItems;
    private int size;
    // construct an empty randomized queue
    public RandomizedQueue() {
        queueItems = (Item[]) new Object[1];
        size = 0;

    }
    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;

    }
    // return the number of items on the randomized queue
    public int size() {
        return size;
    }
    // add the item
    public void enqueue(Item item) {
        if (null == item) {
            throw new IllegalArgumentException();
        }
        int currentSize = queueItems.length;
        if (currentSize == size) {
            resizeQueue(currentSize*2);
            queueItems[size] = item;
            size++;
        } else {
            queueItems[size] = item;
            size++;
        }

    }
    private void resizeQueue(int capacity) {
        Item[] copyQueueItems = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copyQueueItems[i] = queueItems[i];
        }
        queueItems = copyQueueItems;

    }
    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int randomIndex = StdRandom.uniform(size);
        Item item = queueItems[randomIndex];
        if ((size - 1) == randomIndex) {
            queueItems[randomIndex] = null;
        } else {
            queueItems[randomIndex] = queueItems[size - 1];
            queueItems[size - 1] = null;
        }
        if (size > 0 && size == (queueItems.length/4)) {
            resizeQueue(queueItems.length/2);
        }
        size--;
        return item;
    }
    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int randomIndex = StdRandom.uniform(size);
        Item item = queueItems[randomIndex];
        return item;
    }
    @Override
    public Iterator<Item> iterator() {
        return new IteratorRandomizedQueue(queueItems, size);
    }
    private class IteratorRandomizedQueue implements Iterator<Item> {
        private int sizeQueueIterator = 0;
        private final Item[] queueIterator;
        public IteratorRandomizedQueue(Item[] queue, int sizeQueue) {
            queueIterator = (Item[]) new Object[sizeQueue];
            for(int i = 0; i < sizeQueue; i++){
                this.queueIterator[i] = queue[i];
            }
            
            this.sizeQueueIterator = sizeQueue;
        }
 
        @Override
        public boolean hasNext() {
            return sizeQueueIterator != 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int randomIndex = StdRandom.uniform(sizeQueueIterator);
            Item item = queueIterator[randomIndex];

            if ((sizeQueueIterator - 1) == randomIndex) {
                sizeQueueIterator--;
            } else {
                queueIterator[randomIndex] = queueIterator[sizeQueueIterator - 1];
                queueIterator[sizeQueueIterator - 1] = item;
                sizeQueueIterator--;
            }
            return item;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }


    }
    public static void main(String [] args){
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        rq.enqueue(4);
        rq.enqueue(0);
        rq.enqueue(4);
        rq.enqueue(0);
        rq.enqueue(4);
        rq.enqueue(0);
        rq.enqueue(4);
        rq.enqueue(0);
        rq.enqueue(4);
        rq.enqueue(0);
        for (Integer integer : rq) {
            System.out.println(integer);
        }
    }

}
