package uz.dariko.collections.file;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.dariko.base.service.AbstractService;
import uz.dariko.exception.exceptions.BadRequestException;
import uz.dariko.exception.exceptions.UniversalException;
import uz.dariko.response.Data;
import uz.dariko.utils.BaseUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileService extends AbstractService<FileRepository, FileValidator> {
    private final ServletContext servletContext;
    //    private static final String FILE_PATH = "/home/database/files/citizen-appeal";
    private static final String FILE_PATH = "D:\\database\\files";

    public FileService(FileRepository repository, FileValidator validator, ServletContext servletContext) {
        super(repository, validator);
        this.servletContext = servletContext;
    }

    public ResponseEntity<?> uploads(MultipartHttpServletRequest requests) throws IOException{

        List<MultipartFile> multipartFiles = requests.getFiles("images");

        List<UUID> uploadIDs=new ArrayList<>();
        boolean b =true;
        for (MultipartFile request : multipartFiles) {
            UUID upload = upload(request,b);
            uploadIDs.add(upload);
            b=false;
        }
        return ResponseEntity.ok(uploadIDs);

    }

    public UUID upload( MultipartFile file,boolean b) throws IOException {

        String folder = FILE_PATH;
        File file2 = new File(folder);
        if (!file2.isDirectory()) {
            file2.mkdirs();
        }

        if (file != null) {
            if (file.getSize() > 100 * 1024 * 1024) {
                throw new UniversalException("File hajmi 100 mb dan kichik bo'lishi kerak", HttpStatus.BAD_REQUEST);
            }
            String contentType = file.getContentType();
            String originalFilename = file.getOriginalFilename();
            long size = file.getSize();
            String extention = FilenameUtils.getExtension(originalFilename);
            if (contentType.contains("png")) {
                extention = ".png";
            } else if (contentType.contains("jpg")) {
                extention = ".jpg";
            } else if (contentType.contains("jpeg")) {
                extention = ".jpeg";
            } else {
                throw new UniversalException("File faqat png, jpg va jpeg formatta bo'lishi kerak", HttpStatus.BAD_REQUEST);
            }
            String generatedName = UUID.randomUUID() + extention;
            String url = folder + "\\" + generatedName;
            FileOutputStream fileOutputStream = new FileOutputStream(new File(url));
            fileOutputStream.write(file.getBytes());
            fileOutputStream.close();
            fileOutputStream.flush();
            uz.dariko.collections.file.File fileEntity = new uz.dariko.collections.file.File();
            fileEntity.setFilePath(url);
            fileEntity.setExtention(contentType);
            fileEntity.setSize(size);
            fileEntity.setOriginalName(originalFilename);
            fileEntity.setGeneratedName(generatedName);
            fileEntity.setMain(b);
            uz.dariko.collections.file.File save = repository.save(fileEntity);
            return save.getId();
        } else {
            throw new BadRequestException("File null bo'lishi mumkin emas");
        }
    }




    public ResponseEntity<InputStreamResource> viewFile(String generatedName) throws FileNotFoundException {
        uz.dariko.collections.file.File fileEntity = findFile(generatedName);
        File send = new File(fileEntity.getFilePath());
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-disposition", "inline;filename=" + fileEntity.getOriginalName());
        InputStreamResource resource = new InputStreamResource(new FileInputStream(send));
        return ResponseEntity.ok().headers(headers).contentLength(send.length()).contentType(MediaType.parseMediaType(fileEntity.getExtention())).body(resource);
    }


    public ResponseEntity<Data<String>> download(HttpServletResponse response, String generatedName) throws IOException {
        uz.dariko.collections.file.File fileEntity = findFile(generatedName);
        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileEntity.getOriginalName());
        File file = new File(fileEntity.getFilePath());

        response.setContentType(mediaType.getType());

        // Content-Disposition
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileEntity.getOriginalName());

        // Content-Length
        response.setContentLength((int) file.length());

        BufferedInputStream inStream = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream());

        byte[] buffer = new byte[1024];
        int bytesRead = 0;
        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
        outStream.flush();

        inStream.close();

        return ResponseEntity.ok(new Data<>("Success"));
    }

    private uz.dariko.collections.file.File findFile(String generatedName) {
        List<uz.dariko.collections.file.File> fileOptional = repository.findByGeneratedName(generatedName);

        return fileOptional.get(0);


        /*return fileOptional.get(0).orElseThrow(() -> {
            throw new NotFoundException("Fayl topilmadi");*/
    }
}





























