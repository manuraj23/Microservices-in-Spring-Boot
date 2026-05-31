package com.hms.UserService.External;

import com.hms.UserService.Entity.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTELSERVICE")
public interface HotelService {
    @GetMapping("/HotelService/getAllHotels")
    Hotel getAllHotels();

    @GetMapping("/HotelService/getHotelById/{hotelId}")
    Hotel getHotelById(@PathVariable String hotelId);
}
