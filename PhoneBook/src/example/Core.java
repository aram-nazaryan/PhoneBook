package example;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Core {
    private static final Validator validator = new ValidatorImpl();
    private static final List<User> users = new ArrayList<>();
    private static final List<ValidationMessage> messages = new ArrayList<>();
    private static Scanner scanner;

    private static void selectSortCriteria(final UserSortingPredicate predicate) {
        Character option = '0';
        while (!option.equals('q')) {
            System.out.println("Please choose criteria: “Name”(0), “Surname”(1) or “PhoneNumberCode\"(2) - press 'q' to quit");
            scanner = new Scanner(System.in);
            option = scanner.nextLine().charAt(0);
            switch (option) {
                case '0' -> {
                    users.sort(predicate.namePredicate);
                    option = 'q';
                }
                case '1' -> {
                    users.sort(predicate.surnamePredicate);
                    option = 'q';
                }
                case '2' -> {
                    users.sort(predicate.phonePredicate);
                    option = 'q';
                }
                case 'q' -> System.out.println("You went so far :(");
                default -> System.out.println("Incorrect option.");
            }
        }
    }

    private static void selectSortOrder() {
        Character option = '0';
        while (!option.equals('q')) {
            System.out.println("Please choose an ordering to sort: “Ascending”(0) or “Descending(1) - press 'q' to exit");
            UserSortingPredicate predicate = null;
            scanner = new Scanner(System.in);
            option = scanner.nextLine().charAt(0);
            switch (option) {
                case '0' -> {
                    predicate = new UserSortingPredicate(1);
                    selectSortCriteria(predicate);
                    option = 'q';
                }
                case '1' -> {
                    predicate = new UserSortingPredicate(-1);
                    selectSortCriteria(predicate);
                    option = 'q';
                }
                case 'q' -> System.out.println("Adios :)");
                default -> System.out.println("Incorrect option.");
            }
        }
    }

    public static void run(final FileInputStream stream) {
        scanner = new Scanner(stream);
        Integer numberOfLine = 1;
        System.out.println("-----File Structure-----\n");
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            System.out.println(numberOfLine + ") " + line);
            final User user = new User();
            final ValidationMessage message = new ValidationMessage(numberOfLine++);
            validator.validate(line, user, message);
            users.add(user);
            messages.add(message);
        }

        selectSortOrder();

        System.out.println("\n-----Sorted Users-----");
        for (User user : users) {
            System.out.println(user);
        }

        System.out.println("\n-----Incorrect Lines-----");
        for (ValidationMessage message : messages) {
            if (!message.getMessage().equals(""))
                System.out.println(message);
        }
    }
}
