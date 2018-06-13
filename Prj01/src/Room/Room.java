package Room;

import Bulb.*;
import Exceptions.CheckValues;
import Furniture.Furniture;

import java.util.ArrayList;

public class Room {

    private String nameOfRoom;

    private double square;

    private double illuminace;

    private int numOfWindows;

    private ArrayList<Furniture> furniture = new ArrayList<Furniture>();

    private ArrayList<Bulb> bulbs = new ArrayList<Bulb>();

    public Room(String nameOfRoom, double square, int numOfWindows) throws CheckValues {
        if (square <= 0 || numOfWindows < 0|| square >= Double.MAX_VALUE ) {
            throw new CheckValues("Не правильно введённые параметры комнаты");
        }
        this.square = square;
        this.nameOfRoom = nameOfRoom;
        this.numOfWindows = numOfWindows;
    }

    public void add(Furniture furniture) {
        this.furniture.add(furniture);
    }

    public void add(Bulb bulb) {
        bulbs.add(bulb);
    }

    public String getNameOfRoom() {
        return nameOfRoom;
    }

    public double getSquare() {
        return square;
    }

    public void setNameOfRoom(String nameOfRoom) {
        this.nameOfRoom = nameOfRoom;
    }

    public void setSquare(int square) {
        this.square = square;
    }

    public double getIlluminace() {
        int val = 0;
        for (int i = 0; i < bulbs.size(); i++) {
            val += bulbs.get(i).getIlluminace();
        }
        val += numOfWindows * 700;
        return illuminace = (val);
    }

    public int getNumOfWindows() {
        return numOfWindows;
    }

    public ArrayList<Furniture> getFurniture() {
        return furniture;
    }

    public ArrayList<Bulb> getBulbs() {
        return bulbs;
    }

    public void setIlluminace(int illuminace) {
        this.illuminace = illuminace;
    }


}

