
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    private static Scanner input=new Scanner(System.in);

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
                    "0. Salir\n"+
                    "Opcion?: ");
            try {
                int option=Integer.parseInt(input.nextLine());
                switch(option){
                    case 1: insertEmpleado(); break;
                    case 2: deleteEmpleado(); break;
                    case 3: break;
                    case 4: break;
                    case 0: exit=true; break;
                    default: System.out.println("Opcion no válida"); break;
                }
            }catch(NumberFormatException nfe){
                System.out.println("Opcion no válida");
            }
        } while (!exit);
    }

    public static void insertEmpleado(){
        Empleado emp=null, mgr=null;
        Integer empno=null;
        String name, job;
        LocalDate hireDate=null;
        Double sal=null;
        Departamento dpt=null;
        do {
            try {
                System.out.print("ID de empleado: "); empno=Integer.parseInt(input.nextLine());
            } catch (NumberFormatException nfe){
                System.out.println("Formato no válido");
            }
        } while (emp==null);
        do {
            System.out.print("Nombre (max 30 caracteres): "); name=input.nextLine();
        } while (name.length()>30);
        do {
            System.out.print("Trabajo (max 9 caracteres): "); job=input.nextLine();
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

}
