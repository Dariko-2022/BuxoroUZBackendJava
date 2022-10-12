package uz.dariko.collections.file;


import org.springframework.stereotype.Service;
import uz.dariko.base.service.AbstractService;

@Service
public class FileService extends AbstractService<FileRepository,FileValidator> {

    public FileService(FileRepository repository, FileValidator validator) {
        super(repository, validator);
    }



}
