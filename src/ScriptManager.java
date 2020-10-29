/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020 Rasmus Olstedt
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import java.io.ObjectStreamClass;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class ScriptManager{

    private static Map<Integer,ShellScriptStore> shellScriptList;

    public static ShellScriptStore addScript(ShellScriptStore newScript){
        return shellScriptList.put(newScript.hashCode(),newScript);
    }

    public static void changeName(ShellScriptStore script, Scanner keyboard){
        System.out.println("Current name: " + script.getName());
        System.out.print("Enter new name: ");
        String input = keyboard.next();
        script.setName(input); //TODO: Confirmation dialogue
    }

    public static ShellScriptStore getScript(String path){
        for(Integer i: shellScriptList.keySet()){
            if(i.hashCode()== Objects.hash(path)) {
                return shellScriptList.get(i);
            }
        }
        return null;
    }

    public static ShellScriptStore findScript(String name){
        for(ShellScriptStore i: shellScriptList.values()){
            if(i.getName().equals(name)){
                return i;
            }
        }
        return null;
    }

    public static boolean removeScript(ShellScriptStore script){
        return shellScriptList.remove(script.hashCode(),script);
    }
}