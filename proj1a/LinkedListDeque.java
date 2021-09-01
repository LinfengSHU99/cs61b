public class LinkedListDeque<datatype> {
    public static class Node<datatype>{
        private datatype data;
        private Node<datatype> next;
        private Node<datatype> prior;

        public Node(datatype x){
            this.data = x;
            this.next = null;
            this.prior = null;
        }
    }

    private int size = 0;
    public Node<datatype> sentinel = null;
    public LinkedListDeque(){
        sentinel = new Node<datatype>(null);
        sentinel.next = sentinel;
        sentinel.prior = sentinel;

    }

    public void addFirst(datatype item){
        Node<datatype> p = new Node<>(item);
        p.next = sentinel.next;
        p.prior = sentinel;
        sentinel.next.prior = p;
        sentinel.next = p;
        size++;
    }

    public void addLast(datatype item){
        Node<datatype> p = new Node<>(item);
        p.next = sentinel;
        p.prior = sentinel.prior;
        sentinel.prior.next = p;
        sentinel.prior = p;
        size++;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        Node<datatype> p = sentinel.next;
        for(int i = 0; i < size; i++){
            System.out.print(p.data+" ");
            p = p.next;
        }
        System.out.print("\n");
    }
    public datatype removeFirst(){
        if(this.isEmpty()){
            return null;
        }
        else{
            datatype r = sentinel.next.data;
            sentinel.next.next.prior = sentinel;
            sentinel.next = sentinel.next.next;
            size--;
            return r;
        }
    }
    public datatype removeLast(){
        if(this.isEmpty()){
            return null;
        }
        else{
            datatype r = sentinel.prior.data;
            sentinel.prior.prior.next = sentinel;
            sentinel.prior = sentinel.prior.prior;
            size--;
            return r;
        }

    }

    public datatype get(int index){
        Node<datatype> p = sentinel.next;
        for(int i = 0;i<index;i++){

            if(p == null){
                return null;
            }
            p = p.next;
        }
        return p.data;

    }
    public LinkedListDeque(LinkedListDeque other){
        sentinel = new Node<datatype>(null);
        Node<datatype> p = other.sentinel.next;
        Node<datatype> p2 = sentinel;
        while(p.data!=null){
            Node<datatype> p1 = new Node<datatype>(p.data);
            p2.next = p1;
            p1.prior = p2;
            p2 = p2.next;
            p = p.next;

        }
        p2.next = sentinel;
        sentinel.prior = p2;
        size = other.size;
    }
    /*public static void main(String[] args){
        LinkedListDeque<Integer> l = new LinkedListDeque<Integer>();
        l.addFirst(1);
        l.addLast(2);
        l.addFirst(3);
        l.printDeque();

        l.removeFirst();
        l.removeLast();
        //System.out.println(l.size);
        l.printDeque();
        LinkedListDeque<Integer> l2 = new LinkedListDeque<>(l);
        l2.printDeque();
        System.out.println(l2.size);
    }*/

}