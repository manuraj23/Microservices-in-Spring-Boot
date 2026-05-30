package com.hms.HotelService.Service;

import com.hms.HotelService.Entity.Hotel;
import com.hms.HotelService.Repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    //create Hotel
    public void createHotel(Hotel hotel){
        hotelRepository.save(hotel);
    }

    // get All Hotels
    public List<Hotel> getAllHotels(){
        return hotelRepository.findAll();
    }

    // get Hotels by id;
    public Hotel getHotelById(String hotelId){
        return hotelRepository.findById(UUID.fromString(hotelId)).orElse(null);
    }
}
