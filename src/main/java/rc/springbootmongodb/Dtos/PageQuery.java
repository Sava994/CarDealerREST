package rc.springbootmongodb.Dtos;

public class PageQuery {
    private int page = 0;
    private int perPage = 5;

    public PageQuery() {}

    public PageQuery(int page, int perPage) {
        this.page = page;
        this.perPage = perPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }
}
