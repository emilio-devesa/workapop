public class Empleado {

    private int empno;
    private String ename;
    private String job;
    private Integer mgr;
    private String hiredate;
    private double sal;
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
