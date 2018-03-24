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

    private ComponentVO componentVO;

    private Integer sorts;

    public ComponentVO getComponentVO() {
        return componentVO;
    }

    public void setComponentVO(ComponentVO componentVO) {
        this.componentVO = componentVO;
    }

    public Integer getSorts() {
        return sorts;
    }

    public void setSorts(Integer sorts) {
        this.sorts = sorts;
    }
}
