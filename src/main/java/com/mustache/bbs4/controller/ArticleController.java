package com.mustache.bbs4.controller;

import com.mustache.bbs4.domain.dto.ArticleDto;
import com.mustache.bbs4.domain.entity.Article;
import com.mustache.bbs4.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/articles")
@Slf4j
public class ArticleController {

    // spring이 ArticleRepository 구현체를 DI 해줌 (인터페이스가아님)
    // ArticleRepository는 interface지만 그 구현체(ArticleDao)를 stringboot가 넣어줌
    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    // findById 사용하여 show 페이지 만들기 -> 작성한 게시글 보기
    @GetMapping("/{id}")
    public String selectSingle(@PathVariable Long id, Model model) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        // optional.get()을 해줘야 DB에서 찾아온 데이터를 Article 타입으로 받을수있음
        if (!optionalArticle.isEmpty()){
            model.addAttribute("article", optionalArticle.get());
            return "articles/show";
        }else {
            return "articles/error";
        }
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
        // 저장한 id 값을 getId로 가져와 해당 게시글을 보여주게 redirect 함
        return String.format("redirect:/articles/%d", savedArticle.getId());
    }
}
