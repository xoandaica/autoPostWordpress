/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autopostsoicomputer;

import autopostsoicomputer.API.WPApi;
import autopostsoicomputer.base.Reader;
import autopostsoicomputer.base.ReaderFactory;
import autopostsoicomputer.base.model.ItemRss;
import autopostsoicomputer.base.model.PostObject;
import autopostsoicomputer.ictnew.ReaderICTNEW;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public static void main(String[] args) throws IOException {
        WPApi instance = new WPApi();
        ReaderFactory.getInstance().addReader(new ReaderICTNEW());

//        for (Reader reader : ReaderFactory.getInstance().getListReader()) {
//            List<PostObject> listPost = reader.parseContent();
//            for (PostObject object : listPost) {
//                instance.createNewPostWP(object, URL_SOI_COMPUTER + "createpost.php");
//            }
//        }
        instance.generateImage(URL_TEST + "generateImage.php", "http://image1.ictnews.vn/_Files/2017/04/27/cuc-tan-so.jpg");

//        Reader reader = new Reader();
//        List<PostObject> listPost = reader.parseContent();
//        WPApi instance = new WPApi();
//        for (PostObject object : listPost) {
//            instance.createNewPostWP(object, "http://soicomputer.com/createpost.php");
//        }
//        instance.getCategoryId(URL_SOI_COMPUTER + "soi_getCategory.php", "tin-cong-nghe");
    }

}
