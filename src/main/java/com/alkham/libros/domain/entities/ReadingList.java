package com.alkham.libros.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity @Table(name = "tb_reading_list")
@NoArgsConstructor
public class ReadingList {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter private Long id;
    @Getter private String name;
    @Getter @Setter private String description;

    @JsonFormat(pattern = "HH:mm dd/MM/yyyy", timezone = "America/Sao_Paulo" )
    @Getter private final LocalDateTime startDate = LocalDateTime.now();

    @ElementCollection
    @CollectionTable(name = "tb_categories", joinColumns = @JoinColumn(name = "tb_reading_list_id"))
    @Column(name = "description")
    @Getter private Set<String> categories = new HashSet<>();

    @ManyToOne
    @JsonIgnore
    @Getter @Setter private User user;

    @OneToMany(mappedBy = "readingList", orphanRemoval = true)
    @JsonIgnore
    @Getter private Set<Author> authors = new HashSet<>();

    /** Duas ReadingList's -> são "iguais" se possuierem o mesmo 'id' ou o mesmo 'nome'.
     * **/
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReadingList readingList = (ReadingList) o;
        if (id != null && id.equals(readingList.id)) return true;
        if (name.equalsIgnoreCase(readingList.name)) return true;
        return false;
    }
    @Override public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
