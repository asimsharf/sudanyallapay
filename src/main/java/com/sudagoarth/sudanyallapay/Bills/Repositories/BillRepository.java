package com.sudagoarth.sudanyallapay.Bills.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sudagoarth.sudanyallapay.Bills.Entities.Bill;

@Repository
public interface  BillRepository extends JpaRepository<Bill, Long> {
    
}
