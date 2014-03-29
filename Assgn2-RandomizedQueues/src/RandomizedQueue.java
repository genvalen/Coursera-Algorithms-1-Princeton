/**
 * @author Mandeep Condle
 *
 * Coursera: Algorithms 1
 * Assignment 2: Randomized Queues and Deques
 *
 * RandomizedQueue.java
 *
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private static final int START_MAX = 10;

    private Item[] resizingArray;
    private int numberOfItems;
    private int max;

    //@SuppressWarnings("unchecked")
    public RandomizedQueue() {
        this.numberOfItems = 0;
        this.max = RandomizedQueue.START_MAX;
        this.resizingArray = (Item[]) new Object[this.max];
    }

    // Private iterator class
    //@SuppressWarnings("unchecked")
    private class RandomizedQueueIterator implements Iterator<Item> {
        private Item[] itemIt;
        private int itemsLeft;

        public RandomizedQueueIterator() {
            itemIt = (Item[]) new Object[numberOfItems];
            this.constructIterator();       //best practice?
            this.itemsLeft = numberOfItems;
        }

        //Helper method to generate array for iterator
        private void constructIterator() {
            int j = 0;
            for (int i = 0; i < max; i++) {
                if (j == numberOfItems) {   break;  }   //not sure if added check reqd
                if (resizingArray[i] != null) {
                    this.itemIt[j++] = resizingArray[i];
                }
            }
        }

        public boolean hasNext()    {   return this.itemsLeft != 0;                 }
        public void remove()        {   throw new UnsupportedOperationException();  }

        public Item next() {
            if (!this.hasNext())    {   throw new NoSuchElementException();         }
            else {
                int rand = StdRandom.uniform(this.itemIt.length);

                while (itemIt[rand] == null) {
                    rand = StdRandom.uniform(this.itemIt.length);
                }

                Item elem = itemIt[rand];
                itemIt[rand] = null;
                this.itemsLeft--;

                return elem;
            }
        }
    }

    /**
     * @return  boolean indicating if the rand queue is empty
     */
    public boolean isEmpty() {
        return this.numberOfItems == 0;
    }

    /**
     * @return  size of the randomized queue
     */
    public int size() {
        return this.numberOfItems;
    }

    /**
     * Adds an item to the back to the randomized queue
     * @param item  Item to be inserted
     */
    public void enqueue(Item item) {
        if (item == null)   {   throw new NullPointerException();   }

        if (this.numberOfItems == this.max) {   //resize array to double the size
            this.max = this.max * 2;
            this.resizingArray = this.createNewArray(this.max);
        }

        this.resizingArray[numberOfItems++] = item;
    }

    /**
     * Removes a random element from the randomized queue
     * @return  Item that is removed
     */
    public Item dequeue() {
        if (this.isEmpty()) {   throw new NoSuchElementException(); }

        //to remove select a random item, if that index is empty select another
        int rand = StdRandom.uniform(this.numberOfItems);
        while (this.resizingArray[rand] == null) {
            rand = StdRandom.uniform(this.numberOfItems);
        }

        Item toRemove = this.resizingArray[rand];
        this.resizingArray[rand] = null;    //mark that value as null
        this.numberOfItems--;

        if (this.fractionOccupied() < 0.25f) {  //resize to half
            this.max = this.max / 2;
            this.resizingArray = this.createNewArray(this.max);
        }

        return toRemove;
    }

    /**
     * Private helper method used by enqueue and dequeue to create new arrays
     * @param newSize   new size
     * @return          new Item[]
     */
    //@SuppressWarnings("unchecked")
    private Item[] createNewArray(int newSize) {
        Item[] newArray = (Item[]) new Object[newSize];
        int oldSize = this.resizingArray.length;

        int j = 0;
        for (int i = 0; i < oldSize; i++) {
            if (j == newSize) { break;  }
            if (this.resizingArray[i] != null) {
                newArray[j++] = this.resizingArray[i];
            }
        }
        //System.arraycopy(newArray, 0, this.resizingArray, 0, newSize);
        return newArray;
    }

    /**
     * Helper method to identify the portion of the array occupied
     * @return  what fraction of the array is occupied
     */
    private float fractionOccupied() {
        float M = (float) this.max;
        float C = (float) this.numberOfItems;

        return C / M;
    }

    /**
     * @return  Returns a sample Item based on StdRandom.uniform(N)
     */
    public Item sample() {
        if (this.isEmpty()) {   throw new NoSuchElementException(); }

        int rand = StdRandom.uniform(this.numberOfItems);
        while (this.resizingArray[rand] == null) {
            rand = StdRandom.uniform(this.numberOfItems);
        }

        return this.resizingArray[rand];
    }

    /**
     * @return  Iterator of the RandomizedQueue
     */
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    public static void main(String[] args) {

        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();

        rq.enqueue(0);
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);
        rq.enqueue(5);
        rq.enqueue(6);
        rq.enqueue(7);
        rq.enqueue(8);
        rq.enqueue(9);



        System.out.println("items: " + rq.numberOfItems);

        System.out.println(rq.toString());

        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());

        //System.out.println(rq.to`String());
        System.out.println("items: " + rq.numberOfItems);

        Iterator it1 = rq.iterator();
        Iterator it2 = rq.iterator();

        while (it1.hasNext()) {
            System.out.print(it1.next());
        }
        System.out.println("\n");
        while (it2.hasNext()) {
            System.out.print(it2.next());
        }









    }








}
