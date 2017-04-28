/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autopostsoicomputer;

import autopostsoicomputer.API.WPApi;
import autopostsoicomputer.base.Reader;
import autopostsoicomputer.base.ReaderFactory;
import autopostsoicomputer.base.model.PostObject;
import autopostsoicomputer.ictnew.ReaderIctGame;
import autopostsoicomputer.ictnew.ReaderIctNew;
import autopostsoicomputer.itexpress.ReaderGenkVn;
import autopostsoicomputer.news.ReaderVietnamnet;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kiendt
 */
public class AutoPostSoiComputer {

    /**
     * @param args the command line arguments
     */
    public static final String URL_SOI_COMPUTER = "http://soicomputer.com/";
    public static final String URL_TEST = "http://localhost/wordpress/";

    public static void main(String[] args) {
        WPApi instance = new WPApi();
        ReaderFactory.getInstance().addReader(new ReaderIctGame());
//        ReaderFactory.getInstance().addReader(new ReaderIctNew());
//        ReaderFactory.getInstance().addReader(new ReaderVietnamnet());
//        ReaderFactory.getInstance().addReader(new ReaderGenkVn());

        for (Reader reader : ReaderFactory.getInstance().getListReader()) {
            List<PostObject> listPost = null;
            try {
                listPost = reader.parseContent();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (listPost != null) {
                for (PostObject object : listPost) {
                    try {
                        instance.createNewPostWP(object, URL_SOI_COMPUTER);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }

        //        ReaderFactory.getInstance().readFile();
//        instance.generateImage(URL_TEST + "generateImage.php", "http://image1.ictnews.vn/_Files/2017/04/27/cuc-tan-so.jpg");
//        Reader reader = new Reader();
//        List<PostObject> listPost = reader.parseContent();
//        WPApi instance = new WPApi();
//        for (PostObject object : listPost) {
//            instance.createNewPostWP(object, "http://soicomputer.com/createpost.php");
//        }
//        instance.getCategoryId(URL_SOI_COMPUTER, "thu-thuat-may-tinh");
    }

}
