package peaksoft.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class PaginationResponse<T> {
    private int pageNumber;
    private int pageSize;
    private long numberOfElements;
    private long numberOfPages;
    private List<T> objects;


}
