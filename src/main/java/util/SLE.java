package util;

import java.util.LinkedList;
import java.util.List;

public class SLE {
    private final int size;
    private List<LE> leList;

    public SLE(int size, List<LE> LEs) {
        this.size = size;
        this.leList = LEs;
    }

    public int getSize() { return this.size; }

    public List<LE> getLeList() { return this.leList; }

    public LE getLe(int index) { return this.leList.get(index); }

    public boolean swapLes(LE[] swapScheme) {
        if (swapScheme.length != this.size) return false;
        List<LE> newLes = new LinkedList<>();
        for (LE le: swapScheme) newLes.add(le);
        this.leList = newLes;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i=0; i<this.size; i++) {
            builder.append(this.leList.get(i).toString());
            builder.append("\n");
        }
        return builder.toString();
    }
}
