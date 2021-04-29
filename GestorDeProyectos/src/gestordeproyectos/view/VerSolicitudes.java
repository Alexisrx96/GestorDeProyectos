/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordeproyectos.view;

import gestordeproyectos.dao.EstadoSolicitudDao;
import gestordeproyectos.dao.SolicitudDao;
import gestordeproyectos.dao.TipoSolicitudDao;
import gestordeproyectos.dto.EmpleadoDto;
import gestordeproyectos.dto.EstadoSolicitudDto;
import gestordeproyectos.dto.RolDto;
import gestordeproyectos.dto.SolicitudDto;
import gestordeproyectos.dto.TipoSolicitudDto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Irvin
 */
public class VerSolicitudes extends javax.swing.JInternalFrame {

    /**
     * Creates new form VerSolicitudes
     */
    public static boolean isOpen = false;

    private final EmpleadoDto EMPLEADO_USUARIO;
    private final SolicitudDao SOLICITUD_DAO;
    private final List<SolicitudDto> SOLICITUDES;
    private final HashMap<Integer, String> TIPOS_SOLICITUD;
    private final HashMap<Integer, String> ESTADOS_SOLICITUD;
    private final String[] COLUMNAS;
    private final int FILTRO_TODO = 0;
    private Consumer<SolicitudDto> rechazoFunc;
    private Consumer<SolicitudDto> casoFunc;
    private int filtroEstado;
    private int filtroTipo;
    private Object[][] datos;
    private SolicitudDto solicitud;

