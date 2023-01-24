package example;

public class ValidationMessage {
    private Integer lineNumber;
    private String message;

    public ValidationMessage(Integer lineNumber) {
        this.lineNumber = lineNumber;
        this.message = "";
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Line " + lineNumber + ") message = " + message;
    }
}
