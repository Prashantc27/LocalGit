package com.VCS.LocalGit.Controller;

import com.VCS.LocalGit.Entity.Folder;
import com.VCS.LocalGit.Service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The FolderController class handles HTTP requests related to folder operations.
 * This includes creating, updating, deleting, and listing folders.
 */
@RestController
@RequestMapping("/folders")
public class FolderController {
    @Autowired
    private FolderService folderService;
    
    /**
     * Creates a new folder with the specified name.
     *
     * @param name the name of the new folder
     * @return the created Folder object
     */
    @PostMapping("/create")
    public Folder createFolder(@RequestParam String name) {
        return folderService.createFolder(name);
    }
    
    /**
     * Updates the name of an existing folder.
     *
     * @param folderId the ID of the folder to be updated
     * @param name the new name for the folder
     * @return the updated Folder object
     */
    @PostMapping("/update")
    public Folder updateFolder(@RequestParam Long folderId, @RequestParam String name) {
        return folderService.updateFolder(folderId, name);
    }
    
    /**
     * Deletes the folder with the specified ID.
     *
     * @param folderId the ID of the folder to be deleted
     */
    @DeleteMapping("/delete")
    public void deleteFolder(@RequestParam Long folderId) {
        folderService.deleteFolder(folderId);
    }
    
    /**
     * Lists all folders.
     *
     * @return a list of Folder objects
     */
    @GetMapping("/list")
    public List<Folder> listFolders() {
        return folderService.listFolders();
    }
}
