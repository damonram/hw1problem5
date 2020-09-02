package edu.tcu.cs.hw1problem5.datainitializer;

import edu.tcu.cs.hw1problem5.dao.UserDao;
import edu.tcu.cs.hw1problem5.domain.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBDataInitializer implements CommandLineRunner {

    private UserDao userDao;

    public DBDataInitializer(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void run(String... args) throws Exception{
        User u1 = new User();
        u1.setId("1234");
        u1.setName("Damon");

        User u2 = new User();
        u2.setId("2345");
        u2.setName("Natalie");

        User u3 = new User();
        u3.setId("3456");
        u3.setName("Michael");

        userDao.save(u1);
        userDao.save(u2);
        userDao.save(u3);
    }
}
