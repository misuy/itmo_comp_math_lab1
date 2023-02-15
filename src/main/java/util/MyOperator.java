package util;

public class MyOperator implements VectorOperator{
    private int size;
    private double[] coefficients;
    private double constantCoefficient;

    public MyOperator(double[] coefficients, double constantCoefficient) {
        this.size = coefficients.length;
        this.coefficients = coefficients;
        this.constantCoefficient = constantCoefficient;
    }

    public double apply(Vector vector) throws IllegalArgumentException{
        double result = 0;
        if (this.size == vector.getSize()) {
            for (int i=0; i<this.size; i++) {
                result += this.coefficients[i] * vector.getComponent(i);
            }
            result += this.constantCoefficient;
            return result;
        }
        throw new IllegalArgumentException("Vector and operator sizes don't match");
    }
}
