package com.hms.UserService.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
    private UUID hotelId;
    private String hotelName;
    private String location;
    private String about;
}
