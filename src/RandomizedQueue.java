import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item [] elements;
    private int elementsNum;

    // construct an empty randomized queue
    public RandomizedQueue(){
        elements = (Item[]) new Object[2];
        elementsNum = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty(){
        return elementsNum == 0;
    }

    // return the number of items on the randomized queue
    public int size(){
        return elementsNum;
    }

    // add the item
    public void enqueue(Item item){
        if(item==null) throw new IllegalArgumentException();
        if(elements.length == elementsNum ) resize( 2 * elementsNum);
        if(elementsNum==0){
            elements[elementsNum++] = item;
            return;
        }
        int randIndex = StdRandom.uniform(elementsNum);
        Item temp = elements[randIndex];
        elements[randIndex] = item;
        elements[elementsNum++] = temp;
    }

    // remove and return a random item
    public Item dequeue(){
        if(isEmpty()) throw new NoSuchElementException();
        if(elementsNum == elements.length/4) resize( elements.length/2);
        int randIndex = StdRandom.uniform(elementsNum);
        Item temp = elements[randIndex];
        elements[randIndex] = elements[elementsNum--];
        elements[elementsNum] = null;
        return temp;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        if (isEmpty()) throw new NoSuchElementException();
        return elements[StdRandom.uniform(elementsNum)];
    }

    //Resize Array
    private void resize(int newSize){
        Item[] newArray = (Item[]) new Object[newSize];
        for (int i=0; i< elementsNum ; i++ ) {
            newArray[i] = elements[i];
        }
        elements = newArray;
    }

    // return an independent iterator over items in random order
    @Override
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }
    // Iterator Class
    private class ArrayIterator implements Iterator<Item> {
        private int i;
        private int[] randomIndices;
        public ArrayIterator() {
            i = 0;
            randomIndices = new int[elementsNum];
            for (int j = 0; j < elementsNum; j++) {
                randomIndices[j] = j;
            }
            StdRandom.shuffle(randomIndices);
        }
        @Override
        public boolean hasNext() {
            return i < elementsNum;
        }
        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return elements[randomIndices[i++]];
        }
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args){
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
        System.out.println(queue.size());
        for (Integer i : queue) {
            System.out.println(i);
        }
        System.out.println("sample:" + queue.sample());
        System.out.println("dequeue");
        while (!queue.isEmpty()) System.out.println(queue.dequeue());
        System.out.println(queue.size());
    }
}
