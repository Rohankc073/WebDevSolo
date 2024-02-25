package com.system.TrendThreads.pojo;

import com.system.TrendThreads.entity.Product;
import com.system.TrendThreads.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WishlistPojo {
    private Integer id;
    private Product product;
    private User user;
}
