/* *****************************************************************************
 *  Name:              Robert Minkler
 *  Coursera User ID:  xxxxxx
 *  Last modified:     January 2, 2025
 **************************************************************************** */

// A randomized queue is similar to a stack or queue, except that the item removed is chosen
// uniformly at random among items in the data structure. Create a generic data type
// RandomizedQueue that implements the following API:
//
// Iterator.  Each iterator must return the items in uniformly random order. The order of two or
// more iterators to the same randomized queue must be mutually independent; each iterator must
// maintain its own random order.
//
// Corner cases.  Throw the specified exception for the following corner cases:
//
//     Throw an IllegalArgumentException if the client calls enqueue() with a null argument.
//     Throw a java.util.NoSuchElementException if the client calls either sample() or dequeue()
//     when the randomized queue is empty.
//     Throw a java.util.NoSuchElementException if the client calls the next() method in the
//     iterator when there are no more items to return.
//     Throw an UnsupportedOperationException if the client calls the remove() method in the
//     iterator.
//
// Unit testing.  Your main() method must call directly every public constructor and method to
// verify that they work as prescribed (e.g., by printing results to standard output).

// Performance requirements.  Your randomized queue implementation must support each randomized
// queue operation (besides creating an iterator) in constant amortized time. That is, any
// intermixed sequence of m randomized queue operations (starting from an empty queue) must take at
// most cm steps in the worst case, for some constant c. A randomized queue containing n items must
// use at most 48n + 192 bytes of memory. Additionally, your iterator implementation must support
// operations next() and hasNext() in constant worst-case time; and construction in linear time;
// you may (and will need to) use a linear amount of extra memory per iterator.

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<T> implements Iterable<T> {

    private T[] queueArray;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        queueArray = (T[]) new Object[10];
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
    public void enqueue(T item) {

        if (item == null) throw new IllegalArgumentException("enqueue parameters cannot be null");

        if (size == queueArray.length) growArray();

        queueArray[size++] = item;
    }

    // remove and return a random item
    public T dequeue() {

        if (isEmpty()) throw new java.util.NoSuchElementException("No items in the queue.");

        swap(randIndex(size), --size);
        T item = queueArray[size];
        queueArray[size] = null;

        if (size < queueArray.length / 3 && size > 10) {
            shrinkArray();
        }

        return item;
    }

    // return a random item (but do not remove it)
    public T sample() {

        if (isEmpty()) throw new java.util.NoSuchElementException("No items in the queue.");

        return queueArray[randIndex(size)];
    }

    // return an independent iterator over items in random order
    public Iterator<T> iterator() {
        return new QueueIterator();
    }

    // Double the size of the array
    private void growArray() {
        T[] newArray = (T[]) new Object[queueArray.length * 2];

        int i = 0;
        for (T item : queueArray) {
            newArray[i++] = item;
        }

        queueArray = newArray;
    }

    // Half the size of the array
    private void shrinkArray() {
        T[] newArray = (T[]) new Object[queueArray.length / 2];

        for (int i = 0; i < size; i++) {
            newArray[i] = queueArray[i];
        }

        queueArray = newArray;
    }

    // Swap item between index1 and index2
    private void swap(int index1, int index2) {
        T temp = queueArray[index1];
        queueArray[index1] = queueArray[index2];
        queueArray[index2] = temp;
    }

    private int randIndex(int n) {
        return StdRandom.uniformInt(n);
    }

    private class QueueIterator implements Iterator<T> {

        int currIndex = 0;
        int[] randomizer;

        public QueueIterator() {
            // assign index values to the array
            randomizer = new int[size];
            for (int i = 0; i < randomizer.length; i++) {
                randomizer[i] = i;
            }

            // randomize values
            for (int i = 0; i < randomizer.length; i++) {
                int randomIndex = randIndex(randomizer.length);
                int temp = randomizer[i];
                randomizer[i] = randomizer[randomIndex];
                randomizer[randomIndex] = temp;
            }
        }

        @Override
        public boolean hasNext() {
            return currIndex < size();
        }

        @Override
        public T next() {

            if (!hasNext()) throw new java.util.NoSuchElementException(
                    "There are no more items in the queue. Use hasNext() before calling next().");

            return queueArray[randomizer[currIndex++]];
        }

        public void remove() {
            throw new UnsupportedOperationException("remove() is not supported in the iterator.");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rQueue1 = new RandomizedQueue<>();

        for (int i = 0; i < 10; i++) {
            rQueue1.enqueue(i);
        }

        System.out.println("Size: " + rQueue1.size());
        System.out.println(rQueue1.isEmpty());

        System.out.println("start iterator");

        for (int num : rQueue1) {
            System.out.println(num);
        }

        System.out.println("end iterator");

        System.out.println("SAMPLE");
        for (int i = 0; i < 30; i++) {
            System.out.println(rQueue1.sample());
        }
        System.out.println("EndSample");
        for (int i = 0; i < 10; i++) {
            System.out.println(rQueue1.dequeue());
        }

        System.out.println("Size: " + rQueue1.size());
        System.out.println(rQueue1.isEmpty());
    }
}