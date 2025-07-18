package com.tgbrow.discrete;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Pixel6x5Char extends DiscreteChar {
    public static final int NUM_ROWS = 6;
    public static final int NUM_COLUMNS = 5;
    public static final int NUM_PIXELS = NUM_ROWS * NUM_COLUMNS;

    private static final char BLANK = ' ';
    private static final char FILL  = '█';

    private static final ArrayList<DiscreteUnit> DEFAULT_UNITS;
    static {
        DEFAULT_UNITS = new ArrayList<>();
        int id = 0;
        for (int i = 0; i < NUM_ROWS; ++i) {
            for (int j = 0; j < NUM_COLUMNS; ++j) {
                DEFAULT_UNITS.add(new DiscreteUnit(id++, i, j, FILL));
            }
        }
    }

    private static final DiscretePrinter PRINTER =
        new DiscretePrinter(NUM_ROWS, NUM_COLUMNS, BLANK, true);

    private static final char CONFIG_FILL = '#';
    private static final String A =
        "     " +
        " ### " +
        "    #" +
        " ####" +
        "#   #" +
        " ####" ;
    private static final String E =
        "     " +
        " ##  " +
        "#  # " +
        "#### " +
        "#    " +
        " ##  " ;
    private static final String D =
        "     " +
        "    #" +
        "    #" +
        " ####" +
        "#   #" +
        " ####" ;
    private static final String I =
        "     " +
        " #   " +
        "     " +
        " #   " +
        " #   " +
        "  #  " ;
    private static final String M =
        "     " +
        "     " +
        "#    " +
        "#### " +
        "# # #" +
        "# # #" ;
    private static final String T =
        "     " +
        " #   " +
        " #   " +
        "###  " +
        " #   " +
        "  ## " ;
    private static final String Y =
        "     " +
        "#  # " +
        "#  # " +
        " ### " +
        "   # " +
        " ##  " ;

    private static final Map<Character, boolean[]> CHAR_TO_CONFIG;
    static {
        Map<Character, boolean[]> m = new HashMap<>();
        m.put(' ', new boolean[NUM_PIXELS]);
        m.put('a', config(A));
        m.put('d', config(D));
        m.put('e', config(E));
        m.put('i', config(I));
        m.put('m', config(M));
        m.put('t', config(T));
        m.put('y', config(Y));
        CHAR_TO_CONFIG = Collections.unmodifiableMap(m);
    }

    public Pixel6x5Char(char c) {
        boolean[] config = CHAR_TO_CONFIG.get(c);
        if (config == null) {
            throw new IllegalArgumentException(
                String.format("Character \'%c\' not supported.", c));
        }

        units = new ArrayList<>();
        for (int i = 0; i < NUM_PIXELS; ++i) {
            DiscreteUnit seg = DiscreteUnit.copyOf(DEFAULT_UNITS.get(i));
            seg.setIsOn(config[i]);
            units.add(seg);
        }
    }

    @Override
    public DiscretePrinter getPrinter() {
        return PRINTER;
    }

    private static boolean[] config(String s) {
        boolean[] config = new boolean[s.length()];
        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; ++i) {
            config[i] = (cs[i] == CONFIG_FILL);
        }
        return config;
    }
}
