import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;


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
    private LocalDate hireDate;

    @Column(name = "SAL")
    private Double sal;

    @ManyToOne(optional = false)
    @JoinColumn(name = "DEPTNO", nullable = false)
    private Departamento deptno;

    protected Empleado(){
        // Empty constructor, required by Hibernate
    }

    public Empleado(Integer id, String ename, String job, Empleado mgr, LocalDate hireDate, Double salEmo, Departamento dept) {
        this.id=id;
        this.ename=ename;
        this.job=job;
        this.mgr=mgr;
        this.hireDate=hireDate;
        this.sal=sal;
        this.deptno=dept;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Empleado getMgr() {
        return mgr;
    }

    public void setMgr(Empleado mgr) {
        this.mgr = mgr;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public Double getSal() {
        return sal;
    }

    public void setSal(Double sal) {
        this.sal = sal;
    }

    public Departamento getDeptno() {
        return deptno;
    }

    public void setDeptno(Departamento deptno) {
        this.deptno = deptno;
    }

    public static Empleado getEmpleadoById(Integer id){
        Query query = HibernateUtil.getCurrentSession().createQuery("FROM EMP ");
        ArrayList<Empleado> list = (ArrayList<Empleado>) query.list();
        Empleado empleado=null;
        for (Empleado e: list) if (e.getId()==id) empleado=e;
        return empleado;
        // **** Método anterior ****
        // Session sesion= HibernateUtil.getCurrentSession();
        // sesion.beginTransaction();
        // Empleado emp=sesion.get(Empleado.class, id);
        // sesion.getTransaction().commit();
        // sesion.close();
    }

    public void save(){
        Session sesion = HibernateUtil.getCurrentSession();
        sesion.beginTransaction();
        sesion.save(this);
        sesion.getTransaction().commit();
        sesion.close();
    }

    public void delete(){
        Session sesion = HibernateUtil.getCurrentSession();
        sesion.beginTransaction();
        sesion.delete(this);
        sesion.getTransaction().commit();
        sesion.close();
    }

}