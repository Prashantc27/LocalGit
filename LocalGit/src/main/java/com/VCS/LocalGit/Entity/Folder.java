package com.VCS.LocalGit.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Folder class represents a folder entity in the system.
 * It includes attributes for folder ID, name, and the list of files it contains.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "folder")
public class Folder {

    /**
     * The unique identifier for the folder.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the folder.
     */
    private String name;

    /**
     * The list of files contained in the folder.
     * The relationship is managed with cascade operations and orphan removal.
     */
    @OneToMany(mappedBy = "folder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<File> files = new ArrayList<>();
}
