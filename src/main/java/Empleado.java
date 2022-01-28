public class Empleado {

    private int empno;
    private String ename;
    private String job;
    private Departamento mgr;
    private String hiredate;
    private double sal;
    private double comm;
    private Departamento deptno;

    public Empleado(int empno, String ename, String job, Departamento mgr, String hiredate, double sal, double comm, Departamento deptno) {
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.hiredate = hiredate;
        this.sal = sal;
        this.comm = comm;
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

    public double getComm() {
        return comm;
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

    public void setComm(double comm) {
        this.comm = comm;
    }

    public void setDeptno(Departamento deptno) {
        this.deptno = deptno;
    }
}
