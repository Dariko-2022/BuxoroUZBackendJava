package uz.dariko.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import uz.dariko.collections.admin.Admin;
import uz.dariko.collections.admin.AdminRepository;
import uz.dariko.collections.file.File;
import uz.dariko.collections.file.FileRepository;
import uz.dariko.exception.exceptions.NotFoundException;
import uz.dariko.exception.exceptions.UniversalException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EntityGetter {

    private final AdminRepository adminRepository;
    private final FileRepository fileRepository;
    public Admin getAdmin(String username) {
        Optional<Admin> optional = adminRepository.findByUsername(username);
        return optional.orElseThrow(() -> {
            throw new UniversalException("Admin topilmadi", HttpStatus.NOT_FOUND);
        });
    }
    public File getFile(UUID fileID){
        Optional<File> entityOptional = fileRepository.findByIdAndIsDeleted(fileID, false);
        return entityOptional.orElseThrow(() -> {
            throw new NotFoundException("File IDsi noto'g'ri berildi");
        });
    }
    public List<File> getFiles(List<UUID> entitiesID) {
        List<File> files = new ArrayList<>();
        for (UUID id : entitiesID) {
            File file = getFile(id);
            files.add(file);
        }
        return files;
    }
}
