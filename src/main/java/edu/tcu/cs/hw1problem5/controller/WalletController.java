package edu.tcu.cs.hw1problem5.controller;

import antlr.ASTNULLType;
import edu.tcu.cs.hw1problem5.domain.Result;
import edu.tcu.cs.hw1problem5.domain.StatusCode;
import edu.tcu.cs.hw1problem5.domain.User;
import edu.tcu.cs.hw1problem5.domain.Wallet;
import edu.tcu.cs.hw1problem5.service.WalletService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WalletController {

    private WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/wallets")
    public Result findAll(){
        List<Wallet> all = walletService.findAll();
        return new Result(true, StatusCode.SUCCESS,"Find All Success", all);
    }

//    @GetMapping("/wallets/{userId}")
//    public Result findByUserId(@PathVariable String userId){
//        return new Result(true, StatusCode.SUCCESS,"Find By User ID Success", walletService.findByUserId(userId));
//    }

    @GetMapping("/wallets/{walletId}")
    public Result findById(@PathVariable String walletId){
        return new Result(true, StatusCode.SUCCESS,"Find All Success", walletService.findById(walletId));
    }
    @PostMapping("/wallets")
    public Result save(@RequestBody Wallet newWallet){
        walletService.save(newWallet);
        return new Result(true, StatusCode.SUCCESS, "Save Success");
    }
    @PutMapping("/wallets/{walletId}")
    public Result update(@PathVariable String walletId, @RequestBody Wallet updatedWallet){
        walletService.update(walletId, updatedWallet);
        return new Result(true, StatusCode.SUCCESS, "Update Success");
    }
    @DeleteMapping("/wallets/{walletId}")
    public Result delete(@PathVariable String walletId){
        walletService.delete(walletId);
        return new Result(true, StatusCode.SUCCESS, "Delete Success");
    }
    @GetMapping("/wallets/balance/{walletId}")
    public Result viewBalance(@PathVariable String walletId){
        int balance = walletService.viewBalance(walletId);
        return new Result(true, StatusCode.SUCCESS, "Balance View Success",balance);
    }
    @PutMapping("/wallets/deposit/{walletId}/{amount}")
    public Result incBalance(@PathVariable String walletId, @PathVariable int amount){
        int num = walletService.incBalance(walletId, amount);
        if(num == StatusCode.SUCCESS)
            return new Result(true, StatusCode.SUCCESS, "Balance Increase Success");
        else
            return new Result(true, StatusCode.FAILURE, "Balance Increase Failure - Wallet Frozen!");
    }
    @PutMapping("/wallets/withdraw/{walletId}/{amount}")
    public Result decBalance(@PathVariable String walletId, @PathVariable int amount){
        int num = walletService.decBalance(walletId, amount);
        if(num == StatusCode.SUCCESS)
            return new Result(true, StatusCode.SUCCESS, "Balance Decrease Success");
        else
            return new Result(true, StatusCode.FAILURE, "Balance Decrease Failure - Wallet Frozen!");
    }
    @PutMapping("/wallets/freeze/{walletId}")
    public Result freeze(@PathVariable String walletId){
        walletService.freeze(walletId);
        return new Result(true, StatusCode.SUCCESS, "Freeze Success");
    }
    @PutMapping("/wallets/unfreeze/{walletId}")
    public Result unfreeze(@PathVariable String walletId){
        walletService.unfreeze(walletId);
        return new Result(true, StatusCode.SUCCESS, "Unfreeze Success");
    }

}
