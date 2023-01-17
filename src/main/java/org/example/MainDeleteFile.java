package org.example;

import java.io.File;

public class MainDeleteFile {
    public static void main(String[] args) {

        String fileName = "cors";
        File file = new File("../../xxxMyAngularProjectsxxx/xxxWebStormxxx/" +
                "StarlangSearchBox/node_modules/" +
                fileName);
        file.delete();
    }
}