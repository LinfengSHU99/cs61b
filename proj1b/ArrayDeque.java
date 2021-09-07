
//front == rear empty
//front - 1 == rear full
//rear++ is the position where the item should be put.
//front is the position where the item should be put.
public class ArrayDeque<T> implements Deque<T> {
    private T[] array = null;
    private int front = 0;
    private int rear = 0;
    private int memory = 0;
    private int size = 0;
    //private int factor = 2;
    public ArrayDeque(){
        array = (T[]) new Object[8];
        memory = 8;
    }
    public ArrayDeque(ArrayDeque other){
        array = (T[]) new Object[other.size];
        System.arraycopy(other.array, 0, array, 0, other.size);
        memory = other.memory;
        size = other.size;
        rear = other.rear;
        front = other.front;
    }
    @Override
    public void addFirst(T item){
        extend();
        array[front] = item;
        front--;
        front = getindex(front);
        size++;
    }

    @Override
    public void addLast(T item){
        extend();
        rear++;
        rear = getindex(rear);
        array[rear] = item;
        size++;
    }
    private void extend(){
        int l = memory - front - 1;
        if(size == memory-1){
            T[] array2 = (T[]) new Object[memory * 2];
            if(rear+1 == front){
                System.arraycopy(array, 0, array2, 0, rear+1);
                System.arraycopy(array, front+1, array2, array2.length-l, l);
                front = array2.length - l - 1;
            }
            else{
                System.arraycopy(array, 0, array2, 0, array.length);
            }

            array = array2;
            memory *= 2;
        }
    }

    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        front++;
        if(front >= array.length){
            front = 0;
        }
        T r = array[front];
        size--;
        reduce();
        return r;
    }

    @Override
    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        T r = array[rear];
        rear--;
        if(rear < 0 ){
            rear = array.length - 1;
        }
        size--;
        return r;
    }

    private void reduce(){
        if(array.length <= 16 || 1.0 * size / array.length >= 0.25)  return;
        else{
            T[] array2 = (T[]) new Object[array.length / 2];
            if(front <= rear){
                System.arraycopy(array, front, array2, 0, size+1);
            }
            else {
                System.arraycopy(array, front, array2, 0, array.length - front);
                System.arraycopy(array, 0, array2, array.length - front, rear+1);
            }
            front = 0;
            rear = size;
            array = array2;
        }

    }

    @Override
    public void printDeque(){

        int i = 0;
        for(i = getindex(front + 1); i!=rear ;i = getindex(i)){
            System.out.print(array[i]+" ");
            i++;
        }
        System.out.print(array[i]+" \n");

    }

    @Override
    public T get(int index){
        int i = getindex(front + index + 1);
        return array[i];
    }

    private int getindex(int x){
        if(x < 0){
            x = array.length + x;
        }
        else if(x >= array.length){
            x = x % array.length;
        }
        return x;
    }

    @Override
    public int size(){
        return size;
    }
    /*public static void main(String[] args){
        ArrayDeque<Integer> a = new ArrayDeque<>();
        a.addFirst(1);
        a.addLast(2);
        a.addLast(3);
        a.addFirst(0);
        a.addFirst(-1);
        for(int i = 0 ;i < 10000;i++){
            a.addFirst(i);
        }
        a.removeFirst();
        a.removeLast();
        a.printDeque();
        System.out.println(a.size);
        System.out.println(a.memory);
    }*/
}
