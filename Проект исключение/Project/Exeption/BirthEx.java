package Project.Exeption;

public class BirthEx extends Exception {
    
    String inputString;

    public BirthEx(String inputString) {
        this.inputString = inputString;
    }

    @Override
    public String getMessage() {
        return "Ошибка при вводе даты '" + inputString + "', нужный формат 'дд.мм.гггг'.\n";
    }
}

