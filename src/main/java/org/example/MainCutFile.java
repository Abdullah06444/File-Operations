package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainCutFile {

    public static void main(String[] args) throws IOException {

        String fileName = "turkish_dictionary_tdk.txt";
        String src = "../ngramspellcheckanalysis/src/main/resources/";
        String dest = "../resources/";

        Path temp = Files.move(Paths.get(src + fileName),Paths.get(dest + fileName));

        if(temp != null)
        {
            System.out.println("File renamed and moved successfully");
        }
        else
        {
            System.out.println("Failed to move the file");
        }
    }
}
