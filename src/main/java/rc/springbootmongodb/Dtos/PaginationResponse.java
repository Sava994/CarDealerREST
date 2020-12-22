package rc.springbootmongodb.Dtos;

import java.util.ArrayList;
import java.util.List;

public class PaginationResponse<TResults> {
   private int page;
   private long results;
   private int pageSize;
   private int totalPages;
   private List<TResults> data = new ArrayList<>();

    public PaginationResponse() {
    }

    public PaginationResponse(int page, long results, int pageSize, int totalPages, List<TResults> data) {
        this.page = page;
        this.results = results;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.data = data;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public long getResults() {
        return results;
    }

    public void setResults(long results) {
        this.results = results;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<TResults> getData() {
        return data;
    }

    public void setData(List<TResults> data) {
        this.data = data;
    }
}
