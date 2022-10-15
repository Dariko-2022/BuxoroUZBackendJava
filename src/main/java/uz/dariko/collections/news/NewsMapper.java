package uz.dariko.collections.news;


import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.dariko.base.mapper.AbstractMapper;
import uz.dariko.collections.file.File;
import uz.dariko.collections.news.dto.NewsCreateDTO;
import uz.dariko.collections.news.dto.NewsDTO;
import uz.dariko.collections.news.dto.NewsUpdateDTO;

import java.util.ArrayList;
import java.util.List;

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
        List<File> images = entity.getImages();
        List<String> res = new ArrayList<>();
        for(File file : images) {
            res.add(file.getGeneratedName());
        }
        dto.setGeneratedNames(res);

        return dto;
    }

    @Override
    default News fromCreateDto(NewsCreateDTO createDto){
        News news = new News();
        news.setCountView(0);
        news.setActual(createDto.isActual());
        news.setSmm(createDto.isSmm());
        news.setKrBody(createDto.getKrBody());
        news.setKrTitle(createDto.getKrTitle());
        news.setRuBody(createDto.getRuBody());
        news.setRuTitle(createDto.getRuTitle());
        news.setUzBody(createDto.getUzBody());
        news.setUzTitle(createDto.getUzTitle());
        news.setSource(createDto.getSource());
        return news;
    }

//    @Override
//    default News fromUpdateDto(NewsUpdateDTO updateDto){
//
//    }
}
