package databaseTables;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "EMP")
public class Empleado {
    @Id
    @Column(name = "EMPNO", nullable = false)
    private Integer id;

    @Column(name = "ENAME", length = 10)
    private String ename;

    @Column(name = "JOB", length = 9)
    private String job;

    @ManyToOne
    @JoinColumn(name = "MGR")
    private Empleado mgr;

    @Column(name = "HIREDATE")
    private LocalDate hiredate;

    @Column(name = "SAL")
    private Double sal;

    @ManyToOne(optional = false)
    @JoinColumn(name = "DEPTNO", nullable = false)
    private Departamento deptno;

    public Departamento getDeptno() {
        return deptno;
    }

    public void setDeptno(Departamento deptno) {
        this.deptno = deptno;
    }

    public Double getSal() {
        return sal;
    }

    public void setSal(Double sal) {
        this.sal = sal;
    }

    public LocalDate getHiredate() {
        return hiredate;
    }

    public void setHiredate(LocalDate hiredate) {
        this.hiredate = hiredate;
    }

    public Empleado getMgr() {
        return mgr;
    }

    public void setMgr(Empleado mgr) {
        this.mgr = mgr;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}