package com.VCS.LocalGit.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.VCS.LocalGit.Entity.File;
import com.VCS.LocalGit.Repository.FileRepository;

import jakarta.transaction.Transactional;

/**
 * The ConflictService class provides methods for resolving conflicts in files.
 * It interacts with the FileRepository to retrieve and save file data, and the VersionService to create new versions of files.
 */
@Service
public class ConflictService {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private VersionService versionService;

    /**
     * Resolves conflicts in a file by updating its content with the provided resolved content.
     * Creates a new version of the file with the resolved content.
     *
     * @param fileId the ID of the file to resolve conflicts for
     * @param resolvedContent the resolved content to update the file with
     * @return the updated File entity
     */
    @Transactional
    public File resolveConflict(Long fileId, String resolvedContent) {
        File file = fileRepository.findById(fileId).orElseThrow();

        file.setContent(resolvedContent);
        file = fileRepository.save(file);

        versionService.createVersion(file, resolvedContent);

        return file;
    }
}
