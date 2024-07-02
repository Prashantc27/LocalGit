package com.VCS.LocalGit.Service;

import com.VCS.LocalGit.Entity.Folder;
import com.VCS.LocalGit.Repository.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service class for handling folder-related operations, including creation, update, deletion, and listing of folders.
 */
@Service
public class FolderService {
    @Autowired
    private FolderRepository folderRepository;

    /**
     * Creates a new folder with the given name.
     *
     * @param name the name of the new folder
     * @return the created folder
     */
    @Transactional
    public Folder createFolder(String name) {
        Folder folder = new Folder();
        folder.setName(name);
        return folderRepository.save(folder);
    }

    /**
     * Updates the name of an existing folder.
     *
     * @param folderId the ID of the folder to update
     * @param name the new name for the folder
     * @return the updated folder
     * @throws NoSuchElementException if the folder is not found
     */
    @Transactional
    public Folder updateFolder(Long folderId, String name) {
        Folder folder = folderRepository.findById(folderId).orElseThrow();
        folder.setName(name);
        return folderRepository.save(folder);
    }

    /**
     * Deletes a folder by its ID.
     *
     * @param folderId the ID of the folder to delete
     */
    @Transactional
    public void deleteFolder(Long folderId) {
        folderRepository.deleteById(folderId);
    }

    /**
     * Lists all folders.
     *
     * @return the list of all folders
     */
    public List<Folder> listFolders() {
        return folderRepository.findAll();
    }
}
