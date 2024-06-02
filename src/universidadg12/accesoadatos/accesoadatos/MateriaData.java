
package universidadg12.accesoadatos.accesoadatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import universidadg12.entidades.entidades.Materia;


public class MateriaData {
    private Connection con=null;

    public MateriaData() {
        this.con= Conexion.getConexion();
    }
    
    public void guardarMateria(Materia materia){
        
        String sql ="INSERT INTO `materia`(`nombre`, `año`, `estado`)"
                + "VALUES(?,?,?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAnioMateria());
            ps.setBoolean(3, materia.isActivo());
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                materia.setIdMateria(1);
                JOptionPane.showMessageDialog(null, "Materia agregada");
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en tabla Materia");
        }
    }
    
    public Materia buscarMateria(int id) {
        // CÃƒÂ³digo para buscar una materia por ID en la base de datos
        String sql ="SELECT nombre, año, estado FROM materia WHERE idMateria = ?";
        Materia materia = null;
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);          
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                materia = new Materia();
                materia.setIdMateria(id);
                materia.setNombre(rs.getString("nombre"));
                materia.setAnioMateria(rs.getInt("año"));
                materia.setActivo(true);
            }else{
                JOptionPane.showMessageDialog(null, "No existe una materia con ese ID");
            }
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Materia");
        }
        return materia;
    }
    
    public void modificarMateria(Materia materia) {
        // CÃƒÂ³digo para actualizar una materia en la base de datos
        try {
            
            String sql = "UPDATE materia SET nombre = ?, año = ?, estado = ? WHERE idMateria = ?";
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAnioMateria());
            ps.setBoolean(3, materia.isActivo());
            ps.setInt(4,materia.getIdMateria());
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Materia actualizada con exito");
            
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la tabla Materia!");
        }
    }
    
   public void eliminarMateria(int idMateria) {
        String query = "UPDATE materia SET estado = 0 WHERE idMateria = ?";
        
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, idMateria);
            preparedStatement.executeUpdate();
            
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo eliminar la materia", "Error", JOptionPane.ERROR_MESSAGE);
            
        }
    }
   
   public List<Materia> listarMaterias() {
        List<Materia> materiasActivas = new ArrayList<>();
        String query = "SELECT alumno.idAlumno, alumno.nombre, materia.nombre FROM inscripcion JOIN alumno ON inscripcion.idAlumno = alumno.idAlumno JOIN materia ON inscripcion.idMateria = materia.idMateria WHERE alumno.idAlumno = ?;";
        
            try {
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet resultSet = ps.executeQuery();

        while (resultSet.next()) {
            int idMateria = resultSet.getInt("idMateria");
            String nombre = resultSet.getString("nombre");
            int año = resultSet.getInt("año");
            boolean activo = resultSet.getBoolean("estado");

            Materia materia = new Materia();
            materia.setIdMateria(idMateria);
            materia.setNombre(nombre);
            materia.setAnioMateria(año);
            materia.setActivo(activo);

            materiasActivas.add(materia);
        }            
        resultSet.close();
        ps.close();
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "No se pudo mostrar la lista, fijate que onda", "Error", JOptionPane.ERROR_MESSAGE);
    }

    return materiasActivas;
}
    
}