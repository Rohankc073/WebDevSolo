package com.system.TrendThreads.services.impl;

import com.system.TrendThreads.entity.Product;
import com.system.TrendThreads.entity.Sale;
import com.system.TrendThreads.entity.Wishlist;
import com.system.TrendThreads.repo.ProductRepo;
import com.system.TrendThreads.repo.SaleRepo;
import com.system.TrendThreads.repo.UserRepo;
import com.system.TrendThreads.repo.WishlistRepo;
import com.system.TrendThreads.services.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

import static com.system.TrendThreads.services.impl.ProductServiceImpl.getImageBase64;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {
    private final WishlistRepo wishlistRepo;

    private final UserRepo userRepo;

    private final ProductRepo productRepo;

    private final SaleRepo saleRepo;

    @Override
    public String saveToWishlist(Integer id, Principal principal) {
        Wishlist wishlist = new Wishlist();
        wishlist.setUser(userRepo.findByEmail(principal.getName()).orElseThrow());
        wishlist.setProduct(productRepo.findById(id).orElseThrow());
        wishlistRepo.save(wishlist);
        return "Saved";
    }

    @Override
    public String deleteFromWishlist(Integer id) {
        wishlistRepo.deleteById(id);
        return "Deleted";
    }

    @Override
    public List<Wishlist> fetchAll(Integer id) {
        List<Wishlist> allItems = wishlistRepo.fetchAll(id).orElseThrow();
        for (Wishlist wishlist : allItems){
            wishlist.setProduct(Product.builder()
                    .id(wishlist.getProduct().getId())
                    .product_size(wishlist.getProduct().getProduct_size())
                    .product_quantity(wishlist.getProduct().getProduct_quantity())
                    .product_category(wishlist.getProduct().getProduct_category())
                    .product_image(getImageBase64(wishlist.getProduct().getProduct_image()))
                    .product_color(wishlist.getProduct().getProduct_color())
                    .product_name(wishlist.getProduct().getProduct_name())
                    .product_description(wishlist.getProduct().getProduct_description())
                    .product_price(discountedPrice(wishlist.getProduct()))
                    .build());
        }
        return allItems;
    }

    public Double discountedPrice(Product product){
        List<Sale> sales = saleRepo.saleProducts();
        for (Sale sale : sales) {
            if (sale.getProduct().getId().equals(product.getId())) {
                return product.getProduct_price() - sale.getDiscountPercent()/100 * product.getProduct_price();
            }
        }
        return product.getProduct_price();
    }
}
