import java.util.ArrayList;
import java.util.List;

public abstract class DiscreteChar {
    public static final boolean ON = true;
    public static final boolean OFF = false;

    protected ArrayList<DiscreteUnit> units;

    public final List<DiscreteUnit> getUnits() {
        return List.copyOf(units);
    }

    public final String toString() {
        return getPrinter().stringForm(this);
    }

    public abstract DiscretePrinter getPrinter();
}
