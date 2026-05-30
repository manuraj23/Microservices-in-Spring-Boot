package com.hms.RatingService.Service;

import com.hms.RatingService.Entity.Rating;
import com.hms.RatingService.Repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    //create Rating
    public void createRating(Rating rating) {
        ratingRepository.save(rating);
    }

    //get all rating
    public List<Rating> allRating(){
        return ratingRepository.findAll();
    }

    //getRating of a User
    public List<Rating> getRatingByUserId(String userId){
        return ratingRepository.findAll().stream().filter(rating -> rating.getUserId().toString().equals(userId)).toList();
    }

    //Get rating of Hotel
    public List<Rating> getRatingByHotelId(String hotelId){
        return ratingRepository.findAll().stream().filter(rating -> rating.getHotelId().equals(hotelId)).toList();
    }

    //Get Rating of Hotel by a user
    public Rating getRatingByHotelIdAndUserId(String hotelId, String userId){
        return ratingRepository.findAll().stream().filter(rating -> rating.getHotelId().equals(hotelId) && rating.getUserId().toString().equals(userId)).findFirst().orElse(null);
    }
}
