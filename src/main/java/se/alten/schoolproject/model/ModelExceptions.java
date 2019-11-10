package se.alten.schoolproject.model;

public class ModelExceptions {


    public static class MissingValueException extends Exception {

        public MissingValueException() {
            super("At least one of the required values are missing.");
        }

    }


}
