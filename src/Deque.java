import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    // Node inner class
    private class Node{
        private Item item;
        private Node nextNode,prevNode;
    }

    // Variables
    private int curSize;
    private Node firstNode,lastNode;

    // construct an empty deque
    public Deque(){
        this.curSize = 0;
//        firstNode = null;
//        lastNode = null;
    }

    // is the deque empty?
    public boolean isEmpty(){
        return curSize==0;
    }

    // return the number of items on the deque
    public int size(){
        return this.curSize;
    }

    // add the item to the front
    public void addFirst(Item item){
        if(item==null) throw new  java.lang.IllegalArgumentException();
        Node oldNode = firstNode;
        firstNode = new Node();
        firstNode.item = item;
        firstNode.nextNode = oldNode;
        if(lastNode==null) lastNode = firstNode;
        else firstNode.nextNode.prevNode = firstNode;
        curSize++;
    }

    // add the item to the back
    public void addLast(Item item){
        if(item==null) throw new  java.lang.IllegalArgumentException();
        Node oldNode = lastNode;
        lastNode = new Node();
        lastNode.item = item;
        lastNode.prevNode = oldNode;
        if(firstNode==null) firstNode = lastNode;
        else lastNode.prevNode.nextNode = lastNode;
        curSize++;
    }

    // remove and return the item from the front
    public Item removeFirst(){
        if (isEmpty()) throw new NoSuchElementException("Underflow");
        Item item = firstNode.item;
        curSize--;
        if (isEmpty()) {
            lastNode = null;
            firstNode = null;
        } else {
            firstNode = firstNode.nextNode;
            firstNode.prevNode = null;
        }
        return item;
    }

    // remove and return the item from the back
    public Item removeLast(){
        if (isEmpty()) throw new NoSuchElementException("Underflow");
        Item item = lastNode.item;
        curSize--;
        if (isEmpty()) {
            firstNode = null;
            lastNode = null;
        } else {
            lastNode = lastNode.prevNode;
            lastNode.nextNode = null;
        }
        return item;
    }

    // return an iterator over items in order from front to back
    @Override
    public Iterator<Item> iterator(){
        return new ListIterator();
    }

    // Iterator Class
    private class ListIterator implements Iterator<Item> {

        private Node current = firstNode;
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.nextNode;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    // unit testing (required)
    public static void main(String[] args){
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.removeLast();
        System.out.println(deque.isEmpty());
        for (int i = 0; i < 10; i++) {
            deque.addFirst(i);
            deque.addLast(i * 10);
        }
        System.out.println(deque.removeLast());
        System.out.println(deque.size());
        while (!deque.isEmpty()) {
            System.out.println(deque.removeFirst());
        }
        deque.addFirst(1);
        System.out.println(deque.removeFirst());
        deque.addFirst(2);
        System.out.println(deque.removeFirst());
        deque.addLast(0);
        deque.removeFirst();
        deque.addLast(3);
        deque.addLast(4);
        deque.removeFirst();
        deque.removeLast();
        deque.addFirst(8);
    }
}
