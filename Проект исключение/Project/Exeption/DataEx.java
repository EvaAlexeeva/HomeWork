package Project.Exeption;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DataEx {
    public static int dataCount = 6;

    private String firstName;
    private String lastName;
    private String patronymicName;
    private LocalDate birthDate;
    private Long phoneNumber;
    private Gender gender;

    public DataEx() {
    }

    public void Data(String[] splitedString) throws StringEx {
        if (splitedString == null) {
            throw new NullPointerException("Нет данных");
        }

        StringBuilder fullErrorsMessages = new StringBuilder();
        for (String string : splitedString) {
            if (Character.isLetter(string.charAt(0))) {
                if (string.length() == 1) {
                    if (this.gender == null) {
                        try {
                            this.gender = Gender1(string);
                        } catch (GenderEx e) {
                            fullErrorsMessages.append(e.getMessage());
                        }
                    } else {
                        fullErrorsMessages.append("Гендер указан больше 1 раза\n");
                    }
                } else {
                    if (this.lastName == null) {
                        try {
                            this.lastName = Initial(string);
                        } catch (InitialEx e) {
                            fullErrorsMessages.append(e.getMessage());
                        }
                    } else if (this.firstName == null) {
                        try {
                            this.firstName = Initial(string);
                        } catch (InitialEx e) {
                            fullErrorsMessages.append(e.getMessage());
                        }
                    } else if (this.patronymicName == null) {
                        try {
                            this.patronymicName = Initial(string);
                        } catch (InitialEx e) {
                            fullErrorsMessages.append(e.getMessage());
                        }
                    } else {
                        fullErrorsMessages.append("Слишком много элементов распознаны как ФИО.\n");
                    }
                }
            } else {

                if (string.matches("[0-9]{2}\\.[0-9]{2}\\.[0-9]{4}")) {
                    if (this.birthDate == null) {
                        try {
                            this.birthDate = Birth(string);
                        } catch (BirthEx e) {
                            fullErrorsMessages.append(e.getMessage());
                        }
                    } else {
                        fullErrorsMessages.append("Дата рождения должна быть только одна, а обнаружены две: '"
                                + this.birthDate + "','" + string + "'\n");
                    }
                } else {
                    if (this.phoneNumber == null) {
                        try {
                            this.phoneNumber = Number(string);
                        } catch (NumberEx e) {
                            fullErrorsMessages.append(e.getMessage());
                        }
                    } else {
                        fullErrorsMessages.append("Должен быть только один телефонный норме, а не несколько: '"
                                + this.phoneNumber + "','" + string + "'\n");
                    }
                }

            }
        }
    }

    public String getLastName() {
        return lastName;
    }

    private String Initial(String inputString) throws InitialEx {
        if (inputString.toLowerCase().matches("^[a-zа-яё]*$")) {
            return inputString;
        } else {
            throw new InitialEx(inputString);
        }
    }

    private long Number(String inpuString) throws NumberEx {
        if (inpuString.length() == 10) {
            try {
                return Long.parseLong(inpuString);
            } catch (NumberFormatException e) {
                throw new NumberEx(inpuString);
            }
        } else {
            throw new NumberEx(inpuString);
        }
    }

    private Gender Gender1(String inputString) throws GenderEx {
        try {
            return Gender.valueOf(inputString);
        } catch (IllegalArgumentException e) {
            throw new GenderEx(inputString);
        }
    }

    private LocalDate Birth(String inputString) throws BirthEx {
        try {
            return LocalDate.parse(inputString,
                    DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        } catch (DateTimeParseException e) {
            throw new BirthEx(inputString);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<").append(lastName).append(">")
                .append("<").append(firstName).append(">")
                .append("<").append(patronymicName).append(">")
                .append("<").append(birthDate.toString()).append(">")
                .append("<").append(phoneNumber).append(">")
                .append("<").append(gender).append(">");
        return sb.toString();
    }

}

