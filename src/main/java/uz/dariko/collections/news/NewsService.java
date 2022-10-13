package uz.dariko.collections.news;


import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.dariko.base.service.BaseService;
import uz.dariko.collections.file.File;
import uz.dariko.collections.govSphere.GovSphere;
import uz.dariko.collections.govSphere.GovSphereRepository;
import uz.dariko.collections.news.dto.NewsCreateDTO;
import uz.dariko.collections.sphere.Sphere;
import uz.dariko.collections.sphere.SphereRepository;
import uz.dariko.exception.exceptions.UniversalException;
import uz.dariko.utils.BaseUtils;
import uz.dariko.utils.EntityGetter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NewsService implements BaseService {
    private final NewsRepository newsRepository;

    private final BaseUtils baseUtils;
    private final SphereRepository sphereRepository;
    private final GovSphereRepository govSphereRepository;

    private final EntityGetter entityGetter;

    public NewsService(NewsRepository newsRepository, BaseUtils baseUtils, SphereRepository sphereRepository, GovSphereRepository govSphereRepository, EntityGetter entityGetter) {
        this.newsRepository = newsRepository;
        this.baseUtils = baseUtils;
        this.sphereRepository = sphereRepository;
        this.govSphereRepository = govSphereRepository;
        this.entityGetter = entityGetter;
    }


    public ResponseEntity<?> create(NewsCreateDTO newsCreateDto) throws Exception {

        Optional<Sphere> byId = sphereRepository.findById(UUID.fromString(newsCreateDto.getSphereID()));
        Optional<GovSphere> byGovId = govSphereRepository.findById(UUID.fromString(newsCreateDto.getGovSphereID()));
        List<UUID> uuids = baseUtils.parseUUID(newsCreateDto.getImageIDs());
        List<File> images = entityGetter.getFiles(uuids);

        if(byId.isPresent() && byGovId.isPresent()) {
            Sphere sphere = byId.get();
            GovSphere govSphere = byGovId.get();


            News news = new News(
                    newsCreateDto.getUzTitle(), newsCreateDto.getKrTitle(), newsCreateDto.getRuTitle(),
                    newsCreateDto.getUzBody(), newsCreateDto.getKrBody(), newsCreateDto.getRuBody(),
                    sphere,govSphere,images,newsCreateDto.isActual(),0,newsCreateDto.getSource()
                    );
            newsRepository.save(news);
            return ResponseEntity.status(201).body("saved");
        }
        return ResponseEntity.badRequest().body("sphere not found");
    }

    private List<File> savePhoto(List<MultipartFile> multipartFiles) throws Exception {
        List<File> list = new ArrayList<>();
        for(MultipartFile multipartFile : multipartFiles) {
            long size = multipartFile.getSize();
            if(size < 1024*1024*100) {
                String originalName = multipartFile.getOriginalFilename();
                String extention = FilenameUtils.getExtension(originalName);
                String generatedName = UUID.randomUUID() + extention;
                try (InputStream inputStream = multipartFile.getInputStream()) {
                    Path uploadPath = Paths.get("newsPhotos");
                    Path filePath = uploadPath.resolve(generatedName);
                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                    File file = new File(
                            filePath.toString(),
                            originalName,
                            generatedName,
                            extention,
                            size,
                            true,
                            1
                            );
                    list.add(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                throw new UniversalException("File hajmi 100 mb dan kichik bo'lishi kerak", HttpStatus.BAD_REQUEST);
            }
        }
        return list;
    }

    public ResponseEntity<?> getById(String code) {
        UUID id = UUID.fromString(code);

        Optional<News> byId = newsRepository.findById(id);

        return byId.isPresent() ? ResponseEntity.ok(byId.get()) : ResponseEntity.status(404).body("Not Found");

    }


}
