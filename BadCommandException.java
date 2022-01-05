class BadCommandException extends RuntimeException {
    // Constructor for BadCommandException that takes a String and sends that to the constructor
    // of the super class which displays the String
    BadCommandException(String message) {
        super(message);
    }
}
