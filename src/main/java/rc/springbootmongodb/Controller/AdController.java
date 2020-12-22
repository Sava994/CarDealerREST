package rc.springbootmongodb.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rc.springbootmongodb.Dtos.CreateAdRequest;
import rc.springbootmongodb.Dtos.FilterDto;
import rc.springbootmongodb.Dtos.PageQuery;
import rc.springbootmongodb.Dtos.PaginationResponse;
import rc.springbootmongodb.EnumTypes.*;
import rc.springbootmongodb.Models.Ad;
import rc.springbootmongodb.Models.Location;
import rc.springbootmongodb.Models.User;
import rc.springbootmongodb.Repository.AdRepository;
import rc.springbootmongodb.Services.AdServices;
import rc.springbootmongodb.Services.UserServices;


import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("api/ads")
@CrossOrigin
public class AdController {
    @Autowired
    private AdRepository adRepository;

    @Autowired
    private AdServices adServices;

    @Autowired
    private UserServices userServices;

    FuelType fuelType;
    GearType gearType;
    PowertrainType powertrainType;
    VehicleCategory vehicleCategory;

    public AdController(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    //get all ads
    @GetMapping("/all")
    public ResponseEntity<PaginationResponse<Ad>> getAll(PageQuery pageQuery) {

        PaginationResponse<Ad> res = adServices.getAll(pageQuery);

        if (res.getData().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // get ads with type
    @GetMapping("/vehicle/{type}")
    public ResponseEntity<List<Ad>> getAdsWithVehicleType(@PathVariable String type) {

        List<Ad> res = adServices.getAdsWithVehicleType(type);

        if (res.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    //get ad by id
    @GetMapping("/{id}")
    public ResponseEntity<Ad> getById(@PathVariable String id) {
        Ad ad = adServices.getById(id);

        if(ad == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(ad, HttpStatus.OK);
    }

    // filter
    @PostMapping("/filter")
    public ResponseEntity<List<Ad>> filterAds(@RequestBody FilterDto filterDto) {
        List<Ad> response = adServices.filterAds(filterDto);

        if (response.isEmpty()) {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // get ads for user
    @GetMapping("/user/{id}")
    public ResponseEntity<PaginationResponse<Ad>> getUserAds(@PathVariable String id, PageQuery pageQuery) {
        PaginationResponse<Ad> res = adServices.getUserAds(id, pageQuery);

        if (res.getData().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    //create new ad
    //learn how to read json obj inside of json obj instead of separating it in requestBody
    @PostMapping("/create")
    public ResponseEntity<Ad> createAd(@RequestBody CreateAdRequest createAdRequest, Principal principal) throws ParseException {

//        String user_id = body.get("user_id");

        //check if user_id is same as user that is logged
        User user = userServices.getById(createAdRequest.getUser_id());
        if(!principal.getName().equals(user.getUsername())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }


//        String city = body.get("city");
//        String street = body.get("street");
        Location loc = new Location(createAdRequest.getCity(), createAdRequest.getStreet());

//        vehicleCategory = VehicleCategory.valueOf(createAdRequest.getVehicleCategory());
//        String vehicleType = body.get("vehicleType");
//        String make = body.get("make");
//        String model = body.get("model");
//        int travelled = (Integer.parseInt(body.get("travelled")));
//        int year_of_made = (Integer.parseInt(body.get("year_of_made")));
//        fuelType = FuelType.valueOf(createAdRequest.getFuelType());
//        double price = (Double.parseDouble(body.get("price")));
//        int engine_capacity = (Integer.parseInt(body.get("engine_capacity")));
//        gearType = GearType.valueOf(body.get("gearType"));
//        int number_of_gears = (Integer.parseInt(body.get("number_of_gears")));
//        boolean is_damaged = (Boolean.parseBoolean(body.get("is_damaged")));
//        powertrainType = PowertrainType.valueOf(body.get("powertrainType"));
//        String description = body.get("description");


//        List<String> images = body.get("images");

//        String showcaseImage = body.get("showcaseImage");

        //get current ISO date in JAVA
        TimeZone tz = TimeZone.getTimeZone("UTC+2");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        df.setTimeZone(tz);
        String nowAsISO  = df.format(new Date());

        Date created_at = df.parse(nowAsISO);

        Ad newAd = new Ad(
                createAdRequest.getUser_id(),
                loc,
                createAdRequest.getVehicleCategory(),
                createAdRequest.getVehicleType(),
                createAdRequest.getMake(),
                createAdRequest.getModel(),
                createAdRequest.getTravelled(),
                createAdRequest.getYear_of_make(),
                createAdRequest.getFuelType(),
                createAdRequest.getPrice(),
                createAdRequest.getEngine_capacity(),
                createAdRequest.getEnginePower(),
                createAdRequest.getGearType(),
                createAdRequest.getNumber_of_gears(),
                createAdRequest.isIs_damaged(),
                createAdRequest.getPowertrainType(),
                createAdRequest.getDescription(),
                created_at,
                createAdRequest.getImages(),
                createAdRequest.getShowcaseImage(),
                false
        );

        adRepository.save(newAd);

        return new ResponseEntity<>(newAd, HttpStatus.OK);
    }

    //update ad
    @PutMapping("/update/{id}")
    public ResponseEntity<Ad> update(@PathVariable String id, @RequestBody Map<String, String> body, Principal principal) {
        Ad ad = adServices.getById(id);

        //check if user_id is same as user that is logged
        User user = userServices.getById(ad.getUser_id());
        if(!principal.getName().equals(user.getUsername())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        if(ad == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        ad.setLocationCity(body.get("city"));
        ad.setLocationStreet(body.get("street"));
        ad.setVehicleCategory(VehicleCategory.valueOf(body.get("vehicleCategory")));
        ad.setVehicleType(body.get("vehicleType"));
        ad.setMake(body.get("make"));
        ad.setModel(body.get("model"));
        ad.setTravelled(Integer.parseInt(body.get("travelled")));
        ad.setYear_of_made(Integer.parseInt(body.get("year_of_make")));
        ad.setFuelType(FuelType.valueOf(body.get("fuelType")));
        ad.setPrice(Double.parseDouble(body.get("price")));
        ad.setEngine_capacity(Integer.parseInt(body.get("engine_capacity")));
        ad.setGearType(GearType.valueOf(body.get("gearType")));
        ad.setNumber_of_gears(Integer.parseInt(body.get("number_of_gears")));
        ad.setIs_damaged(Boolean.parseBoolean(body.get("is_damaged")));
        ad.setPowertrainType(PowertrainType.valueOf(body.get("powertrainType")));
        ad.setDescription(body.get("description"));
        ad.setEnginePower(Integer.parseInt(body.get("enginePower")));

        adRepository.save(ad);
        return new ResponseEntity<>(ad, HttpStatus.OK);
    }
    //delete ad
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteAd(@PathVariable String id) {
        Ad ad = adServices.getById(id);

        if(ad == null) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }

        adRepository.delete(ad);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
