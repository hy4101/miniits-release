package com.miniits.base.service;

import com.miniits.base.dao.ArticleRepository;
import com.miniits.base.model.entity.Article;
import com.miniits.base.mysql.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.miniits.base.utils.SystemDict.ARTICLES_STATUS_ENABLE;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/3/28
 * @Time: 23:50
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
@Service
@Transactional
public class ArticleServer extends BaseServiceImpl<Article, String> {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    public void setBaseDao(ArticleRepository articleRepository) {
        super.setBaseDao(articleRepository);
    }

    public void changeStatus(String id, Integer status) {
        articleRepository.changeStatus(id, status, status.equals(ARTICLES_STATUS_ENABLE) ? "显示" : "隐藏");
    }

    public List<Object> counts(String filters) {
        return articleRepository.count(filters);
    }

}
