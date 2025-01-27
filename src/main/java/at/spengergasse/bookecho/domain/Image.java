package at.spengergasse.bookecho.domain;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import jakarta.persistence.*;

@Builder

@Embeddable
public record Image(
        String description,
        @NotNull String path){

    public Image{
        if(path == null || path.isEmpty()){
            throw new IllegalArgumentException("Path must not be null or empty");
        }
    }
}