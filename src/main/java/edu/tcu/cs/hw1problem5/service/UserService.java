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
    private WalletService walletService;

    //Spring will automatically inject an instance of UserDao and IdWorker into this class


    public UserService(UserDao userDao, IdWorker idWorker, ProductDao productDao, WalletService walletService) {
        this.userDao = userDao;
        this.idWorker = idWorker;
        this.productDao = productDao;
        this.walletService = walletService;
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
        //productToBeAssigned.setOwner(user);
        productToBeAssigned.setStatus("Owned by " + user.getName());
    }

    public String transaction(String userId, String userId2, String productId) {
        User buyer = userDao.findById(userId).get();
        User seller = userDao.findById(userId2).get();
        Product product = productDao.findById(productId).get();
        //check if seller has product
        if(product.getOwner() == seller){
            int price = product.getPrice();
            Wallet bWallet = buyer.getWallet();
            Wallet sWallet = seller.getWallet();
            //check if either wallet frozen
            if(bWallet.getStatus()=="Frozen")
                return bWallet.getOwner().getName() + " wallet frozen";
            else if(sWallet.getStatus()=="Frozen")
                return sWallet.getOwner().getName() + " wallet frozen";
            //check if buyer has enough money
            else if(bWallet.getBalance()>=price){
                walletService.decBalance(bWallet.getId(),price);    //take money from buyer
                walletService.incBalance(sWallet.getId(),price);    //give money to seller
                assignProduct(userId,productId);                    //seller gives buyer product
                return "Transaction Success";
            }
            else
                return "Buyer does not have sufficient funds";
        }
        else
            return "Seller does not have specified product";
    }

}
