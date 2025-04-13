package com.ex.caffeine_bliss.controllers;

import com.ex.caffeine_bliss.DTOs.CustomerDTO;
import com.ex.caffeine_bliss.DTOs.ProductDTO;
import com.ex.caffeine_bliss.DTOs.paginated.PaginatedResponse;
import com.ex.caffeine_bliss.DTOs.request.RequestAddCustomerDTO;
import com.ex.caffeine_bliss.DTOs.request.RequestAddProductDTO;
import com.ex.caffeine_bliss.DTOs.request.RequestUpdateCustomerDTO;
import com.ex.caffeine_bliss.DTOs.request.RequestUpdateProductDTO;
import com.ex.caffeine_bliss.entities.enums.ProductCategory;
import com.ex.caffeine_bliss.services.CustomerService;
import com.ex.caffeine_bliss.services.ProductService;
import com.ex.caffeine_bliss.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping(path = "/get-by-Id", params = "id")
    public ResponseEntity<StandardResponse> getProductById(@RequestParam(value = "id") UUID id) {
        ProductDTO productDTO = productService.getProductById(id);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "SUCCESS",
                        productDTO), HttpStatus.OK);
    }

    @GetMapping(path = "/get-by-name", params = "name")
    public ResponseEntity<StandardResponse> getProductByName(@RequestParam(value = "name") String name){
        ProductDTO productDTO = productService.getProductByName(name);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "SUCCESS",
                        productDTO), HttpStatus.OK);
    }

    @GetMapping(path = "/get-by-category", params = "category")
    public ResponseEntity<StandardResponse> getProductsByCategory(@RequestParam(value = "category") ProductCategory category){
        List<ProductDTO> products = productService.getProductsByCategory(category);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "SUCCESS",
                        products), HttpStatus.OK);
    }

    @GetMapping(path = "/get-all-products")
    public ResponseEntity<StandardResponse> getAllProducts(){
        List<ProductDTO> products = productService.getAllProducts();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "SUCCESS",
                        products), HttpStatus.OK);
    }

    @GetMapping(path = "/get-limited-products", params = {"page", "limit"})
    public ResponseEntity<StandardResponse> getLimitedProducts(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit
    ){
        if(limit > 20){ limit = 20; }
        PaginatedResponse<ProductDTO> productList = productService.getLimitedProducts(page,limit);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "SUCCESS",
                        productList), HttpStatus.OK);
    }

    @PostMapping(path = "/ad/add")
    public ResponseEntity<StandardResponse> addProduct(@RequestBody RequestAddProductDTO productDTO){
        String message = productService.addProduct(productDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "SUCCESS",
                        message), HttpStatus.CREATED);
    }

    @PostMapping(path = "/ad/update")
    public ResponseEntity<StandardResponse> updateCustomer(@RequestBody RequestUpdateProductDTO productDTO){
        ProductDTO product = productService.updateProduct(productDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "SUCCESS",
                        product), HttpStatus.OK);
    }

    @DeleteMapping(path = "/ad/delete-by-Id", params = "id")
    public ResponseEntity<StandardResponse> deleteProductById(@RequestParam(value = "id") UUID id) {
        String message = productService.deleteProductById(id);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "SUCCESS",
                        message), HttpStatus.OK);
    }
}
