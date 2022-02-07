import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name = "EMP", catalog = "worka-pop")
public class Empleado {

    @Id
    @Column(name="EMPNO")
    private int empno;
    @Column(name="ENAME")
    private String ename;
    @Column(name="JOB")
    private String job;
    @Column(name="MGR")
    private Integer mgr;
    @Column(name="HIREDATE")
    private String hiredate;
    @Column(name="SAL")
    private double sal;
    @Column(name="DEPTNO")
    private Departamento deptno;

    public Empleado(int empno, String ename, String job, Integer mgr, String hiredate, double sal, Departamento deptno) {
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.hiredate = hiredate;
        this.sal = sal;
        this.deptno = deptno;
    }

    public int getEmpno() {
        return empno;
    }

    public String getEname() {
        return ename;
    }

    public String getJob() {
        return job;
    }

    public Integer getMgr() {
        return mgr;
    }

    public String getHiredate() {
        return hiredate;
    }

    public double getSal() {
        return sal;
    }

    public Departamento getDeptno() {
        return deptno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public void setename(String ename) {
        this.ename = ename;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setMgr(Integer mgr) {
        this.mgr = mgr;
    }

    public void setHiredate(String hiredate) {
        this.hiredate = hiredate;
    }

    public void setSal(double sal) {
        this.sal = sal;
    }

    public void setDeptno(Departamento deptno) {
        this.deptno = deptno;
    }
}
