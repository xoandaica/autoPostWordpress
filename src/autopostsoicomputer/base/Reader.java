/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autopostsoicomputer.base;

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
public abstract class Reader {

    protected String RSS_CNTT;

    public static final String RSS_TITLE = "title";
    public static final String RSS_DESCRIPTION = "description";
    public static final String RSS_LINK = "link";
    public static final String RSS_PUBDATE = "pubDate";
    public static final String RSS_IMAGE = "image";

    public Document readSource(String url) throws IOException {
        return Jsoup.connect(url).get();
    }

    protected List<ItemRss> parseSouce() throws IOException {
        List<ItemRss> listItem = new ArrayList<>();
        Document doc = readSource(RSS_CNTT);

        Element eData = doc.select("channel").first();
        Elements elements = eData.getElementsByTag("item");
        for (Element tmp : elements) {
            ItemRss item = new ItemRss(tmp.getElementsByTag(RSS_TITLE).text(),
                    tmp.getElementsByTag(RSS_DESCRIPTION).text(),
                    tmp.getElementsByTag(RSS_LINK).text(),
                    tmp.getElementsByTag(RSS_IMAGE).text());
            listItem.add(item);
        }

        return listItem;
    }

    public abstract List<PostObject> parseContent() throws IOException;

    protected void setUrl(String url) {
        this.RSS_CNTT = url;
    }

}