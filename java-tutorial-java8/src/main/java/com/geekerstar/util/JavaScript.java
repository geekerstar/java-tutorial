package com.geekerstar.util;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author geekerstar
 * date: 2019/11/17 23:21
 * description:
 */
public class JavaScript {
    public static void main(String[] args) throws ScriptException {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine javascript = scriptEngineManager.getEngineByName("JavaScript");

        System.out.println(javascript.getClass().getName());
        System.out.println("Result:"+javascript.eval("function f() { return 10; }; f() * 24;"));
    }

}
