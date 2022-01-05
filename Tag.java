class Tag {
    // Relation function that contains the SMALLER, LARGER, EQUAL constants
    public enum Relation {
        SMALLER, LARGER, EQUAL
    }
    // Private fields of Tag
    private Relation relation;
    private String name;
    private String value;

    // Constructor that assigns the tokens to their corresponding fields. The relation field
    // is assigned a Relation from the Relation function based on the binary operator that
    // appears in the second token. If a "<", ">", or "=" is not used then an exception is given
    Tag(String[] tokens) {
        // Assigns the fields with the necessary information taken from the tokens
        name = tokens[0];

        // Switch to handle the various binary operators as cases
        switch (tokens[1].charAt(0)) {
            // If case is '<' then relation is set to SMALLER
            case '<':
                relation = Relation.SMALLER;
                break;
            // If case is '>' then relation is set to LARGER
            case '>':
                relation = Relation.LARGER;
                break;
            // If case is '=' then relation is set to EQUAL
            case '=':
                relation = Relation.EQUAL;
                break;
            // Throws exception if none of the cases are met
            default:
                throw new BadCommandException("Invalid tag: ill-defined bad relation.");
        }
        value = tokens[2];
    }

    // Returns the relation of the tag
    public Relation getRelation() {
        return relation;
    }

    // Returns the name of the tag
    public String getName() {
        return name;
    }

    // Returns the value of the tag
    public String getValue() {
        return value;
    }
}