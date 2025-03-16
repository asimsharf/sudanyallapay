package com.sudagoarth.sudanyallapay.Bills.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.sudagoarth.sudanyallapay.Bills.Dtos.BillRequest;
import com.sudagoarth.sudanyallapay.Bills.Dtos.BillResponse;
import com.sudagoarth.sudanyallapay.Bills.Dtos.BillStatusRequest;
import com.sudagoarth.sudanyallapay.Bills.Interfaces.BillInterface;
import com.sudagoarth.sudanyallapay.utils.ApiResponse;
import com.sudagoarth.sudanyallapay.utils.LocaledData;

@RestController
@RequestMapping("/api/v1/bills")
public class BillController {

    @Autowired
    private BillInterface billInterface;

    private static final Logger LOGGER = LoggerFactory.getLogger(BillController.class.getName());

    @GetMapping
    public ResponseEntity<ApiResponse> getBills(@RequestParam("userId") Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        LOGGER.info("Getting bills - User ID: {} | Page: {} | Size: {}", userId, page, size);
        Pageable pageable = PageRequest.of(page, size);
        Page<BillResponse> billResponses = billInterface.getBills(userId, pageable);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        new LocaledData("Bills retrieved successfully",
                                "تم استرجاع الفواتير بنجاح"),
                        HttpStatus.OK.value(),
                        billResponses.getContent(),
                        billResponses.getPageable()));
    }

    @GetMapping("/bill")
    public ResponseEntity<ApiResponse> getBill(@RequestParam Long billId) {
        LOGGER.info("Getting bill by ID: {}", billId);
        BillResponse billResponse = billInterface.getBill(billId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        new LocaledData("Bill retrieved successfully",
                                "تم استرجاع الفاتورة بنجاح"),
                        HttpStatus.OK.value(),
                        billResponse));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createBill(@RequestBody BillRequest billRequest) {
        LOGGER.info("Creating bill: {}", billRequest);
        BillResponse billResponse = billInterface.createBill(billRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        new LocaledData("Bill created successfully",
                                "تم إنشاء الفاتورة بنجاح"),
                        HttpStatus.CREATED.value(),
                        billResponse));
    }

    @PutMapping
    public ResponseEntity<ApiResponse> updateBill(@RequestParam("billId") Long billId,
            @RequestBody BillRequest billRequest) {
        LOGGER.info("Updating bill: {}", billRequest);
        BillResponse billResponse = billInterface.updateBill(billId, billRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        new LocaledData("Bill updated successfully",
                                "تم تحديث الفاتورة بنجاح"),
                        HttpStatus.OK.value(),
                        billResponse));
    }

    @PutMapping("/status")
    public ResponseEntity<ApiResponse> updateBillStatus(@RequestParam("billId") Long billId,
            @RequestBody BillStatusRequest billStatusRequest) {
        LOGGER.info("Updating bill status: {}", billStatusRequest);
        BillResponse billResponse = billInterface.updateBillStatus(billId, billStatusRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        new LocaledData("Bill status updated successfully",
                                "تم تحديث حالة الفاتورة بنجاح"),
                        HttpStatus.OK.value(),
                        billResponse));
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteBill(@RequestParam Long billId) {
        LOGGER.info("Deleting bill by ID: {}", billId);
        billInterface.deleteBill(billId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        new LocaledData("Bill deleted successfully",
                                "تم حذف الفاتورة بنجاح"),
                        HttpStatus.OK.value(), null));
    }

    @PostMapping("/pay")
    public ResponseEntity<ApiResponse> payBill(@RequestParam Long billId) {
        LOGGER.info("Paying bill by ID: {}", billId);
        BillResponse billResponse = billInterface.payBill(billId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        new LocaledData("Bill paid successfully",
                                "تم دفع الفاتورة بنجاح"),
                        HttpStatus.OK.value(),
                        billResponse));
    }

    @PostMapping("/cancel")
    public ResponseEntity<ApiResponse> cancelBill(@RequestParam Long billId) {
        LOGGER.info("Cancelling bill by ID: {}", billId);
        BillResponse billResponse = billInterface.cancelBill(billId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        new LocaledData("Bill cancelled successfully",
                                "تم إلغاء الفاتورة بنجاح"),
                        HttpStatus.OK.value(),
                        billResponse));
    }

}
