package net.fhegele.udeck.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtils {

    // Get class reference
    private static final StackWalker stackWalker = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);

    public static Logger getLogger() {
        return LoggerFactory.getLogger(stackWalker.getCallerClass());
    }
}
