package pack.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table
public class Buser {

    @Id
    @Column(name="buser_no")
    private int bno;

    @Column(name="buser_name")
    private String bname;

    @Column(name="buser_tel")
    private String btel;

    @OneToMany(mappedBy = "buser")
    private List<Jikwon> jikwonList;
}
