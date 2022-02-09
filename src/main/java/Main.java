
import java.util.Scanner;

public class Main {

    private static Scanner input=new Scanner(System.in);

    public static void main(String[] args){
        HibernateUtil.buildSessionFactory();
        menu();
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
                    case 1: break;
                    case 2: deleteEmpleado(); break;
                    case 3: break;
                    case 4: break;
                    case 0: exit=true; break;
                    default: System.out.println("Opcion no válida"); break;
                }
            }catch(NumberFormatException nfe){
                // nfe.printStackTrace();
                System.out.println("Opcion no válida");
            }
        } while (!exit);
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
            // nfe.printStackTrace();
            System.out.println("Formato de ID no válido");
        }
    }

}
