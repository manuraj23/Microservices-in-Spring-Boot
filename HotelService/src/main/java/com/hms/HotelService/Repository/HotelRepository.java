package com.hms.HotelService.Repository;


import com.hms.HotelService.Entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
public interface HotelRepository extends JpaRepository<Hotel, UUID> {
}
