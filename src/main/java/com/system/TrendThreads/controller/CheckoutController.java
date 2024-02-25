package com.system.TrendThreads.controller;

import com.system.TrendThreads.entity.Cart;
import com.system.TrendThreads.pojo.CartPojo;
import com.system.TrendThreads.services.CartService;
import com.system.TrendThreads.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class CheckoutController {
    private final UserService userService;
    private final CartService cartService;

    @GetMapping("/checkout")
    public String displayCart(Principal principal, Model model){
        Integer id = userService.findByEmail(principal.getName()).getId();
        List<Cart> list = cartService.fetchAll(id);

        double total = 0.0;
        for(Cart totalCalc:list){
            if (totalCalc.getProduct().getProduct_quantity()>0) total += totalCalc.getQuantity()*totalCalc.getProduct().getProduct_price();
        }

        model.addAttribute("total", total);
        model.addAttribute("cartItems", list);
        model.addAttribute("checkout", new CartPojo());

        return "/checkout";
    }

    @PostMapping("/checkout/confirm")
    public String checkoutProcess(Principal principal, @Valid CartPojo pojo){
        Integer id = userService.findByEmail(principal.getName()).getId();
        List<Cart> list = cartService.fetchAvailable(id);

        for (Cart cartItem: list){
            cartService.updateProduct(cartItem.getProduct().getProduct_quantity()-cartItem.getQuantity(),cartItem.getProduct().getId());
        }

        cartService.checkout(id, pojo, list);
        return "redirect:/dashboard";
    }
}
