package com.catalyst.interviews.commons;

/**
 * Helper functions for logging.
 */
public class Logging {

    /**
     * Logs an object as a side-effect.
     * @returns the object itself for further use
     */
    public static <T> T logged(T thing, Object logging_originator_object, String label) {
        return logged(thing, logging_originator_object.getClass(), label);
    }

    private static <T> T logged(T thing, Class<?> logging_originator_class, String label) {
        // VT-100 escape codes nowadays also work on Windows:
        System.err.println("[" + logging_originator_class.getName() +  "]" + "\u001b[7m" + label + ":\u001b[0m " + thing + "");
        return thing;
    }
}
