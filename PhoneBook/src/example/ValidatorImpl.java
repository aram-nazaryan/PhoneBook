package example;

import java.util.List;

public class ValidatorImpl implements Validator {
    @Override
    public void validate(final String userInfo, final User user, final ValidationMessage message) {
        String[] userDetails = null;
        if (userInfo.contains(":")) {
            userDetails = userInfo.split(":");
        } else if (userInfo.contains("-")) {
            userDetails = userInfo.split("-");
        } else {
            message.setMessage(ErrorMessage.INCORRECT_SEPARATOR.getName());
            final List<Character> characters = List.of('@', '#', '%', '&', '|');
            for (Character c : characters) {
                if (userInfo.contains(c.toString())) {
                    userDetails = userInfo.split(c.toString());
                    break;
                }
            }
        }
        if (userDetails != null) {
            if (userDetails.length == 1)
                phoneValidate("", user, message);
            else
                phoneValidate(userDetails[1], user, message);

            userValidate(userDetails[0], user, message);

        } else {
            message.setMessage((message.getMessage().equals("") ? ErrorMessage.UNABLE_TO_PROCESS.getName() :
                    message.getMessage() + ", " + ErrorMessage.UNABLE_TO_PROCESS.getName()));
            user.setPhoneNumber("");
        }
    }

    private void userValidate(String userInfo, User user, ValidationMessage message) {
        String[] nameSurname = userInfo.split(" ");
        if (nameSurname.length == 2) {
            user.setName(nameSurname[0]);
            user.setSurname(nameSurname[1]);
        } else if (nameSurname.length == 1) {
            user.setName(nameSurname[0]);
        } else {
            message.setMessage((message.getMessage().equals("") ? ErrorMessage.UNABLE_TO_PROCESS.getName() :
                    message.getMessage() + ", " + ErrorMessage.UNABLE_TO_PROCESS.getName()));
        }
    }

    private void phoneValidate(String userInfo, User user, ValidationMessage message) {
        String phoneNumber = userInfo.replace(" ", "");
        if (phoneNumber.length() != 9 || isNumberIncludeCharacters(phoneNumber)) {
            message.setMessage((message.getMessage().equals("") ? ErrorMessage.INVALID_NUMBER.getName() :
                    message.getMessage() + ", " + ErrorMessage.INVALID_NUMBER.getName()));
        }
        user.setPhoneNumber(phoneNumber);
    }

    private boolean isNumberIncludeCharacters(String phoneNumber) {
        for (int i = 0; i < phoneNumber.length(); ++i) {
            char c = phoneNumber.charAt(i);
            if (c < '0' || c > '9')
                return true;
        }
        return false;
    }
}
