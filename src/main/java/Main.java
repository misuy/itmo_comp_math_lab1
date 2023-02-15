import util.GaussSeidelSolver;
import util.LE;
import util.SLE;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        double[] ar0 = new double[3];
        ar0[0] = 0;
        ar0[1] = 1;
        ar0[2] = 1;

        double[] ar1 = new double[3];
        ar1[0] = 1;
        ar1[1] = 1;
        ar1[2] = 0;

        double[] ar2 = new double[3];
        ar2[0] = 0;
        ar2[1] = 0;
        ar2[2] = 1;

        List<LE> les = new LinkedList<>();
        les.add(new LE(3, ar0, 1));
        les.add(new LE(3, ar1, 2));
        les.add(new LE(3, ar2, 0));
        for (LE le: les) {
            System.out.println(le);
        }

        SLE sle = new SLE(3, les);

        GaussSeidelSolver solver = new GaussSeidelSolver(sle);

        if (solver.checkSpecialCases()) return;

        System.out.println(solver.transformToDiagonallyDominatingMatrix());

        solver.solve(0.0001);
    }
}