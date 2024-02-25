package com.system.TrendThreads.repo;

import com.system.TrendThreads.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishlistRepo extends JpaRepository<Wishlist, Integer> {
    @Query(value = "DELETE * FROM wishlist WHERE user_id = ?1, product_id = ?1", nativeQuery = true)
    void deleteWishlistById(Integer userId, Integer productId);

    @Query(value = "SELECT * FROM wishlist WHERE user_id = ?1", nativeQuery = true)
    Optional<List<Wishlist>> fetchAll(Integer userId);
}
