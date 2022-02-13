import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;

/**
 * Una clase que modela un departamento de una empresa.
 * @author Alejandro Rey Fernández
 * @author Emilio Devesa
 * @author Miguel Alejandro Pita Prieto
 * @author Adrián Brey Becerra
 * @version 0.1
 */
@Entity
@Table(name = "DEPT")
public class Departamento {
    @Id
    @Column(name = "DEPTNO", nullable = false)
    private Integer id;

    @Column(name = "NDepartamento", length = 15)
    private String nDepartamento;

    @Column(name = "LOC", length = 14)
    private String loc;

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getNDepartamento() {
        return nDepartamento;
    }

    public void setNDepartamento(String nDepartamento) {
        this.nDepartamento = nDepartamento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString(){
        String s="";
        s+=this.getId()+" \t";
        s+=this.getNDepartamento()+" \t";
        s+=this.getLoc();
        return s;
    }

    /**
     * Returns an object of class Departamento, selected by ID from the DB
     * @param id Integer representing an object unique ID
     * @return Object from class Departamento with the requested ID or null if it doesn't exist
     */
    public static Departamento getDepartamentoById(Integer id){
        Session sesion= HibernateUtil.getCurrentSession();
        sesion.beginTransaction();
        Departamento dpt=sesion.get(Departamento.class, id);
        sesion.getTransaction().commit();
        sesion.close();
        return dpt;
    }

    /**
     * Returns all objects from class Departamento in the database
     * @return A new collection of type ArrayList containing all objects of class Departamento in the database
     */
    public static ArrayList<Departamento> getAll(){
        Query query = HibernateUtil.getCurrentSession().createQuery("FROM Departamento");
        return (ArrayList<Departamento>) query.list();
    }
}