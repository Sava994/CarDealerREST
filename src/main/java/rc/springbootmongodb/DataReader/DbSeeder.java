package rc.springbootmongodb.DataReader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import rc.springbootmongodb.EnumTypes.*;
import rc.springbootmongodb.Models.Ad;
import rc.springbootmongodb.Models.Location;
import rc.springbootmongodb.Models.User;
import rc.springbootmongodb.Repository.AdRepository;
import rc.springbootmongodb.Repository.UserRepository;

import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class DbSeeder implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdRepository adRepository;

    private PasswordEncoder passwordEncoder;

    public DbSeeder(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        //drop all users
        this.userRepository.deleteAll();
        //drop all ads
        this.adRepository.deleteAll();

        ObjectMapper om = new ObjectMapper();

        File file = new File(".\\src\\main\\resources\\data\\user.json");
        File adFile = new File(".\\src\\main\\resources\\data\\ad.json");
        File bikeAdFile = new File(".\\src\\main\\resources\\data\\bikeAds.json");
        File truckAdFile = new File(".\\src\\main\\resources\\data\\truckAds.json");

        List<User> users = om.readValue(file, new TypeReference<List<User>>() {
        });
        List<Ad> ads = om.readValue(adFile, new TypeReference<List<Ad>>() {
        });
        List<Ad> bikeAds = om.readValue(bikeAdFile, new TypeReference<List<Ad>>() {
        });
        List<Ad> truckAds = om.readValue(truckAdFile, new TypeReference<List<Ad>>() {
        });

        this.userRepository.saveAll(users);
        this.adRepository.saveAll(ads);
        this.adRepository.saveAll(bikeAds);
        this.adRepository.saveAll(truckAds);

    }
}
