package com.alkham.libros.domain.entities.enums;
import com.alkham.libros.services.exceptions.ResourceNotFoundException;

public enum ReadStatus {
    // Leitura em progresso
    READING(1),
    // Leitura completa
    COMPLETED(2),
    // O usuário não retornou a leitura em x tempo
    PENDING(3),
    // Leitura abandonada
    ABANDONED(4);

    private int code;

    ReadStatus(int code){
        this.code = code;
    }
    public int getCode(){
        return code;
    }

    public static ReadStatus valueOf(int code){
        for (ReadStatus value : ReadStatus.values()){
            if (value.code == code) return value;
        }
        throw new ResourceNotFoundException("Invalid ReadingStatus code.");
    }
}
