package util;

import java.util.*;

public class GaussSeidelSolver {
    private final SLE sle;

    private Vector prevX;

    private Vector x;

    private VectorOperator[] operators = null;

    public GaussSeidelSolver(SLE sle) {
        this.sle = sle;
    }

    public boolean transformToDiagonallyDominatingMatrix() {
        Map<LE, Boolean> freeMap = new HashMap<>();
        LE[] swapScheme = new LE[this.sle.getSize()];
        Arrays.fill(swapScheme, null);
        List<LE>[] potentialSwapScheme = new List[this.sle.getSize()];
        for (int i=0; i<this.sle.getSize(); i++) {
            potentialSwapScheme[i] = new LinkedList<>();
        }

        for (LE le: this.sle.getLeList()) {
            freeMap.put(le, true);
            double sum = 0;
            for (double element: le.getA()) {
                sum += Math.abs(element);
            }

            for (int i=0; i<le.getSize(); i++) {
                if (2*Math.abs(le.getA()[i]) >= sum) {
                    potentialSwapScheme[i].add(le);
                }
            }
        }

        boolean iterate = true;
        while (iterate) {
            iterate = false;

            for (int i=0; i<potentialSwapScheme.length; i++) {
                if (swapScheme[i] == null) {
                    for (int j = 0; j < potentialSwapScheme[i].size(); j++) {
                        if (!freeMap.get(potentialSwapScheme[i].get(j))) {
                            potentialSwapScheme[i].remove(j);
                            j--;
                        }
                    }
                    if (potentialSwapScheme[i].size() == 1) {
                        swapScheme[i] = potentialSwapScheme[i].get(0);
                        freeMap.replace(potentialSwapScheme[i].get(0), false);
                        iterate = true;
                    }
                }
            }

            if (!iterate) {
                for (int i=0; i<potentialSwapScheme.length; i++) {
                    if (swapScheme[i] == null) {
                        if (potentialSwapScheme[i].size() > 1) {
                            swapScheme[i] = potentialSwapScheme[i].get(0);
                            freeMap.replace(potentialSwapScheme[i].get(0), false);
                            iterate = true;
                        }
                    }
                }
            }
        }

        boolean ok = true;
        for (int i=0; i<swapScheme.length; i++) if (swapScheme[i] == null) ok = false;

        if (ok) return this.sle.swapLes(swapScheme);
        return false;
    }

    public boolean checkSpecialCases() {
        boolean specialCase = false;
        for (int i=0; i<this.sle.getSize(); i++) {
            boolean allCoefficientsAre0 = true;
            for (int j=0; j<this.sle.getSize(); j++) {
                if (this.sle.getLe(i).getA()[j] != 0) allCoefficientsAre0 = false;
            }
            if (allCoefficientsAre0) {
                if (this.sle.getLe(i).getB() == 0) System.out.println("Система имеет бесконечное множество решений.");
                else System.out.println("Система не имеет решений.");
                specialCase = true;
                break;
            }
        }
        return specialCase;
    }

    private void init() {
        this.prevX = null;
        this.x = Vector.getZeroVector(this.sle.getSize(), "X_0");

        this.operators = new VectorOperator[this.sle.getSize()];
        for (int i=0; i<this.sle.getSize(); i++) {
            double[] coefficients = new double[this.sle.getSize()];
            for (int j=0; j<this.sle.getSize(); j++) {
                if (i == j) coefficients[j] = 0;
                else coefficients[j] = - this.sle.getLe(i).getA()[j] / this.sle.getLe(i).getA()[i];
            }
            this.operators[i] = new MyOperator(coefficients, this.sle.getLe(i).getB() / this.sle.getLe(i).getA()[i]);
        }
    }

    private void iterate(int iterationNumber) {
        this.prevX = new Vector(this.x.getComponents().clone(), this.x.getName());

        this.x.setName("X_" + iterationNumber);
        for (int i=0; i<this.sle.getSize(); i++) {
            this.x.setComponent(i, operators[i].apply(this.x));
        }
    }

    public void solve(double eps) {
        this.init();
        int maxIterationsCount = 100000;
        System.out.println(this.x + "\n");
        for (int i=0; i<maxIterationsCount; i++) {
            System.out.println("Итерация " + (i+1) + "-------------------------");

            this.iterate(i+1);
            System.out.println(this.x);

            Vector deltaVector = this.x.absSub(this.prevX);
            System.out.println(deltaVector);
            double maxDelta = deltaVector.maxValue();

            System.out.println("max(" + deltaVector.getName() + ") = " + maxDelta);

            System.out.println("-----------------------------------\n");

            if (maxDelta < eps) {
                System.out.println("max(" + deltaVector.getName() + ") < eps(=" + eps + "). Решение окончено.");
                this.x.setName("X");
                System.out.println("Ответ: " + this.x);
                break;
            }
        }
    }
}
