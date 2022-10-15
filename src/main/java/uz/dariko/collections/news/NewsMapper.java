package uz.dariko.collections.news;


import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.dariko.base.mapper.AbstractMapper;
import uz.dariko.collections.news.dto.NewsCreateDTO;
import uz.dariko.collections.news.dto.NewsDTO;
import uz.dariko.collections.news.dto.NewsUpdateDTO;

@Mapper(componentModel = "spring")
@Component
public interface NewsMapper extends AbstractMapper<NewsCreateDTO, NewsUpdateDTO, NewsDTO,News> {

    @Override
    default NewsDTO toDto(News entity) {
        NewsDTO dto = new NewsDTO();
        dto.setActual(entity.isActual());
        dto.setCountView(entity.getCountView());
        dto.setGovSphereID(entity.getGovSphere().getId());
        dto.setUzBody(entity.getUzBody());
        dto.setKrBody(entity.getKrBody());
        dto.setRuBody(entity.getRuBody());
        dto.setUzTitle(entity.getUzTitle());
        dto.setKrTitle(entity.getKrTitle());
        dto.setRuTitle(entity.getRuTitle());
        dto.setSource(entity.getSource());
        dto.setSmm(entity.isSmm());
        dto.setSphereID(entity.getSphere().getId());

        return dto;

    }
}
