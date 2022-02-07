package com.quellkunst.nemesis.service.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.util.Base64;


@RegisterForReflection
public class FileDto {
    public String name;
    public String extension;
    public String data;

    public static FileDto of(File file) {
        var dto = new FileDto();
        dto.name = file.name;
        dto.extension = file.extension;
        dto.data = Base64.getEncoder().encodeToString(file.data);
        return dto;
    }
}