/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autopostsoicomputer.ictnew;

import autopostsoicomputer.base.Reader;
import autopostsoicomputer.base.model.ItemRss;
import autopostsoicomputer.base.model.PostObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author kiendt
 */
public class ReaderICTNEW extends Reader {

    public static final String URL = "http://vietnamnet.vn/rss/cong-nghe.rss";

    @Override
    public List<PostObject> parseContent() throws IOException {
        List<PostObject> listPostObject = new ArrayList<PostObject>();
        for (ItemRss item : parseSouce()) {
            PostObject object = new PostObject();
            Document doc = Jsoup.connect(item.getLink()).get();
            Element article = doc.select("html").first();
            Elements elements = article.getElementsByClass("ArticleDetail");
            Element title = elements.select("h1").first();
            Element content = article.getElementById("ArticleContent");
            // remove tag a
            for (Element aTag : content.getElementsByTag("a")) {
                aTag.remove();

            }
            object.setPostTitle(title.text());
            object.setPostImageFeature(item.getImage());
            object.setPostCategory("104");
            object.setPostContent(content.html());
            listPostObject.add(object);
        }
        return listPostObject;
    }

    public ReaderICTNEW() {
        setUrl(URL);
    }

}
