package rc.springbootmongodb.Dtos;

import rc.springbootmongodb.EnumTypes.FuelType;
import rc.springbootmongodb.EnumTypes.GearType;
import rc.springbootmongodb.EnumTypes.VehicleCategory;

public class FilterDto {
    private VehicleCategory category;
    private String transmission;
    private String modelYearFrom;
    private String modelYearTo;
    private String maker;
    private String city;
    private int distanceFrom;
    private int distanceTo;
    private String body;
    private String fuel;
    private double priceFrom;
    private double priceTo;

    public VehicleCategory getCategory() {
        return category;
    }

    public void setCategory(VehicleCategory category) {
        this.category = category;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getModelYearFrom() {
        return modelYearFrom;
    }

    public void setModelYearFrom(String modelYearFrom) {
        this.modelYearFrom = modelYearFrom;
    }

    public String getModelYearTo() {
        return modelYearTo;
    }

    public void setModelYearTo(String modelYearTo) {
        this.modelYearTo = modelYearTo;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getDistanceFrom() {
        return distanceFrom;
    }

    public void setDistanceFrom(int distanceFrom) {
        this.distanceFrom = distanceFrom;
    }

    public int getDistanceTo() {
        return distanceTo;
    }

    public void setDistanceTo(int distanceTo) {
        this.distanceTo = distanceTo;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public double getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(double priceFrom) {
        this.priceFrom = priceFrom;
    }

    public double getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(double priceTo) {
        this.priceTo = priceTo;
    }

    public FilterDto(VehicleCategory vehicleCategory, String gearType, String modelYearFrom, String modelYearTo,
                     String manufacturer, String city, int distanceFrom, int distanceTo, String vehicleType,
                     String fuelType, double priceFrom, double priceTo) {
        this.category = vehicleCategory;
        this.transmission = gearType;
        this.modelYearFrom = modelYearFrom;
        this.modelYearTo = modelYearTo;
        this.maker = manufacturer;
        this.city = city;
        this.distanceFrom = distanceFrom;
        this.distanceTo = distanceTo;
        this.body = vehicleType;
        this.fuel = fuelType;
        this.priceFrom = priceFrom;
        this.priceTo = priceTo;
    }

    public FilterDto() {
    }
}