package edu.tcu.cs.hw1problem5.service;

import edu.tcu.cs.hw1problem5.dao.UserDao;
import edu.tcu.cs.hw1problem5.domain.User;
import edu.tcu.cs.hw1problem5.utils.IdWorker;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {

    private UserDao userDao;
    private IdWorker idWorker;

    //Spring will automatically inject an instance of UserDao and IdWorker into this class
    public UserService(UserDao userDao, IdWorker idWorker) {
        this.userDao = userDao;
        this.idWorker = idWorker;
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
}
