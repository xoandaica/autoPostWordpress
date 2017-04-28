/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autopostsoicomputer.ictnew;

import autopostsoicomputer.API.WPApi;
import autopostsoicomputer.base.ICATEGORY;
import autopostsoicomputer.base.Reader;
import autopostsoicomputer.base.model.ItemRss;
import autopostsoicomputer.base.model.PostObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author kiendt
 */
public class ReaderIctGame extends Reader implements ICATEGORY {

    public static final String URL = "http://ictnews.vn/rss/game";
    WPApi api = new WPApi();

    @Override
    public List<PostObject> parseContent() throws IOException {
        List<PostObject> listPostObject = new ArrayList<PostObject>();
        for (ItemRss item : parseSouce()) {
            PostObject object = new PostObject();
            Document doc = Jsoup.connect(item.getLink()).get();
            Element article = doc.select("html").first();
            Element elements = article.getElementById("page-wraper");
            Element title = elements.getElementsByClass("news-title").first();
            Element content = elements.getElementsByClass("maincontent").first().getElementsByClass("content-detail").first();
            // remove tag a
            for (Element aTag : content.getElementsByTag("a")) {
                aTag.remove();
            }
            for (Element aTag : content.getElementsByTag("script")) {
                aTag.remove();
            }

            Element alright = elements.getElementsByClass("maincontent").first().getElementsByAttributeValue("align", "right").first();
            content = attrachImage(content);
            
            // parse descriptiton to get image description
            int posSrc = item.getDescription().indexOf("src=\"") + 5;
            int desSrc = item.getDescription().indexOf("\" width");
            String url = item.getDescription().substring(posSrc, desSrc);
            for (Element el : content.getElementsByTag("p")) {
                el.attr("style", "color: #000000;");
            }
            object.setPostTitle(title.text());
            object.setPostImageFeature(url);
            object.setPostCategory(String.valueOf(CATEGORY_TIN_GAME));
            object.setPostContent(content.html() + alright.html());
            listPostObject.add(object);
        }
        return listPostObject;
    }

    public ReaderIctGame() {
        setUrl(URL);
    }

}
