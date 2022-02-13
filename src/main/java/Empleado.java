import org.hibernate.Session;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Una clase que modela un empleado de una empresa.
 * @author Alejandro Rey Fernández
 * @author Emilio Devesa
 * @author Miguel Alejandro Pita Prieto
 * @author Adrián Brey Becerra
 * @version 0.1
 */
@Entity
@Table(name = "EMP", catalog="WorkaPOP")
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

    public Empleado() {
    }

    public Empleado(Integer id, String ename, String job, Empleado mgr, LocalDate hireDate, Double sal, Departamento dept) {
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

    @Override
    public String toString(){
        String s="";
        s+=this.getId() + " \t";
        s+=this.getEname() + " \t";
        s+=this.getJob() + " \t";
        if (this.getMgr()==null){
            s+=0;
        }else {
            s+=this.getMgr().getId()+ " \t";
        }
        s+=this.getHireDate() + " \t";
        s+=this.getSal() + " \t";
        s+=this.getDeptno();
        return s;
    }

    /**
     * Saves the Empleado object in the database
     */
    public void save(){
        Session sesion = HibernateUtil.getCurrentSession();
        sesion.beginTransaction();
        sesion.save(this);
        sesion.getTransaction().commit();
        sesion.close();
    }

    /**
     * Deletes this Empleado object from the database
     */
    public void delete(){
        Session sesion = HibernateUtil.getCurrentSession();
        sesion.beginTransaction();
        sesion.delete(this);
        sesion.getTransaction().commit();
        sesion.close();
    }

    /**
     * Returns an object of class Empleado, selected by ID from the DB
     * @param id Integer representing an object unique ID
     * @return Object from class Empleado with the requested ID or null if it doesn't exist
     */
    public static Empleado getEmpleadoById(Integer id){
        return (Empleado) HibernateUtil.getCurrentSession().get(Empleado.class, id);
    }

    /**
     * Returns all objects from class Empleado in the database
     * @return A new collection of type ArrayList containing all objects of class Empleado in the database
     */
    public static ArrayList<Empleado> getAll(){
        Query query = HibernateUtil.getCurrentSession().createQuery("FROM Empleado");
        return (ArrayList<Empleado>) ((org.hibernate.query.Query<?>) query).list();
    }

    /**
     * Returns all objects from class Empleado in the database that have a specified manager
     * @param manager A object of class Empleado that is manager of the objects that will be returned
     * @return A new collection of type ArrayList containing all objects of class Empleado in the database that have a specific manager
     */
    public static ArrayList<Empleado> getEmpleadosFromManager(Empleado manager){
        ArrayList<Empleado> listaEmpleados=getAll();
        ArrayList<Empleado> empleadosFromManager=new ArrayList<Empleado>();
        for (Empleado e: listaEmpleados){
            if (e.getMgr()==manager) {
                empleadosFromManager.add(e);
            }
        }
        return empleadosFromManager;
    }

}