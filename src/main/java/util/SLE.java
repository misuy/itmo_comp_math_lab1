package util;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SLE {
    private final int size;
    private List<LE> les;

    public SLE(int size, List<LE> LEs) {
        this.size = size;
        this.les = LEs;
    }

    public int getSize() { return this.size; }

    public List<LE> getLeList() { return this.les; }

    public LE getLe(int index) { return this.les.get(index); }

    public boolean swapLes(LE[] swapScheme) {
        if (swapScheme.length != this.size) return false;
        List<LE> newLes = new LinkedList<>();
        for (LE le: swapScheme) newLes.add(le);
        this.les = newLes;
        return true;
    }
}
