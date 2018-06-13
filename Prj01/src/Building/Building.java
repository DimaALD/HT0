package Building;

import Exceptions.CheckValues;
import Furniture.Furniture;
import Room.Room;
import Exceptions.*;

import java.util.ArrayList;

public class Building {
    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public String getNameOfBuilding() {
        return nameOfBuilding;
    }

    public void setNameOfBuilding(String nameOfBuilding) {
        this.nameOfBuilding = nameOfBuilding;
    }

    private ArrayList<Room> rooms = new ArrayList<Room>();

    private String nameOfBuilding;

    public Building(String nameOfBuilding) {
        this.nameOfBuilding = nameOfBuilding;
    }

    public void addRoom(String name, double square, int windows) throws CheckValues {
        boolean check = true;
        for (Room room : rooms) {
            if (room.getNameOfRoom().equals(name)) {
                check = false;
                break;
            }
        }
        if (check) {
            rooms.add(new Room(name, square, windows));
        } else throw new CheckValues("Комната с именем " + name + " в здании " + nameOfBuilding + " уже существует");
    }

    public Room getRoom(String room) throws CheckValues {
        for (Room room1 : rooms) {
            if (room1.getNameOfRoom().equals(room)) {
                return room1;
            }
        }
        throw new CheckValues(" Комната " + room +  " в здании " + nameOfBuilding + " отсутсвует " );
    }

    public void describe() throws IlluminanceTooMuchException {

        try {

            for (int i = 0; i < rooms.size(); i++) {
                double min = 0, max = 0;
                for (Furniture room : rooms.get(i).getFurniture()) {
                    min += room.getMinSquare();
                    max += room.getMaxSquare();
                }
                System.out.println(nameOfBuilding);
                System.out.println(rooms.get(i).getNameOfRoom());
                {
                    String nameForWindow = null;
                    if (rooms.get(i).getIlluminace() < 300 || rooms.get(i).getIlluminace() > 4000) {
                        throw new IlluminanceTooMuchException();
                    } else if (rooms.get(i).getNumOfWindows() == 0) {
                        nameForWindow = " окон ";
                    } else if (rooms.get(i).getNumOfWindows() == 1) {
                        nameForWindow = " окно 700 лк";
                    } else if (rooms.get(i).getNumOfWindows() == 5) {
                        nameForWindow = "5 окон по 700 лк ";
                    } else if (rooms.get(i).getNumOfWindows() > 1 || rooms.get(i).getNumOfWindows() < 5) {
                        nameForWindow = " окна по 700 лк";
                    }
                    System.out.print("Освещенность = " + rooms.get(i).getIlluminace()
                            + " " + rooms.get(i).getNumOfWindows() + nameForWindow);
                    if (!rooms.get(i).getBulbs().isEmpty()) {
                        for (int z = 0; z < rooms.get(i).getBulbs().size(); z++) {
                            System.out.print(",лампочка - " + rooms.get(i).getBulbs().get(z).getIlluminace() + " лк ");
                        }
                    } else
                        System.out.print(",лампочки отстутсвуют");

                    System.out.println();

                    if ((max / rooms.get(i).getSquare()) * 100 < 70) {
                        System.out.print("Площадь = " + rooms.get(i).getSquare() + " м2");
                        if (min == max) {
                            System.out.println(" Занято - " + max + " м2 " + "Свободно - "
                                    + (rooms.get(i).getSquare() - max) + " м2");
                            System.out.printf("Гарантированно свободно - " + "%.2f ", (100 - (max / rooms.get(i).getSquare()) * 100));
                            System.out.println("%");
                        } else {
                            System.out.print(" Занято: " + min + " - " + max + " м2 " + "Гарантированно свободно - "
                                    + (rooms.get(i).getSquare() - max) + " м2 ");
                            System.out.printf("или " + "%.2f ", (100 - (max / rooms.get(i).getSquare()) * 100));
                            System.out.println("%");
                        }

                        System.out.println("Мебель:");
                        if (!rooms.get(i).getFurniture().isEmpty()) {
                            for (int j = 0; j < rooms.get(i).getFurniture().size(); j++) {
                                if (rooms.get(i).getFurniture().get(j).getMaxSquare() == rooms.get(i).getFurniture().get(j).getMinSquare()) {
                                    System.out.println(rooms.get(i).getFurniture().get(j).getName() +
                                            " (Площадь " + rooms.get(i).getFurniture().get(j).getMaxSquare() + " м2 )");
                                } else
                                    System.out.println(rooms.get(i).getFurniture().get(j).getName() +
                                            " (Площадь от " + rooms.get(i).getFurniture().get(j).getMinSquare() + " до " +
                                            rooms.get(i).getFurniture().get(j).getMaxSquare() + " м2 )");

                            }

                        } else System.out.println("Мебель отсутсвует");
                    } else {
                        throw new SpaceUsageTooMuchException();

                    }
                }
            }
        } catch (IlluminanceTooMuchException exc) {
            System.out.println(exc.toString());
        } catch (SpaceUsageTooMuchException exc) {
            System.out.println(exc.toString());
        }
    }

}
