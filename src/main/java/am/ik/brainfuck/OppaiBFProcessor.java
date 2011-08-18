package am.ik.brainfuck;

import java.io.IOException;

public enum OppaiBFProcessor {
    /**
     * &gt;
     */
    INCREMENT_POINTER {
        @Override
        public void process(OppaiBFEnvironment env) {
            env.incrementPointer();
        }
    },
    /**
     * &lt;
     */
    DECREMENT_POINTER {
        @Override
        public void process(OppaiBFEnvironment env) {
            env.decrementPointer();
        }
    },
    /**
     * +
     */
    INCREMENT_VALUE {
        @Override
        public void process(OppaiBFEnvironment env) {
            env.incrementValue();
        }
    },
    /**
     * -
     */
    DECREMENT_VALUE {
        @Override
        public void process(OppaiBFEnvironment env) {
            env.decrementValue();
        }
    },
    /**
     * .
     */
    WRITE_VALUE {
        @Override
        public void process(OppaiBFEnvironment env) {
            env.getOut().print((char) env.getValue());
        }
    },
    /**
     * ,
     */
    READ_VALUE {
        @Override
        public void process(OppaiBFEnvironment env) {
            try {
                env.setValue(env.getIn().read());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    },
    /**
     * [
     */
    FORWARD {
        @Override
        public void process(OppaiBFEnvironment env) {
            if (env.getValue() == 0) {
                env.incrementCurrent();
                for (int brackets = 1; brackets > 0; env.incrementCurrent()) {
                    String op = env.getOp();
                    if ("(q )".equals(op)) {
                        // [
                        brackets++;
                    } else if ("( p)".equals(op)) {
                        // ]
                        brackets--;
                    }
                }
                env.decrementCurrent();
            }
        }
    },
    /**
     * ]
     */
    BACKWARD {
        @Override
        public void process(OppaiBFEnvironment env) {
            if (env.getValue() != 0) {
                env.decrementCurrent();
                for (int brackets = 1; brackets > 0; env.decrementCurrent()) {
                    String op = env.getOp();
                    if ("(q )".equals(op)) {
                        // [
                        brackets--;
                    } else if ("( p)".equals(op)) {
                        // ]
                        brackets++;
                    }
                }
                env.incrementCurrent();
            }
        }
    };

    abstract public void process(OppaiBFEnvironment env);
}
