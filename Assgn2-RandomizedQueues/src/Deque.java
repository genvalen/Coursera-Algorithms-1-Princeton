/**
 * @author Mandeep Condle
 *
 * Coursera: Algorithms 1
 * Assignment 2: Randomized Queues and Deques
 *
 * Deque.java
 *
 */

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Implementation of a deque, double-ended queue
 * Supports inserting and removing items from either front or back
 *
 * Node first   always has prev = null
 * Node last    always has next = null
 */
public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int numberOfItems;

    /**
     * private inner node class
     * Has next and prev pointers to implement constant worst time operations
     */
    private class Node {
        Item item;
        Node next;
        Node prev;

        Node(Item item) {
            this.item = item;
            this.next = null;
            this.prev = null;
        }
    }

    /**
     * private inner class that defines the DequeIterator
     * and implements hasNext(), remove() and next()
     */
    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() { return current != null;                      }
        public void remove()     { throw new UnsupportedOperationException();   }

        public Item next() {
            if (!this.hasNext()) { throw new NoSuchElementException();          }
            else {
                Node node = current;
                current = current.next;
                return node.item;
            }
        }
    }

    /**
     * Constructs an empty deque
     * first and last nodes are both null at this point
     */
    public Deque() {
        this.first = null;
        this.last = null;
        this.numberOfItems = 0;
    }

    /**
     * Returns a boolean if the Deque is empty or not
     * @return  true or false depending on the size of the array
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Returns the number of items in the Deque stored as a private int
     * @return  size of Deque
     */
    public int size() {
        return this.numberOfItems;
    }

    /**
     * Adds an item to the front of the deque
     * @param item  Item to be inserted
     */
    public void addFirst(Item item) {
        if (item == null) { throw new NullPointerException();   }

        if (this.isEmpty()) {
            this.first = new Node(item);
            this.last = first;
//            this.first.next = this.last;
//            //this.first.prev = null;   //redundant
//            this.last.prev = this.first;
//            //this.last.next = null;    //redundant
        } else {
            Node node = new Node(item);
            node.next = this.first;
            //node.prev = null;         //redundant
            this.first.prev = node;
            this.first = node;
        }
        this.numberOfItems++;
    }

    /**
     * Adds an item to the back of the deque
     * @param item  Item to be inserted
     */
    public void addLast(Item item) {
        if (item == null) { throw new NullPointerException();   }

        if (this.isEmpty()) {
            this.last = new Node(item);
            this.first = last;
//            this.first.next = this.last;
//            //this.first.prev = null;   //redundant
//            this.last.prev = this.first;
//            //this.last.next = null;    //redundant
        } else {
            Node node = new Node(item);
            this.last.next = node;
            node.prev = this.last;
            //node.next = null;         //redundant
            this.last = node;
        }
        this.numberOfItems++;
    }


    /**
     * Removes the first item in the Deque
     * and replaces the first reference accordingly
     * @return  First node's item
     */
    public Item removeFirst() {
        if (this.isEmpty()) { throw new NoSuchElementException(); }

        Node node = this.first;
        if (this.size() == 1) {
            this.first = null;
            this.last = null;
        } else {
            this.first.next.prev = null;
            this.first = this.first.next;
        }
        this.numberOfItems--;
        node.next = null;
        return node.item;
    }

    /**
     * Removes the last item in the Deque
     * and replaces the last reference accordingly
     * @return  Last node's item
     */
    public Item removeLast() {
        if (this.isEmpty()) { throw new NoSuchElementException(); }

        Node node = this.last;
        if (this.size() == 1) {
            this.first = null;
            this.last = null;
        } else {
            this.last.prev.next = null;
            //this.last.prev = null;
            this.last = this.last.prev;
        }
        this.numberOfItems--;
        node.next = null;
        return node.item;
    }

    /**
     * Returns an iterator over items in order from front to end
     * @return  DequeIterator()
     */
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    //TEST
    //TODO: Remove before submitting
    public String toString() {
        StringBuilder str = new StringBuilder();

        Node node = this.first;
        str.append("First -> ");
        while (node != null) {
            str.append(node.item + " ");
            node = node.next;
        }
        str.append(" <- Last");

        return str.toString().trim();
    }

    /**
     * Unit testing
     * @param args
     */
    public static void main(String[] args) {

        Deque<String> deq1 = new Deque<String>();
        Deque<Integer> deq2 = new Deque<Integer>();

        //System.out.println("deq1: " + deq1.toString());
        System.out.println("deq2: " + deq2.toString());
        System.out.println("size: " + deq2.size());

//        deq1.addFirst("four");
//        deq1.addFirst("five");
//        deq1.addLast("two");
//        deq1.addLast("one");

        deq2.addFirst(1);
        deq2.addFirst(2);
        deq2.addFirst(3);
        deq2.addFirst(4);
        deq2.addFirst(5);


        System.out.println("deq2: " + deq2.toString());

        deq2.removeLast();
        System.out.println("deq2: " + deq2.toString());

        deq2.removeFirst();
        deq2.removeFirst();
        System.out.println("deq2: " + deq2.toString());
        System.out.println("size: " + deq2.size());

        deq2.removeLast();
        deq2.removeLast();
        System.out.println("deq2: " + deq2.toString());

        deq2.addFirst(1);
        deq2.addLast(2);
        System.out.println("deq2: " + deq2.toString());

        deq2.addFirst(3);
        deq2.addLast(4);
        System.out.println("deq2: " + deq2.toString());

        System.out.println("size: " + deq2.size());

    }



}
