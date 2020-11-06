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
 *
 */

package Menu;

import ScriptHandling.ScriptExecutor;
import ScriptHandling.ScriptManager;
import ScriptHandling.ShellScript;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public abstract class MenuAction extends UI {

    private final String name;

    protected MenuAction(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

class ListScriptsAction extends MenuAction{

    protected ListScriptsAction() {
        super("List Scripts");
    }

    @Override
    public void run() throws IOException, InterruptedException {
        ArrayList<ShellScript> values = ScriptManager.listScripts();
        System.out.println("Run Script:");
        int input=MenuItem.keyboard.nextInt();
        if(input==-1){
            return;
        }
        ScriptExecutor.runScript(values.get(input - 1));

    }
}

class ExecuteScriptAction extends MenuAction{

    protected ExecuteScriptAction() {
        super("Execute Script");
    }

    @Override
    public void run() throws IOException, InterruptedException {
        System.out.println("Enter path to script to execute:");
        String input = MenuItem.keyboard.next();
        ScriptExecutor.runScript(Objects.requireNonNull(ScriptManager.getScript(input)));
    }
}

class AddScriptObjectAction extends MenuAction{

    protected AddScriptObjectAction() {
        super("Add Script");
    }

    @Override
    void run() throws IOException, InterruptedException {
        ShellScript script = ScriptManager.newScript(MenuItem.keyboard);
        ScriptManager.addScript(script);
    }
}
