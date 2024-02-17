package org.jimmydev.entity;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public record ComplexNumber(double realPart, double imaginePart) {

    @Override
    public String toString() {
        NumberFormat format = new DecimalFormat("#.####");
        if (imaginePart == -1) {
            return String.format("%s + i", format.format(realPart));
        }
        if (imaginePart == 1) {
            return String.format("%s - i", format.format(realPart));
        }

        return String.format("%s + %si", format.format(realPart), format.format(imaginePart * -1));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComplexNumber that = (ComplexNumber) o;
        return Double.compare(realPart, that.realPart) == 0 && Double.compare(imaginePart, that.imaginePart) == 0;
    }

}
