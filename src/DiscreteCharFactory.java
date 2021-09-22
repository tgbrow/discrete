public class DiscreteCharFactory {
    private DiscreteCharType type;

    public DiscreteCharFactory(DiscreteCharType type) {
        this.type = type;
    }

    public DiscreteChar create(char c) {
        switch(type) {
            case SEVEN_SEGMENT_CHAR:
                return new SevenSegmentChar(c);
            default:
                throw new UnsupportedOperationException(
                    String.format("DiscreteCharType %s not supported.", type.name()));
        }
    }
}
