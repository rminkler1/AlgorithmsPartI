/* *****************************************************************************
 *  Name:              Robert Minkler
 *  Coursera User ID:  33333
 *  Last modified:     January 1, 2025
 **************************************************************************** */

// A double-ended queue or deque (pronounced “deck”) is a generalization of a stack and a queue
// that supports adding and removing items from either the front or the back of the data structure.
// Create a generic data type Deque that implements the following API:
//
// Corner cases.  Throw the specified exception for the following corner cases:
//
//     Throw an IllegalArgumentException if the client calls either addFirst() or addLast() with a
//     null argument.
//     Throw a java.util.NoSuchElementException if the client calls either removeFirst() or
//     removeLast when the deque is empty.
//     Throw a java.util.NoSuchElementException if the client calls the next() method in the
//     iterator when there are no more items to return.
//     Throw an UnsupportedOperationException if the client calls the remove() method in the
//     iterator.
//
// Unit testing.  Your main() method must call directly every public constructor and method to help
// verify that they work as prescribed (e.g., by printing results to standard output).

// Performance requirements.  Your deque implementation must support each deque operation
// (including construction) in constant worst-case time. A deque containing n items must use at
// most 48n + 192 bytes of memory. Additionally, your iterator implementation must support each
// operation (including construction) in constant worst-case time.


import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first;
    private Node<Item> last;
    private int size;

    // construct an empty deque
    public Deque() {
        size = 0;
        first = null;
        last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {

        if (item == null) {
            throw new IllegalArgumentException("Must provide an item to add to the Deque.");
        }

        Node<Item> oldFirst = first;
        first = new Node<>();

        // If this is the first item added to the deque, set it to last and first
        if (oldFirst == null) {
            last = first;
        }
        else {
            oldFirst.prev = first;
        }

        first.item = item;
        first.next = oldFirst;
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {

        if (item == null) {
            throw new IllegalArgumentException("Must provide an item to add to the Deque.");
        }

        Node<Item> newLast = new Node<>();

        // If this is the first item added to the deque, set the last and first node
        if (last == null) {
            last = newLast;
            first = last;
        }
        else {
            newLast.prev = last;
            last.next = newLast;
            last = newLast;
        }
        last.item = item;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {

        if (isEmpty()) {
            throw new java.util.NoSuchElementException("The Deque is empty. No Items to remove.");
        }

        Item firstItem = first.item;
        first = first.next;
        if (first != null) {
            first.prev = null;
        }
        else {
            last = null;
        }
        size--;
        return firstItem;
    }

    // remove and return the item from the back
    public Item removeLast() {

        if (isEmpty()) {
            throw new java.util.NoSuchElementException("The Deque is empty. No Items to remove.");
        }

        Item lastItem = last.item;
        last = last.prev;
        if (last != null) {
            last.next = null;
        }
        else {
            first = null;
        }
        size--;
        return lastItem;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {

        private Node<Item> currentNode = first;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public Item next() {

            if (currentNode == null) {
                throw new java.util.NoSuchElementException(
                        "The Deque is empty. No Items to retrieve.");
            }

            Item item = currentNode.item;
            currentNode = currentNode.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove is not supported in the iterator.");
        }
    }

    private class Node<Item> {
        Item item;
        Node<Item> next;
        Node<Item> prev;
    }

    // unit testing (required)
    public static void main(String[] args) {

        // Deque testing
        Deque<Integer> deque = new Deque<>();

        deque.addFirst(300);
        deque.addFirst(200);
        deque.addFirst(100);
        deque.addLast(0);
        deque.addLast(400);
        deque.addLast(500);
        deque.addLast(600);
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeLast());
        System.out.println(deque.removeLast());


        System.out.println(deque.first.item);
        System.out.println(deque.last.item);
        System.out.println(deque.size());

        for (int num : deque) {
            System.out.println(num);
        }

        System.out.println(deque.size());


        System.out.println("DEQUE 2");

        Deque<String> deque2 = new Deque<>();
        deque2.addLast("First");
        deque2.addLast("Second");
        deque2.addLast("Third");
        deque2.addFirst("newFirst");
        deque2.addFirst("New Second");
        deque2.addFirst("NewThird");


        for (String text : deque2) {
            System.out.println(text);
        }

        deque2.removeLast();
        deque2.removeLast();
        deque2.removeLast();
        deque2.removeLast();
        deque2.removeLast();
        deque2.removeLast();

        System.out.println(deque2.first);
        System.out.println(deque2.last);
        System.out.println(deque2.isEmpty());

        deque.removeFirst();
        deque.removeFirst();
        deque.removeFirst();

        System.out.println(deque.first);
        System.out.println(deque.last);
        System.out.println(deque.isEmpty());

        try {
            deque.removeLast();
        }
        catch (NoSuchElementException e) {
            System.out.println(e);
        }

        try {
            deque2.removeFirst();
        }
        catch (NoSuchElementException e) {
            System.out.println(e);
        }

        try {
            deque.addFirst(null);
        }
        catch (IllegalArgumentException e) {
            System.out.println(e);
        }
        try {
            deque.addLast(null);
        }
        catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }
}