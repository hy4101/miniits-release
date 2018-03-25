package com.miniits.base.model.vo;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/3/24
 * @Time: 22:54
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
public class PageComponentAssociateVO extends BaseEntityVO {

    private ComponentImageVO componentImageVO;

    private ComponentImageVO componentImagePIdVO;

    private Integer sorts;

    public ComponentImageVO getComponentImageVO() {
        return componentImageVO;
    }

    public void setComponentImageVO(ComponentImageVO componentImageVO) {
        this.componentImageVO = componentImageVO;
    }

    public Integer getSorts() {
        return sorts;
    }

    public void setSorts(Integer sorts) {
        this.sorts = sorts;
    }

    public ComponentImageVO getComponentImagePIdVO() {
        return componentImagePIdVO;
    }

    public void setComponentImagePIdVO(ComponentImageVO componentImagePIdVO) {
        this.componentImagePIdVO = componentImagePIdVO;
    }
}
