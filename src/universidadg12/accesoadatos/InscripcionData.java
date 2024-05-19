package universidadg12.accesoadatos;

import java.sql.Connection;
import java.sql.*;
import universidadg12.entidades.Inscripcion;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import universidadg12.entidades.Alumno;
import universidadg12.entidades.Materia;

/**
 *
 * @author MI EQUIPO
 */
public class InscripcionData {

    private Connection con = null;
    private MateriaData md= new MateriaData();
    private AlumnoData ad= new AlumnoData();

    public InscripcionData() {
        this.con = Conexion.getConexion();
    }

    public void guardarInscripcion(Inscripcion insc) {
        String sql = "INSERT INTO inscripcion(idAlumno, idMateria,nota)"
                + "VALUES(?,?,?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, insc.getAlumno().getIdAlumno());
            ps.setInt(2, insc.getMateria().getIdMateria());
            ps.setDouble(3, insc.getNota());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                insc.setIdInscripcion(rs.getInt(1));//solo hay una
                JOptionPane.showMessageDialog(null, "Inscripcion registrada");
            }

            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion");
        }
    }
    
    public List<Inscripcion> obtenerInscripciones(){
        ArrayList<Inscripcion>cursadas = new ArrayList<>();
        String sql="SELECT * FROM inscripcion";
        try{
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Inscripcion insc= new Inscripcion();
                insc.setIdInscripcion(rs.getInt("idInscripcion"));
                Alumno alu= ad.buscarAlumnoPor(rs.getInt("idAlumno"));
                Materia mat= md.buscarMateria(rs.getInt("idMateria"));
                insc.setAlumno(alu);
                insc.setMateria(mat);
                insc.setNota(rs.getDouble("nota"));
                cursadas.add(insc);
               
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion");
        }
        return cursadas;
    }
    
    public List<Inscripcion> obtenerInscripcionesPorAlumno(int idAlumno){
        ArrayList<Inscripcion>cursadas = new ArrayList<>();
        String sql="SELECT `idInscripto`,`idAlumno`,materia.idMateria,materia.nombre, nota FROM inscripcion JOIN materia ON (inscripcion.idMateria = materia.idMateria) WHERE idAlumno = ?";
        try{
            
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Inscripcion insc= new Inscripcion();
                insc.setIdInscripcion(rs.getInt("idInscripto"));
                insc.setNota(rs.getInt("nota"));
                
                //modificacion ahora
                Alumno alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                insc.setAlumno(alumno);
                
                Materia materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                insc.setMateria(materia);
                
                cursadas.add(insc);
//                Alumno alu= ad.buscarAlumnoPor(rs.getInt("idAlumno"));
//                Materia mat= md.buscarMateria(rs.getInt("idMateria"));
//                insc.setAlumno(alu);
//                insc.setMateria(mat);
//                insc.setNota(rs.getDouble("nota"));
//                cursadas.add(insc);
               
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion");
        }
        return cursadas;
    }
    
    //REVISAR
    public List<Materia> obtenerMateriasCursadas(int idAlumno){
        ArrayList<Materia> materias = new ArrayList<>();
        String sql=  "SELECT inscripcion.idMateria, nombre, año FROM inscripcion,"
            + "materia WHERE inscripcion.idMateria = materia.idMateria "
            + "AND inscripcion.idAlumno= ?;";
        try{
            PreparedStatement ps= con.prepareStatement (sql);
            ps.setInt(1, idAlumno);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                Materia materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnioMateria(rs.getInt("año"));
                materias.add(materia);
                
            }
             ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion");
        }
        return materias;
    }
    
    public List<Materia> obtenerMateriasNOCursadas(int idAlumno){
        ArrayList<Materia> materias = new ArrayList<>();
        String sql= "SELECT * FROM materia WHERE estado = 1 AND idMateria not in "
                +"(SELECT idMateria FROM inscripcion WHERE idAlumno = ?)";
        try{
            PreparedStatement ps= con.prepareStatement (sql);
            ps.setInt(1, idAlumno);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                Materia materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnioMateria(rs.getInt("año"));
                materias.add(materia);
                
            }
        ps.close();
    }   catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion");
            
        }
         return materias;
    }

    public void borrarInscripcionMateriaAlumno(int idAlumno,int idMateria){
        try {
            String sql = "DELETE FROM inscripcion WHERE idAlumno = ? AND idMateria = ?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);
            
            int filas=ps.executeUpdate();
            if(filas>0){
                JOptionPane.showMessageDialog(null, "Inscripcion Borrada");
            }
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion");
        }
    }
    
    public void actualizarNota(int idAlumno, int idMateria, double nota) {

        try {
            String sql = "UPDATE inscripcion SET nota = ? WHERE idAlumno = ? AND idMateria = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, nota);
            ps.setInt(2, idAlumno);
            ps.setInt(3, idMateria);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Nota actualizada");
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error ");
        }
    }
    
    public List<Alumno> obtenerAlumnosXMateria(int idMateria){
        ArrayList<Alumno> alumnos = new ArrayList<>();

        String sql= "SELECT * FROM alumno WHERE idAlumno IN "
            +"(SELECT idAlumno FROM inscripcion WHERE idMateria = ?)";
    try{
        PreparedStatement ps= con.prepareStatement (sql);
        ps.setInt(1, idMateria);
        ResultSet rs= ps.executeQuery();
        while(rs.next()){
            Alumno alumno = new Alumno();
            alumno.setIdAlumno(rs.getInt("idAlumno"));
            alumno.setDni(rs.getInt("dni"));
            alumno.setApellido(rs.getString("apellido"));
            alumno.setNombre(rs.getString("nombre"));
            
            alumnos.add(alumno);
        }
    ps.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion: " + ex.getMessage());
        JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion");
    }
    return alumnos;
    }
}
