package com.miniits.base.service;

import com.miniits.base.dao.CategoryRepository;
import com.miniits.base.model.entity.Category;
import com.miniits.base.mysql.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/4/1
 * @Time: 21:47
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
@Service
@Transactional
public class CategoryServer extends BaseServiceImpl<Category, String> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    public void setBaseDao(CategoryRepository categoryRepository) {
        super.setBaseDao(categoryRepository);
    }

    public Category findByCategoryName(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }

}
