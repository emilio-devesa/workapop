public class Departamento {

    private int deptno;
    private String dName;
    private String loc;

    public Departamento(int deptno, String NDepartamento, String loc) {
        this.deptno = deptno;
        this.dName = NDepartamento;
        this.loc = loc;
    }

    public int getDeptno() {
        return deptno;
    }

    public String getNDepartamento() {
        return dName;
    }

    public String getLoc() {
        return loc;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    public void setNDepartamento(String NDepartamento) {
        this.dName = NDepartamento;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }
}
