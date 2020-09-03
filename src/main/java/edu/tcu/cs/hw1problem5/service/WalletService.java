package edu.tcu.cs.hw1problem5.service;

import edu.tcu.cs.hw1problem5.dao.WalletDao;
import edu.tcu.cs.hw1problem5.domain.StatusCode;
import edu.tcu.cs.hw1problem5.domain.User;
import edu.tcu.cs.hw1problem5.domain.Wallet;
import edu.tcu.cs.hw1problem5.utils.IdWorker;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WalletService {
    private WalletDao walletDao;
    private IdWorker idWorker;

    public WalletService(WalletDao walletDao, IdWorker idWorker) {
        this.walletDao = walletDao;
        this.idWorker = idWorker;
    }

    public List<Wallet> findAll() {return walletDao.findAll();
    }

//    public Wallet findByUserId(String userId) {
//        return walletDao.findById(userId).get();
//    }

    public Wallet findById(String walletId) {
        return walletDao.findById(walletId).get();
    }

    public void save(Wallet newWallet) {
        newWallet.setId(idWorker.nextId() + "");
        walletDao.save(newWallet);
    }

    public void update(String walletId, Wallet updatedWallet) {
        updatedWallet.setId(walletId);
        walletDao.save(updatedWallet);
    }

    public void delete(String walletId) {walletDao.deleteById(walletId);
    }

    public int viewBalance(String walletId) {
        Wallet wallet = walletDao.findById(walletId).get();
        return wallet.getBalance();
    }

    public int incBalance(String walletId, int amount) {
        Wallet wallet = walletDao.findById(walletId).get();
        if(wallet.getStatus() != "Frozen"){
            int temp = wallet.getBalance();
            temp = temp + amount;
            wallet.setBalance(temp);
            return StatusCode.SUCCESS;
        }
        else
            return StatusCode.FAILURE;
    }

    public int decBalance(String walletId, int amount) {
        Wallet wallet = walletDao.findById(walletId).get();
        if(wallet.getStatus() != "Frozen"){
            int temp = wallet.getBalance();
            temp = temp - amount;
            wallet.setBalance(temp);
            return StatusCode.SUCCESS;
        }
        else
            return StatusCode.FAILURE;
    }

    public void freeze(String walletId) {
        Wallet wallet = walletDao.findById(walletId).get();
        wallet.setStatus("Frozen");
    }

    public void unfreeze(String walletId) {
        Wallet wallet = walletDao.findById(walletId).get();
        wallet.setStatus("Unfrozen");
    }
}
