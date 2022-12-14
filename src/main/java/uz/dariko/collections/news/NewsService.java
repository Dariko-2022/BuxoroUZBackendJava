package uz.dariko.collections.news;


import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.dariko.base.service.BaseService;
import uz.dariko.collections.admin.Admin;
import uz.dariko.collections.file.File;
import uz.dariko.collections.news.dto.NewsCreateDTO;
import uz.dariko.collections.news.dto.NewsDTO;
import uz.dariko.collections.news.dto.NewsUpdateDTO;
import uz.dariko.collections.newsSphere.NewsSphere;
import uz.dariko.collections.submenu.Submenu;
import uz.dariko.criteria.ResponsePage;
import uz.dariko.exception.exceptions.UniversalException;
import uz.dariko.utils.BaseUtils;
import uz.dariko.utils.EntityGetter;

import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NewsService implements BaseService {
    private final NewsRepository newsRepository;

    private final NewsMapper newsMapper;

    private final EntityGetter entityGetter;

    private final BaseUtils baseUtils;

    public NewsService(NewsRepository newsRepository, NewsMapper newsMapper, EntityGetter entityGetter, BaseUtils baseUtils) {
        this.newsRepository = newsRepository;
        this.newsMapper = newsMapper;
        this.entityGetter = entityGetter;
        this.baseUtils = baseUtils;
    }


    public ResponseEntity<?> getForHome() {
        Optional<List<News>> newsByIsActualAndLimit = newsRepository.findNewsByIsActualAndLimit(true, 3);
        Optional<List<News>> newsByIsActualAndLimit1 = newsRepository.findNewsByIsActualAndLimit(false, 4);
        if(newsByIsActualAndLimit1.isPresent() & newsByIsActualAndLimit.isPresent()) {
            List<News> list = newsByIsActualAndLimit.get();
            list.addAll(newsByIsActualAndLimit1.get());
            List<NewsDTO> newsDTOS = newsMapper.toDto(list);
            return ResponseEntity.ok(newsDTOS);
        }
        return null;
    }

    public ResponseEntity<?> create(NewsCreateDTO newsCreateDto) throws Exception {
        NewsSphere newsSphere = entityGetter.getNewsSphere(newsCreateDto.getNewsSphereId());
        Submenu submenu = entityGetter.getSubmenu(newsCreateDto.getSubmenuID());
        List<File> images = entityGetter.getFiles(newsCreateDto.getImageIDs());
        News news = newsMapper.fromCreateDto(newsCreateDto);
        news.setImages(images);
        news.setSubmenu(submenu);
        news.setNewsSphere(newsSphere);
        News save = newsRepository.save(news);
        NewsDTO newsDTO = newsMapper.toDto(save);
        return ResponseEntity.status(201).body(newsDTO);

    }

    public ResponseEntity<?> getById(String code) {
        UUID id = baseUtils.parseUUID(code);
        News news = entityGetter.getNews(id);
        news.setCountView(news.getCountView() + 1);
        News save = newsRepository.save(news);
        NewsDTO newsDTO = newsMapper.toDto(save);

        return ResponseEntity.ok(newsDTO);
    }

    public ResponseEntity<?> update(NewsUpdateDTO dto) {
        News news0 = entityGetter.getNews(dto.getId());
        News news = newsMapper.fromUpdateDto(dto, news0);
        List<File> images = entityGetter.getFiles(dto.getImageIDs());
        Submenu submenu = entityGetter.getSphere(dto.getSubmenuID());
        NewsSphere newsSphere = entityGetter.getNewsSphere(dto.getNewsSphereId());
        news.setImages(images);
        news.setNewsSphere(newsSphere);
        news.setSubmenu(submenu);
        Admin sessionUser= (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        news.setUpdatedBy(sessionUser.getId());
        news.setUpdatedAt(LocalDateTime.now());
        newsRepository.save(news);
        NewsDTO newsDTO = newsMapper.toDto(news);
        return ResponseEntity.ok(newsDTO);

    }
    public ResponseEntity<?> delete(String code) {
        UUID id = baseUtils.parseUUID(code);
        News news = entityGetter.getNews(id);
        news.setDeleted(true);
        Admin sessionUser= (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        news.setDeletedBy(sessionUser.getId());
        news.setDeletedAt(LocalDateTime.now());
        newsRepository.save(news);
        return ResponseEntity.ok(true);

    }

    public ResponseEntity<?> get() {
        Optional<List<News>> allByDeleted = newsRepository.findAllByDeleted(false);
        if (allByDeleted.isEmpty()) {
            throw new UniversalException("Yangiliklar Topilmadi", HttpStatus.NOT_FOUND);
        }

        List<News> list = allByDeleted.get();
        List<NewsDTO> newsDTOS = newsMapper.toDto(list);

        return ResponseEntity.ok(newsDTOS);
    }

    public ResponseEntity<?> getBySphere(String code,boolean isDeleted) {
        UUID uuid = baseUtils.parseUUID(code);
        entityGetter.getSubmenu(uuid);
        List<News> list = newsRepository.findBySphereAndDeleted(uuid, isDeleted);
        List<NewsDTO> newsDTOS = newsMapper.toDto(list);
        return ResponseEntity.ok(newsDTOS);
    }

    public ResponseEntity<?> getAll(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();
        int offset = pageSize * pageNumber;
        Page<News> page = newsRepository.findByIsDeleted(false,pageable);
        List<News> news = newsRepository.findAllByDeleted(false, pageSize, offset);
        List<NewsDTO> newsDTOS = newsMapper.toDto(news);
        ResponsePage<NewsDTO> responsePage = baseUtils.toResponsePage(page, newsDTOS);
        return ResponseEntity.ok(responsePage);
    }

    public ResponseEntity<?> getBySize(int size) {
        List<News> newsDeletedNotAndLimit = newsRepository.findNewsDeletedNotAndLimit(size);
        List<NewsDTO> newsDTOS = newsMapper.toDto(newsDeletedNotAndLimit);
        return ResponseEntity.ok(newsDTOS);
    }


    public ResponseEntity<?> getBySubmenuId(Pageable pageable,String code) {
        UUID id = baseUtils.parseUUID(code);
        int offset = pageable.getPageSize()* pageable.getPageNumber();
        int size = pageable.getPageSize();
        Page<News> page = newsRepository.findBySubmenuIdAndIsDeleted(id,pageable,false);
        List<News> allBySubmenuIdAndDeletedNot = newsRepository.findAllBySubmenuIdAndDeletedNot(id, size, offset,false);
        List<NewsDTO> newsDTOS = newsMapper.toDto(allBySubmenuIdAndDeletedNot);
        ResponsePage<NewsDTO> responsePage = baseUtils.toResponsePage(page, newsDTOS);

        return ResponseEntity.ok(responsePage);
    }


    public ResponseEntity<?> findByString(String s,Pageable pageable){
        Page<News> byString = newsRepository.findByString(s,pageable);
        List<News> byString2 = newsRepository.findByString2(s, pageable.getPageSize(), pageable.getPageSize()* pageable.getPageNumber());

        if(byString2.size()>0) {
            List<NewsDTO> newsDTOS = newsMapper.toDto(byString2);
            ResponsePage<NewsDTO> responsePage = baseUtils.toResponsePage(byString, newsDTOS);

            return ResponseEntity.ok(responsePage);
        }
        return ResponseEntity.status(404).body("Not found");

    }

    public void viewCount(String code) {
        UUID newsId = baseUtils.parseUUID(code);
        Optional<News> byIdAndDeletedNot = newsRepository.findByIdAndDeletedNot(newsId);
        if(byIdAndDeletedNot.isEmpty()) {
            return ;
        }
        News news = byIdAndDeletedNot.get();
        news.setCountView(news.getCountView()+1);
        News save = newsRepository.save(news);
    }




}
