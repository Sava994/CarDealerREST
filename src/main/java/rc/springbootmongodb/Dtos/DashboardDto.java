package rc.springbootmongodb.Dtos;

public class DashboardDto {
    private long users;
    private long ads;
    private long carAds;
    private long bikeAds;
    private long truckAds;

    public DashboardDto(long users, long ads, long carAds, long bikeAds, long truckAds) {
        this.users = users;
        this.ads = ads;
        this.carAds = carAds;
        this.bikeAds = bikeAds;
        this.truckAds = truckAds;
    }

    public long getUsers() {
        return users;
    }

    public void setUsers(long users) {
        this.users = users;
    }

    public long getAds() {
        return ads;
    }

    public void setAds(long ads) {
        this.ads = ads;
    }

    public long getCarAds() {
        return carAds;
    }

    public void setCarAds(long carAds) {
        this.carAds = carAds;
    }

    public long getBikeAds() {
        return bikeAds;
    }

    public void setBikeAds(long bikeAds) {
        this.bikeAds = bikeAds;
    }

    public long getTruckAds() {
        return truckAds;
    }

    public void setTruckAds(long truckAds) {
        this.truckAds = truckAds;
    }
}
