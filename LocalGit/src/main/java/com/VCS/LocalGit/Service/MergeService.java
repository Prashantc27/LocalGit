package com.VCS.LocalGit.Service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.VCS.LocalGit.Entity.File;
import com.VCS.LocalGit.Repository.FileRepository;

import jakarta.transaction.Transactional;

/**
 * Service class for handling file merge operations.
 */
@Service
public class MergeService {
    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private VersionService versionService;

    /**
     * Merges the content of the source file into the target file.
     *
     * @param targetFileId the ID of the target file
     * @param sourceFileId the ID of the source file
     * @return the merged file
     * @throws NoSuchElementException if either file is not found
     */
    @Transactional
    public File mergeFiles(Long targetFileId, Long sourceFileId) {
        File targetFile = fileRepository.findById(targetFileId).orElseThrow();
        File sourceFile = fileRepository.findById(sourceFileId).orElseThrow();

        String targetContent = targetFile.getContent();
        String sourceContent = sourceFile.getContent();

        String mergedContent = mergeContent(targetContent, sourceContent);

        targetFile.setContent(mergedContent);
        targetFile = fileRepository.save(targetFile);

        versionService.createVersion(targetFile, mergedContent);

        return targetFile;
    }

    /**
     * Merges the content of the source content into the target content.
     * This method appends the source content to the target content.
     *
     * @param targetContent the content of the target file
     * @param sourceContent the content of the source file
     * @return the merged content
     */
    private String mergeContent(String targetContent, String sourceContent) {
     
        return targetContent + "\n" + sourceContent;
    }
}
