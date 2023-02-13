package example;

public enum ErrorMessage {

    INCORRECT_SEPARATOR("the separator should be `:` or `-`"),
    INVALID_NUMBER("phone number should be with 9 digits and include only digits"),
    UNABLE_TO_PROCESS("unable to process due to missing or incorrect data");

    private final String name;

    ErrorMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
