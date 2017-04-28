/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autopostsoicomputer.itexpress;

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
import org.jsoup.select.Elements;

/**
 *
 * @author kiendt
 */
public class ReaderGenkVn extends Reader {

    public static final String BASE_URL = "http://genk.vn";
    public static final String URL = "http://genk.vn/thu-thuat.chn";

    @Override
    public List<PostObject> parseContent() throws IOException {
        List<PostObject> listPostObject = new ArrayList<PostObject>();
        for (ItemRss item : parseSouce()) {
            PostObject object = new PostObject();
            Document doc = Jsoup.connect(item.getLink()).get();
            Element article = doc.select("html").first();
            String title = item.getTitle();
            Element content = article.getElementById("ContentDetail");
            Element lastLink = content.getElementsByClass("VCSortableInPreviewMode link-content-footer").first();
            lastLink.remove();
            // remove tag a
            for (Element aTag : content.getElementsByTag("a")) {
                aTag.remove();
            }
            for (Element aTag : content.getElementsByTag("p")) {
                aTag.attr("style", "color: #000000;");
            }
            content = attrachImage(content);
//            System.out.println(content);
            object.setPostTitle(title);
            object.setPostImageFeature(item.getImage());
            object.setPostCategory(String.valueOf(ICATEGORY.CATEGORY_THU_THUAT));
            object.setPostContent(content.html());
            listPostObject.add(object);
        }
        return listPostObject;
    }

    @Override
    protected List<ItemRss> parseSouce() throws IOException {
        List<ItemRss> listItem = new ArrayList<>();
        Document doc = readSource(RSS_CNTT);

        Element eData = doc.select("html").first();
        Element elements = eData.getElementsByClass("kds-new-stream-wrapper").first();
        Elements listItemRss = elements.getElementsByClass("knswli clearfix ");
        for (Element tmp : listItemRss) {
            String title = tmp.getElementsByTag("a").attr("title");
            String description = title;
            String img = tmp.getElementsByTag("img").attr("src");
            String link = BASE_URL + tmp.getElementsByTag("a").attr("href");

            ItemRss item = new ItemRss(title,
                    description,
                    link,
                    img);
            listItem.add(item);
        }
        return listItem;
    }

    public ReaderGenkVn() {
        setUrl(URL);
    }

    public static void main(String[] args) throws IOException {
        ReaderGenkVn read = new ReaderGenkVn();
        read.parseSouce();
    }

}
