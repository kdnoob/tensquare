package com.tensquare.search.service;

import com.tensquare.search.dao.ArticleDao;
import com.tensquare.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

@Service
public class ArticleService {

    @Autowired
    private ArticleDao articleDao;

    public void save(Article article) {
        articleDao.save(article);
    }

    public Page<Article> search(String keywords, int page, int size) {
        PageRequest pageable = PageRequest.of(page - 1, size);
        return articleDao.findByTitleOrContent(keywords, keywords, pageable);
    }
}
