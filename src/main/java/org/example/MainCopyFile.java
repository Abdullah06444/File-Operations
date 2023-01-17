package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class MainCopyFile {

    public static void main(String[] args) throws IOException {

        String fileName = "aile_root_ngram.txt";
        //File src = new File("../hierarchyannotation/src/main/resources/" +
        //        fileName);
        File src = new File("/Users/merveakcakaya/Library/CloudStorage/Dropbox/" +
                //"Product Annotation/Program/" +
                "Blogs/Aile/Program/" +
                fileName);
        //File src = new File("../../xxxMyAngularProjectsxxx/xxxWebStormxxx/" +
        //        "SearchBox-TextEditting/node_modules/nlptoolkit-deasciifier/" +
        //        fileName);
        File dest = new File("../../xxxMyAngularProjectsxxx/" +
                //"typescriptdeneme/" +
                "xxxWebStormxxx/" +
                "StarlangTextEditting/" +
                fileName);
//        File dest0 = new File("../../xxxMyAngularProjectsxxx/" +
//                //"typescriptdeneme/" +
//                "xxxWebStormxxx/" +
//                "SearchBox-TextEditting/" +
//                fileName);

        //File dest = new File("../../xxxMyCodesxxx/ngramspellcheckanalysis/src/main/resources/"+ fileName);

        Files.copy(src.toPath(), dest.toPath());
    }
}
