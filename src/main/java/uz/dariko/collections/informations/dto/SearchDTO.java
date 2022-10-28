package uz.dariko.collections.informations.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchDTO {
    private UUID submenuID;
    private Integer page = 0;
    private Integer size = 20;
    private String content;
}
