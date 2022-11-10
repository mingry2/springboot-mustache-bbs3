package com.mustache.bbs4.repository;

import com.mustache.bbs4.domain.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    // CRUD 기능을 추가 코드 작성 없이 (쿼리 작성없이) 사용할 수 있게 해준다.
}
