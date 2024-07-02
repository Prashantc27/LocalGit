package com.VCS.LocalGit.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.VCS.LocalGit.Entity.File;
import com.VCS.LocalGit.Entity.Folder;
import com.VCS.LocalGit.Entity.Version;
import com.VCS.LocalGit.Repository.FileRepository;
import com.VCS.LocalGit.Repository.FolderRepository;
import com.VCS.LocalGit.Repository.VersionRepository;

import jakarta.transaction.Transactional;

/**
 * Service class for handling file-related operations, including creation, update, versioning,
 * merging, conflict resolution, and diff generation.
 */
@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private FolderRepository folderRepository;

    @Autowired
    private VersionRepository versionRepository;
    
    @Autowired
    private MergeService mergeService;
    
    @Autowired
    private ConflictService conflictService;
    
    @Autowired
    private DiffService diffService;

    /**
     * Retrieves a specific version of a file by file ID and version number.
     *
     * @param fileId the ID of the file
     * @param versionNumber the version number to retrieve
     * @return the specified version of the file
     * @throws NoSuchElementException if the version is not found
     */
    public Version getFileVersion(Long fileId, int versionNumber) {
        return versionRepository.findByFileIdAndVersionNumber(fileId, versionNumber)
                .orElseThrow(() -> new NoSuchElementException("File version not found"));
    }

    /**
     * Creates a new file in the specified folder with the given name and content.
     *
     * @param folderId the ID of the folder
     * @param name the name of the new file
     * @param content the content of the new file
     * @return the created file
     */
    @Transactional
    public File createFile(Long folderId, String name, String content) {
        Folder folder = folderRepository.findById(folderId).orElseThrow();
        File file = new File();
        file.setName(name);
        file.setContent(content);
        file.setFolder(folder);
        file = fileRepository.save(file);
        createVersion(file, content);
        return file;
    }

    /**
     * Updates the content of an existing file.
     *
     * @param fileId the ID of the file to update
     * @param content the new content for the file
     * @return the updated file
     */
    @Transactional
    public File updateFile(Long fileId, String content) {
        File file = fileRepository.findById(fileId).orElseThrow();
        file.setContent(content);
        file = fileRepository.save(file);
        createVersion(file, content);
        return file;
    }

    /**
     * Creates a new version for a file with the specified content.
     *
     * @param file the file to create a new version for
     * @param content the content of the new version
     * @return the created version
     */
    @Transactional
    public Version createVersion(File file, String content) {
        Version version = new Version();
        version.setFile(file);
        version.setContent(content);
        version.setTimestamp(LocalDateTime.now());
        
        List<Version> versions = versionRepository.findByFileIdOrderByVersionNumberDesc(file.getId());
        int latestVersionNumber = versions.stream().findFirst().map(Version::getVersionNumber).orElse(0);
        
        version.setVersionNumber(latestVersionNumber + 1);
        return versionRepository.save(version);
    }

    /**
     * Lists all files in a specified folder.
     *
     * @param folderId the ID of the folder
     * @return the list of files in the folder
     */
    public List<File> listFiles(Long folderId) {
        return fileRepository.findByFolderId(folderId);
    }

    /**
     * Lists all versions of a specified file.
     *
     * @param fileId the ID of the file
     * @return the list of versions for the file
     */
    public List<Version> listFileVersions(Long fileId) {
        return versionRepository.findByFileIdOrderByVersionNumberDesc(fileId);
    }
    
    /**
     * Merges the content of two files, specified by their IDs.
     *
     * @param targetFileId the ID of the target file
     * @param sourceFileId the ID of the source file
     * @return the merged file
     */
    @Transactional
    public File mergeFiles(Long targetFileId, Long sourceFileId) {
        return mergeService.mergeFiles(targetFileId, sourceFileId);
    }

    /**
     * Resolves conflicts in a file by setting its content to the specified resolved content.
     *
     * @param fileId the ID of the file
     * @param resolvedContent the resolved content for the file
     * @return the file with resolved content
     */
    @Transactional
    public File resolveConflicts(Long fileId, String resolvedContent) {
        return conflictService.resolveConflict(fileId, resolvedContent);
    }

    /**
     * Generates a diff between two versions of a file.
     *
     * @param fileId the ID of the file
     * @param version1 the first version number
     * @param version2 the second version number
     * @return a list of strings representing the diff
     */
    public List<String> diffVersions(Long fileId, int version1, int version2) {
        return diffService.diff(fileId, version1, version2);
    }
}
