import Building.Building;
import Exceptions.CheckValues;
import Exceptions.IlluminanceTooMuchException;
import Exceptions.SpaceUsageTooMuchException;
import Furniture.Bed;
import Furniture.Table;
import Bulb.*;

public class Start {
    public static void main(String[] args) throws SpaceUsageTooMuchException, IlluminanceTooMuchException {
        Building building1 = new Building("B1");
        Building building2 = new Building("B2");
        try {
            building1.addRoom("Комната 1", 21, 0);
            building1.getRoom("Комната 1").add(new Table("Стол обеденный", 2,3));
            building1.getRoom("Комната 1").add(new Table("Стол письменыый", 6));
            building1.getRoom("Комната 1").add(new Bed("Кровать спальная", 3,2));
            building1.getRoom("Комната 1").add(new Bulb(150));
            building1.getRoom("Комната 1").add(new Bulb(350));
            building1.addRoom("Комната 2", 23, 2);
            building1.getRoom("Комната 2").add(new Bed("Кровать спальная", 3));
            building1.getRoom("Комната 2").add(new Bulb(400));
            building1.addRoom("Комната 3", 10, 4);
            building1.getRoom("Комната 3").add(new Bulb(3));
            building1.describe();


        } catch (CheckValues checkValues) {
            System.out.println(checkValues.getMessage());
        }
    }
}
