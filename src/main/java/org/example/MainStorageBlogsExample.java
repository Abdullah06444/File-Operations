package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.Arrays;

public class MainStorageBlogsExample {

    static String url = "jdbc:postgresql://localhost:5432/nodetexteditting";
    static Connection connection = null;

    static void baglan(){
        try {
            connection = DriverManager.getConnection(url, "postgres", "postgres");
            System.out.println("bağlandı " + connection);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    static ResultSet listele(String sorgu) throws SQLException {

        Statement st = connection.createStatement();
        ResultSet rs = null;
        //rs = st.executeQuery(sorgu);
        st.executeUpdate(sorgu);
        return rs;
    }

    static int max = 0;
    static String maxFile = "";
    static void RecursivePrint(File[] arr, int index, int level, String domain) throws IOException, SQLException, MalformedInputException {
        // terminate condition
        if (index == arr.length)
            return;

        // for files
        if (arr[index].isFile() && !arr[index].getName().equals(".DS_Store")) {
            //System.out.print(domain + "\t"); // alan adı index degeri
            //System.out.print((new File(arr[index].getParent())).getName() + "\t"); // alan adı
            //System.out.print(arr[index].getName() + "\t"); // ismi
            String content = Files.readString(Path.of(arr[index].getPath()));
            //System.out.println(content); // içeriği
            //System.out.println();

            content = content.replace("'","\\'");
            ResultSet rs = listele("Insert into blogsexamples (domain, textname, alltexts) " +
                    "values ('"+ domain +"',E'"+ arr[index].getName().replace("'","\\'") +"'," +
                    "E'" + content +"')");

            if(content.length() > max) {
                max = content.length();
                maxFile = arr[index].getName();
                System.out.println("domain :"+ domain + ", girdi max:" + max + ", " + arr[index].getName());
            }
        }
            // for sub-directories
        else if (arr[index].isDirectory()) {
            // System.out.println(arr[index].getName()); // alan adı

            String[] domainList = {"Genel","Aile","Akademik","Arkeoloji","Astroloji","Bilim","Bilim-Teknoloji",
                    "Eğitim","Eğlence","Ekonomi","Ev tasarımı","Gaming","Güzellik ve Moda","İlişkiler","İş ve Ticaret",
                    "Kişisel Gelişim","Lifestyle","Pazarlama","Politika","Pop Kültür","Psikoloji","Sağlık","Seyahat",
                    "Spor","Tasarım ve Geliştirme","Teknoloji","Workout Önerileri","Yemek-İçecek","Yerel haberler"};

            for(int i=0; i<domainList.length; i++){

                if(arr[index].getName().equals(domainList[i])){
                    domain = String.valueOf(i);
                    System.out.println("girdi domain " + domain);
                    break;
                }
            }

            String folder = arr[index].getName();
            // recursion for sub-directories
            if(domain.equals("14"))
                RecursivePrint(arr[index].listFiles(), 0,level + 1, domain);
        }

        // recursion for main directory
        RecursivePrint(arr, ++index, level, domain);
    }


    // Driver Method
    public static void main(String[] args) throws IOException, SQLException {

        baglan();
        // Provide full path for directory(change
        // accordingly)
        String maindirpath = "/Users/merveakcakaya/Library/CloudStorage/Dropbox/Blog/";

        // File object
        File maindir = new File(maindirpath);

        if (maindir.exists() && maindir.isDirectory()) {

            // array for files and sub-directories
            // of directory pointed by maindir
            File[] arr = maindir.listFiles();
            for (int i = 0; i < arr.length; i++) {
                if(arr[i].getName().equals("Eğitim"))
                    arr[i] = new File(maindirpath + "Egitim");
                if(arr[i].getName().equals("Eğlence"))
                    arr[i] = new File(maindirpath + "Eglence");
                if(arr[i].getName().equals("İş ve Ticaret"))
                    arr[i] = new File(maindirpath + "Iş ve Ticaret");
                if(arr[i].getName().equals("İlişkiler"))
                    arr[i] = new File(maindirpath + "Ilişkiler");
            }
            Arrays.sort(arr);
            for (int i = 0; i < arr.length; i++) {
                if(arr[i].getName().equals("Egitim"))
                    arr[i] = new File(maindirpath + "Eğitim");
                if(arr[i].getName().equals("Eglence"))
                    arr[i] = new File(maindirpath + "Eğlence");
                if(arr[i].getName().equals("Iş ve Ticaret"))
                    arr[i] = new File(maindirpath + "İş ve Ticaret");
                if(arr[i].getName().equals("Ilişkiler"))
                    arr[i] = new File(maindirpath + "İlişkiler");
            }

            // Calling recursive method
            RecursivePrint(arr, 0, 0, "-1");
        }

        System.out.println("maximum character size => " + max + "\nfile => " + maxFile);

//        ResultSet rs = listele("Select * from users;");
//
//        try {
//            while (rs.next()) {
//                System.out.print(rs.getString("name") + " ");
//                System.out.print(rs.getString("surname") + " ");
//                System.out.print(rs.getString("email") + " ");
//                System.out.print(rs.getArray("id") + " ");
//                System.out.println();
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

    }
}
