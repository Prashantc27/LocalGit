package com.VCS.LocalGit.Repository;

import com.VCS.LocalGit.Entity.Version;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * The VersionRepository interface provides methods for interacting with the Version entity in the database.
 * It extends JpaRepository to provide basic CRUD operations and additional methods for version-specific queries.
 */
@Repository
public interface VersionRepository extends JpaRepository<Version, Long> {

    /**
     * Finds a list of versions for a given file, ordered by version number in descending order.
     *
     * @param fileId the ID of the file
     * @return a list of versions for the specified file
     */
    List<Version> findByFileIdOrderByVersionNumberDesc(Long fileId);

    /**
     * Finds a specific version of a file by its file ID and version number.
     *
     * @param fileId the ID of the file
     * @param versionNumber the version number
     * @return an Optional containing the found version, or an empty Optional if not found
     */
    Optional<Version> findByFileIdAndVersionNumber(Long fileId, int versionNumber);
}
