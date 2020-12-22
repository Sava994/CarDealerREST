package rc.springbootmongodb.Services;

import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import rc.springbootmongodb.Dtos.FilterDto;
import rc.springbootmongodb.Dtos.PageQuery;
import rc.springbootmongodb.Dtos.PaginationResponse;
import rc.springbootmongodb.EnumTypes.FuelType;
import rc.springbootmongodb.EnumTypes.GearType;
import rc.springbootmongodb.Models.Ad;
import rc.springbootmongodb.Models.User;
import rc.springbootmongodb.Repository.AdRepository;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static com.mongodb.client.model.Filters.where;

@Service
public class AdServices {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    AdRepository adRepository;

    public Ad getById(String id) {
        Optional<Ad> ad = adRepository.findById(id);

        return ad.isPresent() ? ad.get() : null;
    }

    public PaginationResponse<Ad> getAll(PageQuery pageQuery) {

        Pageable pageable = PageRequest.of(pageQuery.getPage(), pageQuery.getPerPage());

        Page<Ad> ads = adRepository.findAll(pageable);

        return new PaginationResponse<>(pageQuery.getPage(), ads.getTotalElements(), ads.getSize(), ads.getTotalPages(), ads.getContent());
    }

    public List<Ad> getAdsWithVehicleType(String type) {

        Query query = new Query();
        query.addCriteria(Criteria.where("vehicleType").is(type));

        return mongoTemplate.find(query, Ad.class);
    }


    public List<Ad> filterAds(FilterDto filter) {

        Query query = new Query();

        if (!filter.getCity().isEmpty()) {
            query.addCriteria(Criteria.where("location.city").is(capitalizeWord(filter.getCity())));
        }
        if (!filter.getFuel().isEmpty()) {
            query.addCriteria(Criteria.where("fuelType").is(FuelType.valueOf(filter.getFuel()).toString()));
        }
        if (!filter.getMaker().isEmpty()) {
            query.addCriteria(Criteria.where("make").is(filter.getMaker()));
        }
        if (!filter.getTransmission().isEmpty()) {
            query.addCriteria(Criteria.where("gearType").is(GearType.valueOf(filter.getTransmission()).toString()));
        }

        if (!filter.getModelYearFrom().isEmpty() && !filter.getModelYearTo().isEmpty()) {
            query.addCriteria(Criteria.where("year_of_made")
                    .gte(Integer.parseInt(filter.getModelYearFrom()))
                    .lte(Integer.parseInt(filter.getModelYearTo())));
        } else if (!filter.getModelYearFrom().isEmpty()) {
            query.addCriteria(Criteria.where("year_of_made")
                    .gte(Integer.parseInt(filter.getModelYearFrom())));
        } else if (!filter.getModelYearTo().isEmpty()) {
            query.addCriteria(Criteria.where("year_of_made")
                    .lte(Integer.parseInt(filter.getModelYearTo())));
        }

        if (!filter.getBody().isEmpty()) {
            query.addCriteria(Criteria.where("vehicleType").is(filter.getBody()));
        }
        if (filter.getCategory() != null) {
            query.addCriteria(Criteria.where("vehicleCategory").is(filter.getCategory().toString()));
        }

        if (filter.getDistanceTo() == 0) {
            filter.setDistanceTo(Integer.MAX_VALUE);
        }
        if (filter.getPriceTo() == 0) {
            filter.setPriceTo(Integer.MAX_VALUE);
        }

        query.addCriteria(Criteria.where("travelled").gte(filter.getDistanceFrom()).lte(filter.getDistanceTo()));
        query.addCriteria(Criteria.where("price").gte(filter.getPriceFrom()).lte(filter.getPriceTo()));

        return mongoTemplate.find(query, Ad.class);

    }

    public PaginationResponse<Ad> getUserAds(String id, PageQuery pageQuery) {
        Pageable pageable = PageRequest.of(pageQuery.getPage(), pageQuery.getPerPage());

        Page<Ad> ads = adRepository.findByUserId(id, pageable);

        return new PaginationResponse<>(pageQuery.getPage(), ads.getTotalElements(), ads.getSize(), ads.getTotalPages(), ads.getContent());
    }

    private String capitalizeWord(String word) {
        return (String.valueOf(word.charAt(0))).toUpperCase() + word.substring(1);
    }
}
