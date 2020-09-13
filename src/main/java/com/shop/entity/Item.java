package com.shop.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="item")
@Getter
@Setter
@ToString
public class Item {

    @Id
    @Column(name="item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;       //상품 코드

    @Column(nullable = false, length = 50)
    private String itemNm; //상품명

    @Column(name="price", nullable = false)
    private Integer price; //가격

    @Lob
    @Column(nullable = false)
    private String itemDetail; //상품 상세 설명

    @Column(nullable = false, length = 2)
    private String sellStatCd; //판매 상태

    @CreatedDate
    @Column(
            updatable = false,
            nullable = false
    )
    private LocalDateTime regTime; //등록 시간

    @LastModifiedDate
    @Column(
            updatable = true,
            nullable = false
    )
    private LocalDateTime updateTime; //수정 시간

}