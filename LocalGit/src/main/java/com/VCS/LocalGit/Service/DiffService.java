package com.VCS.LocalGit.Service;

import com.VCS.LocalGit.Entity.Version;
import com.VCS.LocalGit.Repository.VersionRepository;
import com.github.difflib.DiffUtils;
import com.github.difflib.UnifiedDiffUtils;
import com.github.difflib.patch.Patch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The DiffService class provides methods for generating diffs between versions of files.
 * It interacts with the VersionRepository to retrieve version data and uses DiffUtils to compute the differences.
 */
@Service
public class DiffService {

    @Autowired
    private VersionRepository versionRepository;

    /**
     * Generates a unified diff between two versions of a file.
     *
     * @param fileId the ID of the file
     * @param version1 the number of the first version
     * @param version2 the number of the second version
     * @return a list of strings representing the unified diff
     */
    public List<String> diff(Long fileId, int version1, int version2) {
        Version v1 = versionRepository.findByFileIdAndVersionNumber(fileId, version1).orElseThrow();
        Version v2 = versionRepository.findByFileIdAndVersionNumber(fileId, version2).orElseThrow();

        List<String> original = List.of(v1.getContent().split("\n"));
        List<String> revised = List.of(v2.getContent().split("\n"));

        Patch<String> patch = DiffUtils.diff(original, revised);
        return UnifiedDiffUtils.generateUnifiedDiff("v" + version1, "v" + version2, original, patch, 0);
    }
}
