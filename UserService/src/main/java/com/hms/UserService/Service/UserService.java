package com.hms.UserService.Service;

import com.hms.UserService.Entity.Rating;
import com.hms.UserService.Entity.Hotel;
import com.hms.UserService.Entity.User;
import com.hms.UserService.External.HotelService;
import com.hms.UserService.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    public User saveNewUser(User user){
        return userRepository.save(user);
    }



    public List<User> getAllUsers(){
        List<User> users = userRepository.findAll();
        if(users == null || users.isEmpty()) return users;

        for (User user : users) {
            String uid = null;
            try {
                uid = user.getUserId() != null ? user.getUserId().toString() : null;
            } catch (Exception e) {
                uid = null;
            }

            if (uid == null) {
                user.setRatings(Collections.emptyList());
                continue;
            }

            Rating[] ratings = null;
            try {
                ratings = restTemplate.getForObject("http://RatingService/RatingService/getRatingsByUser/" + uid, Rating[].class);
            } catch (Exception e) {
                ratings = null;
            }

            if (ratings != null) {
                user.setRatings(Arrays.stream(ratings).map(rating -> {
                    try {
                        // Use Feign client to fetch hotel instead of RestTemplate
                        Hotel hotel = hotelService.getHotelById(rating.getHotelId());
                        rating.setHotel(hotel);
                    } catch (Exception e) {
                        rating.setHotel(null);
                    }
                    return rating;
                }).collect(Collectors.toList()));
            } else {
                user.setRatings(Collections.emptyList());
            }
        }

        return users;
    }

    public User getUserById(String userId){
        User user= userRepository.findById(UUID.fromString(userId)).orElse(null);
        if(user==null) return null;
        Rating[] ratings = restTemplate.getForObject("http://RatingService/RatingService/getRatingsByUser/" + userId, Rating[].class);
        if (ratings != null) {
            user.setRatings(Arrays.stream(ratings).map(rating -> {
                try {
                    // Use Feign client to fetch hotel instead of RestTemplate
                    Hotel hotel = hotelService.getHotelById(rating.getHotelId());
                    rating.setHotel(hotel);
                } catch (Exception e) {
                    rating.setHotel(null);
                }
                return rating;
            }).collect(Collectors.toList()));
        } else {
            user.setRatings(Collections.emptyList());
        }
        return user;

    }

}
