package edu.tcu.cs.hw1problem5.datainitializer;

import edu.tcu.cs.hw1problem5.dao.ProductDao;
import edu.tcu.cs.hw1problem5.dao.UserDao;
import edu.tcu.cs.hw1problem5.dao.WalletDao;
import edu.tcu.cs.hw1problem5.domain.Product;
import edu.tcu.cs.hw1problem5.domain.User;
import edu.tcu.cs.hw1problem5.domain.Wallet;
import javafx.scene.canvas.GraphicsContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBDataInitializer implements CommandLineRunner {

    private UserDao userDao;
    private ProductDao productDao;
    private WalletDao walletDao;

    public DBDataInitializer(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void run(String... args) throws Exception{
        Product p1 = new Product();
        p1.setId("1");
        p1.setPrice("20");
        p1.setStatus("Available");
        p1.setName("AM1");
        p1.setOwner(null);

        Product p2 = new Product();
        p2.setId("2");
        p2.setPrice("15");
        p2.setStatus("Available");
        p2.setName("AF1");
        p2.setOwner(null);

//        productDao.save(p1);
//        productDao.save(p2);

        Wallet w1 = new Wallet();
        w1.setId("11");
        w1.setBalance(100);
        w1.setCreationTime("9/2/20 13:00");
        w1.setLastModified("9/2/20 13:00");
        w1.setStatus("Unfrozen");

        Wallet w2 = new Wallet();
        w2.setId("12");
        w2.setBalance(200);
        w2.setCreationTime("9/2/20 13:00");
        w2.setLastModified("9/2/20 13:00");
        w2.setStatus("Unfrozen");

        Wallet w3 = new Wallet();
        w3.setId("13");
        w3.setBalance(50);
        w3.setCreationTime("9/2/20 13:00");
        w3.setLastModified("9/2/20 13:00");
        w3.setStatus("Unfrozen");


        User u1 = new User();
        u1.setId("1234");
        u1.setName("Damon");
        u1.addProduct(p1);
        w1.setUserId(u1.getId());
        //w1.setOwner(u1);
        u1.addWallet(w1);

        User u2 = new User();
        u2.setId("2345");
        u2.setName("Natalie");
        u2.addProduct(p2);
        w2.setUserId(u2.getId());
        //w2.setOwner(u2);
        u2.addWallet(w2);

        User u3 = new User();
        u3.setId("3456");
        u3.setName("Michael");
        w3.setUserId(u3.getId());
        //w3.setOwner(u3);
        u3.addWallet(w3);


//        walletDao.save(w1);
//        walletDao.save(w2);
//        walletDao.save(w3);

        userDao.save(u1);
        userDao.save(u2);
        userDao.save(u3);



    }
}
