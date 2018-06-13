package Furniture;

import Exceptions.*;

public abstract class Furniture {
    private String name;
    private double minSquare;
    private double maxSquare;

    public Furniture(String name, double square) throws CheckValues {
        if (square <= 0 || square >= Double.MAX_VALUE) {
            throw new CheckValues("Площадь объекта " + "'" + name + "'" +" не может быть меньше 0 или >= значения Double.MAX_VALUE");
        }
        this.name = name;
        this.maxSquare = square;
        this.minSquare = square;
    }

    public Furniture(String name, double minSquare, double maxSquare) throws CheckValues {
        if (maxSquare <= 0 || minSquare <= 0 || minSquare >= maxSquare) {
            throw new CheckValues("Площадь объекта " +"'"+ name +"'"+ " должна быть > 0 , Min не может быть >= Max");
        }
        this.name = name;
        this.minSquare = minSquare;
        this.maxSquare = maxSquare;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMinSquare() {
        return minSquare;
    }

    public void setMinSquare(double minSquare) {
        this.minSquare = minSquare;
    }

    public double getMaxSquare() {
        return maxSquare;
    }

    public void setMaxSquare(double maxSquare) {
        this.maxSquare = maxSquare;
    }

    public void describe() {

    }
}
