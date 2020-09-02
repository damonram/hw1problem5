package edu.tcu.cs.hw1problem5.controller;

import edu.tcu.cs.hw1problem5.domain.Product;
import edu.tcu.cs.hw1problem5.domain.Result;
import edu.tcu.cs.hw1problem5.domain.StatusCode;
import edu.tcu.cs.hw1problem5.domain.User;
import edu.tcu.cs.hw1problem5.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    public Result findAll(){
        List<Product> all = productService.findAll();
        return new Result(true, StatusCode.SUCCESS, "Find All Success", all);
    }
    @GetMapping("/{productId}")
    public Result findById(@PathVariable String productId){
        return new Result(true, StatusCode.SUCCESS, "Find One Success", productService.findById(productId));
    }

    @PostMapping
    public Result save(@RequestBody Product newProduct){
        productService.save(newProduct);
        return new Result(true, StatusCode.SUCCESS, "Save Success");
    }
    @PutMapping("/{productId}")
    public Result update(@PathVariable String productId, @RequestBody Product updatedProduct){
        productService.update(productId, updatedProduct);
        return new Result(true, StatusCode.SUCCESS, "Update Success");
    }
    @DeleteMapping("/{userId}")
    public Result delete(@PathVariable String productId){
        productService.delete(productId);
        return new Result(true, StatusCode.SUCCESS, "Delete Success");

    }
}
