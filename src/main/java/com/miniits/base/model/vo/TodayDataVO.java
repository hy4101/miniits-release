package com.miniits.base.model.vo;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/6/19
 * @Time: 23:46
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
public class TodayDataVO {

    private long article;

    private long image;

    private long user;

    public long getArticle() {
        return article;
    }

    public void setArticle(long article) {
        this.article = article;
    }

    public long getImage() {
        return image;
    }

    public void setImage(long image) {
        this.image = image;
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    public TodayDataVO() {
    }

    public TodayDataVO(long article, long image, long user) {
        this.article = article;
        this.image = image;
        this.user = user;
    }
}
