package com.sudagoarth.sudanyallapay.Bills.Interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sudagoarth.sudanyallapay.Bills.Dtos.BillRequest;
import com.sudagoarth.sudanyallapay.Bills.Dtos.BillResponse;
import com.sudagoarth.sudanyallapay.Bills.Dtos.BillStatusRequest;

public interface BillInterface {
    Page<BillResponse> getBills(Long userId, Pageable pageable);
    BillResponse getBill(Long billId);
    BillResponse createBill(BillRequest billRequest);
    BillResponse updateBill(Long billId, BillRequest billRequest);
    BillResponse updateBillStatus(Long billId, BillStatusRequest billStatusRequest);
    void deleteBill(Long billId);
    BillResponse statusBill(Long billId, BillStatusRequest billStatusRequest);
    BillResponse payBill(Long billId);
    BillResponse cancelBill(Long billId);

}