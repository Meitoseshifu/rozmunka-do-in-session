package morning.everyone.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "address")
@NoArgsConstructor
@Getter
@Setter
public class Address {
    @Id
    private Long id;
    @Transient
    private String apt;
    private String city;
    private Integer number;
    private String street;

    @MapsId
    @OneToOne
    @JoinTable
    @JoinColumn(name = "user_id")
    private User user;
}
