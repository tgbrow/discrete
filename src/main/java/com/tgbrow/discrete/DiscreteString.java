package com.tgbrow.discrete;

import java.util.ArrayList;

public class DiscreteString {
    private ArrayList<DiscreteChar> dChars;

    public DiscreteString(ArrayList<DiscreteChar> dChars) {
        this.dChars = dChars;
    }

    public DiscreteString() {
        this.dChars = new ArrayList<>();
    }

    public static DiscreteString create(DiscreteCharType type, String s) {
        return new DiscreteString(getDChars(type, s));
    }

    public static DiscreteString create(DiscreteCharType type, int n) {
        return create(type, String.valueOf(n));
    }

    private static ArrayList<DiscreteChar> getDChars(DiscreteCharType type, String s) {
        DiscreteCharFactory f = new DiscreteCharFactory(type);
        ArrayList<DiscreteChar> dChars = new ArrayList<>();
        for (char c : s.toCharArray()) {
            dChars.add(f.create(c));
        }
        return dChars;
    }

    public String toString() {
        if (dChars.isEmpty()) {
            return "";
        }
        return dChars.get(0).getPrinter().stringForm(dChars);
    }
}
