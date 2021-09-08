// TODO: Make sure to make this class a part of the synthesizer package
// package <package name>;
package synthesizer;
import synthesizer.AbstractBoundedQueue;

import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb =  (T[]) new Object[capacity];
        this.capacity = capacity;
        fillCount = 0;
        first = 0;
        last = 0;
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public int nextindex(int index){
        if (index >= capacity()){
            index = 0;
        }
        else if(index < 0){
            index = capacity() - 1;
        }
        return index;
    }
    public void enqueue(T x) {
        if (isFull()){
            throw new RuntimeException("Ring buffer overflow");
        }
        else{
            fillCount++;
            rb[last] = x;
            last = nextindex(last + 1);
        }
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        if (isEmpty()){
            throw new RuntimeException("Ring buffer underflow");
        }
        else{
            T rt = rb[first];
            fillCount--;
            first = nextindex(first + 1);
            return rt;
        }
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update 
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {


        return rb[first];

        
        // TODO: Return the first item. None of your instance variables should change.
    }
    public Iterator<T> iterator(){
        return new ArrayRingBufferIterator();
    }

    public class ArrayRingBufferIterator/*这里不要<T>*/ implements Iterator<T>{
        private int it = first;
        @Override
        public boolean hasNext() {
            return it == last;
        }
        public T next(){
            T returnvalue = rb[it];
            it = nextindex(it + 1);
            return returnvalue;
        }



    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
}
