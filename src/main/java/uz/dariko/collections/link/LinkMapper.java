package uz.dariko.collections.link;

import org.mapstruct.Mapper;
import uz.dariko.base.mapper.BaseMapper;
import uz.dariko.collections.link.dto.LinkDTO;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface LinkMapper extends BaseMapper {

    default LinkDTO toLinkDTO(Link link){
        return new LinkDTO(link.getUzName(), link.getKrName(),
                        link.getRuName(), link.getLinkTypeCode(),
                        link.getImage().getGeneratedName(),
                        link.getUrl());
    }

    default List<LinkDTO> toLinkDTO(List<Link> links){
        List<LinkDTO> linkDTOS=new ArrayList<>();
        for (Link link : links) {
            LinkDTO linkDTO = toLinkDTO(link);
            linkDTOS.add(linkDTO);
        }
        return linkDTOS;
    }
}
