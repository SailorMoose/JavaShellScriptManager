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

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Objects;

public class ShellScriptStore implements Serializable {

    private String path;
    private String description;
    private String name;
    private String directory;

    /**
     * @param path Full path to script to store
     */
    ShellScriptStore(@NotNull String path){
        this.path=path;

        // Use the position of the last slash to detect default working directory and name
        int lastSlash=path.lastIndexOf('/');
        if(lastSlash!=-1) {
            this.name = path
                    .substring(lastSlash + 1)
                    .trim();
            this.directory = path
                    .substring(0, lastSlash)
                    .trim();
        }

        else{
            this.name=path;
            this.directory="$HOME";
        }
        this.description=null;
    }

    /**
     * @param original The object to make a copy of
     */
    ShellScriptStore(ShellScriptStore original){
        this.name=original.name;
        this.description=original.description;
        this.directory=original.directory;
        this.path=original.path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    @Override
    public String toString() {
        return "ShellScriptStore{" +
                "path='" + path + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", directory='" + directory + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShellScriptStore that = (ShellScriptStore) o;
        return getPath().equals(that.getPath());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPath());
    }
}