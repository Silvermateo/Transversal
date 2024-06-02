/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package universidadg12.vistas;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import universidadg12.accesoadatos.accesoadatos.AlumnoData;
import universidadg12.accesoadatos.accesoadatos.InscripcionData;
import universidadg12.accesoadatos.accesoadatos.MateriaData;
import universidadg12.entidades.entidades.Alumno;
import universidadg12.entidades.entidades.Inscripcion;
import universidadg12.entidades.entidades.Materia;

/**
 *
 * @author Gonza
 */
public class FormularioNotas extends javax.swing.JInternalFrame {
    private DefaultTableModel modelo;
    private List<Materia> listaMat;
    private List<Alumno> listaAlu;
    //
    private List<Inscripcion> listaInscripcion;
    
    private InscripcionData inscData;
    private MateriaData matData;
    private AlumnoData alData;
    
    private Inscripcion insc = null;
    
    /**
     * Creates new form FormularioNotas
     */
    public FormularioNotas() {
        initComponents();
        alData = new AlumnoData();
        listaAlu = alData.listaAlumnos();
        matData = new MateriaData();
        modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int f, int c) {
                if(c==2){
                    return true;
                }
                return false;
            }
        };
        inscData = new InscripcionData();
        armarCabecera();
        
        cargarAlumnos();
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
        jcElegirAlumnoNotas = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtMaterias = new javax.swing.JTable();
        jbGuardarNota = new javax.swing.JButton();
        jbSalir = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        jPanel1.setBackground(new java.awt.Color(208, 208, 250));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel1.setText("Carga de Notas");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, -1));

        jLabel2.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel2.setText("Seleccione un Alumno");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, -1, -1));

        jcElegirAlumnoNotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcElegirAlumnoNotasActionPerformed(evt);
            }
        });
        jPanel1.add(jcElegirAlumnoNotas, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 180, -1));

        jtMaterias.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jtMaterias);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 340, 250));

        jbGuardarNota.setText("Guardar");
        jbGuardarNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGuardarNotaActionPerformed(evt);
            }
        });
        jPanel1.add(jbGuardarNota, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, -1, -1));

        jbSalir.setText("Salir");
        jbSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalirActionPerformed(evt);
            }
        });
        jPanel1.add(jbSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 370, -1, -1));

        jSeparator1.setForeground(new java.awt.Color(102, 102, 102));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 39, 340, 10));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalirActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jbSalirActionPerformed

    private void jcElegirAlumnoNotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcElegirAlumnoNotasActionPerformed
        // TODO add your handling code here:
        borrarFilaTabla();
        Alumno elegido = (Alumno)jcElegirAlumnoNotas.getSelectedItem();
        listaInscripcion = inscData.obtenerInscripcionesPorAlumno(elegido.getIdAlumno());
        if(listaInscripcion.size()>0){
            for(Inscripcion insc : listaInscripcion){
                modelo.addRow(new Object[]{
                    insc.getMateria().getIdMateria(),
                    insc.getMateria().getNombre(),
                    insc.getNota()
                });
            }
        }

//        cargarAlumnos();
//      
//        cargarNota();
        
    }//GEN-LAST:event_jcElegirAlumnoNotasActionPerformed

    private void jbGuardarNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGuardarNotaActionPerformed
          int filaSelec = jtMaterias.getSelectedRow();
          if(filaSelec != -1){
              Alumno a = (Alumno) jcElegirAlumnoNotas.getSelectedItem();
              int idMat = (Integer) modelo.getValueAt(filaSelec, 0);
              double nota = Double.parseDouble((String) modelo.getValueAt(filaSelec, 2));
              inscData.actualizarNota(a.getIdAlumno(), idMat, nota);
              borrarFilaTabla();
          }else{
              JOptionPane.showMessageDialog(this, "Seleccione la materia para modificar su nota");
          }

//        Alumno al = (Alumno) jcElegirAlumnoNotas.getSelectedItem();
//        for (int i = 0; i < jtMaterias.getRowCount(); i++) {
//            int idMateria = (int) jtMaterias.getValueAt(i, 0);
//            int nota = (int) jtMaterias.getValueAt(i, 2);
//            
//            inscData.actualizarNota(al.getIdAlumno(), idMateria, nota);
//        }
//        cargarNota();
    }//GEN-LAST:event_jbGuardarNotaActionPerformed

    private void armarCabecera(){
          modelo.addColumn("codigo");
          modelo.addColumn("Nombre");
          modelo.addColumn("Nota");
          jtMaterias.setModel(modelo);
//        ArrayList<Object> cabe = new ArrayList<>();
//        cabe.add("Codigo");
//        cabe.add("Nombre");
//        cabe.add("Nota");
//        
//        for (Object object : cabe) {
//            modelo.addColumn(object);
//        }
//        jtMaterias.setModel(modelo);
    }
    
    private void cargarAlumnos(){
        for(Alumno item: listaAlu){
            jcElegirAlumnoNotas.addItem(item);
        }
    }
    
    private void cargarNota(){
        Alumno al = (Alumno) jcElegirAlumnoNotas.getSelectedItem();
        List<Materia> listaMat = (ArrayList) inscData.obtenerMateriasCursadas(al.getIdAlumno());
        for (Materia ma : listaMat) {
            modelo.addRow(new Object[]{ma.getIdMateria(), ma.getNombre(),insc});
        }
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
    private javax.swing.JButton jbGuardarNota;
    private javax.swing.JButton jbSalir;
    private javax.swing.JComboBox<Alumno> jcElegirAlumnoNotas;
    private javax.swing.JTable jtMaterias;
    // End of variables declaration//GEN-END:variables
}
