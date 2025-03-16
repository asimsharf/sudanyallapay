package com.sudagoarth.sudanyallapay.Bills.Interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sudagoarth.sudanyallapay.Bills.Dtos.BillRequest;
import com.sudagoarth.sudanyallapay.Bills.Dtos.BillResponse;
import com.sudagoarth.sudanyallapay.Bills.Dtos.BillStatusRequest;

public interface BillInterface {
    Page<BillResponse> getBills(Long referenceId, Pageable pageable);
    BillResponse getBill(Long id);
    BillResponse createBill(BillRequest billRequest);
    BillResponse updateBill(Long id, BillRequest billRequest);
    void deleteBill(Long id);
    BillResponse statusBill(Long id, BillStatusRequest billStatusRequest);

}