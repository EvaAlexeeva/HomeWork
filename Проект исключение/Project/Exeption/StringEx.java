package Project.Exeption;


public class StringEx extends Exception {
    String message;

    public StringEx(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
