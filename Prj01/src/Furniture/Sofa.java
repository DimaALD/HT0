package Furniture;

import Exceptions.CheckValues;

public class Sofa extends Furniture {
    public Sofa(String name, double square) throws CheckValues {
        super(name, square);
    }
    public Sofa(String name , double min , double max) throws CheckValues {
        super(name , min , max);
    }
}
