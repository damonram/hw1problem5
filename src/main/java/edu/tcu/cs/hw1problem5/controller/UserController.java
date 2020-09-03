package edu.tcu.cs.hw1problem5.controller;

import edu.tcu.cs.hw1problem5.domain.Result;
import edu.tcu.cs.hw1problem5.domain.StatusCode;
import edu.tcu.cs.hw1problem5.domain.User;
import edu.tcu.cs.hw1problem5.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    
    private UserService userService;

    //must have constructor to inject dependency bean
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Result findAll(){
        List<User> all = userService.findAll();
        Result result = new Result(true, StatusCode.SUCCESS, "Find All Success", all);
        return result;
    }
    @GetMapping("/{userId}")
    public Result findById(@PathVariable String userId){
        return new Result(true, StatusCode.SUCCESS, "Find One Success", userService.findById(userId));
    }
    @PostMapping
    public Result save(@RequestBody User newUser){
        userService.save(newUser);
        return new Result(true, StatusCode.SUCCESS, "Save Success");
    }
    @PutMapping("/{userId}")
    public Result update(@PathVariable String userId, @RequestBody User updatedUser){
        userService.update(userId, updatedUser);
        return new Result(true, StatusCode.SUCCESS, "Update Success");
    }
    @DeleteMapping("/{userId}")
    public Result delete(@PathVariable String userId){
        userService.delete(userId);
        return new Result(true, StatusCode.SUCCESS, "Delete Success");

    }
    @PutMapping("/assignProduct/{userId}/{productId}")
    public Result assignProduct(@PathVariable String userId, @PathVariable String productId){
        userService.assignProduct(userId, productId);
        return new Result(true, StatusCode.SUCCESS, "Product Assignment Success");
    }

    @PutMapping("/transaction/{userId}/{userId2}/{productId}")
    public Result transaction(@PathVariable String userId,@PathVariable String userId2,
                              @PathVariable String productId){
        String message = userService.transaction(userId, userId2, productId);
        if(message == "Transaction Success")
            return new Result(true, StatusCode.SUCCESS,message);
        else
            return new Result(true, StatusCode.FAILURE,message);
    }

}
