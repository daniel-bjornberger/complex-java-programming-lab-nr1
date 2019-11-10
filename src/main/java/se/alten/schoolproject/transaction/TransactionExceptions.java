package se.alten.schoolproject.transaction;

public class TransactionExceptions {


    public static class EmailNotFoundException extends Exception {

        public EmailNotFoundException(String email) {
            super("A student with email " + email + " could not be found in the database.");
        }

    }


    public static class DuplicateEmailException extends Exception {

        public DuplicateEmailException(String email) {
            super("There is already a student with email " + email + " stored in the database.");
        }

    }


}
