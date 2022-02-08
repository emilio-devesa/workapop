package databaseTables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DEPT")
public class Departamento {
    @Id
    @Column(name = "DEPTNO", nullable = false)
    private Integer id;

    @Column(name = "NDepartamento", length = 15)
    private String nDepartamento;

    @Column(name = "LOC", length = 14)
    private String loc;

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getNDepartamento() {
        return nDepartamento;
    }

    public void setNDepartamento(String nDepartamento) {
        this.nDepartamento = nDepartamento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}