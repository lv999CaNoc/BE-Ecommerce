package com.example.beecommerce.controller;


import com.example.beecommerce.pojo.entity.Shop;
import com.example.beecommerce.pojo.requests.ShopRequest;
import com.example.beecommerce.pojo.responses.ObjectResponse;
import com.example.beecommerce.service.ShopService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shop")
@CrossOrigin(maxAge = 3600)
public class ShopController {
    @Autowired
    private ShopService shopService;

    @PostMapping("/save")
    ResponseEntity<ObjectResponse> createShop(@RequestBody @Valid ShopRequest request) {
        Shop shop = shopService.createShop(request);
        return ResponseEntity.ok().body(
                new ObjectResponse(HttpStatus.OK, "Create shop successfully", shop)
        );
    }

    @GetMapping("")
    ResponseEntity<ObjectResponse> getShopByUserId(@RequestParam("id") Long id) {
        Shop shop = shopService.getShopByUserId(id);
        return ResponseEntity.ok().body(
                new ObjectResponse(HttpStatus.OK, "Get shop successfully", shop)
        );
    }


}
