package com.hms.UserService.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    private UUID ratingId;
    private UUID userId;
    private String hotelId;
    private int rating;
    private String feedback;
}
