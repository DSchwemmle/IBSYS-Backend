package com.ibsys.ibsysbackend.repositories;

import com.ibsys.ibsysbackend.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    Optional<Article> findByNumber(final String number);
}
