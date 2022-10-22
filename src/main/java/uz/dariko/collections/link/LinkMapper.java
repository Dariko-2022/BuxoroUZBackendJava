package uz.dariko.collections.link;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.dariko.base.mapper.BaseMapper;
import uz.dariko.collections.file.File;
import uz.dariko.collections.link.dto.LinkCreateDTO;
import uz.dariko.collections.link.dto.LinkDTO;
import uz.dariko.collections.link.dto.LinkUpdateDTO;
import uz.dariko.utils.EntityGetter;
import uz.dariko.utils.dtos.SessionUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class LinkMapper implements BaseMapper {

    private final EntityGetter entityGetter;

    public LinkMapper(EntityGetter entityGetter) {
        this.entityGetter = entityGetter;
    }

    Link fromCreatedDTO(LinkCreateDTO DTO){
        SessionUser sessionUser= (SessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        File image = entityGetter.getFile(DTO.getImageID());
        Link link=new Link(DTO.getUzName(),DTO.getKrName(),DTO.getRuName(),DTO.getLinkTypeCode(),image,DTO.getUrl());
        link.setCreatedBy(sessionUser.getId());
        link.setUpdatedBy(sessionUser.getId());
        return link;
    }

    Link fromUpdateDTO(LinkUpdateDTO DTO){
        SessionUser sessionUser= (SessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Link link = entityGetter.getLink(DTO.getId());
        File image = entityGetter.getFile(DTO.getImageID());
        link.setUzName(DTO.getUzName());
        link.setKrName(DTO.getKrName());
        link.setRuName(DTO.getRuName());
        link.setLinkTypeCode(DTO.getLinkTypeCode());
        link.setImage(image);
        link.setUrl(DTO.getUrl());
        link.setUpdatedAt(LocalDateTime.now());
        link.setUpdatedBy(sessionUser.getId());
        return link;
    }

     LinkDTO toDTO(Link link){
        return new LinkDTO(link.getUzName(), link.getKrName(),
                        link.getRuName(), link.getLinkTypeCode(),
                        link.getImage().getGeneratedName(),
                        link.getUrl());
    }

     List<LinkDTO> toDTO(List<Link> links){
        List<LinkDTO> linkDTOS=new ArrayList<>();
        for (Link link : links) {
            LinkDTO linkDTO = toDTO(link);
            linkDTOS.add(linkDTO);
        }
        return linkDTOS;
    }
}
