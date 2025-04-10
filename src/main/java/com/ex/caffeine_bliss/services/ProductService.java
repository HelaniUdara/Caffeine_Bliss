package com.ex.caffeine_bliss.services;

import com.ex.caffeine_bliss.DTOs.CustomerDTO;
import com.ex.caffeine_bliss.DTOs.ProductDTO;
import com.ex.caffeine_bliss.DTOs.paginated.PaginatedResponse;
import com.ex.caffeine_bliss.DTOs.request.RequestAddProductDTO;
import com.ex.caffeine_bliss.DTOs.request.RequestUpdateProductDTO;
import com.ex.caffeine_bliss.entities.enums.ProductCategory;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    String addProduct(RequestAddProductDTO productDTO);
    ProductDTO updateProduct(RequestUpdateProductDTO productDTO);
    String deleteProductById(UUID id);
    ProductDTO getProductById(UUID id);
    ProductDTO getProductByName(String name);
    List<ProductDTO> getProductsByCategory(ProductCategory category);
    List<ProductDTO> getAllProducts();
    PaginatedResponse<ProductDTO> getLimitedProducts(int page, int limit);
    PaginatedResponse<ProductDTO> getAllOnStockProducts(int page, int limit);
    List<ProductDTO> getAllOutOfStockProducts();
}
