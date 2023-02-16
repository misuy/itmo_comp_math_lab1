package util;

import java.util.List;

public class LE {
    private int size;
    private double[] a;
    private double b;

    public LE(int size, double[] a, double b) {
        this.size = size;
        this.a = a;
        this.b = b;
    }

    public int getSize() { return this.size; }

    public double[] getA() { return this.a; }

    public double getB() { return this.b; }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i=0 ; i<this.size; i++) {
            builder.append(this.a[i]);
            builder.append("*x");
            builder.append(i);
            builder.append(" + ");
        }
        builder.delete(builder.length()-2, builder.length());
        builder.append("= ");
        builder.append(this.b);
        return builder.toString();
    }
}