    public VerSolicitudes(EmpleadoDto empleado,
            Consumer<SolicitudDto> rechazoFunc,
            Consumer<SolicitudDto> casoFunc) {

        initComponents();
        isOpen = true;
        this.rechazoFunc = rechazoFunc;
        this.casoFunc = casoFunc;
        filtroEstado = FILTRO_TODO;
        filtroTipo = FILTRO_TODO;
        EMPLEADO_USUARIO = empleado;
        SOLICITUD_DAO = new SolicitudDao();
        SOLICITUDES = new ArrayList<>();

        SOLICITUD_DAO.list().forEach(s -> {
            if (s.getIdDepartamento().equals(EMPLEADO_USUARIO.getIdDepartamento())) {
                SOLICITUDES.add(s);
            }
        });

        TIPOS_SOLICITUD = new HashMap<>();
        new TipoSolicitudDao().list().forEach(ts -> TIPOS_SOLICITUD.put(
                ts.getIdTipoSolicitud(), ts.getNombreTipoSolicitud())
        );
        ESTADOS_SOLICITUD = new HashMap<>();
        new EstadoSolicitudDao().list().forEach(es -> ESTADOS_SOLICITUD.put(
                es.getIdEstadoSolicitud(), es.getNombreEstadoSolicitud())
        );

        String[] cols = {"Id", "Estado", "Tipo", "Descripción"};
        COLUMNAS = cols;

        String textoBtnRechazo = "";
        String textoBtnCaso = "";
        switch (EMPLEADO_USUARIO.getIdRol()) {
            case RolDto.JEFE_DESARROLLO: {
                textoBtnRechazo = "Rechazar";
                textoBtnCaso = "Aprobar";
                break;
            }
            case RolDto.JEFE_FUNCIONAL: {
                textoBtnRechazo = "Razón de rechazo";
                textoBtnCaso = "Ver caso";
                break;
            }
        }

        btnRechazo.setText(textoBtnRechazo);
        btnCaso.setText(textoBtnCaso);
        fillTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        radGroupEstado = new javax.swing.ButtonGroup();
        radGroupTipo = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        radEstadoTodo = new javax.swing.JRadioButton();
        radEstadoEspera = new javax.swing.JRadioButton();
        radEstadoAceptado = new javax.swing.JRadioButton();
        radEstadoRechazado = new javax.swing.JRadioButton();
        radTipoTodo = new javax.swing.JRadioButton();
        radTipoSistema = new javax.swing.JRadioButton();
        radTipoFuncionalidad = new javax.swing.JRadioButton();
        radTipoCorreccion = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtEstado = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTipo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnRechazo = new javax.swing.JButton();
        btnCaso = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Solicitudes");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtro"));

        radGroupEstado.add(radEstadoTodo);
        radEstadoTodo.setSelected(true);
        radEstadoTodo.setText("Todo");
        radEstadoTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radEstadoTodoActionPerformed(evt);
            }
        });

        radGroupEstado.add(radEstadoEspera);
        radEstadoEspera.setText("En Espera");
        radEstadoEspera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radEstadoEsperaActionPerformed(evt);
            }
        });

        radGroupEstado.add(radEstadoAceptado);
        radEstadoAceptado.setText("Aprobado");
        radEstadoAceptado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radEstadoAceptadoActionPerformed(evt);
            }
        });

        radGroupEstado.add(radEstadoRechazado);
        radEstadoRechazado.setText("Rechazado");
        radEstadoRechazado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radEstadoRechazadoActionPerformed(evt);
            }
        });

        radGroupTipo.add(radTipoTodo);
        radTipoTodo.setSelected(true);
        radTipoTodo.setText("Todo");
        radTipoTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radTipoTodoActionPerformed(evt);
            }
        });

        radGroupTipo.add(radTipoSistema);
        radTipoSistema.setText("Nuevo Sistema");
        radTipoSistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radTipoSistemaActionPerformed(evt);
            }
        });

        radGroupTipo.add(radTipoFuncionalidad);
        radTipoFuncionalidad.setText("Nueva funcionalidad");
        radTipoFuncionalidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radTipoFuncionalidadActionPerformed(evt);
            }
        });

        radGroupTipo.add(radTipoCorreccion);
        radTipoCorreccion.setText("Corrección");
        radTipoCorreccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radTipoCorreccionActionPerformed(evt);
            }
        });

        jLabel1.setText("Estado");

        jLabel2.setText("Tipo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radEstadoTodo)
                    .addComponent(radEstadoEspera)
                    .addComponent(radEstadoAceptado)
                    .addComponent(radEstadoRechazado)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(radTipoTodo)
                    .addComponent(radTipoSistema)
                    .addComponent(radTipoFuncionalidad)
                    .addComponent(radTipoCorreccion))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radEstadoTodo)
                    .addComponent(radTipoTodo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radEstadoEspera)
                    .addComponent(radTipoSistema))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radEstadoAceptado)
                    .addComponent(radTipoFuncionalidad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radEstadoRechazado)
                    .addComponent(radTipoCorreccion))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Solicitud"));

        jLabel3.setText("Estado");

        txtEstado.setEditable(false);

        jLabel4.setText("Tipo");

        txtTipo.setEditable(false);

        jLabel5.setText("Descripción");

        txtDescripcion.setEditable(false);
        txtDescripcion.setColumns(20);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setRows(5);
        txtDescripcion.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtDescripcion);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel5))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        btnRechazo.setText("btnRechazo");
        btnRechazo.setEnabled(false);
        btnRechazo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRechazoActionPerformed(evt);
            }
        });

        btnCaso.setText("btnCaso");
        btnCaso.setEnabled(false);
        btnCaso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCasoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRechazo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCaso))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCaso, btnRechazo});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(btnCaso)
                            .addComponent(btnRechazo)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void radEstadoEsperaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radEstadoEsperaActionPerformed
        // TODO add your handling code here:
        filtroEstado = EstadoSolicitudDto.EN_ESPERA;
        fillTable();
    }//GEN-LAST:event_radEstadoEsperaActionPerformed

    private void radEstadoAceptadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radEstadoAceptadoActionPerformed
        // TODO add your handling code here:
        filtroEstado = EstadoSolicitudDto.APROBADA;
        fillTable();
    }//GEN-LAST:event_radEstadoAceptadoActionPerformed

    private void radEstadoRechazadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radEstadoRechazadoActionPerformed
        // TODO add your handling code here:
        filtroEstado = EstadoSolicitudDto.RECHAZADA;
        fillTable();
    }//GEN-LAST:event_radEstadoRechazadoActionPerformed

    private void radTipoTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radTipoTodoActionPerformed
        // TODO add your handling code here:
        filtroTipo = FILTRO_TODO;
        fillTable();
    }//GEN-LAST:event_radTipoTodoActionPerformed

    private void radTipoSistemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radTipoSistemaActionPerformed
        // TODO add your handling code here:
        filtroTipo = TipoSolicitudDto.NUEVO_SISTEMA;
        fillTable();
    }//GEN-LAST:event_radTipoSistemaActionPerformed

    private void radTipoFuncionalidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radTipoFuncionalidadActionPerformed
        // TODO add your handling code here:
        filtroTipo = TipoSolicitudDto.NUEVA_FUNCIONALIDAD;
        fillTable();
    }//GEN-LAST:event_radTipoFuncionalidadActionPerformed

    private void radTipoCorreccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radTipoCorreccionActionPerformed
        // TODO add your handling code here:
        filtroTipo = TipoSolicitudDto.CORRECCION;
        fillTable();
    }//GEN-LAST:event_radTipoCorreccionActionPerformed

    private void radEstadoTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radEstadoTodoActionPerformed
        // TODO add your handling code here:
        filtroEstado = FILTRO_TODO;
        fillTable();
    }//GEN-LAST:event_radEstadoTodoActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int fila = jTable1.rowAtPoint(evt.getPoint());

        if (fila > -1) {
            solicitud = SOLICITUD_DAO.read((int) datos[fila][0]);
            txtEstado.setText((String) datos[fila][1]);
            txtTipo.setText((String) datos[fila][2]);
            txtDescripcion.setText((String) datos[fila][3]);
        }
        if (solicitud != null) {

            if (EMPLEADO_USUARIO.getIdRol() == RolDto.JEFE_FUNCIONAL) {
                btnCaso.setEnabled(solicitud.getIdEstadoSolicitud()
                        == EstadoSolicitudDto.APROBADA);
                btnRechazo.setEnabled(solicitud.getIdEstadoSolicitud()
                        == EstadoSolicitudDto.RECHAZADA);
            }

            if (EMPLEADO_USUARIO.getIdRol() == RolDto.JEFE_DESARROLLO) {
                boolean estaEnEspera = solicitud.getIdEstadoSolicitud()
                        == EstadoSolicitudDto.EN_ESPERA;
                
                btnCaso.setEnabled(estaEnEspera);
                btnRechazo.setEnabled(estaEnEspera);
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        // TODO add your handling code here:
        isOpen = false;
    }//GEN-LAST:event_formInternalFrameClosed

    private void btnRechazoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRechazoActionPerformed
        // TODO add your handling code here:
        rechazoFunc.accept(solicitud);
    }//GEN-LAST:event_btnRechazoActionPerformed

    private void btnCasoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCasoActionPerformed
        // TODO add your handling code here:
        casoFunc.accept(solicitud);
    }//GEN-LAST:event_btnCasoActionPerformed

    private void fillTable() {
        int i = 0;

        List<SolicitudDto> filteredList = new LinkedList<>();
        if (filtroEstado == FILTRO_TODO) {
            filteredList.addAll(SOLICITUDES);
        } else {
            SOLICITUDES.forEach(s -> {
                if (s.getIdEstadoSolicitud() == filtroEstado) {
                    filteredList.add(s);
                }
            });
        }
        if (filtroTipo != FILTRO_TODO) {
            filteredList.removeIf(s -> s.getIdTipoSolicitud() != filtroTipo);
        }
        datos = new Object[filteredList.size()][COLUMNAS.length];
        for (SolicitudDto s : filteredList) {
            datos[i][0] = s.getIdSolicitud();
            datos[i][1] = ESTADOS_SOLICITUD.get(s.getIdEstadoSolicitud());
            datos[i][2] = TIPOS_SOLICITUD.get(s.getIdTipoSolicitud());
            datos[i][3] = s.getDescripcionSolicitud();
            i++;
        }

        DefaultTableModel dtm = new DefaultTableModel(datos, COLUMNAS);
        jTable1.setModel(dtm);

        solicitud = null;
        txtEstado.setText("");
        txtTipo.setText("");
        txtDescripcion.setText("");
        btnCaso.setEnabled(false);
        btnRechazo.setEnabled(false);
    }

    
    public Runnable getRunnableRefreshTable() {
        return () -> {
            SOLICITUDES.clear();
            SOLICITUD_DAO.list().forEach(s -> {
                if (s.getIdDepartamento().equals(EMPLEADO_USUARIO.getIdDepartamento())) {
                    SOLICITUDES.add(s);
                }
            });
            fillTable();
        };
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCaso;
    private javax.swing.JButton btnRechazo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JRadioButton radEstadoAceptado;
    private javax.swing.JRadioButton radEstadoEspera;
    private javax.swing.JRadioButton radEstadoRechazado;
    private javax.swing.JRadioButton radEstadoTodo;
    private javax.swing.ButtonGroup radGroupEstado;
    private javax.swing.ButtonGroup radGroupTipo;
    private javax.swing.JRadioButton radTipoCorreccion;
    private javax.swing.JRadioButton radTipoFuncionalidad;
    private javax.swing.JRadioButton radTipoSistema;
    private javax.swing.JRadioButton radTipoTodo;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtTipo;
    // End of variables declaration//GEN-END:variables
}
