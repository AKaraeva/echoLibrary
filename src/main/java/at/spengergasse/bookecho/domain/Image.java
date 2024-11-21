package at.spengergasse.bookecho.domain;

public record Image (String description, String path){

    public Image{
        if(description == null || description.isBlank()){
            throw new IllegalArgumentException("Description must not be null or empty");
        }
        if(path == null || path.isBlank()){
            throw new IllegalArgumentException("Path must not be null or empty");
        }
    }
}