package com.system.TrendThreads.services;

import com.system.TrendThreads.entity.Wishlist;

import java.security.Principal;
import java.util.List;

public interface WishlistService {
    String saveToWishlist(Integer id, Principal principal);

    String deleteFromWishlist(Integer id);

    List<Wishlist> fetchAll(Integer id);
}
