package com.lvyj001.doclet;

import java.io.IOException;
import java.util.List;

import static com.lvyj001.listfile.ListProjectFile.initFileList;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> lists = initFileList();
        for (String list : lists) {
            com.sun.tools.javadoc.Main.execute(new String[]{"-doclet", MyDoclet.class.getName(), "-encoding", "utf-8", "@" + list});
        }
    }
}
