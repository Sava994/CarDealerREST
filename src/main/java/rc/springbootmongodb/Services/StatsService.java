package rc.springbootmongodb.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import rc.springbootmongodb.Dtos.DashboardDto;
import rc.springbootmongodb.Dtos.PageQuery;
import rc.springbootmongodb.Dtos.PaginationResponse;
import rc.springbootmongodb.Models.Ad;
import rc.springbootmongodb.Models.User;
import rc.springbootmongodb.Repository.AdRepository;
import rc.springbootmongodb.Repository.UserRepository;

@Service
public class StatsService {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdRepository adRepository;

    public DashboardDto getDashboardStats() {
        Query carQuery = new Query();
        carQuery.addCriteria(Criteria.where("vehicleCategory").is("CAR"));

        Query bikeQuery = new Query();
        bikeQuery.addCriteria(Criteria.where("vehicleCategory").is("MOTORCYCLE"));

        Query truckQuery = new Query();
        truckQuery.addCriteria(Criteria.where("vehicleCategory").is("TRUCK"));

        long users = mongoTemplate.estimatedCount(User.class);
        long ads = mongoTemplate.estimatedCount(Ad.class);

        long carAds = mongoTemplate.find(carQuery, Ad.class).size();
        long bikeAds = mongoTemplate.find(bikeQuery, Ad.class).size();
        long truckAds = mongoTemplate.find(truckQuery, Ad.class).size();

        return new DashboardDto(users, ads, carAds, bikeAds, truckAds);
    }

    public PaginationResponse<User> getAllUsers(PageQuery pageQuery) {
        Pageable pageable = PageRequest.of(pageQuery.getPage(), pageQuery.getPerPage());

        Page<User> users = userRepository.findAll(pageable);

        return new PaginationResponse<>(pageQuery.getPage(), users.getTotalElements(), users.getSize(), users.getTotalPages(), users.getContent());
    }

    public PaginationResponse<Ad> getAllAds(PageQuery pageQuery) {
        Pageable pageable = PageRequest.of(pageQuery.getPage(), pageQuery.getPerPage());

        Page<Ad> ads = adRepository.findAll(pageable);

        return new PaginationResponse<>(pageQuery.getPage(), ads.getTotalElements(), ads.getSize(), ads.getTotalPages(), ads.getContent());
    }
}
