/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autopostsoicomputer.news;

/**
 *
 * @author kiendt
 */
public enum DataVietNamNet {

    CONGNGHE("http://vietnamnet.vn/rss/cong-nghe.rss", "cong-nghe"),
    PHAPLUAT("http://vietnamnet.vn/rss/phap-luat.rss", "phap-luat"),
    KINHDOANH("http://vietnamnet.vn/rss/kinh-doanh.rss", "kinh-doanh"),
    GIAODUC("http://vietnamnet.vn/rss/giao-duc.rss", "giao-duc"),
    THOISU("http://vietnamnet.vn/rss/thoi-su.rss", "thoi-su"),
    GIAITRI("http://vietnamnet.vn/rss/giai-tri.rss", "giai-tri"),
    SUCKHOE("http://vietnamnet.vn/rss/suc-khoe.rss", "suc-khoe"),
    THETHAO("http://vietnamnet.vn/rss/the-thao.rss", "the-thao"),
    DOISONG("http://vietnamnet.vn/rss/doi-song.rss", "doi-song"),
    BATDONGSAN("http://vietnamnet.vn/rss/bat-dong-san.rss", "bat-dong-san"),
    THEGIOI("http://vietnamnet.vn/rss/the-gioi.rss", "the-gioi"),
    BANDOC("http://vietnamnet.vn/rss/ban-doc.rss", "ban-doc"),
    TINMOINONG("http://vietnamnet.vn/rss/tin-moi-nong.rss", "tin-moi-nong"),
    TINNOIBAT("http://vietnamnet.vn/rss/tin-noi-bat.rss", "tin-noi-bat"),
    TUANVIETNAM("http://vietnamnet.vn/rss/tuanvietnam.rss", "tuan-viet-nam"),
    GOCNHINTHANG("http://vietnamnet.vn/rss/goc-nhin-thang.rss", "goc-nhin-thang");

    private String linkRss;
    private String slug;

    DataVietNamNet(String linkRss, String slug) {
        this.linkRss = linkRss;
        this.slug = slug;
    }

    public String getLinkRss() {
        return linkRss;
    }

    public String getSlug() {
        return slug;
    }

}
