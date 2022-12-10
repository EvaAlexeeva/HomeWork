package Project.Exeption;

public class GenderEx extends Exception {
    String inputString;

    public GenderEx(String inputString) {
        this.inputString = inputString;
    }

    @Override
    public String getMessage() {
        return "Пол указан неверно, укажите одну из букв m или f, вместо '" + inputString + "'\n";
    }
}

