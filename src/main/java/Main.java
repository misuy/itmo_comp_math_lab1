import util.GaussSeidelSolver;
import util.InputAdapter;

public class Main {
    public static void main(String[] args) {
        InputAdapter ioAdapter = new InputAdapter();
        boolean fromConsole = ioAdapter.usage();

        GaussSeidelSolver solver = null;
        if (fromConsole) solver = ioAdapter.consoleInput();
        else solver = ioAdapter.fileInput();

        System.out.println("\nСистема уравнений:");
        System.out.println(solver.getSle());

        if (!solver.checkSpecialCases()) {
            if (solver.transformToDiagonallyDominatingMatrix()) {
                System.out.println("Система уравнений после преобразований для достижения диагонального преобладания.");
                System.out.println(solver.getSle());
                solver.solve();
            }
            else System.out.println("Невозможно достичь диагонального преобладания.");
        }
    }
}