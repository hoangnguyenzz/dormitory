package com.manage.quanlykytucxa.config;

import java.security.Permission;
import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.manage.quanlykytucxa.domain.NguoiDiLam;
import com.manage.quanlykytucxa.domain.Role;
import com.manage.quanlykytucxa.domain.Room;
import com.manage.quanlykytucxa.domain.SoDienNuoc;
import com.manage.quanlykytucxa.domain.Student;
import com.manage.quanlykytucxa.domain.User;
import com.manage.quanlykytucxa.domain.Vipham;
import com.manage.quanlykytucxa.repository.HoadonRepository;
import com.manage.quanlykytucxa.repository.RoleRepository;
import com.manage.quanlykytucxa.repository.RoomRepository;
import com.manage.quanlykytucxa.repository.SoDienNuocRepository;
import com.manage.quanlykytucxa.repository.UserRepository;
import com.manage.quanlykytucxa.repository.ViphamRepository;
import com.manage.quanlykytucxa.service.HoaDonService;
import com.manage.quanlykytucxa.service.UserService;
import com.manage.quanlykytucxa.service.ViphamService;
import com.manage.quanlykytucxa.util.constant.DoiTuongEnum;
import com.manage.quanlykytucxa.util.constant.GenderEnum;
import com.manage.quanlykytucxa.util.constant.RoomEnum;
import com.manage.quanlykytucxa.util.constant.XuLyViPhamEnum;

@Service
public class DatabaseInitializer implements CommandLineRunner {

        private final UserRepository userRepository;
        private final RoomRepository roomRepository;
        private final RoleRepository roleRepository;
        private final UserService userService;
        private final SoDienNuocRepository soDienNuocRepository;
        private final HoadonRepository hoadonRepository;
        private final HoaDonService hoaDonService;
        private final ViphamRepository viphamRepository;

        public DatabaseInitializer(UserRepository userRepository, RoomRepository roomRepository,
                        RoleRepository roleRepository, UserService userService,
                        SoDienNuocRepository soDienNuocRepository,
                        HoadonRepository hoadonRepository, HoaDonService hoaDonService,
                        ViphamRepository viphamRepository) {
                this.viphamRepository = viphamRepository;
                this.hoaDonService = hoaDonService;
                this.hoadonRepository = hoadonRepository;
                this.soDienNuocRepository = soDienNuocRepository;
                this.userService = userService;
                this.roleRepository = roleRepository;
                this.userRepository = userRepository;
                this.roomRepository = roomRepository;
        }

