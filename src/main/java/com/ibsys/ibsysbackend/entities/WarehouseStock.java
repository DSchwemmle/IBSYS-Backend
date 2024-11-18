package com.ibsys.ibsysbackend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WarehouseStock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer amount;
    private Double pct;
    private Double price;
    private Double stockValue;
    private Integer period;
    private Integer articleId;

    // Neue Methode zur Kompatibilität
    public Article getArticle() {
        Article article = new Article();
        article.setId(this.articleId);
        article.setAmount(this.amount);
        article.setPrice(this.price);
        return article;
    }
}
