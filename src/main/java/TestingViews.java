import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Scanner;

import databaseTables.*;
import database.Empleado;
import database.Departamento;


public class TestingViews {

    final String indiceEmpleado = "Número    Nombre    Trabajo    Trabaja para    Data de contratación    Salario    Departamento";

    private ArrayList<Empleado> empleados;
    private final Scanner scanner = new Scanner(System.in);

    /**
     * En caso de que no se hayan cargado todavía los datos de Empleado de la base de datos los solicitará.
     * Si ya existen datos cargados preguntará si se desean actualizar, en caso afirmativo borrará los datos actuales y volverá a solicitarlos.
     */
    private void cargarEmpleados() {
        if (empleados == null) {
            System.out.println("Solicitando datos de empleados...");
            Query query = HibernateUtil.getCurrentSession().createQuery("FROM EMP ");
            empleados = (ArrayList<Empleado>) query.list();
            System.out.println("Los se han obtenido exitosamente.");
        } else {
            System.out.println("Ya existen datos cargados, quieres actualizarlos? ('si' para confirmar)");
            if (scanner.nextLine().equalsIgnoreCase("si")) {
                empleados = null;
                cargarEmpleados();
            } else {
                System.out.println("Procediendo a mostrar los datos...");
            }

        }
    }

    /**
     * Crea una query manejada por HibernateUtil para solicitar un ArrayList con todos los "departamentos" de la base de datos.
     *
     * @return Devuelve el ArrayList solicitado.
     */
    public ArrayList<Departamento> cargarDepartamentos() {
        Query query = HibernateUtil.getCurrentSession().createQuery("FROM DEPT");
        return (ArrayList<Departamento>) query.list();
    }

    /**
     * Muestra por terminal todos los datos de una instancia de la clase Empleado.
     *
     * @param empleado Instancia de la clase Empleado que será mostrada.
     */
    public void verSoloEmpleado(Empleado empleado) {
        System.out.print(empleado.getId() + " ");
        System.out.print(empleado.getEname() + "  ");
        System.out.print(empleado.getJob() + "  ");
        System.out.print(empleado.getMgr() + " ");
        System.out.print(empleado.getHiredate() + " ");
        System.out.print(empleado.getSal() + " ");
        System.out.print(empleado.getDeptno() + " ");
    }

    /**
     * Muestra por terminal todos los datos de una instancia de la clase Departamento.
     *
     * @param departamento Instancia de la clase Departamento que será mostrada.
     */
    public void verSoloDepartamento(Departamento departamento) {
        System.out.print(departamento.getNDepartamento() + "  ");
        System.out.print(departamento.getId() + "  ");
        System.out.print(departamento.getLoc() + "  ");
    }

    /**
     * Llama a obtenerEmpleados() para conseguir un arrayList de la clase Empleado que ira mostrando en pantalla
     * mediante repetidas llamadas a verSoloEmpleado().
     */
    public void verEmpleados() {
        cargarEmpleados();
        System.out.println("************************************************************************************************************");
        System.out.println();
        System.out.println(indiceEmpleado);
        for (Empleado empleado : empleados) {
            verSoloEmpleado(empleado);
        }

    }

    /**
     * Llama a obtenerDepartamentos() para conseguir un arrayList de la clase Departamento que ira mostrando en pantalla
     * mediante repetidas llamadas a verSoloDepartamento().
     */
    public void verDepartamentos() {
        ArrayList<Departamento> departamento = cargarDepartamentos();
        System.out.println("************************************************************************************************************");
        System.out.println();
        System.out.println("Número    Nombre    Localización");
        for (Departamento value : departamento) {
            verSoloDepartamento(value);
        }
    }

    //Todos los métodos siguientes sirven para gestionar mostrarRelacionJefeEmpleado()

