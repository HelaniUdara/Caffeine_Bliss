package com.ex.caffeine_bliss.services.impl;

import com.ex.caffeine_bliss.DTOs.ProductDTO;
import com.ex.caffeine_bliss.DTOs.paginated.PaginatedResponse;
import com.ex.caffeine_bliss.DTOs.request.RequestAddProductDTO;
import com.ex.caffeine_bliss.DTOs.request.RequestUpdateProductDTO;
import com.ex.caffeine_bliss.entities.Product;
import com.ex.caffeine_bliss.entities.enums.ProductCategory;
import com.ex.caffeine_bliss.exceptions.DuplicateElementException;
import com.ex.caffeine_bliss.exceptions.ResourceNotFoundException;
import com.ex.caffeine_bliss.repositories.ProductRepository;
import com.ex.caffeine_bliss.services.ProductService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String addProduct(RequestAddProductDTO productDTO) {
        if(productRepository.existsByName(productDTO.getName())){
            throw new DuplicateElementException("This product is already added!");
        }else{
            Product product = modelMapper.map(productDTO, Product.class);
            productRepository.save(product);
            return "Added the product " + productDTO.getName();
        }
    }

    @Override
    public ProductDTO updateProduct(RequestUpdateProductDTO productDTO) {
        if(productRepository.existsById(productDTO.getId())){
            Product product = productRepository.getReferenceById(productDTO.getId());
            product.setUnitPrice(productDTO.getUnitPrice());
            product.setStockQuantity(productDTO.getStockQuantity());
            productRepository.save(product);
            return modelMapper.map(product, ProductDTO.class);
        }else {
            throw new ResourceNotFoundException("Product not exists by ID: " + productDTO.getId());
        }
    }

    @Override
    public String deleteProductById(UUID id) {
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
            return "Successfully Deleted";
        }else {
            throw new ResourceNotFoundException("There is no product with ID: " + id);
        }
    }

    @Override
    public ProductDTO getProductById(UUID id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            return modelMapper.map(product, ProductDTO.class);
        }else {
            throw new ResourceNotFoundException("There is no product with ID: " + id);
        }
    }

    @Override
    public ProductDTO getProductByName(String name) {
        Product product = productRepository.findByName(name);
        if(product != null){
            return modelMapper.map(product, ProductDTO.class);
        }else {
            throw new ResourceNotFoundException("This product does not exists");
        }
    }

    @Override
    public List<ProductDTO> getProductsByCategory(ProductCategory category) {
        List<Product> products = productRepository.findAllByCategory(category);
        if(products.size() < 1){
            throw new ResourceNotFoundException("There isn't any product in the database.");
        }else{
            List<ProductDTO> productDTOList = modelMapper.map(products, new TypeToken<List<ProductDTO>>(){}.getType());
            return productDTOList;
        }
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if(products.size() < 1){
            throw new ResourceNotFoundException("There isn't any product in the database.");
        }else{
            List<ProductDTO> productDTOList = modelMapper.map(products, new TypeToken<List<ProductDTO>>(){}.getType());
            return productDTOList;
        }
    }

    @Override
    public PaginatedResponse<ProductDTO> getLimitedProducts(int page, int limit) {
        Page<Product> products = productRepository.findAll(PageRequest.of(page, limit));
        if (products.getSize() < 1) {
            throw new ResourceNotFoundException("There isn't any product according to the request.");
        } else {
            List<Product> productEntities = products.getContent();
            List<ProductDTO> productList = modelMapper.map(productEntities, new TypeToken<List<ProductDTO>>() {
            }.getType());
            int totalCount = (int) productRepository.count();
            return new PaginatedResponse<ProductDTO>(productList, page, limit, totalCount);
        }
    }

    @Override
    public PaginatedResponse<ProductDTO> getAllOnStockProducts(int page, int limit) {
        return null;
    }


    @Override
    public List<ProductDTO> getAllOutOfStockProducts() {
        return null;
    }
}
