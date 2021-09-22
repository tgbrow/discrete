import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class SevenSegmentChar extends DiscreteChar {
    /**
     * The segments of a seven-segement character are arranged like so:
     *    _
     *   |_|
     *   |_|
     * 
     * For example, think of a digit in the display of an retro digital alarm
     * clock, which would display the time "12:56" as follows.
     *     _   _  _
     *   | _|.|_ |_
     *   ||_ . _||_|
     * 
     * This following diagram shows the IDs associated with each segment. The
     * labels along the edges identify row and column indicies. The numbers
     * inside are segment IDs. An 'x' means there's no segment at the given
     * position.
     * 
     *       c0 c1 c2
     *       ---------
     *   r0 | x  0  x
     *   r1 | 1  2  3
     *   r2 | 4  5  6 
     */

    public static final int NUM_SEGMENTS = 7;
    public static final int NUM_ROWS = 3;
    public static final int NUM_COLUMNS = 3;

    private static final char BLANK      = ' ';
    private static final char HORIZONTAL = '_';
    private static final char VERTICAL   = '|';

    private static final DiscreteUnit[] DEFAULT_SEGMENTS = {
        //           id, row, col, representation
        new DiscreteUnit( 0,  0,   1,   HORIZONTAL    ),
        new DiscreteUnit( 1,  1,   0,   VERTICAL      ),
        new DiscreteUnit( 2,  1,   1,   HORIZONTAL    ),
        new DiscreteUnit( 3,  1,   2,   VERTICAL      ),
        new DiscreteUnit( 4,  2,   0,   VERTICAL      ),
        new DiscreteUnit( 5,  2,   1,   HORIZONTAL    ),
        new DiscreteUnit( 6,  2,   2,   VERTICAL      ),
    };

    private static final DiscretePrinter PRINTER = new DiscretePrinter(NUM_ROWS, NUM_COLUMNS, BLANK);

    // Each of the following arrays holds the segment on/off configuration
    // that will produce the specified character.
    // Example: TWO[i] specifies whether the segment with ID i should be off or
    // on in order to display the number two.
    //
    // Digits
    private static final boolean[] ZERO = 
    //    0    1    2    3    4    5    6   <-- segment ids
        { ON,  ON,  OFF, ON,  ON,  ON,  ON  };
    private static final boolean[] ONE = 
        { OFF, OFF, OFF, ON,  OFF, OFF, ON  };
    private static final boolean[] TWO = 
        { ON,  OFF, ON,  ON,  ON,  ON,  OFF };
    private static final boolean[] THREE = 
        { ON,  OFF, ON,  ON,  OFF, ON,  ON  };
    private static final boolean[] FOUR = 
        { OFF, ON,  ON,  ON,  OFF, OFF, ON  };
    private static final boolean[] FIVE =
        { ON,  ON,  ON,  OFF, OFF, ON,  ON  };
    private static final boolean[] SIX =
        { ON,  ON,  ON,  OFF, ON,  ON,  ON  };
    private static final boolean[] SEVEN =
        { ON,  OFF, OFF, ON,  OFF, OFF, ON  };
    private static final boolean[] EIGHT =
        { ON,  ON,  ON,  ON,  ON,  ON,  ON  };
    private static final boolean[] NINE =
        { ON,  ON,  ON,  ON,  OFF, ON,  ON  };
    // Letters
    private static final boolean[] A =
        { ON,  ON,  ON,  ON,  ON,  OFF, ON  };
    private static final boolean[] B =
        { OFF, ON,  ON,  OFF, ON,  ON,  ON  };
    private static final boolean[] C =
        { ON,  ON,  OFF, OFF, ON,  ON,  OFF };
    private static final boolean[] H =
        { OFF, ON,  ON,  OFF, ON,  OFF, ON  };
    private static final boolean[] O = ZERO;
    // Misc
    private static final boolean[] SPACE =
        { OFF, OFF, OFF, OFF, OFF, OFF, OFF };
    private static final boolean[] DASH =
        { OFF, OFF, ON,  OFF, OFF, OFF, OFF };

    // A map for easily looking up the segment on/off configuration that
    // corresponds to a given character.
    private static final Map<Character, boolean[]> CHAR_TO_SEG_CONFIG;
    static {
        Map<Character, boolean[]> m = new HashMap<>();
        // Digits
        m.put('0', ZERO);
        m.put('1', ONE);
        m.put('2', TWO);
        m.put('3', THREE);
        m.put('4', FOUR);
        m.put('5', FIVE);
        m.put('6', SIX);
        m.put('7', SEVEN);
        m.put('8', EIGHT);
        m.put('9', NINE);
        // Letters
        m.put('A', A);
        m.put('B', B);
        m.put('C', C);
        m.put('H', H);
        m.put('O', O);
        // Misc
        m.put(' ', SPACE);
        m.put('-', DASH);
        CHAR_TO_SEG_CONFIG = Collections.unmodifiableMap(m);
    }

    public SevenSegmentChar(char c) {
        boolean[] config = CHAR_TO_SEG_CONFIG.get(Character.toUpperCase(c));
        if (config == null) {
            throw new IllegalArgumentException(
                String.format("Character \'%c\' not supported.", c));
        }

        units = new ArrayList<>();
        for (int i = 0; i < NUM_SEGMENTS; ++i) {
            DiscreteUnit seg = DiscreteUnit.copyOf(DEFAULT_SEGMENTS[i]);
            seg.setIsOn(config[i]);
            units.add(seg);
        }
    }

    @Override
    public List<DiscreteUnit> getUnits() {
        return List.copyOf(units);
    }

    @Override
    public DiscretePrinter getPrinter() {
        return PRINTER;
    }

    @Override
    public String toString() {
        return PRINTER.stringForm(this);
    }
}
