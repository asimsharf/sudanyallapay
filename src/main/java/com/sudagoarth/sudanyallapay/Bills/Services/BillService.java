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
    public Page<BillResponse> getBills(Long referenceId, Pageable pageable) {
        throw new UnsupportedOperationException("Unimplemented method 'getBills'");
    }

    @Override
    public BillResponse getBill(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'getBill'");
    }

    @Override
    public BillResponse createBill(BillRequest billRequest) {
        throw new UnsupportedOperationException("Unimplemented method 'createBill'");
    }

    @Override
    public BillResponse updateBill(Long id, BillRequest billRequest) {
        throw new UnsupportedOperationException("Unimplemented method 'updateBill'");
    }

    @Override
    public void deleteBill(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteBill'");
    }

    @Override
    public BillResponse statusBill(Long id, BillStatusRequest billStatusRequest) {
        throw new UnsupportedOperationException("Unimplemented method 'statusBill'");
    }
    
}
