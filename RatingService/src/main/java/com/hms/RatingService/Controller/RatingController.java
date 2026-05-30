package com.hms.RatingService.Controller;

import com.hms.RatingService.Entity.Rating;
import com.hms.RatingService.Service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/createRating")
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
        ratingService.createRating(rating);
        return ResponseEntity.ok(rating);
    }

    @GetMapping("/getAllRatings")
    public ResponseEntity<List<Rating>> allRating() {
        return ResponseEntity.ok(ratingService.allRating());
    }

    @GetMapping("/getRatingsByUser/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(ratingService.getRatingByUserId(userId));
    }

    @GetMapping("/getRatingsByHotel/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId) {
        return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
    }

    @GetMapping("/getRatingByHotelAndUser/{hotelId}/{userId}")
    public ResponseEntity<Rating> getRatingByHotelIdAndUserId(@PathVariable String hotelId, @PathVariable String userId) {
        Rating rating = ratingService.getRatingByHotelIdAndUserId(hotelId, userId);
        if (rating != null) return ResponseEntity.ok(rating);
        return ResponseEntity.notFound().build();
    }

}
