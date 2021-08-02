public class NBody {
    public  static double readRadius(String file){
        In in = new In(file);
        int num = in.readInt();
        double radius = in.readDouble();
        return radius;
    }
    public static Planet[] readPlanets(String file){
        In in = new In(file);

        int n = in.readInt();
        double radius = in.readDouble();
        Planet[] array = new Planet[n];
        for (int i = 0; i < n; i++){
            array[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(),
                    in.readDouble(), in.readDouble(), in.readString());
        }
        return array;
    }
    /*public static void main(String[] args){

    }*/
}
