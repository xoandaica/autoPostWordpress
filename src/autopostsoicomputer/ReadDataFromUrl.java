/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autopostsoicomputer;

import autopostsoicomputer.base.model.PostObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Admin
 */
public class ReadDataFromUrl {

    int count_getPost = 0;
    int maxTries_getPost = 1;

    public List<PostObject> readDataFromUrl(String strUrl, String strCategory) throws IOException, InterruptedException {
        List<PostObject> lpo = new ArrayList<PostObject>();
        for (;;) {
            try {
                Document doc = Jsoup.connect(strUrl).get();
                //Only Have One Then Get First
                
                Element eData = doc.select("div.ui-slider-tabs").first();
                //Get List
                Elements ePost = eData.select("a[href]");
                //For each
                for (int index = 0; index < ePost.size(); index++) {
                    Element link = ePost.get(index);
                    if (link.toString().contains("http://www.daikynguyenvn.com/")
                            && link.toString().contains("src") && link.toString().contains(".html")) {

                        List<String> strGetPost = getContent(link.attr("href"));

                        String strTitle = strGetPost.get(0);
                        String strImage = strGetPost.get(1);
                        String strContent = strGetPost.get(2);

                        PostObject po = new PostObject();
                        po.setPostTitle(strTitle);
                        po.setPostCategory(strCategory);
                        po.setPostContent(strContent);
                        po.setPostImageFeature(strImage);
                        lpo.add(po);
                    }
                }

                break;
            } catch (Exception ex) {
                System.out.println("ERROR GET POST : " + ex.toString());
                count_getPost++;
                ///
                Thread.sleep(1000l);
                ///
                if (count_getPost == maxTries_getPost) {
                    System.out.println("I TRY MY POST !!! " + strUrl);
                    count_getPost = 0;
                    break;
                } else {
                    System.out.println("TRY TIMES  : " + count_getPost);
                    readDataFromUrl(strUrl, strCategory);
                }
            } finally {
                break;
            }
        }
        return lpo;
    }

    int count_getcontent = 0;
    int maxTries_getcontent = 1;

    public List<String> getContent(String strUrl) throws IOException, InterruptedException {
        List<String> strContent = new ArrayList<>();
        for (;;) {
            try {
                Document doc = Jsoup.connect(strUrl).get();

                String strTitle = doc.title();
                String strImageUrl = "";
                Element metaOgImage = doc.select("meta[property=og:image]").first();
                if (metaOgImage != null) {
                    strImageUrl = metaOgImage.attr("content");
                }
                Element eContent = doc.select("div#the-post-content").first();
                eContent.select("div.insert-post-ads").remove();
                eContent.select("ul").remove();

                String strContent_Fulls = "<meta charset=\"UTF-8\">" + eContent.html().replace("Xem thêm:", "");

                strContent.add(strTitle);
                strContent.add(strImageUrl);
                strContent.add(strContent_Fulls);

                break;
            } catch (Exception ex) {
                System.out.println("ERROR GET CONTENT : " + ex.toString());
                count_getcontent++;
                ///
                Thread.sleep(1000l);
                ///
                if (count_getcontent == maxTries_getcontent) {
                    System.out.println("I TRY MY BEST CONTENT!!! " + strUrl);
                    count_getcontent = 0;
                    break;
                } else {
                    System.out.println("TRY TIMES : " + count_getcontent);
                    getContent(strUrl);
                }
            } finally {
                break;
            }
        }

        return strContent;
    }

    public static List<String> readFromFile(String strFileName) {
        List<String> lstReturn = new ArrayList<String>();
        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader(strFileName);
            br = new BufferedReader(fr);
            String sCurrentLine;
            br = new BufferedReader(new FileReader(strFileName));
            int i = 0;
            while ((sCurrentLine = br.readLine()) != null) {
//                System.out.println(sCurrentLine);
//                strReturn[i] = sCurrentLine;
                lstReturn.add(sCurrentLine);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return lstReturn;
    }

    public static void writeFile(String strUrl, List<String> lstLastPost) throws IOException {
        File fout = new File(strUrl);
        FileOutputStream fos = new FileOutputStream(fout);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        for (int i = 0; i < lstLastPost.size(); i++) {
            bw.write(lstLastPost.get(i));
            bw.newLine();
        }

        bw.close();
    }
}
