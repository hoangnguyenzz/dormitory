package com.manage.quanlykytucxa.Controller;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.manage.quanlykytucxa.domain.Hoadon;
import com.manage.quanlykytucxa.domain.SoDienNuoc;
import com.manage.quanlykytucxa.domain.User;
import com.manage.quanlykytucxa.domain.response.BillStatistics;
import com.manage.quanlykytucxa.domain.response.RestResponse;
import com.manage.quanlykytucxa.domain.response.ResultPagination;
import com.manage.quanlykytucxa.repository.HoadonRepository;
import com.manage.quanlykytucxa.service.HoaDonService;
import com.manage.quanlykytucxa.util.constant.InvoiceEnum;
import com.turkraft.springfilter.boot.Filter;

@Controller
@RequestMapping("/api/v1/hoadon")
public class HoaHonController {

    private final HoaDonService hoaDonService;
    private final HoadonRepository hoadonRepository;

    public HoaHonController(HoaDonService hoaDonService, HoadonRepository hoadonRepository) {
        this.hoadonRepository = hoadonRepository;
        this.hoaDonService = hoaDonService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<RestResponse> createHoaDon(@PathVariable("id") Long id) {
        RestResponse res = new RestResponse();
        res.setData(hoaDonService.create(id));
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @GetMapping()
    public ResponseEntity<ResultPagination> getHoaDon(

            @Filter Specification<Hoadon> spec, Pageable pageable) {

        return ResponseEntity.ok(this.hoaDonService.getAllHoaDon(spec, pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        this.hoaDonService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/status")
    public ResponseEntity<?> updateTrangThai(@RequestBody Hoadon request) {
        try {
            Hoadon hoaDon = this.hoadonRepository.findById(request.getId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy hóa đơn"));

            hoaDon.setTrangThai(request.getTrangThai());
            this.hoadonRepository.save(hoaDon);

            return ResponseEntity.ok().body("Cập nhật trạng thái thành công");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lỗi cập nhật trạng thái");
        }
    }

    @GetMapping("/statistics")
    public ResponseEntity<List<BillStatistics>> getBillStatistics(@RequestParam("roomId") Long roomId,
            @RequestParam("year") int year) {
        List<Hoadon> hoadonList = this.hoadonRepository.findByRoomIdAndYear(roomId, year);

        // Chuyển dữ liệu thành BillStatistics
        List<BillStatistics> statistics = hoadonList.stream().map(hoadon -> {
            int month = hoadon.getCreateAt()
                    .atZone(ZoneId.systemDefault())
                    .getMonthValue();

            double tongTien = hoadon.getTongTien();
            String trangThai = hoadon.getTrangThai() == InvoiceEnum.DADONG ? "1" : "0";
            return new BillStatistics(month, tongTien, trangThai);
        }).collect(Collectors.toList());

        return ResponseEntity.ok(statistics);
    }

}
