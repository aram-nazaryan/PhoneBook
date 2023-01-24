package example;

import java.util.Comparator;

public class UserSortingPredicate {

    private final Integer sortOrder;

    public UserSortingPredicate(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public final Comparator<User> namePredicate = new Comparator<>() {
        @Override
        public int compare(User a, User b) {
            return a.getName().compareTo(b.getName()) * sortOrder;
        }
    };

    public final Comparator<User> surnamePredicate = new Comparator<>() {
        @Override
        public int compare(User a, User b) {
            if (a.getSurname().equals(""))
                return 1;
            else if (b.getSurname().equals(""))
                return -1;
            return a.getSurname().compareTo(b.getSurname()) * sortOrder;
        }
    };

    public final Comparator<User> phonePredicate = new Comparator<>() {
        @Override
        public int compare(User a, User b) {
            if (a.getPhoneNumber().equals(""))
                return 1;
            else if (b.getPhoneNumber().equals(""))
                return -1;
            return a.getPhoneNumber().compareTo(b.getPhoneNumber()) * sortOrder;
        }
    };
}
