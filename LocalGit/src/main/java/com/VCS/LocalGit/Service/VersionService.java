package com.VCS.LocalGit.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.VCS.LocalGit.Entity.File;
import com.VCS.LocalGit.Entity.Version;
import com.VCS.LocalGit.Repository.VersionRepository;

import jakarta.transaction.Transactional;

/**
 * Service class for handling version management operations.
 */
@Service
public class VersionService {
    @Autowired
    private VersionRepository versionRepository;

    /**
     * Creates a new version for the given file with the provided content.
     *
     * @param file the file for which the version is being created
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
}
