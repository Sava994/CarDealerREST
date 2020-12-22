package rc.springbootmongodb.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rc.springbootmongodb.Dtos.DashboardDto;
import rc.springbootmongodb.Dtos.PageQuery;
import rc.springbootmongodb.Dtos.PaginationResponse;
import rc.springbootmongodb.Models.Ad;
import rc.springbootmongodb.Models.User;
import rc.springbootmongodb.Services.StatsService;

@RestController
@RequestMapping("api/stats")
@CrossOrigin
public class StatsController {

    @Autowired
    private StatsService statsService;

    @GetMapping("/dashboard")
    public ResponseEntity<DashboardDto> getDashboardStats() {
        DashboardDto res = statsService.getDashboardStats();

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<PaginationResponse<User>> getUserStats(PageQuery pageQuery) {
        PaginationResponse<User> res = statsService.getAllUsers(pageQuery);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/ads")
    public ResponseEntity<PaginationResponse<Ad>> getAdStats(PageQuery pageQuery) {
        PaginationResponse<Ad> res = statsService.getAllAds(pageQuery);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
