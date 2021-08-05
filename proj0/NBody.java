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
        double radius = NBody.readRadius(filename);
        StdDraw.setScale(-2 * radius, 2 * radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");
        Planet[] galaxy = NBody.readPlanets(filename);
        double[] xForces = new double[galaxy.length];
        double[] yForces = new double[galaxy.length];

        for (Planet p : galaxy){
            p.draw();
        }

        for (int i = 0; i < T; i+=dt){
            for (int j = 0; j < galaxy.length; j++){
                xForces[j] = galaxy[j].calcNetForceExertedByX(galaxy);
                yForces[j] = galaxy[j].calcNetForceExertedByY(galaxy);
            }
            for (int j = 0; j < galaxy.length; j++){
                galaxy[j].update(dt, xForces[j], yForces[j]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet p : galaxy){
                p.draw();
            }
            StdDraw.enableDoubleBuffering();
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", galaxy.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < galaxy.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    galaxy[i].xxPos, galaxy[i].yyPos, galaxy[i].xxVel,
                    galaxy[i].yyVel, galaxy[i].mass, galaxy[i].imgFileName);
        }
    }
}
