package am.ik.brainfuck;

import static am.ik.brainfuck.OppaiBFProcessor.BACKWARD;
import static am.ik.brainfuck.OppaiBFProcessor.DECREMENT_POINTER;
import static am.ik.brainfuck.OppaiBFProcessor.DECREMENT_VALUE;
import static am.ik.brainfuck.OppaiBFProcessor.FORWARD;
import static am.ik.brainfuck.OppaiBFProcessor.INCREMENT_POINTER;
import static am.ik.brainfuck.OppaiBFProcessor.INCREMENT_VALUE;
import static am.ik.brainfuck.OppaiBFProcessor.READ_VALUE;
import static am.ik.brainfuck.OppaiBFProcessor.WRITE_VALUE;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class OppaiBFInterpreter {
    public static final int MEMORY_SIZE = 1024;
    private final OppaiBFEnvironment env;
    private final static Map<String, OppaiBFProcessor> PROCESSOR_MAP;

    static {
        PROCESSOR_MAP = new HashMap<String, OppaiBFProcessor>();
        PROCESSOR_MAP.put("( ・ )", INCREMENT_POINTER); // >
        PROCESSOR_MAP.put("(・)", DECREMENT_POINTER); // <
        PROCESSOR_MAP.put("( o )", INCREMENT_VALUE); // +
        PROCESSOR_MAP.put("(o)", DECREMENT_VALUE); // -
        PROCESSOR_MAP.put("( ● )", WRITE_VALUE); // .
        PROCESSOR_MAP.put("( @ )", READ_VALUE); // ,
        PROCESSOR_MAP.put("(q )", FORWARD); // [
        PROCESSOR_MAP.put("( p)", BACKWARD); // ]
    }

    public OppaiBFInterpreter(String source) {
        env = new OppaiBFEnvironment(source, MEMORY_SIZE);
    }

    public void eval() {
        while (env.hasOp()) {
            String op = env.getOp();
            if (PROCESSOR_MAP.containsKey(op)) {
                OppaiBFProcessor p = PROCESSOR_MAP.get(op);
                p.process(env);
            }
            env.incrementCurrent();
        }
    }

    public void setIn(InputStream in) {
        env.setIn(in);
    }

    public void setOut(PrintStream out) {
        env.setOut(out);
    }
}
