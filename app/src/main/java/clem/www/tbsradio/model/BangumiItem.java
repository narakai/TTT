package clem.www.tbsradio.model;

/**
 * Created by laileon on 2017/9/28.
 */

public class BangumiItem {
    //    RadioItem{title='川村亜未 午前1時のシンデレラ', detail='2011年のTBSラジオパーソナリティ公募企画で優勝したズブの素人。シンデ……', url='https://radiocloud.jp/archive/am1', imgUrl='https://s3-ap-northeast-1.amazonaws.com/otonari%2Ftmp/program_icon_pc_2.jpg'}
    private String title;
    private String detail;
    private String url;
    private String imgUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getBangumiName() {
        String name = "";
        int index = url.lastIndexOf("/");
        if (index != -1) {
            name = url.substring(index + 1, url.length());
        }
        return name;
    }

    @Override
    public String toString() {
        return "BangumiItem{" +
                "title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                ", url='" + url + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
