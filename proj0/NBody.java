public class NBody {
    public  static double readRadius(String file){
        In in = new In(file);
        int num = in.readInt();
        return in.readDouble();
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
    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radious = NBody.readRadius(filename);
        StdDraw.setScale(-2 * radious, 2 * radious);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");
        Planet[] galaxy = NBody.readPlanets(filename);
        for (Planet p : galaxy){
            p.draw();
        }
        StdDraw.show();
        StdDraw.pause(2000);
    }
}
