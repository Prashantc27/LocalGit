package com.VCS.LocalGit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.VCS.LocalGit.Entity.Folder;

/**
 * The FolderRepository interface provides methods for interacting with the Folder entity in the database.
 * It extends JpaRepository to provide basic CRUD operations.
 */
public interface FolderRepository extends JpaRepository<Folder, Long> {

}
