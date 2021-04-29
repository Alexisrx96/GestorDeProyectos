/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordeproyectos.view.programador;

import gestordeproyectos.dao.DepartamentoDao;
import gestordeproyectos.dao.EmpleadoDao;
import gestordeproyectos.dto.CasoDto;
import gestordeproyectos.dto.DepartamentoDto;
import gestordeproyectos.dto.EmpleadoDto;
import gestordeproyectos.dto.EstadoEmpleadoDto;
import gestordeproyectos.dto.RolDto;
import gestordeproyectos.dto.UsuarioDto;
import gestordeproyectos.view.CasoActual;
import gestordeproyectos.view.LoginView;
import gestordeproyectos.view.MonitorearCasos;
import gestordeproyectos.view.VerBitacoras;
import gestordeproyectos.view.VerObservaciones;
import java.util.function.Consumer;
import javax.swing.JOptionPane;

/**
 *
 * @author Irvin
 */
public class MdiProgramador extends javax.swing.JFrame {

    /**
     * Creates new form MdiProgramador
     */
    private final EmpleadoDto PROGRAMADOR;
    private final DepartamentoDto DEPARTAMENTO;
    private final Consumer<CasoDto> VER_BITACORA;
    private final Consumer<CasoDto> VER_OBSERVACIONES;

    public MdiProgramador(UsuarioDto user) {
        initComponents();
        EmpleadoDao empDao = new EmpleadoDao();
        DepartamentoDao depDao = new DepartamentoDao();
        this.PROGRAMADOR = empDao.read(user);
        this.DEPARTAMENTO = depDao.read(PROGRAMADOR.getIdDepartamento());
        jMenuUsuario.setText(PROGRAMADOR.getNombreEmpleado());
        setTitle(DEPARTAMENTO.getNombreDepartamento() + " - Programador");
        setExtendedState(MdiProgramador.MAXIMIZED_BOTH);

        VER_BITACORA = caso -> {
            if (!VerBitacoras.isOpen) {
                VerBitacoras ver = new VerBitacoras(caso);
                desktopPane.add(ver);
                ver.setVisible(true);
            }
        };

        VER_OBSERVACIONES = id -> {
            if (!VerBitacoras.isOpen) {
                VerObservaciones ver = new VerObservaciones(id);
                desktopPane.add(ver);
                ver.setVisible(true);
            }
        };
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuUsuario = new javax.swing.JMenu();
        jMenuItemCerrarSesion = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu3.setText("Gestión");

        jMenuItem1.setText("Casos Asignados");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuItem2.setText("Caso actual");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        menuBar.add(jMenu3);

        jMenuUsuario.setText("usuario");

        jMenuItemCerrarSesion.setText("Cerrar sesión");
        jMenuItemCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCerrarSesionActionPerformed(evt);
            }
        });
        jMenuUsuario.add(jMenuItemCerrarSesion);

        menuBar.add(jMenuUsuario);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCerrarSesionActionPerformed
        // TODO add your handling code here:
        LoginView login = new LoginView();
        login.setVisible(true);
        gestordeproyectos.view.MonitorearCasos.isOpen = false;
        gestordeproyectos.view.VerBitacoras.isOpen = false;
        gestordeproyectos.view.VerObservaciones.isOpen = false;
        gestordeproyectos.view.CasoActual.isOpen = false;
        gestordeproyectos.view.empleado.RechazarCaso.isOpen = false;

        this.dispose();
    }//GEN-LAST:event_jMenuItemCerrarSesionActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        if (!MonitorearCasos.isOpen) {
            MonitorearCasos verCasos = new MonitorearCasos(
                    PROGRAMADOR,
                    VER_OBSERVACIONES,
                    VER_BITACORA
            );
            desktopPane.add(verCasos);
            verCasos.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        if (PROGRAMADOR.getIdEstadoEmpleado() == EstadoEmpleadoDto.OCUPADO) {
            if (!CasoActual.isOpen) {
                CasoActual caso = new CasoActual(PROGRAMADOR, desktopPane);
                desktopPane.add(caso);
                caso.setVisible(true);
            }
        } else {
                JOptionPane.showMessageDialog(
                        rootPane,
                        "No tienes casos activos",
                        "AVISO",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MdiProgramador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MdiProgramador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MdiProgramador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MdiProgramador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MdiProgramador(new UsuarioDto("D0007", RolDto.PROGRAMADOR, 1, null)).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItemCerrarSesion;
    private javax.swing.JMenu jMenuUsuario;
    private javax.swing.JMenuBar menuBar;
    // End of variables declaration//GEN-END:variables

}
