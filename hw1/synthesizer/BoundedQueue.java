package synthesizer;
import java.util.Iterator;
public interface BoundedQueue<T> extends Iterable<T>{
    public int capacity();
    public int fillCount();
    public void enqueue(T x);  // add item x to the end
    public T dequeue();        // delete and return item from the front
    public T peek();
    default public boolean isEmpty(){
        return fillCount() == 0;
    }
    default public boolean isFull(){
        return fillCount() == capacity();
    }
}
