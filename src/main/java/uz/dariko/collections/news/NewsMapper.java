package uz.dariko.collections.news;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.dariko.base.mapper.AbstractMapper;
import uz.dariko.collections.admin.Admin;
import uz.dariko.collections.file.File;
import uz.dariko.collections.news.dto.NewsCreateDTO;
import uz.dariko.collections.news.dto.NewsDTO;
import uz.dariko.collections.news.dto.NewsUpdateDTO;
import uz.dariko.utils.EntityGetter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Component
public class NewsMapper implements AbstractMapper<NewsCreateDTO, NewsUpdateDTO, NewsDTO,News> {

    private final EntityGetter entityGetter;

    public NewsMapper(EntityGetter entityGetter) {
        this.entityGetter = entityGetter;
    }

    @Override
    public NewsDTO toDto(News entity) {
        NewsDTO dto = new NewsDTO();
        dto.setId(entity.getId());
        dto.setActual(entity.isActual());
        dto.setCountView(entity.getCountView());
        dto.setUzBody(entity.getUzBody());
        dto.setKrBody(entity.getKrBody());
        dto.setRuBody(entity.getRuBody());
        dto.setUzTitle(entity.getUzTitle());
        dto.setKrTitle(entity.getKrTitle());
        dto.setRuTitle(entity.getRuTitle());
        dto.setSourceUrl(entity.getSourceUrl());
        dto.setSourceName(entity.getSourceName());
        dto.setSubmenuID(entity.getSubmenu().getId());
        dto.setCreatedDate(entity.getCreatedAt());
        dto.setUzDescription(entity.getUzDescription());
        dto.setRuDescription(entity.getRuDescription());
        dto.setKrDescription(entity.getKrDescription());
        dto.setNewsSphereId(entity.getNewsSphere().getId());
        dto.setUzNewsSphereName(entity.getNewsSphere().getUzName());
        dto.setRuNewsSphereName(entity.getNewsSphere().getRuName());
        dto.setKrNewsSphereName(entity.getNewsSphere().getKrName());
        List<File> images = entity.getImages();
        List<UUID> res = new ArrayList<>();
        for(File file : images) {
            res.add(file.getId());
        }
        dto.setGeneratedNames(res);

        return dto;
    }


    public List<NewsDTO> toDto(List<News> list){
        List<NewsDTO> res = new ArrayList<>();
        for(News news : list) {
            res.add(toDto(news));
        }
        return res;
    }

    @Override
    public News fromCreateDto(NewsCreateDTO createDto){
        News news = new News();
        news.setCountView(0);
        news.setActual(createDto.isActual());
        news.setKrBody(createDto.getKrBody());
        news.setKrTitle(createDto.getKrTitle());
        news.setRuBody(createDto.getRuBody());
        news.setRuTitle(createDto.getRuTitle());
        news.setUzBody(createDto.getUzBody());
        news.setUzTitle(createDto.getUzTitle());
        news.setSourceName(createDto.getSourceName());
        news.setSourceUrl(createDto.getSourceUrl());

        news.setUzDescription(createDto.getUzDescription());
        news.setRuDescription(createDto.getRuDescription());
        news.setKrDescription(createDto.getKrDescription());

        Admin sessionUser= (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        news.setCreatedBy(sessionUser.getId());
        news.setCreatedAt(LocalDateTime.now());

        return news;
    }

    @Override
    public News fromUpdateDto(NewsUpdateDTO updateDto) {
        return null;
    }

    public News fromUpdateDto(NewsUpdateDTO updateDto,News news){
        news.setSourceUrl(updateDto.getSourceUrl());
        news.setSourceName(updateDto.getSourceName());
        news.setUzTitle(updateDto.getUzTitle());
        news.setUzBody(updateDto.getUzBody());
        news.setKrTitle(updateDto.getKrTitle());
        news.setKrBody(updateDto.getKrBody());
        news.setRuTitle(updateDto.getRuTitle());
        news.setRuBody(updateDto.getRuBody());
        news.setActual(updateDto.isActual());

        news.setUzDescription(updateDto.getUzDescription());
        news.setRuDescription(updateDto.getRuDescription());
        news.setKrDescription(updateDto.getKrDescription());

        Admin sessionUser= (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        news.setUpdatedBy(sessionUser.getId());
        news.setUpdatedAt(LocalDateTime.now());

        return news;
    }
}
