/*
 *
 *
 * The MIT License (MIT)
 *
 * Copyright (c) 2020 Rasmus Olstedt
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation
 *  files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify,
 *  merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
 *  LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN
 *  NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 *  WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 *  SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package ScriptHandling;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class ScriptManager{

    private static Map<Integer,ShellScriptStore> shellScriptList; //QUESTION: Better to use ArrayList??

    public static ShellScriptStore addScript(ShellScriptStore newScript){
        return shellScriptList.put(newScript.hashCode(),newScript);
    }

    public static void changeName(ShellScriptStore script, Scanner keyboard){
        System.out.println("Current name: " + script.getName());
        System.out.print("Enter new name: ");
        String input = keyboard.next();
        System.out.println();
        script.setName(input); //TODO: Confirmation dialogue
    }

    public static ShellScriptStore getScript(String path){
        for(Integer i: shellScriptList.keySet()){
            if(i.hashCode() == Objects.hash(path)) { //QUESTION: is this right?
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

    public static ShellScriptStore newScript(Scanner keyboard){
        System.out.println("Enter script path");
        String input = keyboard.next();
        ShellScriptStore script = new ShellScriptStore(input);
        System.out.println("Script created at path: " + script.getPath());
        System.out.println("Do you want to change default values? [y/N]");
        input = keyboard.next();
        while(!(input.toLowerCase().equals("y")) && !(input.toLowerCase().equals("n"))){
            System.out.println("Please enter y or n... ");
            input = keyboard.next();
        }
        if ("y".equals(input.toLowerCase())) {
            modifyScript(script, keyboard);
        }
        return script;
    }

    public static void listScripts(){
        ArrayList<ShellScriptStore> values= new ArrayList<>(shellScriptList.values());
        for(int i=0;i<values.size();i++){
            System.out.println((i+1) + ". \n" + values.get(i));
        }
    }

    public static void modifyScript(ShellScriptStore script, Scanner keyboard) {
        changeName(script,keyboard);
        changeDescription(script,keyboard);
        changeDirectory(script,keyboard);
    }

    public static void changeDirectory(ShellScriptStore script, Scanner keyboard) {
        System.out.println("Current working directory: " + script.getDirectory());
        System.out.print("Enter new directory: ");
        String input=keyboard.next();
        System.out.println();
        script.setDirectory(input);//TODO: Add confirmation dialogue
    }

    public static void changeDescription(ShellScriptStore script, Scanner keyboard) {
        System.out.println("Current description:\n" + script.getDescription());
        System.out.print("Enter new description: ");
        String input=keyboard.next();
        System.out.println();
        script.setDescription(input);//TODO: Add confirmation dialogue
    }
}