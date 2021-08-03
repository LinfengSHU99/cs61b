import javax.management.loading.PrivateMLet;
import javax.swing.*;

public class Planet {
    static final double G = 6.67e-11;

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public Planet(double xp, double yp, double xv, double yv, double m,
                  String img){
        this.xxPos = xp;
        this.yyPos = yp;
        this.xxVel = xv;
        this.yyVel = yv;
        this.mass = m;
        this.imgFileName = img;

    }
    public Planet(Planet p){
        this.xxVel = p.xxVel;
        this.imgFileName = p.imgFileName;
        this.yyVel = p.yyVel;
        this.yyPos = p.yyPos;
        this.xxPos = p.xxPos;
        this.mass = p.mass;
    }

    public double calcDistance(Planet p){
        return Math.sqrt(Math.pow(this.xxPos - p.xxPos, 2) + Math.pow(this.yyPos - p.yyPos, 2));

    }
    public double calcForceExertedBy(Planet p){
        return G * this.mass * p.mass / this.calcDistance(p) / this.calcDistance(p);
    }
    public double calcForceExertedByX(Planet p){
        double dx = p.xxPos - this.xxPos;

        return this.calcForceExertedBy(p) / this.calcDistance(p) * dx;

    }
    public double calcForceExertedByY(Planet p){
        double dy = p.yyPos - this.yyPos;
        return this.calcForceExertedBy(p) / this.calcDistance(p) * dy;
    }
    public double calcNetForceExertedByX(Planet[] p){
        double fx = 0;
        for (Planet planet : p) {
            if (!this.equals(planet)) {
                fx += this.calcForceExertedByX(planet);
            }

        }
        return fx;
    }
    public double calcNetForceExertedByY(Planet[] p){
        double fy = 0;
        for (Planet planet : p){
            if (!this.equals(planet)) {
                fy += this.calcForceExertedByY(planet);
            }
        }
        return fy;
    }
    public void update(double dt, double fx, double fy) {
        double ax = fx / this.mass;
        double ay = fy / this.mass;
        this.xxVel += dt * ax;
        this.yyVel += dt * ay;
        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;
    }
    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }
}