    /**
     * Submenu que deja al usuario irse "posicionando" en los empleados para ir mostrando su jefe
     * o empleados de los que esta a cargo. Una vez mostrado una de las opciónes se posicionará en un nuevo
     * ya sea el jefe o una de las personas que tiene a cargo el empleado en el que estaba posicionado.
     * Este método trata de dejar al usuario ver la relación de empleado-jefe movimiendose por un
     * "esquema en arból".
     * Esto realmente no almacena los datos de ningúna manera especial, tan solo simula hacerlo de modo que es bastante
     * ineficiente en cuanto al procesamiento de estos y no sería factible si la base de datos constase de un gran tamaño.
     */
    public void mostrarRelacionJefeEmpleados() {
        cargarEmpleados();
        ArrayList<Empleado> auxEmpleados = empleados;
        Empleado empleadoActual;
        int option = 0;
        System.out.println("A continuación se irán mostrando la relación empleado-jefe a modo de árbol.");
        System.out.println("Puede irse moviendo por él introduciendo una de las opciónes o saltando directamente a un empleado concreto.");

        verEmpleados();
        //Posicionando empleado inicial.
        do {
            System.out.print("Introduzca el número de algún empleado: ");
            empleadoActual = buscarEmpleadoPorNumero(scanner.nextInt());
        }while(empleadoActual!=null);

        //Trabajando con el árbol de empleados
        //Aunque se denomine arbol el usuario puede introducir cualquier id y posicionarse en esta.
        do {
            System.out.println("Empleado actual: ");
            System.out.println(empleadoActual.getEname());
            System.out.println();
            System.out.println("Que desea hacer?");
            System.out.println("1. Mostrar jefe.");
            System.out.println("2. Mostrar empleados.");
            System.out.println("3. Posicionarse en otro usuario");
            System.out.println("4. Salir");
            System.out.println();
            System.out.println("Introduzca una opción: ");

            switch (scanner.nextInt()){
                case 1:
                    System.out.println("Mostrando jefe...");
                    empleadoActual = buscarJefeDe(empleadoActual);
                    break;
                case 2:
                    empleadoActual = buscarEmpleadosDe(empleadoActual);
                    break;
                case 3:
                    do {
                        System.out.print("Introduzca el número de algún empleado: ");
                        empleadoActual = buscarEmpleadoPorNumero(scanner.nextInt());
                    }while(empleadoActual!=null);
                    break;
                case 4:
                    System.out.println("Tenga un buen día!");
                    empleadoActual = null;
                    break;
                default:
                    System.out.println("Introduzca una opción válida");
                    break;
            }
        }while (empleadoActual != null);
    }

    /**
     * Consulta la relación de los empleados para devolver el "jefe" del "empleado" pasado como parámetro, de momento es solo
     * llamado dentro del método mostrarRelacionJefeEmpleado().
     * @param empleado instancia de la clase Empleado, hace referencia al empleadoActual del método mostrarRelacionJefeEmpleado().
     * @return Devoverá el "jefe" del "empleado" pásado como parámetro o, en caso de no existir este, el propio parámetro introducido de nuevo.
     */
    private Empleado buscarJefeDe(Empleado empleado) {
        System.out.println("Buscando Jefe del empleado: " + empleado.getEname());
        if(empleado.getMgr() != null) {
            for (Empleado value : empleados) {
                if (value.getId() == empleado.getMgr().getId()) {
                    System.out.println("Busqueda exitosa!");
                    System.out.println(indiceEmpleado);
                    verSoloEmpleado(value);
                    return value;
                }
            }
        }
        System.out.println("No se puede realizar esta acción.");
        return empleado;
    }
    /**
     * Consulta la relación de los empleados para devolver los empleados del "jefe" pasado como parámetro, de momento es solo
     * llamado dentro del método mostrarRelacionJefeEmpleado().
     * @param jefe instancia de la clase Empleado, hace referencia al empleadoActual del método mostrarRelacionJefeEmpleado().
     * @return Devolverá un empleado elegido por el usuario de entre todos de los que el "jefe está a cargo".
     */
    private Empleado buscarEmpleadosDe(Empleado jefe) {
        ArrayList<Empleado> listaEmpleados = null;
        Empleado empleadoDevuelto = jefe; //Empleado devuelto de la lista obtenida, será en el que se posicione el usuario.
        boolean bool;
        System.out.println("Buscando empleados de: " + jefe.getId());
        System.out.print("   "); //Meramente decorativo
        System.out.println(indiceEmpleado);

        //Lista todos los empleados del "jefe"
        for (Empleado empleado : empleados) {
            if (jefe.getId() == empleado.getMgr().getId()) {
                listaEmpleados.add(empleado);
                System.out.println((listaEmpleados.indexOf(empleado)+1)+")");
                verSoloEmpleado(empleado);
            }
            System.out.println();
            System.out.println("Introduzca la opción del empleado en el que desea posicionarse: ");
        }

        //Solicita al usuario posicionarse en uno de los empleados.
        do{
            bool = false;
            try {
                empleadoDevuelto = listaEmpleados.get(scanner.nextInt());
            }catch(Exception e){
                System.out.println("Introduzca un número valido");
                bool = true;
            }
        }while(bool);
        return empleadoDevuelto;
    }

    /**
     * Busca y retorna a un empleado de la lista empleados.
     * @param NoEmpleado número único de cada empleado para identificarlo.
     * @return Devuelve una instancia de la clase Empleado.
     */
    private Empleado buscarEmpleadoPorNumero(int NoEmpleado) {
        System.out.println("Buscando al empleado número: " + NoEmpleado);
        for (Empleado empleado : empleados) {
            if (empleado.getId() == NoEmpleado) {
                System.out.println("Empleado encontrado con exito.");
                return empleado;
            }
        }
        System.out.println("No se ha encontrado al empleado buscado.");
        return null;
    }
}
