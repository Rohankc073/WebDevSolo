package com.system.TrendThreads.controller.Admin;


import com.system.TrendThreads.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class DashboardController {

    private final CartService cartService;
    @GetMapping
    public String getCustomerDetails(Model model){

        List<Integer> weeksalesdetails = cartService.fetch5weeksales();
        List<Object[]> lastweeksale = cartService.fetchlastweeksale();
        List<Object[]> lastweekproductsale = cartService.fetchproductlastweeksales();
        List<Object[]> lastweekstatus = cartService.fetchstatusCount();



        model.addAttribute("weeksales",weeksalesdetails);
        model.addAttribute("thisweeksale",lastweeksale);
        model.addAttribute("productsale",lastweekproductsale);
        model.addAttribute("status",lastweekstatus);
        return "Admin/dashboard";

    }
}
