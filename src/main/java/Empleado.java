import javax.persistence.criteria.CriteriaBuilder;
import java.util.Scanner;

public class Empleado {

    private static Scanner input=new Scanner(System.in);

    private int empno;
    private String ename;
    private String job;
    private Departamento mgr;
    private String hiredate;
    private double sal;
    private Departamento deptno;

    public Empleado(int empno, String ename, String job, Departamento mgr, String hiredate, double sal, double comm, Departamento deptno) {
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

    public Departamento getMgr() {
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

    public void setMgr(Departamento mgr) {
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
        boolean vv = false;
            while(!vv) {
                try {
                    int codEmp = Integer.valueOf(readAttr(0));
                    vv=true;
                } catch (NumberFormatException NFE) {
                    System.out.println("Debes introducir un número");
                }
            }
            vv = false;

            while(!vv){
                String nomEmp = readAttr(1);
                if (nomEmp.length()<=10){
                    vv=true;
                }
                else{
                    System.out.println("el nombre es demasiado largo");
                }
            }
            vv = false;

            while(!vv){
                String trbEmp = readAttr(2);
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
                    int jefEmp = Integer.valueOf(readAttr(3));
                    vv=true;
                } catch (NumberFormatException NFE) {
                    System.out.println("Debes introducir un número");
                }
            }
            vv = false;
            while(!vv) {
                try {
                    int salEmo = Integer.valueOf(readAttr(5));
                    vv=true;
                } catch (NumberFormatException NFE) {
                    System.out.println("Debes introducir un número");
                }
            }
            vv = false;

            while(!vv) {
                try{
                    int codDep = Integer.valueOf(readAttr(6));
                    vv=true;
                }catch (NumberFormatException NFE) {
                    System.out.println("Debes introducir un número");
                }
            }

    }
}