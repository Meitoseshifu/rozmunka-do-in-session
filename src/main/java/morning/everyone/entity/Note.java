package morning.everyone.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "notes")
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "person")
@MappedSuperclass
public class Note {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String body;

    @ManyToOne(optional = false)
    @JoinColumn(name = "person_id", foreignKey = @ForeignKey(name = "notes_persons_fk"))
    private Person person;
}
