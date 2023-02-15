package util;

import java.util.Arrays;

public class Vector {
    private int size;
    private double[] components;
    private String name;

    public Vector(double[] components, String name) {
        this.size = components.length;
        this.components = components;
        this.name = name;
    }

    public int getSize() { return this.size; }

    public void setComponent(int index, double value) {
        this.components[index] = value;
    }

    public double getComponent(int index) { return this.components[index]; }

    public double[] getComponents() { return this.components; }

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    public static Vector getZeroVector(int size, String name) {
        double[] components = new double[size];
        Arrays.fill(components, 0);
        return new Vector(components, name);
    }

    public Vector absSub(Vector vector) {
        double[] components = new double[this.size];
        for (int i=0; i<this.size; i++) {
            components[i] = Math.abs(this.components[i] - vector.getComponent(i));
        }
        return new Vector(components, "|(" + this.name + ") - (" + vector.getName() + ")|");
    }

    public double maxValue() {
        double maxValue = Double.MIN_VALUE;
        for (int i=0; i<this.size; i++) {
            if (this.components[i] > maxValue) maxValue = this.components[i];
        }
        return maxValue;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.name);
        builder.append(" = (");
        for (int i=0; i<this.size; i++) {
            builder.append(this.components[i]);
            builder.append(", ");
        }
        builder.delete(builder.length()-2, builder.length());
        builder.append(")");
        return builder.toString();
    }
}
