/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autopostsoicomputer.base;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kiendt
 */
public class ReaderFactory {

    public static ReaderFactory _instance;

    private List<Reader> listReader;
    private static final String FILE_NAME = "data.txt";
    private List<String> listTitlePosted;

    public static synchronized ReaderFactory getInstance() {
        if (_instance == null) {
            _instance = new ReaderFactory();
        }
        return _instance;
    }

    public ReaderFactory() {
        listReader = new ArrayList<>();
        listTitlePosted = new ArrayList<>();
        listTitlePosted = readFile();
    }

    public void addReader(Reader reader) {
        listReader.add(reader);
    }

    public List<Reader> getListReader() {
        return listReader;
    }

    public List<String> readFile() {
        File file = new File("data.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        try {

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(FILE_NAME), "UTF8"));

            String str;

            while ((str = in.readLine()) != null) {
                listTitlePosted.add(str);
            }
            in.close();
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listTitlePosted;
    }

    public void writeFile(String content) {
        try {
            Writer out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(FILE_NAME, true), "UTF8"));
            out.append(content);
            out.append("\r\n");
            out.flush();
            out.close();

        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
//        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
//            bw.append(content);
//            bw.newLine();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public List<String> getListTitlePosted() {
        return listTitlePosted;
    }

    public void setListTitlePosted(List<String> listTitlePosted) {
        this.listTitlePosted = listTitlePosted;
    }

}
