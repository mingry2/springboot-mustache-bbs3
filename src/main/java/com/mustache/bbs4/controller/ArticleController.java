package com.mustache.bbs4.controller;

import com.mustache.bbs4.domain.dto.ArticleDto;
import com.mustache.bbs4.domain.entity.Article;
import com.mustache.bbs4.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/articles")
@Slf4j
public class ArticleController {

    // springboot가 의존성 주입을 해준다. (DI)
    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    // 클라이언트가 title, content 작성하는 페이지
    @GetMapping("/new")
    public String articleCreate() {

        return "articles/new";
    }

    // new에서 작성한 title, content를 가져와서 저장하는 메서드
    @PostMapping("")
    public String articleAdd(ArticleDto articleDto) {
        log.info(articleDto.toString()); // articleDto로 데이터가 잘 넘어 왔는지 확인
        Article articleEntity = articleDto.toEntity();
        log.info(articleEntity.toString());
        Article savedArticle = articleRepository.save(articleEntity);
        log.info(savedArticle.toString());
        return "";
    }
}
