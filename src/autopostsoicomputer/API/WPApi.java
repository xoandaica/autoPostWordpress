/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autopostsoicomputer.API;

import autopostsoicomputer.base.model.PostObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

/**
 *
 * @author Admin
 */
public class WPApi implements SoiComputerApi {

    int count_getPost = 0;
    int maxTries_getPost = 1;

    public static final String USER = "kiendt";
    public static final String PASS = "123";

    @Override
    public void createNewPostWP(PostObject po, String strUrlWordpress) throws UnsupportedEncodingException, IOException {

        for (;;) {
            try {
                HttpClient client = new DefaultHttpClient();

                ////// Get Variable
                String strPostTitle = po.getPostTitle();
                String strPostImageFeature = po.getPostImageFeature();
                String strPostCategory = po.getPostCategory();
                String strPostContent = po.getPostContent();
                //////
                HttpPost post = new HttpPost(strUrlWordpress);

                // add header
                post.setHeader("User-Agent", "PosterBotDefault");

                List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
                urlParameters.add(new BasicNameValuePair("user", USER));
                urlParameters.add(new BasicNameValuePair("pass", PASS));
                ///
                urlParameters.add(new BasicNameValuePair("post_title", strPostTitle));
                urlParameters.add(new BasicNameValuePair("cat", strPostCategory));
                urlParameters.add(new BasicNameValuePair("post_content", strPostContent));
                urlParameters.add(new BasicNameValuePair("image_future", strPostImageFeature));

                post.setEntity(new UrlEncodedFormEntity(urlParameters, "UTF-8"));
                HttpResponse response = client.execute(post);
                System.out.println("\nSending 'POST' request to URL : " + strUrlWordpress);
                System.out.println("Post parameters : " + post.getEntity());
                System.out.println("Response Code : "
                        + response.getStatusLine().getStatusCode());

                BufferedReader rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));

                StringBuffer result = new StringBuffer();
                String line = "";
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }

                System.out.println(result.toString());
            } catch (Exception ex) {
                System.out.println("ERROR CREATE POST : " + ex.toString());
                count_getPost++;
                if (count_getPost == maxTries_getPost) {
                    System.out.println("I TRY CREATE POST TO!!! " + strUrlWordpress);
                    count_getPost = 0;
                    break;
                } else {
                    System.out.println("TRY TIMES  : " + count_getPost);
                    createNewPostWP(po, strUrlWordpress);
                }
            } finally {
                break;
            }
        }
    }

    @Override
    public void getCategoryId(String strUrlWordpress, String slug) {
        try {
            HttpClient client = new DefaultHttpClient();
            //////
            HttpPost post = new HttpPost(strUrlWordpress);

            // add header
            post.setHeader("User-Agent", "PosterBotDefault");

            List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
            urlParameters.add(new BasicNameValuePair("user", USER));
            urlParameters.add(new BasicNameValuePair("pass", PASS));
            ///
            urlParameters.add(new BasicNameValuePair("slug", slug));

            post.setEntity(new UrlEncodedFormEntity(urlParameters, "UTF-8"));
            HttpResponse response = client.execute(post);
            System.out.println("\nSending 'POST' request to URL : " + strUrlWordpress);
            System.out.println("Post parameters : " + post.getEntity());
            System.out.println("Response Code : "
                    + response.getStatusLine().getStatusCode());

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            System.out.println(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String generateImage(String strUrlWordpress, String imageUrl) {
        try {
            HttpClient client = new DefaultHttpClient();
            //////
            HttpPost post = new HttpPost(strUrlWordpress);
            // add header
            post.setHeader("User-Agent", "PosterBotDefault");
            List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
            urlParameters.add(new BasicNameValuePair("user", USER));
            urlParameters.add(new BasicNameValuePair("pass", PASS));
            urlParameters.add(new BasicNameValuePair("image_url", imageUrl));
            post.setEntity(new UrlEncodedFormEntity(urlParameters, "UTF-8"));
            HttpResponse response = client.execute(post);
            System.out.println("\nSending 'POST' request to URL : " + strUrlWordpress);
            System.out.println("Post parameters : " + post.getEntity());
            System.out.println("Response Code : "
                    + response.getStatusLine().getStatusCode());
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));
            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
