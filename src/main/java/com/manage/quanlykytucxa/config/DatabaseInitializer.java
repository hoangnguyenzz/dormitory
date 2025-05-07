package com.manage.quanlykytucxa.config;

import java.security.Permission;
import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.manage.quanlykytucxa.domain.Role;
import com.manage.quanlykytucxa.domain.Room;
import com.manage.quanlykytucxa.domain.SoDienNuoc;
import com.manage.quanlykytucxa.domain.Student;
import com.manage.quanlykytucxa.domain.User;
import com.manage.quanlykytucxa.repository.HoadonRepository;
import com.manage.quanlykytucxa.repository.RoleRepository;
import com.manage.quanlykytucxa.repository.RoomRepository;
import com.manage.quanlykytucxa.repository.SoDienNuocRepository;
import com.manage.quanlykytucxa.repository.UserRepository;
import com.manage.quanlykytucxa.service.HoaDonService;
import com.manage.quanlykytucxa.service.UserService;
import com.manage.quanlykytucxa.util.constant.GenderEnum;
import com.manage.quanlykytucxa.util.constant.RoomEnum;

@Service
public class DatabaseInitializer implements CommandLineRunner {

        private final UserRepository userRepository;
        private final RoomRepository roomRepository;
        private final RoleRepository roleRepository;
        private final UserService userService;
        private final SoDienNuocRepository soDienNuocRepository;
        private final HoadonRepository hoadonRepository;
        private final HoaDonService hoaDonService;

        public DatabaseInitializer(UserRepository userRepository, RoomRepository roomRepository,
                        RoleRepository roleRepository, UserService userService,
                        SoDienNuocRepository soDienNuocRepository,
                        HoadonRepository hoadonRepository, HoaDonService hoaDonService) {
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

                if (countRoles == 0) {
                        ArrayList<Role> roles = new ArrayList<>();
                        roles.add(new Role("ADMIN"));
                        roles.add(new Role("MANAGE"));
                        roles.add(new Role("USER"));
                        this.roleRepository.saveAll(roles);

                }

                if (countRooms == 0) {
                        ArrayList<Room> rooms = new ArrayList<>();

                        rooms.add(new Room("201", 4, 1200000, RoomEnum.TRONG));
                        rooms.add(new Room("202", 4, 1200000, RoomEnum.TRONG));
                        rooms.add(new Room("203", 4, 1200000, RoomEnum.TRONG));
                        rooms.add(new Room("204", 4, 1200000, RoomEnum.TRONG));
                        rooms.add(new Room("205", 4, 1200000, RoomEnum.TRONG));
                        rooms.add(new Room("206", 4, 1200000, RoomEnum.TRONG));
                        rooms.add(new Room("207", 4, 1200000, RoomEnum.TRONG));
                        rooms.add(new Room("208", 4, 1200000, RoomEnum.TRONG));
                        rooms.add(new Room("209", 4, 1200000, RoomEnum.TRONG));
                        rooms.add(new Room("210", 4, 1200000, RoomEnum.TRONG));
                        rooms.add(new Room("211", 4, 1200000, RoomEnum.TRONG));
                        rooms.add(new Room("212", 4, 1200000, RoomEnum.TRONG));
                        rooms.add(new Room("301", 4, 1200000, RoomEnum.TRONG));
                        rooms.add(new Room("302", 4, 1200000, RoomEnum.TRONG));
                        rooms.add(new Room("303", 4, 1200000, RoomEnum.TRONG));

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
                                        this.roomRepository.findByName("201")));

                        users.add(new User("Trần Thị Mai", "mai.tran@gmail.com", "123456", "0987654321",
                                        "https://randomuser.me/api/portraits/women/21.jpg", GenderEnum.FEMALE,
                                        this.roleRepository.findByName("USER"),
                                        new Student("CD51CT1002", "CD51CT", "Công nghệ thông tin"),
                                        this.roomRepository.findByName("202")));

