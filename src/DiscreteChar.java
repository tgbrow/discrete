import java.util.ArrayList;
import java.util.List;

public abstract class DiscreteChar {
    public static final boolean ON = true;
    public static final boolean OFF = false;

    protected ArrayList<DiscreteUnit> units;

    public abstract List<DiscreteUnit> getUnits();

    public abstract DiscretePrinter getPrinter();
    
    public abstract String toString();
}
