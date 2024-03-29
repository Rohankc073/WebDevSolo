package com.system.TrendThreads.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "review")
public class Review {
    @Id
    @SequenceGenerator(name = "Fashionhive_product_seq_gen", sequenceName = "Fashionhive_product_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "Fashionhive_product_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_productId"))
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_userId"))
    private User user;

    @Column
    private Integer rate;

    @Column
    private String review;

    @Column
    private Date date;
}
