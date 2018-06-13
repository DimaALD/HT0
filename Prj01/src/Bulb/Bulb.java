package Bulb;
import Exceptions.CheckValues;
public class Bulb {

    private double illuminace;

    public Bulb(double illuminace) throws CheckValues {
        if (illuminace <= 0 || illuminace > 4000)
        {
            throw new CheckValues("Освещенность лампочки должны быть > 0 и меньше 4000");
        }
        this.illuminace = illuminace;
    }
    public double getIlluminace() {
        return illuminace;
    }

    public void setIlluminace(double illuminace) {
        this.illuminace = illuminace;
    }
}
