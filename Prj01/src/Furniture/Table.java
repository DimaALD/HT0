package Furniture;

import Exceptions.CheckValues;

public class Table extends Furniture {
    public Table(String name, double square) throws CheckValues {
        super(name, square);
    }
    public Table(String name , double min , double max) throws CheckValues {
        super(name , min , max);
    }
}
