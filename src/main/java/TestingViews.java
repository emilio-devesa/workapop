
import org.hibernate.query.Query;
import java.util.ArrayList;
import database.Empleado;
import database.Departamento;

public class TestingViews {

    /**
     * Crea una query manejada por HibernateUtil para solicitar un ArrayList con todos los "empleados" de la base de datos.
     * @return Devuelve el ArrayList solicitado.
     */
    public ArrayList<Empleado> obtenerEmpleados(){
        Query query = (Query) HibernateUtil.getCurrentSession().createQuery("FROM EMP");
        ArrayList<Empleado> empleados = (ArrayList<Empleado>) query.list();
        return empleados;
    }

    /**
     * Crea una query manejada por HibernateUtil para solicitar un ArrayList con todos los "departamentos" de la base de datos.
     * @return Devuelve el ArrayList solicitado.
     */
    public ArrayList<Departamento> obtenerDepartamentos(){
        Query query = (Query) HibernateUtil.getCurrentSession().createQuery("FROM DEPT");
        ArrayList<Departamento> departamentos = (ArrayList<Departamento>) query.list();
        return departamentos;
    }

    /**
     * Muestra por terminal todos los datos de una instancia de la clase Empleado.
     * @param empleado Instancia de la clase Empleado que será mostrada.
     */
    public void verSoloEmpleado(Empleado empleado){
        System.out.print(empleado.getEmpno()+" ");
        System.out.print(empleado.getEname()+"  ");
        System.out.print(empleado.getJob()+"  ");
        System.out.print(empleado.getMgr()+" ");
        System.out.print(empleado.getHiredate()+" ");
        System.out.print(empleado.getSal()+" ");
        System.out.print(empleado.getDeptno()+" ");
    }
    /**
     * Muestra por terminal todos los datos de una instancia de la clase Departamento.
     * @param departamento Instancia de la clase Departamento que será mostrada.
     */
    public void verSoloDepartamento(Departamento departamento){
        System.out.print(departamento.getNDepartamento()+"  ");
        System.out.print(departamento.getDeptno()+"  ");
        System.out.print(departamento.getLoc()+"  ");
    }

    public void verEmpleados(){
        ArrayList<Empleado> empleados = obtenerEmpleados();
        System.out.println("************************************************************************************************************");
        System.out.println();
        System.out.println("Número    Nombre    Trabajo    Trabaja para    Data de contratación    Salario    Departamento");
        for(int i=0; i<empleados.size(); i++){
            verSoloEmpleado(empleados.get(i));
        }

    }
    public void verDepartamentos(){
        ArrayList<Departamento> departamento = obtenerDepartamentos();
        System.out.println("************************************************************************************************************");
        System.out.println();
        System.out.println("Número    Nombre    Localización");
        for(int i=0; i<departamento.size(); i++){
            verSoloDepartamento(departamento.get(i));
        }
    }
}
