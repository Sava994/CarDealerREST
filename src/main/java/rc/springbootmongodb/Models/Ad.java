package rc.springbootmongodb.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import rc.springbootmongodb.EnumTypes.*;

import java.util.Date;
import java.util.List;

@Document(collection="Ads")
public class Ad {
    @Id
    private String id;
    private String user_id;
    private Location location;
    private VehicleCategory vehicleCategory;
    private String vehicleType;
    private String make;
    private String model;
    private int travelled;
    private int year_of_made;
    private FuelType fuelType;
    private double price;
    private int engine_capacity;
    private int enginePower;
    private GearType gearType;
    private int number_of_gears;
    private boolean is_damaged;
    private PowertrainType powertrainType;
    private String description;
    private Date created_at;
    private String[] images;
    private String showcaseImage;
    private boolean is_deleted;

    public Ad(String user_id, Location location, VehicleCategory vehicleCategory, String vehicleType, String make, String model, int travelled,
              int year_of_made, FuelType fuelType, double price, int engine_capacity, int enginePower, GearType gearType, int number_of_gears, boolean is_damaged, PowertrainType powertrainType,
              String description, Date created_at, String[] images, String showcaseImage, boolean is_deleted) {
        this.user_id = user_id;
        this.location = location;
        this.vehicleCategory = vehicleCategory;
        this.vehicleType = vehicleType;
        this.make = make;
        this.model = model;
        this.travelled = travelled;
        this.year_of_made = year_of_made;
        this.fuelType = fuelType;
        this.price = price;
        this.engine_capacity = engine_capacity;
        this.gearType = gearType;
        this.number_of_gears = number_of_gears;
        this.is_damaged = is_damaged;
        this.powertrainType = powertrainType;
        this.description = description;
        this.created_at = created_at;
        this.images = images;
        this.showcaseImage = showcaseImage;
        this.is_deleted = is_deleted;
        this.enginePower = enginePower;
    }

    //constructor without is_deleted as parameter
    @PersistenceConstructor
    public Ad(String user_id, Location location, VehicleCategory vehicleCategory, String vehicleType, String make, String model, int travelled,
              int year_of_made, FuelType fuelType, double price, int engine_capacity, int enginePower, GearType gearType, int number_of_gears, boolean is_damaged, PowertrainType powertrainType,
              String description, Date created_at, String[] images, String showcaseImage) {
        this.user_id = user_id;
        this.location = location;
        this.vehicleCategory = vehicleCategory;
        this.vehicleType = vehicleType;
        this.make = make;
        this.model = model;
        this.travelled = travelled;
        this.year_of_made = year_of_made;
        this.fuelType = fuelType;
        this.price = price;
        this.engine_capacity = engine_capacity;
        this.gearType = gearType;
        this.number_of_gears = number_of_gears;
        this.is_damaged = is_damaged;
        this.powertrainType = powertrainType;
        this.description = description;
        this.created_at = created_at;
        this.images = images;
        this.showcaseImage = showcaseImage;
        this.is_deleted = false;
        this.enginePower = enginePower;
    }

    public Ad() {}

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setLocationCity(String city) {
        this.location.setCity(city);
    }

    public void setLocationStreet(String street) {
        this.location.setStreet(street);
    }

    public void setVehicleCategory(VehicleCategory vehicleCategory) {
        this.vehicleCategory = vehicleCategory;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setTravelled(int travelled) {
        this.travelled = travelled;
    }

    public void setYear_of_made(int year_of_made) {
        this.year_of_made = year_of_made;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setEngine_capacity(int engine_capacity) {
        this.engine_capacity = engine_capacity;
    }

    public void setGearType(GearType gearType) {
        this.gearType = gearType;
    }

    public void setNumber_of_gears(int number_of_gears) {
        this.number_of_gears = number_of_gears;
    }

    public void setIs_damaged(boolean is_damaged) {
        this.is_damaged = is_damaged;
    }

    public void setPowertrainType(PowertrainType powertrainType) {
        this.powertrainType = powertrainType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public String getUser_id() {
        return user_id;
    }

    public Location getLocation() {
        return location;
    }

    public VehicleCategory getVehicleCategory() {
        return vehicleCategory;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getTravelled() {
        return travelled;
    }

    public int getYear_of_made() {
        return year_of_made;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public double getPrice() {
        return price;
    }

    public int getEngine_capacity() {
        return engine_capacity;
    }

    public GearType getGearType() {
        return gearType;
    }

    public int getNumber_of_gears() {
        return number_of_gears;
    }

    public boolean isIs_damaged() {
        return is_damaged;
    }

    public PowertrainType getPowertrainType() {
        return powertrainType;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public String[] getImages() {
        return images;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShowcaseImage() {
        return showcaseImage;
    }

    public void setShowcaseImage() {
        this.showcaseImage = showcaseImage;
    }

    public int getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(int enginePower) {
        this.enginePower = enginePower;
    }
}
