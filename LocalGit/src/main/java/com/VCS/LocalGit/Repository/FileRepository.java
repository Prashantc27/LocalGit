package com.VCS.LocalGit.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.VCS.LocalGit.Entity.File;

/**
 * The FileRepository interface provides methods for interacting with the File entity in the database.
 * It extends JpaRepository to provide basic CRUD operations.
 */
public interface FileRepository extends JpaRepository<File, Long> {

    /**
     * Finds all files associated with a specific folder ID.
     * 
     * @param folderId the ID of the folder
     * @return a list of files in the specified folder
     */
    List<File> findByFolderId(Long folderId);
}
