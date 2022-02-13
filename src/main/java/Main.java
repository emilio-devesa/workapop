import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Un programa que modela un software de gestión de una empresa.
 * @author Alejandro Rey Fernández
 * @author Emilio Devesa
 * @author Miguel Alejandro Pita Prieto
 * @author Adrián Brey Becerra
 * @version 0.1
 */
public class Main {

    private static final Scanner input=new Scanner(System.in);
    private static final String empHeader="ID \t Nombre \t Puesto \t Manager \t Contratación \t Salario \t Departamento";
    private static final String dptHeader="ID \t Nombre \t Ubicación";

    /**
     * Punto de entrada al código de ejecución del programa. Se muestra un menú con distintas opciones.
     * @param args El String que representa los argumentos con los que el programa se puede lanzar desde consola
     */
    public static void main(String[] args){
        try {
            HibernateUtil.buildSessionFactory();
            menu();
        } catch (Throwable ex){
            System.out.println("No se pudo crear la sesión de conexión a la BBDD");
        }
    }

    public static void menu(){
        boolean exit=false;
        do{
            System.out.print("WorkaPOP\n"+
                    "1. Insertar empleado\n"+
                    "2. Borrar empleado\n"+
                    "3. Listar empleados\n"+
                    "4. Listar departamentos\n"+
                    "5. Mostrar relacion Jefe <-> Empleados\n"+
                    "0. Salir\n"+
                    "Opcion?: ");
            try {
                int option=Integer.parseInt(input.nextLine());
                switch(option){
                    case 1: insertEmpleado(); break;
                    case 2: deleteEmpleado(); break;
                    case 3: listEmpleados(); break;
                    case 4: listDepartamentos(); break;
                    case 5: mostrarRelacionJefeEmpleados(); break;
                    case 0: exit=true; break;
                    default: System.out.println("Opcion no válida"); break;
                }
            }catch(NumberFormatException nfe){
                System.out.println("Opcion no válida");
            }
        } while (!exit);
    }

    /**
     * Lee los datos de un nuevo Empleado y lo almacena en la base de datos
     */
    public static void insertEmpleado(){
        Empleado emp, mgr=null;
        Integer empno=null;
        String name, job;
        LocalDate hireDate=LocalDate.now();
        Double sal=null;
        Departamento dpt;
        do {
            try {
                System.out.print("ID de empleado: "); empno=Integer.parseInt(input.nextLine());
            } catch (NumberFormatException nfe){
                System.out.println("Formato no válido");
            }
        } while (empno==null);
        do {
            System.out.print("Nombre (max 30 caracteres): "); name=input.nextLine();
        } while (name.length()>30);
        do {
            System.out.print("Puesto de trabajo (max 9 caracteres): "); job=input.nextLine();
        } while (job.length()>9);
        do {
            try{
                System.out.print("ID de manager: "); mgr=Empleado.getEmpleadoById(Integer.parseInt(input.nextLine()));
                if (mgr==null){
                    System.out.println("No existe un empleado con ese ID");
                }
            } catch (NumberFormatException nfe){
                System.out.println("Formato no válido");
            }
        } while (mgr==null);
        do {
            try {
                System.out.print("Salario: "); sal=Double.parseDouble(input.nextLine());
            } catch (NumberFormatException nfe){
                System.out.println("Formato no válido");
            }
        } while (sal==null);
        do {
            System.out.print("ID de departamento: "); dpt=Departamento.getDepartamentoById(Integer.parseInt(input.nextLine()));
        } while (dpt==null);
        System.out.print("Almacenando empleado... ");
        emp=new Empleado(empno, name, job, mgr, hireDate, sal, dpt);
        emp.save();
        System.out.println("OK!");
    }

    /**
     * Lee el ID de un Empleado y lo elimina en la base de datos
     */
    public static void deleteEmpleado(){
        System.out.print("Introducir ID de empleado: ");
        try{
            Integer empno=Integer.parseInt(input.nextLine());
            Empleado e=Empleado.getEmpleadoById(empno);
            if (e==null){
                System.out.println("El empleado no existe");
            } else {
                e.delete();
                System.out.println("Empleado borrado");
            }
        } catch(NumberFormatException nfe){
            System.out.println("Formato de ID no válido");
        }
    }

    /**
     * Muestra información de los empleados
     */
    public static void listEmpleados(){
        System.out.println(empHeader);
        ArrayList<Empleado> list=Empleado.getAll();
        for (Empleado e: list){
            System.out.println(e.toString());
        }
    }

    /**
     * Muestra información de los departamentos
     */
    public static void listDepartamentos(){
        System.out.println(dptHeader);
        ArrayList<Departamento> list=Departamento.getAll();
        for (Departamento d: list){
            System.out.println(d.toString());
        }
    }

    /**
     * Submenu que permite al usuario ver el jefe de un empleado o los empleados bajo su cargo
     */
    public static void mostrarRelacionJefeEmpleados(){
        Empleado empleado=null;
        ArrayList<Empleado> listaEmpleados=null;
        do {
            try {
                listEmpleados();
                System.out.print("ID de empleado: ");
                empleado=Empleado.getEmpleadoById(Integer.parseInt(input.nextLine()));
                if (empleado==null){
                    System.out.println("El empleado no existe");
                }
            } catch (NumberFormatException nfe){
                System.out.print("Formato no válido. Pulse INTRO. ");
                input.nextLine();
            }
        } while (empleado==null);
        boolean exit=false;
        do {
            System.out.println("Empleado actual: "+empleado.getEname());
            System.out.print("Que desea hacer?\n"+
                    "1. Mostrar jefe\n"+
                    "2. Mostrar empleados a su cargo\n"+
                    "0. Volver al menu principal\n"+
                    "Introduzca una opción: ");
            try{
                switch (input.nextInt()){
                    case 1:
                        System.out.println("Jefe: "+empleado.getMgr().getEname());
                        break;
                    case 2:
                        listaEmpleados = Empleado.getEmpleadosFromManager(empleado);
                        if (!listaEmpleados.isEmpty()) {
                            System.out.println(empHeader);
                            for (Empleado e: listaEmpleados){
                                System.out.println(e.toString());
                            }
                        } else {
                            System.out.println("No tiene empledos a su cargo");
                        }
                        break;
                    case 0: exit=true; break;
                    default: System.out.println("Opcion no válida"); break;
                }
            } catch (NumberFormatException nfe){
                System.out.println("Opcion no válida");
            }
        }while (!exit);
    }

}