        @Override
        public void run(String... args) throws Exception {
                System.out.println(">>> START INIT DATABASE");
                long countRooms = this.roomRepository.count();
                long countRoles = this.roleRepository.count();
                long countUsers = this.userRepository.count();
                long countSoDienNuoc = this.soDienNuocRepository.count();
                long countHoadon = this.hoadonRepository.count();
                long countViphams = this.userRepository.count();

                if (countRoles == 0) {
                        ArrayList<Role> roles = new ArrayList<>();
                        roles.add(new Role("ADMIN"));
                        roles.add(new Role("MANAGE"));
                        roles.add(new Role("USER"));
                        this.roleRepository.saveAll(roles);

                }

                if (countRooms == 0) {
                        ArrayList<Room> rooms = new ArrayList<>();

                        rooms.add(new Room("K2-201", 4, 1200000, DoiTuongEnum.STUDENT, RoomEnum.TRONG));
                        rooms.add(new Room("K2-202", 4, 1200000, DoiTuongEnum.STUDENT, RoomEnum.TRONG));
                        rooms.add(new Room("K2-203", 4, 1200000, DoiTuongEnum.STUDENT, RoomEnum.TRONG));
                        rooms.add(new Room("K2-204", 4, 1200000, DoiTuongEnum.STUDENT, RoomEnum.TRONG));
                        rooms.add(new Room("K2-205", 4, 1200000, DoiTuongEnum.STUDENT, RoomEnum.TRONG));
                        rooms.add(new Room("K2-206", 4, 1200000, DoiTuongEnum.STUDENT, RoomEnum.TRONG));
                        rooms.add(new Room("K2-207", 4, 1200000, DoiTuongEnum.STUDENT, RoomEnum.TRONG));
                        rooms.add(new Room("K2-208", 4, 1200000, DoiTuongEnum.STUDENT, RoomEnum.TRONG));
                        rooms.add(new Room("K2-209", 4, 1200000, DoiTuongEnum.STUDENT, RoomEnum.TRONG));
                        rooms.add(new Room("K2-210", 4, 1200000, DoiTuongEnum.STUDENT, RoomEnum.TRONG));
                        rooms.add(new Room("K2-211", 4, 1200000, DoiTuongEnum.STUDENT, RoomEnum.TRONG));
                        rooms.add(new Room("K2-212", 4, 1200000, DoiTuongEnum.STUDENT, RoomEnum.TRONG));
                        rooms.add(new Room("K2-301", 4, 1200000, DoiTuongEnum.STUDENT, RoomEnum.TRONG));
                        rooms.add(new Room("K2-302", 4, 1200000, DoiTuongEnum.STUDENT, RoomEnum.TRONG));
                        rooms.add(new Room("K2-303", 4, 1200000, DoiTuongEnum.STUDENT, RoomEnum.TRONG));
                        rooms.add(new Room("K1-401", 4, 1200000, DoiTuongEnum.WORKER, RoomEnum.TRONG));
                        rooms.add(new Room("K1-402", 4, 1200000, DoiTuongEnum.WORKER, RoomEnum.TRONG));
                        rooms.add(new Room("K1-403", 4, 1200000, DoiTuongEnum.WORKER, RoomEnum.TRONG));
                        rooms.add(new Room("K1-404", 4, 1200000, DoiTuongEnum.WORKER, RoomEnum.TRONG));
                        rooms.add(new Room("K1-405", 4, 1200000, DoiTuongEnum.WORKER, RoomEnum.TRONG));
                        rooms.add(new Room("K1-406", 4, 1200000, DoiTuongEnum.WORKER, RoomEnum.TRONG));
                        rooms.add(new Room("K1-407", 4, 1200000, DoiTuongEnum.WORKER, RoomEnum.TRONG));
                        rooms.add(new Room("K1-408", 4, 1200000, DoiTuongEnum.WORKER, RoomEnum.TRONG));
                        rooms.add(new Room("K1-409", 4, 1200000, DoiTuongEnum.WORKER, RoomEnum.TRONG));
                        rooms.add(new Room("K1-410", 4, 1200000, DoiTuongEnum.WORKER, RoomEnum.TRONG));
                        rooms.add(new Room("K1-411", 4, 1200000, DoiTuongEnum.WORKER, RoomEnum.TRONG));
                        rooms.add(new Room("K1-412", 4, 1200000, DoiTuongEnum.WORKER, RoomEnum.TRONG));

                        this.roomRepository.saveAll(rooms);

                }
                if (countUsers == 0) {
                        ArrayList<User> users = new ArrayList<>();
                        users.add(new User("Admin", "admin@gmail.com", "123456", "0123456789",
                                        "https://res.cloudinary.com/dqtnkkapg/image/upload/v1745070115/sontung_osejbm.jpg",
                                        GenderEnum.MALE,
                                        this.roleRepository.findByName("ADMIN"),
                                        new Student("CD51CT1004", "CD51CT", "CNTT"), null));

                        users.add(new User("Nguyễn Văn Hưng", "hung.nguyen@gmail.com", "123456", "0912345678",
                                        "https://randomuser.me/api/portraits/men/11.jpg", GenderEnum.MALE,
                                        this.roleRepository.findByName("USER"),
                                        new Student("CD51CT1001", "CD51CT", "Công nghệ thông tin"),
                                        this.roomRepository.findByName("K2-201")));

                        users.add(new User("Trần Thị Mai", "mai.tran@gmail.com", "123456", "0987654321",
                                        "https://randomuser.me/api/portraits/women/21.jpg", GenderEnum.FEMALE,
                                        this.roleRepository.findByName("USER"),
                                        new Student("CD51CT1002", "CD51CT", "Công nghệ thông tin"),
                                        this.roomRepository.findByName("K2-202")));

                        users.add(new User("Lê Quốc Khánh", "khanh.le@gmail.com", "123456", "0909090909",
                                        "https://randomuser.me/api/portraits/men/31.jpg", GenderEnum.MALE,
                                        this.roleRepository.findByName("USER"),
                                        new Student("CD51CT1003", "CD51CT", "Công nghệ thông tin"),
                                        this.roomRepository.findByName("K2-201")));

                        users.add(new User("Phạm Ngọc Bích", "bich.pham@gmail.com", "123456", "0933445566",
                                        "https://randomuser.me/api/portraits/women/41.jpg", GenderEnum.FEMALE,
                                        this.roleRepository.findByName("USER"),
                                        new Student("CD51CT1005", "CD51CT", "Công nghệ thông tin"),
                                        this.roomRepository.findByName("K2-202")));

                        users.add(new User("Hoàng Minh Tú", "tu.hoang@gmail.com", "123456", "0977112233",
                                        "https://randomuser.me/api/portraits/men/51.jpg", GenderEnum.MALE,
                                        this.roleRepository.findByName("USER"),
                                        new Student("CD51CT1006", "CD51CT", "Công nghệ thông tin"),
                                        this.roomRepository.findByName("K2-201")));

                        users.add(new User("Đặng Thị Thảo", "thao.dang@gmail.com", "123456", "0944556677",
                                        "https://randomuser.me/api/portraits/women/61.jpg", GenderEnum.FEMALE,
                                        this.roleRepository.findByName("USER"),
                                        new Student("CD51CT1007", "CD51CT", "Công nghệ thông tin"),
                                        this.roomRepository.findByName("K2-202")));

                        users.add(new User("Võ Anh Duy", "duy.vo@gmail.com", "123456", "0966887799",
                                        "https://randomuser.me/api/portraits/men/71.jpg", GenderEnum.MALE,
                                        this.roleRepository.findByName("USER"),
                                        new Student("CD51CT1008", "CD51CT", "Công nghệ thông tin"),
                                        this.roomRepository.findByName("K2-201")));

                        users.add(new User("Lê Thị Hồng", "hong.le@gmail.com", "123456", "0922334455",
                                        "https://randomuser.me/api/portraits/women/81.jpg", GenderEnum.FEMALE,
                                        this.roleRepository.findByName("USER"),
                                        new Student("CD51CT1009", "CD51CT", "Công nghệ thông tin"),
                                        this.roomRepository.findByName("K2-202")));

                        users.add(new User("Bùi Văn Tài", "tai.bui@gmail.com", "123456", "0911998877",
                                        "https://randomuser.me/api/portraits/men/91.jpg", GenderEnum.MALE,
                                        this.roleRepository.findByName("USER"),
                                        new Student("CD51CT1010", "CD51CT", "Công nghệ thông tin"),
                                        this.roomRepository.findByName("K2-203")));

                        users.add(new User("Ngô Thị Quỳnh", "quynh.ngo@gmail.com", "123456", "0955667788",
                                        "https://randomuser.me/api/portraits/women/99.jpg", GenderEnum.FEMALE,
                                        this.roleRepository.findByName("USER"),
                                        new Student("CD51CT1011", "CD51CT", "Công nghệ thông tin"),
                                        this.roomRepository.findByName("K2-204")));

                        users.add(new User("Trần Minh Hoàng", "hoang.tran@gmail.com", "123456", "0911223344",
                                        "https://randomuser.me/api/portraits/men/11.jpg", GenderEnum.MALE,
                                        this.roleRepository.findByName("USER"),
                                        new Student("CD51CT1012", "CD51CT", "Công nghệ thông tin"),
                                        this.roomRepository.findByName("K2-203")));

                        users.add(new User("Lê Thị Thanh", "thanh.le@gmail.com", "123456", "0933445566",
                                        "https://randomuser.me/api/portraits/women/22.jpg", GenderEnum.FEMALE,
                                        this.roleRepository.findByName("USER"),
                                        new Student("CD51CT1013", "CD51CT", "Công nghệ thông tin"),
                                        this.roomRepository.findByName("K2-205")));

                        users.add(new User("Nguyễn Văn Hậu", "hau.nguyen@gmail.com", "123456", "0966889900",
                                        "https://randomuser.me/api/portraits/men/33.jpg", GenderEnum.MALE,
                                        this.roleRepository.findByName("USER"),
                                        new Student("CD51CT1014", "CD51CT", "Công nghệ thông tin"),
                                        this.roomRepository.findByName("K2-203")));

                        users.add(new User("Phạm Thị Hồng", "hong.pham@gmail.com", "123456", "0944556677",
                                        "https://randomuser.me/api/portraits/women/44.jpg", GenderEnum.FEMALE,
                                        this.roleRepository.findByName("USER"),
                                        new Student("CD51CT1015", "CD51CT", "Công nghệ thông tin"),
                                        this.roomRepository.findByName("K2-303")));

                        users.add(new User("Vũ Quang Dũng", "dung.vu@gmail.com", "123456", "0977332211",
                                        "https://randomuser.me/api/portraits/men/55.jpg", GenderEnum.MALE,
                                        this.roleRepository.findByName("USER"),
                                        new Student("CD51CT1016", "CD51CT", "Công nghệ thông tin"),
                                        this.roomRepository.findByName("K2-206")));

                        users.add(new User("Đỗ Thị Mai", "mai.do@gmail.com", "123456", "0922113344",
                                        "https://randomuser.me/api/portraits/women/66.jpg", GenderEnum.FEMALE,
                                        this.roleRepository.findByName("USER"),
                                        new Student("CD51CT1017", "CD51CT", "Công nghệ thông tin"),
                                        this.roomRepository.findByName("K2-207")));

                        users.add(new User("Lâm Hoàng Anh", "anh.lam@gmail.com", "123456", "0988776655",
                                        "https://randomuser.me/api/portraits/men/77.jpg", GenderEnum.MALE,
                                        this.roleRepository.findByName("USER"),
                                        new Student("CD51CT1018", "CD51CT", "Công nghệ thông tin"),
                                        this.roomRepository.findByName("K2-301")));

                        users.add(new User("Trịnh Thị Kim", "kim.trinh@gmail.com", "123456", "0933557799",
                                        "https://randomuser.me/api/portraits/women/88.jpg", GenderEnum.FEMALE,
                                        this.roleRepository.findByName("USER"),
                                        new Student("CD51CT1019", "CD51CT", "Công nghệ thông tin"),
                                        this.roomRepository.findByName("K2-210")));

                        users.add(new User("Bùi Minh Tuấn", "tuan.bui@gmail.com", "123456", "0900112233",
                                        "https://randomuser.me/api/portraits/men/99.jpg", GenderEnum.MALE,
                                        this.roleRepository.findByName("USER"),
                                        new Student("CD51CT1020", "CD51CT", "Công nghệ thông tin"),
                                        this.roomRepository.findByName("K2-208")));

                        users.add(new User("Nguyễn Thị Thảo", "thao.nguyen@gmail.com", "123456", "0911998877",
                                        "https://randomuser.me/api/portraits/women/21.jpg", GenderEnum.FEMALE,
                                        this.roleRepository.findByName("USER"),
                                        new Student("CD51CT1021", "CD51CT", "Công nghệ thông tin"),
                                        this.roomRepository.findByName("K2-209")));

                        users.add(new User("Nguyễn Thị Hương", "huong.nguyen@gmail.com", "123456", "0911000001",
                                        "https://randomuser.me/api/portraits/women/21.jpg", GenderEnum.FEMALE,
                                        this.roleRepository.findByName("USER"),
                                        new NguoiDiLam("Nhân viên ngân hàng", "Quận 1, TP.HCM"),
                                        this.roomRepository.findByName("K1-401")));

                        users.add(new User("Trần Minh Hùng", "hung.tran@gmail.com", "123456", "0911000002",
                                        "https://randomuser.me/api/portraits/men/22.jpg", GenderEnum.MALE,
                                        this.roleRepository.findByName("USER"),
                                        new NguoiDiLam("Lập trình viên", "Cầu Giấy, Hà Nội"),
                                        this.roomRepository.findByName("K1-402")));

                        users.add(new User("Lê Thị Thu Trang", "trang.le@gmail.com", "123456", "0911000003",
                                        "https://randomuser.me/api/portraits/women/23.jpg", GenderEnum.FEMALE,
                                        this.roleRepository.findByName("USER"),
                                        new NguoiDiLam("Giáo viên mầm non", "Thanh Xuân, Hà Nội"),
                                        this.roomRepository.findByName("K1-403")));

                        users.add(new User("Phạm Quang Huy", "huy.pham@gmail.com", "123456", "0911000004",
                                        "https://randomuser.me/api/portraits/men/24.jpg", GenderEnum.MALE,
                                        this.roleRepository.findByName("USER"),
                                        new NguoiDiLam("Chuyên viên truyền thông", "Bình Thạnh, TP.HCM"),
                                        this.roomRepository.findByName("K1-404")));

                        users.add(new User("Đặng Thị Mai", "mai.dang@gmail.com", "123456", "0911000005",
                                        "https://randomuser.me/api/portraits/women/25.jpg", GenderEnum.FEMALE,
                                        this.roleRepository.findByName("USER"),
                                        new NguoiDiLam("Nhân viên bán hàng", "Thủ Đức, TP.HCM"),
                                        this.roomRepository.findByName("K1-405")));

                        users.add(new User("Võ Tuấn Anh", "anh.vo@gmail.com", "123456", "0911000006",
                                        "https://randomuser.me/api/portraits/men/26.jpg", GenderEnum.MALE,
                                        this.roleRepository.findByName("USER"),
                                        new NguoiDiLam("Kỹ thuật viên điện tử", "Tân Bình, TP.HCM"),
                                        this.roomRepository.findByName("K1-406")));

                        users.add(new User("Ngô Thị Lan", "lan.ngo@gmail.com", "123456", "0911000007",
                                        "https://randomuser.me/api/portraits/women/27.jpg", GenderEnum.FEMALE,
                                        this.roleRepository.findByName("USER"),
                                        new NguoiDiLam("Nhân viên hành chính", "Hải Châu, Đà Nẵng"),
                                        this.roomRepository.findByName("K1-407")));

                        users.add(new User("Huỳnh Thanh Tùng", "tung.huynh@gmail.com", "123456", "0911000008",
                                        "https://randomuser.me/api/portraits/men/28.jpg", GenderEnum.MALE,
                                        this.roleRepository.findByName("USER"),
                                        new NguoiDiLam("Chuyên viên pháp lý", "Ninh Kiều, Cần Thơ"),
                                        this.roomRepository.findByName("K1-408")));

                        users.add(new User("Bùi Thị Kim Ngân", "ngan.bui@gmail.com", "123456", "0911000009",
                                        "https://randomuser.me/api/portraits/women/29.jpg", GenderEnum.FEMALE,
                                        this.roleRepository.findByName("USER"),
                                        new NguoiDiLam("Tư vấn viên tuyển sinh", "Nam Từ Liêm, Hà Nội"),
                                        this.roomRepository.findByName("K1-409")));

                        users.add(new User("Tạ Văn Lâm", "lam.ta@gmail.com", "123456", "0911000010",
                                        "https://randomuser.me/api/portraits/men/30.jpg", GenderEnum.MALE,
                                        this.roleRepository.findByName("USER"),
                                        new NguoiDiLam("Bác sĩ đa khoa", "Phú Nhuận, TP.HCM"),
                                        this.roomRepository.findByName("K1-410")));

                        for (User user : users) {
                                this.userService.create(user);
                        }

                }
                if (countSoDienNuoc == 0) {
                        ArrayList<SoDienNuoc> soDienNuocs = new ArrayList<>();
                        String[] roomNames = {
                                        "K2-201", "K2-202", "K2-203", "K2-204", "K2-205", "K2-206", "K2-207", "K2-208",
                                        "K2-209", "K2-210", "K2-211", "K2-212",
                                        "K2-301", "K2-302", "K2-303"
                        };

                        for (String roomName : roomNames) {
                                Room room = this.roomRepository.findByName(roomName);

                                int soDien = 0;
                                int soNuoc = 0;

                                for (int month = 1; month <= 4; month++) {
                                        LocalDate lastDayOfMonth = YearMonth.of(2025, month).atEndOfMonth();
                                        Instant createAt = lastDayOfMonth.atStartOfDay(ZoneId.systemDefault())
                                                        .toInstant();

                                        int soDienDau = soDien;
                                        soDien += 50;
                                        int soDienCuoi = soDien;

                                        int soNuocDau = soNuoc;
                                        soNuoc += 20;
                                        int soNuocCuoi = soNuoc;

                                        SoDienNuoc record = new SoDienNuoc();
                                        record.setRoom(room);
                                        record.setSoDienDau(soDienDau);
                                        record.setSoDienCuoi(soDienCuoi);
                                        record.setSoNuocDau(soNuocDau);
                                        record.setSoNuocCuoi(soNuocCuoi);
                                        record.setCreateAt(createAt);

                                        soDienNuocs.add(record);
                                }
                        }
                        this.soDienNuocRepository.saveAll(soDienNuocs);
                        for (SoDienNuoc soDienNuoc : soDienNuocs) {
                                this.hoaDonService.create(soDienNuoc.getId());
                        }
                }
                if (countViphams == 0) {
                        ArrayList<Vipham> viphams = new ArrayList<>();
                        viphams.add(new Vipham("Nguyễn Thị Hương", "401", "Để đồ đạc bừa bãi trong phòng",
                                        XuLyViPhamEnum.CHUAXULY));
                        viphams.add(new Vipham("Trần Minh Hoàng", "402", "Mở nhạc lớn gây ảnh hưởng đến người khác",
                                        XuLyViPhamEnum.CHUAXULY));
                        viphams.add(new Vipham("Lê Thị Thu Trang", "403", "Không tắt điện khi ra khỏi phòng",
                                        XuLyViPhamEnum.CHUAXULY));
                        viphams.add(new Vipham("Phạm Quang Huy", "404", "Hút thuốc trong khu vực cấm",
                                        XuLyViPhamEnum.CHUAXULY));
                        viphams.add(new Vipham("Đặng Thị Mai", "405", "Mang thú cưng vào phòng",
                                        XuLyViPhamEnum.CHUAXULY));
                        viphams.add(new Vipham("Võ Tuấn Anh", "406", "Làm ồn vào ban đêm", XuLyViPhamEnum.CHUAXULY));
                        viphams.add(new Vipham("Ngô Thị Lan", "407", "Vứt rác không đúng nơi quy định",
                                        XuLyViPhamEnum.CHUAXULY));
                        viphams.add(new Vipham("Huỳnh Thanh Tùng", "408", "Tự ý sửa đổi thiết bị trong phòng",
                                        XuLyViPhamEnum.CHUAXULY));
                        viphams.add(new Vipham("Bùi Thị Kim Ngân", "409", "Mời người lạ vào phòng mà không đăng ký",
                                        XuLyViPhamEnum.CHUAXULY));
                        viphams.add(new Vipham("Tạ Văn Lâm", "410", "Không tham gia vệ sinh chung định kỳ",
                                        XuLyViPhamEnum.CHUAXULY));
                        this.viphamRepository.saveAll(viphams);
                }

                if (countRooms > 0 && countRoles > 0 && countUsers > 0 && countSoDienNuoc > 0 && countHoadon > 0) {
                        System.out.println(">>> SKIP INIT DATABASE ~ ALREADY HAVE DATA...");
                } else
                        System.out.println(">>> END INIT DATABASE");
        }

}
