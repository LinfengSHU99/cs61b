package synthesizer;

public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T>{
    protected int fillCount;
    protected int capacity;
    public int capacity(){
        return capacity;
    }


    public int fillCount() {
        return fillCount;
    }
    /*@Override
    public boolean isFull(){
        return fillCount() == capacity();
    }

    @Override
    public boolean isEmpty(){
        return fillCount() == 0;
    }*/
    public abstract T peek();
    public abstract T dequeue();
    public abstract void enqueue(T x);

}
