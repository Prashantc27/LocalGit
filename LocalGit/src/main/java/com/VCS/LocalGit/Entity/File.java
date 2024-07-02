package com.VCS.LocalGit.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The File class represents a file entity in the system.
 * It includes attributes for file ID, name, content, folder, and versions.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "file")
public class File {

    /**
     * The unique identifier for the file.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the file.
     */
    private String name;
   
    /**
     * The content of the file.
     */
    private String content;

    /**
     * The folder to which the file belongs.
     */
    @ManyToOne
    @JoinColumn(name = "folder_id", nullable = false)
    private Folder folder;

    /**
     * The list of versions associated with the file.
     */
    @OneToMany(mappedBy = "file", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Version> versions;
}
