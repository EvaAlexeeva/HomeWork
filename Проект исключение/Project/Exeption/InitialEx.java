package Project.Exeption;


public class InitialEx extends Exception {

    String inputString;

    public InitialEx(String inputString) {
        this.inputString = inputString;
    }

    @Override
    public String getMessage() {
        return "Формат ФИО введен не верно '" + inputString + "'. ФИО могут состоять только из букв.\n";
    }
}

