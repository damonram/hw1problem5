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

}