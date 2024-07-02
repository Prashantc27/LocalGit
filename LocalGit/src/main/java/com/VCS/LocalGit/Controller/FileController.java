package com.VCS.LocalGit.Controller;

import com.VCS.LocalGit.Entity.File;
import com.VCS.LocalGit.Entity.Version;
import com.VCS.LocalGit.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The FileController class handles HTTP requests related to file operations.
 * This includes creating, updating, listing files, and managing file versions.
 * It also supports merging files and resolving conflicts.
 */
@RestController
@RequestMapping("/files")
public class FileController {
    @Autowired
    private FileService fileService;

    @Autowired
    private ConflictService conflictService;
    
    /**
     * Creates a new file in the specified folder.
     *
     * @param folderId the ID of the folder where the file will be created
     * @param name the name of the new file
     * @param content the content of the new file
     * @return the created File object
     */
    @PostMapping("/create")
    public File createFile(@RequestParam Long folderId, @RequestParam String name, @RequestParam String content) {
        return fileService.createFile(folderId, name, content);
    }
    
    /**
     * Updates the content of an existing file.
     *
     * @param fileId the ID of the file to be updated
     * @param content the new content for the file
     * @return the updated File object
     */
    @PostMapping("/update")
    public File updateFile(@RequestParam Long fileId, @RequestParam String content) {
        return fileService.updateFile(fileId, content);
    }
    
    /**
     * Lists all files in the specified folder.
     *
     * @param folderId the ID of the folder
     * @return a list of File objects in the folder
     */
    @GetMapping("/list/{folderId}")
    public List<File> listFiles(@PathVariable Long folderId) {
        return fileService.listFiles(folderId);
    }
    
    /**
     * Lists all versions of the specified file.
     *
     * @param fileId the ID of the file
     * @return a list of Version objects for the file
     */
    @GetMapping("/versions/{fileId}")
    public List<Version> listFileVersions(@PathVariable Long fileId) {
        return fileService.listFileVersions(fileId);
    }
    
    /**
     * Compares two versions of a file and returns the differences.
     *
     * @param fileId the ID of the file
     * @param version1 the first version number to compare
     * @param version2 the second version number to compare
     * @return a list of strings describing the differences between the two versions
     */
    @GetMapping("/diff")
    public List<String> diff(@RequestParam Long fileId, @RequestParam int version1, @RequestParam int version2) {
        return fileService.diffVersions(fileId, version1, version2);
    }
    
    /**
     * Merges the content of two files into one.
     *
     * @param targetFileId the ID of the target file to merge into
     * @param sourceFileId the ID of the source file to merge from
     * @return a ResponseEntity containing the merged File object
     */
    @PostMapping("/merge")
    public ResponseEntity<File> mergeFiles(@RequestParam Long targetFileId, @RequestParam Long sourceFileId) {
        try {
            File mergedFile = fileService.mergeFiles(targetFileId, sourceFileId);
            return ResponseEntity.ok(mergedFile);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    /**
     * Resolves conflicts in a file by setting its content to the resolved content.
     *
     * @param fileId the ID of the file with conflicts
     * @param resolvedContent the resolved content for the file
     * @return a ResponseEntity containing the resolved File object
     */
    @PostMapping("/resolve-conflicts")
    public ResponseEntity<File> resolveConflicts(@RequestParam Long fileId, @RequestParam String resolvedContent) {
        try {
            File resolvedFile = fileService.resolveConflicts(fileId, resolvedContent);
            return ResponseEntity.ok(resolvedFile);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    /**
     * Checks and resolves conflicts in a file by comparing its content with new content.
     *
     * @param fileId the ID of the file to check for conflicts
     * @param newContent the new content to compare with the file's current content
     * @return the File object with resolved conflicts
     */
    @GetMapping("/conflict")
    public File resolveConflict(
            @RequestParam Long fileId,
            @RequestParam String newContent) {
        return conflictService.resolveConflict(fileId, newContent);
    }
}
