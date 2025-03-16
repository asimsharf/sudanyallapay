package com.sudagoarth.sudanyallapay.Bills.Services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sudagoarth.sudanyallapay.Bills.Dtos.BillRequest;
import com.sudagoarth.sudanyallapay.Bills.Dtos.BillResponse;
import com.sudagoarth.sudanyallapay.Bills.Dtos.BillStatusRequest;
import com.sudagoarth.sudanyallapay.Bills.Interfaces.BillInterface;

@Service 
public class BillService implements BillInterface {

    @Override
    public Page<BillResponse> getBills(Long userId, Pageable pageable) {
        throw new UnsupportedOperationException("Unimplemented method 'getBills'");
    }

    @Override
    public BillResponse getBill(Long billId) {
        throw new UnsupportedOperationException("Unimplemented method 'getBill'");
    }

    @Override
    public BillResponse createBill(BillRequest billRequest) {
        throw new UnsupportedOperationException("Unimplemented method 'createBill'");
    }

    @Override
    public BillResponse updateBill(Long billId, BillRequest billRequest) {
        throw new UnsupportedOperationException("Unimplemented method 'updateBill'");
    }

    @Override
    public void deleteBill(Long billId) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteBill'");
    }

    @Override
    public BillResponse statusBill(Long billId, BillStatusRequest billStatusRequest) {
        throw new UnsupportedOperationException("Unimplemented method 'statusBill'");
    }

    @Override
    public BillResponse updateBillStatus(Long billId, BillStatusRequest billStatusRequest) {
        throw new UnsupportedOperationException("Unimplemented method 'updateBillStatus'");
    }

    @Override
    public BillResponse payBill(Long billId) {
        throw new UnsupportedOperationException("Unimplemented method 'payBill'");
    }

    @Override
    public BillResponse cancelBill(Long billId) {
        throw new UnsupportedOperationException("Unimplemented method 'cancelBill'");
    }
    
}
