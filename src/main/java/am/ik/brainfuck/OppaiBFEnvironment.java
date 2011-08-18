package am.ik.brainfuck;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;

public class OppaiBFEnvironment {
    private final String source;
    private final int sourceLength;
    private final int[] memory;
    private int pointer;
    private int current;
    private InputStream in = System.in;
    private PrintStream out = System.out;

    public OppaiBFEnvironment(String source, int memorySize) {
        this.source = source;
        sourceLength = source.length();
        memory = new int[memorySize];
        Arrays.fill(memory, 0);
        pointer = 0;
        current = source.indexOf("(");
    }

    public int getPointer() {
        return pointer;
    }

    public void incrementPointer() {
        pointer++;
    }

    public void decrementPointer() {
        pointer--;
    }

    public int getValue() {
        return memory[pointer];
    }

    public void setValue(int value) {
        memory[pointer] = value;
    }

    public void incrementValue() {
        memory[pointer]++;
    }

    public void decrementValue() {
        memory[pointer]--;
    }

    public int getCurrent() {
        return current;
    }

    public void incrementCurrent() {
        current = source.indexOf("(", current + 1);
    }

    public void decrementCurrent() {
        current = source.substring(0, current).lastIndexOf("(");
    }

    public String getOp() {
        int q = source.indexOf(")", current);
        String op = source.substring(current, q + 1);
        return op;
    }

    public boolean hasOp() {
        boolean hasOp = current < sourceLength && current >= 0;
        return hasOp;
    }

    public InputStream getIn() {
        return in;
    }

    public void setIn(InputStream in) {
        this.in = in;
    }

    public PrintStream getOut() {
        return out;
    }

    public void setOut(PrintStream out) {
        this.out = out;
    }

    @Override
    public String toString() {
        return "BFEnvironment [memory=" + Arrays.toString(memory)
                + ", pointer=" + pointer + ", current=" + current + "]";
    }
}
