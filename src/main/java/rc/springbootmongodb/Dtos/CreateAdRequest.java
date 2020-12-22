package rc.springbootmongodb.Dtos;

import rc.springbootmongodb.EnumTypes.FuelType;
import rc.springbootmongodb.EnumTypes.GearType;
import rc.springbootmongodb.EnumTypes.PowertrainType;
import rc.springbootmongodb.EnumTypes.VehicleCategory;

public class CreateAdRequest {
    private String user_id;
    private String city;
    private String street;
    private VehicleCategory vehicleCategory;
    private String vehicleType;
    private String make;
    private String model;
    private int travelled;
    private int year_of_make;
    private FuelType fuelType;
    private double price;
    private int engine_capacity;
    private int enginePower;
    private GearType gearType;
    private int number_of_gears;
    private boolean is_damaged;
    private PowertrainType powertrainType;
    private String description;
    private String[] images;
    private String showcaseImage;


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public VehicleCategory getVehicleCategory() {
        return vehicleCategory;
    }

    public void setVehicleCategory(VehicleCategory vehicleCategory) {
        this.vehicleCategory = vehicleCategory;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getTravelled() {
        return travelled;
    }

    public void setTravelled(int travelled) {
        this.travelled = travelled;
    }

    public int getYear_of_make() {
        return year_of_make;
    }

    public void setYear_of_make(int year_of_make) {
        this.year_of_make = year_of_make;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getEngine_capacity() {
        return engine_capacity;
    }

    public void setEngine_capacity(int engine_capacity) {
        this.engine_capacity = engine_capacity;
    }

    public GearType getGearType() {
        return gearType;
    }

    public void setGearType(GearType gearType) {
        this.gearType = gearType;
    }

    public int getNumber_of_gears() {
        return number_of_gears;
    }

    public void setNumber_of_gears(int number_of_gears) {
        this.number_of_gears = number_of_gears;
    }

    public boolean isIs_damaged() {
        return is_damaged;
    }

    public void setIs_damaged(boolean is_damaged) {
        this.is_damaged = is_damaged;
    }

    public PowertrainType getPowertrainType() {
        return powertrainType;
    }

    public void setPowertrainType(PowertrainType powertrainType) {
        this.powertrainType = powertrainType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public String getShowcaseImage() {
        return showcaseImage;
    }

    public void setShowcaseImage(String showcaseImage) {
        this.showcaseImage = showcaseImage;
    }

    public int getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(int enginePower) {
        this.enginePower = enginePower;
    }
}
