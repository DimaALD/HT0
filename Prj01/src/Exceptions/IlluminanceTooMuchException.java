package Exceptions;

public class IlluminanceTooMuchException extends Exception {
    @Override
    public String toString() {
        return "Освещённость не в пределах 300 - 4000 лк";
    }
}
