package com.alkham.libros.domain.entities;

import com.alkham.libros.domain.entities.enums.ReadStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_reads")
@NoArgsConstructor @Getter
public class Read {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Setter private Integer page = 1;
    @Setter private Integer totalPages;
    @Setter private Double zoom = 2.5;
    @Setter private LocalDateTime lastRead = LocalDateTime.now();
    private final LocalDateTime startReading = LocalDateTime.now();
    private Integer status = ReadStatus.READING.getCode();

    @JsonIgnore
    @OneToOne(mappedBy = "read", cascade = CascadeType.REFRESH)
    private Book book;

    public void setStatus(ReadStatus readStatus) {
        if (readStatus != null) this.status = readStatus.getCode();
    }
    public ReadStatus getStatus() {
        return ReadStatus.valueOf(status);
    }
}
