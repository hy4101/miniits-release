package com.miniits.base.utils;

import com.miniits.base.model.entity.ComponentImage;
import org.jsoup.nodes.Document;
import org.springframework.ui.ModelMap;

import java.util.List;

/**
 * @author: wq
 * @Date: 2018/4/11
 * @Time: 14:28
 * <p>
 * Description:
 * ***
 */
public class ComponentImageAndDocument {

    private Document document;

    private ModelMap modelMap;

    private List<ComponentImage> componentImage;

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public List<ComponentImage> getComponentImage() {
        return componentImage;
    }

    public void setComponentImage(List<ComponentImage> componentImage) {
        this.componentImage = componentImage;
    }

    public ModelMap getModelMap() {
        return modelMap;
    }

    public void setModelMap(ModelMap modelMap) {
        this.modelMap = modelMap;
    }

    public ComponentImageAndDocument() {
    }

    public ComponentImageAndDocument(Document document, ModelMap modelMap, List<ComponentImage> componentImage) {
        this.document = document;
        this.modelMap = modelMap;
        this.componentImage = componentImage;
    }
}
