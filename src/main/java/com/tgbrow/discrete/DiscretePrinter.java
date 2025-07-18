package com.tgbrow.discrete;

import java.util.List;

public class DiscretePrinter {

    private int numRows;
    private int numColumns;
    private char blankChar;
    // If true, add an extra space between each DiscreteChar.
    private boolean spaceBetween;

    public DiscretePrinter(int numRows, int numColumns, char blankChar, boolean spaceBetween) {
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.blankChar = blankChar;
        this.spaceBetween = spaceBetween;
    }

    public String stringForm(DiscreteChar dChar) {
        return stringForm(List.of(dChar));
    }

    public String stringForm(List<DiscreteChar> dChars) {
        StringBuilder[] sbs = new StringBuilder[numRows];
        for (int i = 0; i < sbs.length; ++i) {
            sbs[i] = new StringBuilder();
        }

        for (DiscreteChar dc : dChars) {
            int column = 0;
            int row = 0;
            StringBuilder sb = sbs[0];

            for (DiscreteUnit unit : dc.getUnits()) {
                while (unit.getRow() != row) {
                    // We're at the first unit of a new row.
                    // Fill remaining columns of old row with blanks.
                    while (column < numColumns) {
                        sb.append(blankChar);
                        ++column;
                    }
                    if (spaceBetween) sb.append(blankChar);
                    // Update state to beginning of new row.
                    column = 0;
                    ++row;
                    sb = sbs[row];
                }

                // Fill skipped columns with blanks.
                while (column < unit.getColumn()) {
                    sb.append(blankChar);
                    ++column;
                }

                // Append the actual unit representation.
                sb.append(charRepresentation(unit));
                ++column;
            }

            // Fill any remaining columns of the final row.
            while (column < numColumns) {
                sb.append(blankChar);
                ++column;
            }
            if (spaceBetween) sb.append(blankChar);
        }

        StringBuilder result = sbs[0];
        for (int i = 1; i < sbs.length; ++i) {
            result.append("\n");
            result.append(sbs[i]);
        }
        return result.toString();
    }

    private char charRepresentation(DiscreteUnit dc) {
        return dc.isOn() ? dc.getRepresentation() : blankChar;
    }
}
