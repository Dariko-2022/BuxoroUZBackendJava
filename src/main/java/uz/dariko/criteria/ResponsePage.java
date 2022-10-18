package uz.dariko.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponsePage<T> {
    private Integer totalPages;
    private long totalElements;
    private Integer size;
    private Integer number;
    private long numberOfElements;
    private List<T> content;
}
