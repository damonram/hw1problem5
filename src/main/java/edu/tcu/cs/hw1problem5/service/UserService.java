package edu.tcu.cs.hw1problem5.service;

import edu.tcu.cs.hw1problem5.dao.ProductDao;
import edu.tcu.cs.hw1problem5.dao.UserDao;
import edu.tcu.cs.hw1problem5.domain.Product;
import edu.tcu.cs.hw1problem5.domain.User;
import edu.tcu.cs.hw1problem5.domain.Wallet;
import edu.tcu.cs.hw1problem5.utils.IdWorker;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {

    private UserDao userDao;
    private IdWorker idWorker;
    private ProductDao productDao;

    //Spring will automatically inject an instance of UserDao and IdWorker into this class


    public UserService(UserDao userDao, IdWorker idWorker, ProductDao productDao) {
        this.userDao = userDao;
        this.idWorker = idWorker;
        this.productDao = productDao;
    }

    public List<User> findAll(){
        return userDao.findAll();
    }

    public User findById(String userId) {
        return userDao.findById(userId).get();
    }
    public void save(User newUser){
        newUser.setId(idWorker.nextId() + "");
        userDao.save(newUser);
    }

    public void update(String userId, User updatedUser) {
        updatedUser.setId(userId);
        userDao.save(updatedUser);
    }

    public void delete(String userId) {
        userDao.deleteById(userId);
    }

    public void assignProduct(String userId, String productId) {
        //find this product by id from DB
        Product productToBeAssigned = productDao.findById(productId).get();
        User user = userDao.findById(userId).get();

        if(productToBeAssigned.getOwner() != null){
            productToBeAssigned.getOwner().removeProduct(productToBeAssigned);
        }
        user.addProduct(productToBeAssigned);
    }

    public int viewBalance(String userId) {
        User user = userDao.findById(userId).get();
        Wallet wallet = user.getWallet();
        return wallet.getBalance();
    }
}
