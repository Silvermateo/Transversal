/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package universidadg12.vistas;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import universidadg12.accesoadatos.InscripcionData;
import universidadg12.accesoadatos.MateriaData;
import universidadg12.entidades.Alumno;
import universidadg12.entidades.Inscripcion;
import universidadg12.entidades.Materia;


/**
 *
 * @author Gonza
 */
public class AlumnosPorMateriaView extends javax.swing.JInternalFrame {
    private DefaultTableModel modelo;
    private List<Alumno> listAlumno;
    private List<Materia> listMateria;
    private List<Inscripcion> listInscripcion;
    
    private InscripcionData inscData;
    private MateriaData matData;
    private Alumno alData;
    
    /**
     * Creates new form AlumnosPorMateriaView
     */
    public AlumnosPorMateriaView() {
        initComponents();
        modelo = new DefaultTableModel();
        armarCabeceraTabla();
        cargarMaterias();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jcMateria = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtAlumnosXmateria = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jbSalir = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel1.setBackground(new java.awt.Color(208, 208, 250));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel1.setText("Listado de Alumnos por Materia");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, -1, -1));

        jLabel2.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel2.setText("Seleccione una Materia");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, -1));

        jcMateria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcMateriaActionPerformed(evt);
            }
        });
        jPanel1.add(jcMateria, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, 210, -1));

        jtAlumnosXmateria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jtAlumnosXmateria);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 390, 250));

        jSeparator1.setForeground(new java.awt.Color(102, 102, 102));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 390, 10));

        jbSalir.setText("Salir");
        jbSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalirActionPerformed(evt);
            }
        });
        jPanel1.add(jbSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(358, 362, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalirActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jbSalirActionPerformed

    private void jcMateriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcMateriaActionPerformed
        cargarMaterias();
        
//        Materia elegida = (Materia) jcMateria.getSelectedItem();
//        listAlumno = matData.listarMaterias(elegida.getIdMateria());
//        if(listMateria.size()>0){
//            for(Materia mat : listMateria){
//                modelo.addRow(new Object[]{
//                    mat.get
//                });
//            }
//        }
    }//GEN-LAST:event_jcMateriaActionPerformed

    private void cargarMaterias(){
        borrarFilaTabla();
        MateriaData mat = new MateriaData();
        InscripcionData i = new InscripcionData();
        String materiaSeleccionada = (String) jcMateria.getSelectedItem();
        ArrayList <Alumno> alumnos = new ArrayList<>();
        
        for(Materia m: mat.listarMaterias()){
            if(m.getNombre().equalsIgnoreCase(materiaSeleccionada)){
                for(Alumno a: i.obtenerAlumnosXMateria(m.getIdMateria())){
                    modelo.addRow(new Object[]{
                        a.getIdAlumno(),
                        a.getDni(),
                        a.getApellido(),
                        a.getNombre()
                    });
                }
            }
        }
//        InscripcionData ins = new InscripcionData();
//        Alumno alu = new Alumno();
//        Materia mat = new Materia();
//        if(jcMateria.getSelectedIndex() >= 1){
//            mat =(Materia)jcMateria.getSelectedItem();
//            
//            List<Alumno> lista = ins.obtenerAlumnosXMateria(mat.getIdMateria());
//            for(Alumno alumn: lista){
//                modelo.addRow(new Object[]{
//                    alumn.getDni(),
//                    alumn.getApellido(),
//                    alumn.getNombre()
//                });
//            }
//        }
    }
    
    private void armarCabeceraTabla(){
        modelo.addColumn("ID");
        modelo.addColumn("DNI");
        modelo.addColumn("Apellido");
        modelo.addColumn("Nombre");
        jtAlumnosXmateria.setModel(modelo);      
    }
    
    private void borrarFilaTabla(){
        int indice = modelo.getRowCount();
        
        for (int i = indice; i > 0; i--) {
            modelo.removeRow(0);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton jbSalir;
    private javax.swing.JComboBox jcMateria;
    private javax.swing.JTable jtAlumnosXmateria;
    // End of variables declaration//GEN-END:variables
}
