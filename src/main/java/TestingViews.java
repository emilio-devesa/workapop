import org.hibernate.query.Query;
import java.util.ArrayList;
import java.util.Scanner;

public class TestingViews {

    private ArrayList<Empleado> empleados;
    private Scanner scanner = new Scanner(System.in);

    /**
     * En caso de que no se hayan cargado todavía los datos de Empleado de la base de datos los solicitará.
     * Si ya existen datos cargados preguntará si se desean actualizar, en caso afirmativo borrará los datos actuales y volverá a solicitarlos.
     */
    private void cargarEmpleados(){
        if (empleados == null) {
            System.out.println("Solicitando datos de empleados...");
            Query query = (Query) HibernateUtil.getCurrentSession().createQuery("FROM EMP");
            ArrayList<Empleado> creandoEmpleados = (ArrayList<Empleado>) query.list();
            empleados = creandoEmpleados;
            System.out.println("Los se han obtenido exitosamente.");
        }else{
            System.out.println("Ya existen datos cargados, quieres actualizarlos? ('si' para confirmar)");
            if(scanner.nextLine().equalsIgnoreCase("si")){
                empleados = null;
                cargarEmpleados();
            }else{
                System.out.println("Procediendo a mostrar los datos...");
            }

        }
    }

    /**
     * Crea una query manejada por HibernateUtil para solicitar un ArrayList con todos los "departamentos" de la base de datos.
     * @return Devuelve el ArrayList solicitado.
     */
    public ArrayList<Departamento> cargarDepartamentos(){
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
        System.out.print(empleado.getComm()+" ");
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

    /**
     * Llama a obtenerEmpleados() para conseguir un arrayList de la clase Empleado que ira mostrando en pantalla
     * mediante repetidas llamadas a verSoloEmpleado().
     */
    public void verEmpleados(){
        cargarEmpleados();
        System.out.println("************************************************************************************************************");
        System.out.println();
        System.out.println("Número    Nombre    Trabajo    Trabaja para    Data de contratación    Salario    Comm    Departamento");
        for(int i=0; i<empleados.size(); i++){
            verSoloEmpleado(empleados.get(i));
        }

    }

    /**
     * Llama a obtenerDepartamentos() para conseguir un arrayList de la clase Departamento que ira mostrando en pantalla
     * mediante repetidas llamadas a verSoloDepartamento().
     */
    public void verDepartamentos(){
        ArrayList<Departamento> departamento = cargarDepartamentos();
        System.out.println("************************************************************************************************************");
        System.out.println();
        System.out.println("Número    Nombre    Localización");
        for(int i=0; i<departamento.size(); i++){
            verSoloDepartamento(departamento.get(i));
        }
    }

    public void mostrarRelacionJefesEmpleados(){
        cargarEmpleados();
        int numEmpleado = 0;
        System.out.println("A continuación se irán mostrando la relación empleado-jefe a modo de árbol.");
        System.out.println("Puede irse moviendo por él introduciendo los números de los empleados.");
        do{

        }while(numEmpleado != 0);;
    }
}
