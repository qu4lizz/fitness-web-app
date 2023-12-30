package qu4lizz.ip.fitness.server.models.entities;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "program_image", schema = "public", catalog = "fitness")
public class ProgramImageEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "image")
    private byte[] image;
    @Basic
    @Column(name = "id_program")
    private Integer idProgram;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Integer getIdProgram() {
        return idProgram;
    }

    public void setIdProgram(Integer idProgram) {
        this.idProgram = idProgram;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgramImageEntity that = (ProgramImageEntity) o;
        return Objects.equals(id, that.id) && Arrays.equals(image, that.image) && Objects.equals(idProgram, that.idProgram);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, idProgram);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }
}
