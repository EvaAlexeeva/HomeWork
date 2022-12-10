package Project.Exeption;




public class NumberEx extends Exception {
    String inputString;

    public NumberEx(String inputString) {
        this.inputString = inputString;
    }

    @Override
    public String getMessage() {
        return "Ошибка " + inputString + " формат телефонного номера 10 цифр без разделителей )\n";
    }
}
