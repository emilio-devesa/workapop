package database;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.Scanner;


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
    private static Scanner input = new Scanner(System.in);


    public Empleado(Integer codEmp, String nomEmp, String trbEmp, int jefEmp, LocalDate fecha, Double salEmo, int codDep) {
    }

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



    public static String readAttr(int option){
        switch(option) {
            case 0: { System.out.print("Codigo empleado? "); break; }
            case 1: { System.out.print("Nombre empleado? "); break; }
            case 2: { System.out.print("Trabajo empleado? "); break; }
            case 3: { System.out.print("Codigo jefe empleado? "); break; }
            case 4: { System.out.print("Fecha de contrato empleado? "); break;}
            case 5: { System.out.print("Salario empleado? "); break;}
            case 6: { System.out.print("Departamento? "); break;}
        }
        return input.nextLine();
    }

    public static void insert() {
        Integer codEmp = 0;
        int codDep=0;
        int jefEmp= 0;
        double salEmo=0.0;
        String nomEmp="", trbEmp="";
        boolean vv = false;
        while(!vv) {
            try {
                codEmp = Integer.valueOf(readAttr(0));
                vv=true;
            } catch (NumberFormatException NFE) {
                System.out.println("Debes introducir un número");
            }
        }
        vv = false;

        while(!vv){
            nomEmp = readAttr(1);
            if (nomEmp.length()<=10){
                vv=true;
            }
            else{
                System.out.println("el nombre es demasiado largo");
            }
        }
        vv = false;

        while(!vv){
            trbEmp = readAttr(2);
            if (trbEmp.length()<=9){
                vv=true;
            }
            else {
                System.out.println("El nombre del trabajo es demasiado largo");
            }
        }
        vv = false;

        while(!vv) {
            try {

                vv=true;
            } catch (NumberFormatException NFE) {
                System.out.println("Debes introducir un número");
            }
        }
        vv = false;
        while(!vv) {
            try {
                salEmo = Double.valueOf(readAttr(5));
                vv=true;
            } catch (NumberFormatException NFE) {
                System.out.println("Debes introducir un número");
            }
        }
        vv = false;

        while(!vv) {
            try{

                vv=true;
            }catch (NumberFormatException NFE) {
                System.out.println("Debes introducir un número");
            }
        }
        LocalDate fecha= LocalDate.now();
//        Empleado emp = new Empleado(codEmp,nomEmp,trbEmp,jefEmp,fecha,salEmo,codDep);

   //     HibernateUtil.addObject(emp);
    }
}