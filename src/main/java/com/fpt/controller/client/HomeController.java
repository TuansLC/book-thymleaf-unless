package com.fpt.controller.client;

import com.fpt.service.admin.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class HomeController {
    @Autowired
    BookServiceImpl bookService;
    // shop index page
    @RequestMapping(method = RequestMethod.GET)
    public String shop_product(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "client/shop-index";
    }

    // shop list item page
    @GetMapping(value = "/shop-product-list")
    public String shop_product_list(Model model) {
        model.addAttribute("productBook",bookService.findAll());
        return "client/shop-product-list";
    }

    // shop list item page
    @GetMapping(value = "/shop-search-result")
    public String shop_search_result() {
        return "client/shop-search-result";
    }

    // shop shopping cart page
    @GetMapping(value = "/shop-shopping-cart")
    public String shop_shopping_cart() {
        return "client/shop-shopping-cart";
    }

    // shop shopping cart null page
    @GetMapping(value = "/shop-shopping-cart-null")
    public String shop_shopping_cart_null() {
        return "client/shop-shopping-cart-null";
    }

    //shop item page
    @GetMapping(value = "/shop-item")
    public String shop_item() {
        return "client/shop-item";
    }

    //shop account page
    @GetMapping(value = "/shop-checkout")
    public String shop_checkout(){
        return "client/shop-checkout";
    }

    //shop account page
    @GetMapping(value = "/shop-account")
        public String shop_account(){
        return "client/shop-account";
        }
}


