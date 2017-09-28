package clem.www.tbsradio.model;

import java.util.List;

/**
 * Created by laileon on 2017/9/28.
 */

public class BangumiArchive {
    private String title;
    private String detail;
    private String imgUrl;
    private String day;
    private List<RadioItem> mRadioItems;

    @Override
    public String toString() {
        return "BangumiArchive{" +
                "title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", day='" + day + '\'' +
                ", mRadioItems=" + mRadioItems +
                '}';
    }

    public List<RadioItem> getRadioItems() {
        return mRadioItems;
    }

    public void setRadioItems(List<RadioItem> radioItems) {
        mRadioItems = radioItems;
    }

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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public static class RadioItem {
        private String date;
        private String expireDate;
        private String title;
        private String detail;
        private String radioUrl;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getExpireDate() {
            return expireDate;
        }

        public void setExpireDate(String expireDate) {
            this.expireDate = expireDate;
        }

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

        public String getRadioUrl() {
            return radioUrl;
        }

        public void setRadioUrl(String radioUrl) {
            this.radioUrl = radioUrl;
        }

        @Override
        public String toString() {
            return "RadioItem{" +
                    "date='" + date + '\'' +
                    ", expireDate='" + expireDate + '\'' +
                    ", title='" + title + '\'' +
                    ", detail='" + detail + '\'' +
                    ", radioUrl='" + radioUrl + '\'' +
                    '}';
        }
    }

}
