package com.manage.quanlykytucxa.config;

import java.security.Permission;
import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.manage.quanlykytucxa.domain.Role;
import com.manage.quanlykytucxa.domain.Room;
import com.manage.quanlykytucxa.domain.Student;
import com.manage.quanlykytucxa.domain.User;
import com.manage.quanlykytucxa.repository.RoleRepository;
import com.manage.quanlykytucxa.repository.RoomRepository;
import com.manage.quanlykytucxa.repository.UserRepository;
import com.manage.quanlykytucxa.service.UserService;
import com.manage.quanlykytucxa.util.constant.GenderEnum;
import com.manage.quanlykytucxa.util.constant.RoomEnum;

@Service
public class DatabaseInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final RoleRepository roleRepository;
    private final UserService userService;

    public DatabaseInitializer(UserRepository userRepository, RoomRepository roomRepository,
            RoleRepository roleRepository, UserService userService) {
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

        if (countRoles == 0) {
            ArrayList<Role> roles = new ArrayList<>();
            roles.add(new Role("ADMIN"));
            roles.add(new Role("MANAGE"));
            roles.add(new Role("USER"));
            this.roleRepository.saveAll(roles);

        }
        if (countUsers == 0) {
            ArrayList<User> users = new ArrayList<>();
            users.add(new User("Admin", "admin@gmail.com", "123456", "0123456789",
                    "https://res.cloudinary.com/dqtnkkapg/image/upload/v1745070115/sontung_osejbm.jpg", GenderEnum.MALE,
                    this.roleRepository.findByName("ADMIN"), new Student("CD51CT1004", "CD51CT", "CNTT")));

            users.add(new User("Nguyễn Văn Hưng", "hung.nguyen@gmail.com", "123456", "0912345678",
                    "https://randomuser.me/api/portraits/men/11.jpg", GenderEnum.MALE,
                    this.roleRepository.findByName("USER"),
                    new Student("CD51CT1001", "CD51CT", "Công nghệ thông tin")));

            users.add(new User("Trần Thị Mai", "mai.tran@gmail.com", "123456", "0987654321",
                    "https://randomuser.me/api/portraits/women/21.jpg", GenderEnum.FEMALE,
                    this.roleRepository.findByName("USER"),
                    new Student("CD51CT1002", "CD51CT", "Công nghệ thông tin")));

            users.add(new User("Lê Quốc Khánh", "khanh.le@gmail.com", "123456", "0909090909",
                    "https://randomuser.me/api/portraits/men/31.jpg", GenderEnum.MALE,
                    this.roleRepository.findByName("USER"),
                    new Student("CD51CT1003", "CD51CT", "Công nghệ thông tin")));

            users.add(new User("Phạm Ngọc Bích", "bich.pham@gmail.com", "123456", "0933445566",
                    "https://randomuser.me/api/portraits/women/41.jpg", GenderEnum.FEMALE,
                    this.roleRepository.findByName("USER"),
                    new Student("CD51CT1005", "CD51CT", "Công nghệ thông tin")));

            users.add(new User("Hoàng Minh Tú", "tu.hoang@gmail.com", "123456", "0977112233",
                    "https://randomuser.me/api/portraits/men/51.jpg", GenderEnum.MALE,
                    this.roleRepository.findByName("USER"),
                    new Student("CD51CT1006", "CD51CT", "Công nghệ thông tin")));

            users.add(new User("Đặng Thị Thảo", "thao.dang@gmail.com", "123456", "0944556677",
                    "https://randomuser.me/api/portraits/women/61.jpg", GenderEnum.FEMALE,
                    this.roleRepository.findByName("USER"),
                    new Student("CD51CT1007", "CD51CT", "Công nghệ thông tin")));

            users.add(new User("Võ Anh Duy", "duy.vo@gmail.com", "123456", "0966887799",
                    "https://randomuser.me/api/portraits/men/71.jpg", GenderEnum.MALE,
                    this.roleRepository.findByName("USER"),
                    new Student("CD51CT1008", "CD51CT", "Công nghệ thông tin")));

            users.add(new User("Lê Thị Hồng", "hong.le@gmail.com", "123456", "0922334455",
                    "https://randomuser.me/api/portraits/women/81.jpg", GenderEnum.FEMALE,
                    this.roleRepository.findByName("USER"),
                    new Student("CD51CT1009", "CD51CT", "Công nghệ thông tin")));

            users.add(new User("Bùi Văn Tài", "tai.bui@gmail.com", "123456", "0911998877",
                    "https://randomuser.me/api/portraits/men/91.jpg", GenderEnum.MALE,
                    this.roleRepository.findByName("USER"),
                    new Student("CD51CT1010", "CD51CT", "Công nghệ thông tin")));

            users.add(new User("Ngô Thị Quỳnh", "quynh.ngo@gmail.com", "123456", "0955667788",
                    "https://randomuser.me/api/portraits/women/99.jpg", GenderEnum.FEMALE,
                    this.roleRepository.findByName("USER"),
                    new Student("CD51CT1011", "CD51CT", "Công nghệ thông tin")));

            for (User user : users) {
                this.userService.create(user);
            }

        }

        if (countRooms == 0) {
            ArrayList<Room> rooms = new ArrayList<>();

            rooms.add(new Room("201", 4, 1200000, RoomEnum.TRONG));
            rooms.add(new Room("202", 4, 1200000,  RoomEnum.TRONG));
            rooms.add(new Room("203", 4, 1200000,  RoomEnum.TRONG));
            rooms.add(new Room("204", 4, 1200000,  RoomEnum.TRONG));
            rooms.add(new Room("205", 4, 1200000,  RoomEnum.TRONG));
            rooms.add(new Room("206", 4, 1200000,  RoomEnum.TRONG));
            rooms.add(new Room("207", 4, 1200000,  RoomEnum.TRONG));
            rooms.add(new Room("208", 4, 1200000,  RoomEnum.TRONG));
            rooms.add(new Room("209", 4, 1200000,  RoomEnum.TRONG));
            rooms.add(new Room("210", 4, 1200000,  RoomEnum.TRONG));
            rooms.add(new Room("211", 4, 1200000,  RoomEnum.TRONG));
            rooms.add(new Room("212", 4, 1200000,  RoomEnum.TRONG));
            rooms.add(new Room("301", 4, 1200000,  RoomEnum.TRONG));
            rooms.add(new Room("302", 4, 1200000,  RoomEnum.TRONG));
            rooms.add(new Room("303", 4, 1200000,  RoomEnum.TRONG));

            this.roomRepository.saveAll(rooms);

        }
        if (countRooms > 0 && countRoles > 0 && countUsers > 0) {
            System.out.println(">>> SKIP INIT DATABASE ~ ALREADY HAVE DATA...");
        } else
            System.out.println(">>> END INIT DATABASE");
    }

}
