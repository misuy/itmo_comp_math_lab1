package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class InputAdapter {
    private Scanner scanner;

    public InputAdapter() {
        this.scanner = new Scanner(System.in);
    }

    public boolean usage() {
        System.out.println("Использование: 1 - ввод матрицы с консоли, 2 - ввод матрицы из файла.");
        boolean fromConsole = true;
        if (this.scanner.nextInt() == 2) fromConsole = false;
        return fromConsole;
    }

    public GaussSeidelSolver consoleInput() throws IllegalArgumentException {
        System.out.println("Введите размерность матрицы n. (n - целое, 1 < n < 21)");
        int size = this.scanner.nextInt();
        if (size < 2 || size > 20) throw new IllegalArgumentException("n - целое, 1 < n < 21");
        System.out.println("Введите матрицу. Формат ввода: n строк, в каждой строке n+1 элемент.\n----------\na11 a12..a1n b1\na21 a22..a2n b2\n.\n.\n.\nan1 an2..ann bn\n----------");
        List<LE> leList = new LinkedList<>();
        for (int i=0; i<size; i++) {
            double[] a = new double[size];
            for (int j=0; j<size; j++) {
                a[j] = this.scanner.nextDouble();
            }
            double b = this.scanner.nextDouble();
            leList.add(new LE(size, a, b));
        }
        System.out.println("Введите точность.");
        double eps = this.scanner.nextDouble();
        return new GaussSeidelSolver(new SLE(size, leList), eps);
    }

    public GaussSeidelSolver fileInput() throws IllegalArgumentException {
        System.out.println("Введите путь файла.");
        String path = this.scanner.next();

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(path);
        }
        catch (FileNotFoundException ex) {
            System.out.println("Файл не найден.");
            throw new IllegalArgumentException();
        }
        Scanner fileScanner = new Scanner(fileInputStream);

        int size = fileScanner.nextInt();
        if (size < 2 || size > 20) throw new IllegalArgumentException("n - целое, 1 < n < 21");
        List<LE> leList = new LinkedList<>();
        for (int i=0; i<size; i++) {
            double[] a = new double[size];
            for (int j=0; j<size; j++) {
                a[j] = fileScanner.nextDouble();
            }
            double b = fileScanner.nextDouble();
            leList.add(new LE(size, a, b));
        }
        double eps = fileScanner.nextDouble();
        return new GaussSeidelSolver(new SLE(size, leList), eps);
    }

}
