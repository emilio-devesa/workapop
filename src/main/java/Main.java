
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final Scanner input=new Scanner(System.in);

    public static void main(String[] args){
        try {
            HibernateUtil.buildSessionFactory();
        } catch (Throwable ex){
            System.out.println("No se pudo crear la sesión de conexión a la BBDD");
        }
        menu();
    }

    public static void menu(){
        boolean exit=false;
        do{
            System.out.print("""
                    WorkaPOP
                    1. Insertar empleado
                    2. Borrar empleado
                    3. Listar empleados
                    4. Listar departamentos
                    0. Salir
                    Opcion?:  """);
            try {
                int option=Integer.parseInt(input.nextLine());
                switch(option){
                    case 1: insertEmpleado(); break;
                    case 2: deleteEmpleado(); break;
                    case 3: listEmpleados(); break;
                    case 4: listDepartamentos(); break;
                    case 0: exit=true; break;
                    default: System.out.println("Opcion no válida"); break;
                }
            }catch(NumberFormatException nfe){
                System.out.println("Opcion no válida");
            }
        } while (!exit);
    }

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

    public static void listEmpleados(){
        System.out.println("ID \t Nombre \t Puesto \t Manager \t Contratación \t Salario \t Departamento");
        ArrayList<Empleado> list=Empleado.listAll();
        for (Empleado e: list){
            System.out.print(e.getId() + " \t");
            System.out.print(e.getEname() + " \t");
            System.out.print(e.getJob() + " \t");
            System.out.print(e.getMgr() + " \t");
            System.out.print(e.getHireDate() + " \t");
            System.out.print(e.getSal() + " \t");
            System.out.print(e.getDeptno() + "\n");
        }
    }

    public static void listDepartamentos(){
        System.out.println("ID \t Nombre \t Localización");
        ArrayList<Departamento> list=Departamento.listAll();
        for (Departamento d: list){
            System.out.print(d.getId()+" \t");
            System.out.print(d.getNDepartamento()+" \t");
            System.out.print(d.getLoc()+" \n");
        }
    }

}
