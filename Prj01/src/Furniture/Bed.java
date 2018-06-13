package Furniture;

import Exceptions.CheckValues;

public class Bed extends  Furniture {
    public Bed(String name, double square) throws CheckValues {
        super(name, square);
    }
    public Bed(String name , double min , double max) throws CheckValues {
        super(name , min , max);
    }
}
