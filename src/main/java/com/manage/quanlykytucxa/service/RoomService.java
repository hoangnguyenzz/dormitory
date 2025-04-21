package com.manage.quanlykytucxa.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.manage.quanlykytucxa.domain.Room;
import com.manage.quanlykytucxa.domain.User;
import com.manage.quanlykytucxa.domain.response.ResDangKiPhong;
import com.manage.quanlykytucxa.domain.response.ResultPagination;
import com.manage.quanlykytucxa.domain.response.Thongkephong;
import com.manage.quanlykytucxa.repository.RoomRepository;
import com.manage.quanlykytucxa.repository.StudentRepository;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final EmailService emailService;

    private final StudentRepository studentRepository;
private final UserService userService;
    private double price = 500000;

    public RoomService(RoomRepository roomRepository, StudentRepository studentRepository,EmailService emailService,UserService userService) {
        this.userService=userService;
        this.emailService=emailService;
        this.roomRepository = roomRepository;
        this.studentRepository = studentRepository;
    }

    public Room create(Room room) {
        if (this.roomRepository.existsByName(room.getName())) {
            throw new RuntimeException("Tên phòng đã tồn tại");
        }

        // if (room.getStudents() != null) {
        // List<Long> studentIds = room.getStudents().stream().map(
        // student -> student.getId()).collect(Collectors.toList());

        // List<Student> students = this.studentRepository.findByIdIn(studentIds);
        // room.setStudents(students);
        // }
        room.setPrice(price);

        return this.roomRepository.save(room);
    }

    public Room update(Room request) {

        Room roomDb = this.roomRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        roomDb.setName(request.getName());
        roomDb.setCapacity(request.getCapacity());
        roomDb.setAvailable(request.isAvailable());
        roomDb.setPrice(price);

        // if (request.getStudents() != null) {
        // List<Long> studentIds = request.getStudents().stream().map(
        // student -> student.getId()).collect(Collectors.toList());

        // List<Student> students = this.studentRepository.findByIdIn(studentIds);
        // roomDb.setStudents(students);
        // }

        return this.roomRepository.save(roomDb);
    }

    public Room getById(Long id) {
        return this.roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));
    }

    public ResultPagination getAllRooms(Specification<Room> spec, Pageable pageable) {

        Page<Room> pageRoom = this.roomRepository.findAll(spec, pageable);

        ResultPagination rs = new ResultPagination();

        ResultPagination.Meta mt = new ResultPagination.Meta();
        mt.setPage(pageRoom.getNumber() + 1);
        mt.setPageSize(pageRoom.getSize());
        mt.setPages(pageRoom.getTotalPages());
        mt.setTotal(pageRoom.getTotalElements());
        rs.setMeta(mt);
        rs.setResult(pageRoom.getContent());

        return rs;
    }

    public void deleteById(Long id) {

        this.roomRepository.deleteById(id);
    }

    public Thongkephong countRoomActive() {
        Thongkephong thongkephong = new Thongkephong();
        thongkephong.setActive(this.roomRepository.countByIsAvailableTrue());
        thongkephong.setInactive(this.roomRepository.countByIsAvailableFalse());
        return thongkephong;
    }
    public void dangKiPhong(Long id) {
        Room room=this.getById(id);
         User currentUser = this.userService.getCurrentUserWithToken();

         ResDangKiPhong dangKiPhong = new ResDangKiPhong(currentUser,room);
       
        this.emailService.sendEmailFromTemplateSync(
            "hoangbn967@gmail.com",          // Địa chỉ email người nhận
        "Thông tin đăng kí phòng",        // Tiêu đề email
            "thongtindangki",       // Template Thymeleaf   
            dangKiPhong          // Truyền đối tượng hoadon vào context Thymeleaf
        );
         this.emailService.sendEmailFromTemplateSync(
            currentUser.getEmail(),          // Địa chỉ email người nhận
        "Thông tin đăng kí phòng",        // Tiêu đề email
            "dangkiphong",       // Template Thymeleaf   
            room          // Truyền đối tượng hoadon vào context Thymeleaf
        );
    }

}
