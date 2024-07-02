# Version Control System : LocalGit

## Introduction

This project implements an version control file system, similar to Git. It includes features for handling files, folders, diffs, conflicts, and merges requests.
- **File and Folder Operations**: Create, update, list, and delete files and folders.
- **Version Control**: Maintain versions of files with support for creating and listing versions.
- **Diff Operations**: Compare different versions of a file and generate diffs.
- **Conflict Resolution**: Detect and resolve conflicts in file versions.
- **Merge Operations**: Merge content from different files into a single file.

## Technology Stack

- **Spring Boot**: Backend framework for building the application.
- **JPA/Hibernate**: ORM for database interactions.
- **H2 Database**: In-memory database for development and testing.
- **Lombok**: Simplifies Java code with annotations.
- **Spring Data JPA**: Simplifies data access layers.

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven

### Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/Prashantc27/LocalGit.git
    cd LocalGit
    ```

2. Build the project using Maven:
    ```bash
    mvn clean install
    ```

3. Run the application:
    ```bash
    mvn spring-boot:run
    ```

4. The application will be available at `http://localhost:8080`.

### API Endpoints

#### File Endpoints

- **Create a folder**:
 curl --location 'http://localhost:8080/folders/create' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'name=New Folder'

- **Update a folder**:
  curl --location 'http://localhost:8080/folders/update' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'folderId=1' \
--data-urlencode 'name=Updated Folder Name'

- **Delete a folder**:
  curl --location --request DELETE 'http://localhost:8080/folders/delete' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'folderId=1'

- **List all the folders**:
- curl --location 'http://localhost:8080/folders/list'
  
- **Create a file**: 
 curl --location 'http://localhost:8080/files/create' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'folderId=1' \
--data-urlencode 'name=example.txt' \
--data-urlencode 'content=Initial content of the file.'
  
- **Update a file**:
- curl --location 'http://localhost:8080/files/update' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'fileId=1' \
--data-urlencode 'content=Updated content of the file.'

- **List all the files**:
- curl --location 'http://localhost:8080/files/list/1'

- **List all the file Versions**:
  curl --location 'http://localhost:8080/files/versions/1'

- **Get the difference in file versions**:
  curl --location 'http://localhost:8080/files/diff?fileId=1&version1=1&version2=2'

- **Check for Conflict**:
- curl --location 'http://localhost:8080/files/resolve-conflicts' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'fileId=1' \
--data-urlencode 'resolvedContent=Resolved content here.'

- **Resolve Conflict**:
- curl --location 'http://localhost:8080/files/resolve-conflicts' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'fileId=1' \
--data-urlencode 'resolvedContent=Resolved content here.'

- **Resolve Conflict**:
- curl --location 'http://localhost:8080/files/merge' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'targetFileId=1' \
--data-urlencode 'sourceFileId=2'
