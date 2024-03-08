package com.zosh.controller;

import com.zosh.model.Restaurant;
import com.zosh.model.User;
import com.zosh.request.CreateRestaurantRequest;
import com.zosh.response.MessageResponse;
import com.zosh.service.RestaurantService;
import com.zosh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/restaurants")
public class AdminRestaurantController {

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private UserService userService;

    public ResponseEntity<Restaurant>createRestaurant
            (@RequestBody CreateRestaurantRequest req,
             @RequestHeader("Authorization")String jwt)throws Exception{
        User user=userService.findUserByJwtToken(jwt);
        Restaurant restaurant=restaurantService.createRestaurant(req,user);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant>updateRestaurant
            (@RequestBody CreateRestaurantRequest req,
             @RequestHeader("Authorization")String jwt,
             @PathVariable long id)
            throws Exception{
        User user=userService.findUserByJwtToken(jwt);
        Restaurant restaurant=restaurantService.updateRestaurant(id,req);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse>deleteRestaurant
            (@RequestBody CreateRestaurantRequest req,
             @RequestHeader("Authorization")String jwt,
             @PathVariable long id)
            throws Exception{
        User user=userService.findUserByJwtToken(jwt);
        restaurantService.deleteRestaurant(id);
        MessageResponse res=new MessageResponse();
        res.setMessage("Restaurant with Id deleted");
       return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Restaurant>updateRestaurantStatus
            (@RequestBody CreateRestaurantRequest req,
             @RequestHeader("Authorization")String jwt,
             @PathVariable long id)
            throws Exception{
        User user=userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.updateRestaurantStatus(id);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<Restaurant>findRestaurantByuserId
            (@RequestBody CreateRestaurantRequest req,
             @RequestHeader("Authorization")String jwt)
            throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.getRestaurantByUserId(user.getId());
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

}
