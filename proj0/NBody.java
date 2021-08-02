public class NBody {
    public  static double readRadius(String file){
        In in = new In(file);
        int num = in.readInt();
        double radius = in.readDouble();
        return radius;
    }
    /*public static void main(String[] args){

    }*/
}
