package com.VCS.LocalGit.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Version class represents a version of a file in the system.
 * It includes attributes for version ID, file ID, version number, content, timestamp, and the associated file.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "version")
public class Version {

    /**
     * The unique identifier for the version.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The ID of the associated file.
     */
    @Column(name = "file_id", insertable = false, updatable = false)
    private Long fileId;

    /**
     * The version number.
     */
    private int versionNumber;

    /**
     * The content of the version.
     */
    private String content;

    /**
     * The timestamp when the version was created.
     */
    private LocalDateTime timestamp;

    /**
     * The associated file.
     * The relationship is managed with a many-to-one association.
     */
    @ManyToOne
    @JoinColumn(name = "file_id", nullable = false)
    private File file;
}