                        users.add(new User("Lê Quốc Khánh", "khanh.le@gmail.com", "123456", "0909090909",
                                        "https://randomuser.me/api/portraits/men/31.jpg", GenderEnum.MALE,
                                        this.roleRepository.findByName("USER"),
                                        new Student("CD51CT1003", "CD51CT", "Công nghệ thông tin"),
                                        this.roomRepository.findByName("201")));

                        users.add(new User("Phạm Ngọc Bích", "bich.pham@gmail.com", "123456", "0933445566",
                                        "https://randomuser.me/api/portraits/women/41.jpg", GenderEnum.FEMALE,
                                        this.roleRepository.findByName("USER"),
                                        new Student("CD51CT1005", "CD51CT", "Công nghệ thông tin"),
                                        this.roomRepository.findByName("202")));

                        users.add(new User("Hoàng Minh Tú", "tu.hoang@gmail.com", "123456", "0977112233",
                                        "https://randomuser.me/api/portraits/men/51.jpg", GenderEnum.MALE,
                                        this.roleRepository.findByName("USER"),
                                        new Student("CD51CT1006", "CD51CT", "Công nghệ thông tin"),
                                        this.roomRepository.findByName("201")));

                        users.add(new User("Đặng Thị Thảo", "thao.dang@gmail.com", "123456", "0944556677",
                                        "https://randomuser.me/api/portraits/women/61.jpg", GenderEnum.FEMALE,
                                        this.roleRepository.findByName("USER"),
                                        new Student("CD51CT1007", "CD51CT", "Công nghệ thông tin"),
                                        this.roomRepository.findByName("202")));

                        users.add(new User("Võ Anh Duy", "duy.vo@gmail.com", "123456", "0966887799",
                                        "https://randomuser.me/api/portraits/men/71.jpg", GenderEnum.MALE,
                                        this.roleRepository.findByName("USER"),
                                        new Student("CD51CT1008", "CD51CT", "Công nghệ thông tin"),
                                        this.roomRepository.findByName("201")));

                        users.add(new User("Lê Thị Hồng", "hong.le@gmail.com", "123456", "0922334455",
                                        "https://randomuser.me/api/portraits/women/81.jpg", GenderEnum.FEMALE,
                                        this.roleRepository.findByName("USER"),
                                        new Student("CD51CT1009", "CD51CT", "Công nghệ thông tin"),
                                        this.roomRepository.findByName("202")));

                        users.add(new User("Bùi Văn Tài", "tai.bui@gmail.com", "123456", "0911998877",
                                        "https://randomuser.me/api/portraits/men/91.jpg", GenderEnum.MALE,
                                        this.roleRepository.findByName("USER"),
                                        new Student("CD51CT1010", "CD51CT", "Công nghệ thông tin"),
                                        this.roomRepository.findByName("203")));

                        users.add(new User("Ngô Thị Quỳnh", "quynh.ngo@gmail.com", "123456", "0955667788",
                                        "https://randomuser.me/api/portraits/women/99.jpg", GenderEnum.FEMALE,
                                        this.roleRepository.findByName("USER"),
                                        new Student("CD51CT1011", "CD51CT", "Công nghệ thông tin"),
                                        this.roomRepository.findByName("204")));

                        users.add(new User("Trần Minh Hoàng", "hoang.tran@gmail.com", "123456", "0911223344",
                                        "https://randomuser.me/api/portraits/men/11.jpg", GenderEnum.MALE,
                                        this.roleRepository.findByName("USER"),
                                        new Student("CD51CT1012", "CD51CT", "Công nghệ thông tin"),
                                        this.roomRepository.findByName("203")));

                        users.add(new User("Lê Thị Thanh", "thanh.le@gmail.com", "123456", "0933445566",
                                        "https://randomuser.me/api/portraits/women/22.jpg", GenderEnum.FEMALE,
                                        this.roleRepository.findByName("USER"),
                                        new Student("CD51CT1013", "CD51CT", "Công nghệ thông tin"),
                                        this.roomRepository.findByName("205")));

