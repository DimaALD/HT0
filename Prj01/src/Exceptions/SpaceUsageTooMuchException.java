package Exceptions;

public class SpaceUsageTooMuchException extends  Exception {
    @Override
    public String toString() {
        return "Занимаемая площадь > 70 %";
    }
}
