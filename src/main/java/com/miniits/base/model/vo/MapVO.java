package com.miniits.base.model.vo;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/6/20
 * @Time: 22:53
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
public class MapVO {

    private String name;

    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public MapVO() {
    }

    public MapVO(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
