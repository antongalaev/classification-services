package com.galaev.classification.util;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * This class
 *
 * @author Anton Galaev
 */
public class RenjinFactory {

    public static ScriptEngine getRenjin() {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("Renjin");

        if(engine == null) {
            throw new RuntimeException("Renjin Script Engine not found on the classpath.");
        }

        return engine;
    }
}