                        users.add(new User("Nguyễn Văn Hậu", "hau.nguyen@gmail.com", "123456", "0966889900",
                                        "https://randomuser.me/api/portraits/men/33.jpg", GenderEnum.MALE,
                                        this.roleRepository.findByName("USER"),
                                        new Student("CD51CT1014", "CD51CT", "Công nghệ thông tin"),
                                        this.roomRepository.findByName("203")));

                        users.add(new User("Phạm Thị Hồng", "hong.pham@gmail.com", "123456", "0944556677",
                                        "https://randomuser.me/api/portraits/women/44.jpg", GenderEnum.FEMALE,
                                        this.roleRepository.findByName("USER"),
                                        new Student("CD51CT1015", "CD51CT", "Công nghệ thông tin"),
                                        this.roomRepository.findByName("303")));

                        users.add(new User("Vũ Quang Dũng", "dung.vu@gmail.com", "123456", "0977332211",
                                        "https://randomuser.me/api/portraits/men/55.jpg", GenderEnum.MALE,
                                        this.roleRepository.findByName("USER"),
                                        new Student("CD51CT1016", "CD51CT", "Công nghệ thông tin"),
                                        this.roomRepository.findByName("206")));

                        users.add(new User("Đỗ Thị Mai", "mai.do@gmail.com", "123456", "0922113344",
                                        "https://randomuser.me/api/portraits/women/66.jpg", GenderEnum.FEMALE,
                                        this.roleRepository.findByName("USER"),
                                        new Student("CD51CT1017", "CD51CT", "Công nghệ thông tin"),
                                        this.roomRepository.findByName("207")));

                        users.add(new User("Lâm Hoàng Anh", "anh.lam@gmail.com", "123456", "0988776655",
                                        "https://randomuser.me/api/portraits/men/77.jpg", GenderEnum.MALE,
                                        this.roleRepository.findByName("USER"),
                                        new Student("CD51CT1018", "CD51CT", "Công nghệ thông tin"),
                                        this.roomRepository.findByName("301")));

                        users.add(new User("Trịnh Thị Kim", "kim.trinh@gmail.com", "123456", "0933557799",
                                        "https://randomuser.me/api/portraits/women/88.jpg", GenderEnum.FEMALE,
                                        this.roleRepository.findByName("USER"),
                                        new Student("CD51CT1019", "CD51CT", "Công nghệ thông tin"),
                                        this.roomRepository.findByName("210")));

                        users.add(new User("Bùi Minh Tuấn", "tuan.bui@gmail.com", "123456", "0900112233",
                                        "https://randomuser.me/api/portraits/men/99.jpg", GenderEnum.MALE,
                                        this.roleRepository.findByName("USER"),
                                        new Student("CD51CT1020", "CD51CT", "Công nghệ thông tin"),
                                        this.roomRepository.findByName("208")));

                        users.add(new User("Nguyễn Thị Thảo", "thao.nguyen@gmail.com", "123456", "0911998877",
                                        "https://randomuser.me/api/portraits/women/21.jpg", GenderEnum.FEMALE,
                                        this.roleRepository.findByName("USER"),
                                        new Student("CD51CT1021", "CD51CT", "Công nghệ thông tin"),
                                        this.roomRepository.findByName("209")));

                        for (User user : users) {
                                this.userService.create(user);
                        }

                }
                if (countSoDienNuoc == 0) {
                        ArrayList<SoDienNuoc> soDienNuocs = new ArrayList<>();
                        String[] roomNames = {
                                        "201", "202", "203", "204", "205", "206", "207", "208", "209", "210", "211",
                                        "212",
                                        "301", "302", "303"
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

                if (countRooms > 0 && countRoles > 0 && countUsers > 0 && countSoDienNuoc > 0 && countHoadon > 0) {
                        System.out.println(">>> SKIP INIT DATABASE ~ ALREADY HAVE DATA...");
                } else
                        System.out.println(">>> END INIT DATABASE");
        }

}
