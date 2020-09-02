package edu.tcu.cs.hw1problem5.service;

import edu.tcu.cs.hw1problem5.dao.ProductDao;
import edu.tcu.cs.hw1problem5.domain.Product;
import edu.tcu.cs.hw1problem5.utils.IdWorker;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductService {
 private ProductDao productDao;
 private IdWorker idWorker;

    public ProductService(ProductDao productDao, IdWorker idWorker) {
        this.productDao = productDao;
        this.idWorker = idWorker;
    }

    public List<Product> findAll() {
        return productDao.findAll();
    }

    public Product findById(String productId) {return productDao.findById(productId).get();
    }

    public void save(Product newProduct) {
        newProduct.setId(idWorker.nextId() + "");
        productDao.save(newProduct);
    }

    public void update(String productId, Product updatedProduct) {
        updatedProduct.setId(productId);
        productDao.save(updatedProduct);
    }

    public void delete(String productId) {
        productDao.deleteById(productId);
    }
}
