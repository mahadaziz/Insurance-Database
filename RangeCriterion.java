class RangeCriterion {
    // Private fields of the RangeCriterion class
    private long maxValue = Long.MAX_VALUE;
    private long minValue = Long.MIN_VALUE;

    // This function determines if the tag has the ">" symbol and if it does it reassigns the
    // minValue field with the largest number between the existing minValue or the value from
    // the tag. If the tag has the "<" symbol and if it does it reassigns the maxValue field with
    // the smallest number between the existing maxValue or the value from the tag.
    void addCriterion(Tag tag) {
        // Checks if the tag has ">"
        if (tag.getRelation() == Tag.Relation.LARGER) {
            // Reassigns the minValue to the larger number between the existing minValue and
            // the value from the tag
            minValue = Math.max(minValue, Long.parseLong(tag.getValue()));
        }
        // Checks if the tag has "<"
        if (tag.getRelation() == Tag.Relation.SMALLER) {
            // Reassigns the maxValue to the smaller number between the existing maxValue and
            // the value from the tag
            maxValue = Math.min(maxValue, Long.parseLong(tag.getValue()));
        }
    }

    // This function determines if the value is between maxValue and minValue and returns true
    // if it is and false if it is not
    boolean isInRange(long value) {
        // Checks if value is between maxValue and minValue
        if (value < maxValue && value > minValue)
            return true;
        return false;
    }
}
