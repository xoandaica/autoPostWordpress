/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autopostsoicomputer.API;

import autopostsoicomputer.base.model.PostObject;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author kiendt
 */
public interface SoiComputerApi {

    void createNewPostWP(PostObject po, String strUrlWordpress) throws UnsupportedEncodingException, IOException;

    void getCategoryId(String strUrlWordpress, String slug);

    String generateImage(String strUrlWordpress, String imageUrl);

}
