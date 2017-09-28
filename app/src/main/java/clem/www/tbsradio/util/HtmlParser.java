package clem.www.tbsradio.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import clem.www.tbsradio.model.BangumiArchive;
import clem.www.tbsradio.model.BangumiItem;

/**
 * Created by laileon on 2017/9/28.
 */

public class HtmlParser {

    public static final String HTML = "http://d2zpt661x1y9iz.cloudfront.net/www.spiral-pf.com/music/";

    public static List<BangumiItem> getBangumiItem(String html) {
        List<BangumiItem> bangumiItems = new ArrayList<>();
        Document doc = Jsoup.parse(html);
        Elements units = doc.getElementsByClass("program_box");
        for (int i = 0; i < units.size(); i++) {
            BangumiItem item = new BangumiItem();

            Element element = units.get(i);
            String title = element.getElementsByTag("h4").get(0).text();
            Elements links = element.getElementsByTag("a");
            String url = links.get(0).attr("href");
            String imgUrl = links.get(0).getElementsByTag("img").attr("src");
            String detail = element.getElementsByClass("program_box_detail").get(0).text();

            item.setDetail(detail);
            item.setImgUrl(imgUrl);
            item.setTitle(title);
            item.setUrl(url);

//            System.out.println(item.toString());
            bangumiItems.add(item);
        }
        return bangumiItems;
    }

    public static BangumiArchive getBangumiArchive(String html) {
        BangumiArchive bangumiArchive = new BangumiArchive();
        List<BangumiArchive.RadioItem> bangumiItems = new ArrayList<>();
        Document doc = Jsoup.parse(html);
        Element imgElement = doc.getElementsByClass("program_img-clip").get(0);
        String imgUrl = imgElement.getElementsByTag("img").attr("src");

        Element element = doc.getElementsByClass("program_info").get(0);
        String title = element.getElementsByTag("h2").get(0).text();
        String day = element.getElementsByClass("day").get(0).text();
        String detail = element.getElementsByClass("program_text").get(0).text();

        bangumiArchive.setImgUrl(imgUrl);
        bangumiArchive.setTitle(title);
        bangumiArchive.setDay(day);
        bangumiArchive.setDetail(detail);

//        System.out.println(bangumiArchive.toString());

        Elements units = doc.getElementsByClass("contents_box");
        for (int i = 0; i < units.size(); i++) {
            BangumiArchive.RadioItem radioItem = new BangumiArchive.RadioItem();
            Element element1 = units.get(i);
            String date = element1.getElementsByTag("dt").get(0).text();
            String expireDate = element1.getElementsByClass("end_date").get(0).text();
            String title1 = element1.getElementsByTag("span").get(0).text();
            String detail1 = element1.getElementsByTag("div").get(0).text();
            String radioUrl = element1.getElementsByTag("input").get(1).attr("value");

            radioItem.setDetail(detail1);
            radioItem.setTitle(title1);
            radioItem.setDate(date);
            radioItem.setExpireDate(expireDate);
            radioItem.setRadioUrl(radioUrl.substring(2, radioUrl.length()));

            bangumiItems.add(radioItem);
//            System.out.println(radioItem.toString());
        }

        bangumiArchive.setRadioItems(bangumiItems);
        return bangumiArchive;
    }
}
