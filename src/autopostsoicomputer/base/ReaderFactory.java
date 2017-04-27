/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autopostsoicomputer.base;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kiendt
 */
public class ReaderFactory {

    public static ReaderFactory _instance;

    private List<Reader> listReader;

    public static synchronized ReaderFactory getInstance() {
        if (_instance == null) {
            _instance = new ReaderFactory();
        }
        return _instance;
    }

    public ReaderFactory() {
        listReader = new ArrayList<>();
    }

    public void addReader(Reader reader) {
        listReader.add(reader);
    }

    public List<Reader> getListReader() {
        return listReader;
    }

}
