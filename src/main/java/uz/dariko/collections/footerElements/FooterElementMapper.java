package uz.dariko.collections.footerElements;


import org.springframework.stereotype.Component;
import uz.dariko.base.mapper.BaseMapper;
import uz.dariko.collections.footerElements.dto.FooterElementCreateDTO;
import uz.dariko.collections.footerElements.dto.FooterElementDTO;

import java.util.ArrayList;
import java.util.List;

@Component
public class FooterElementMapper implements BaseMapper {

    public FooterElement fromCreateDTO(FooterElement footerElement,FooterElementCreateDTO dto) {
        footerElement.setBody(dto.getBody());
        footerElement.setKrRegulations(dto.getKrRegulations());
        footerElement.setRuRegulations(dto.getRuRegulations());
        footerElement.setUzRegulations(dto.getUzRegulations());
        footerElement.setType(dto.getType());
        return footerElement;
    }

    public FooterElementDTO toDTO(FooterElement element) {
        FooterElementDTO dto = new FooterElementDTO();
        dto.setBody(element.getBody());
        dto.setKrRegulations(element.getKrRegulations());
        dto.setUzRegulations(element.getUzRegulations());
        dto.setRuRegulations(element.getRuRegulations());
        dto.setType(element.getType());
        dto.setId(element.getId());
        return dto;
    }

    public List<FooterElementDTO> toDTO(List<FooterElement> list) {
        List<FooterElementDTO> res = new ArrayList<>();
        for(FooterElement f : list) {
            res.add(toDTO(f));
        }
        return res;
    }
}
