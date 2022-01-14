package com.quellkunst.nemesis.model;

import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@NoArgsConstructor
public abstract class FileEntityBase {
    public String fileName;
    public Long fileId;

    public FileEntityBase(String fileName, Long fileId) {
        this.fileName = fileName;
        this.fileId = fileId;
    }
}
