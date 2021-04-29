/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordeproyectos.view;

import gestordeproyectos.dao.BitacoraDao;
import gestordeproyectos.dao.CasoDao;
import gestordeproyectos.dao.EmpleadoDao;
import gestordeproyectos.dao.EstadoCasoDao;
import gestordeproyectos.dao.ObservacionCasoDao;
import gestordeproyectos.dto.BitacoraDto;
import gestordeproyectos.dto.CasoDto;
import gestordeproyectos.dto.EmpleadoDto;
import gestordeproyectos.dto.EstadoCasoDto;
import gestordeproyectos.dto.EstadoEmpleadoDto;
import gestordeproyectos.dto.ObservacionCasoDto;
import gestordeproyectos.dto.RolDto;
import gestordeproyectos.dto.SolicitudDto;
import gestordeproyectos.util.ConstantFormats;
import gestordeproyectos.view.empleado.AprobarCaso;
import gestordeproyectos.view.empleado.RechazarCaso;
import gestordeproyectos.view.programador.ReportarBitacora;
import java.text.DateFormat;
import java.util.List;
import javax.swing.JDesktopPane;

/**
 *
 * @author Irvin
 */
public class CasoActual extends javax.swing.JInternalFrame {

    /**
     * Creates new form VerSolicitudes
     */
    public static boolean isOpen = false;

    private final EmpleadoDto EMPLEADO_USUARIO;
    private final CasoDto CASO;
    private final JDesktopPane DESKTOP;
    

    /**
     *
     * @param solicitud
     *
     * CASO SELECCIONADO POR UN JEFE
     */
    public CasoActual(SolicitudDto solicitud) {
        initComponents();

        btnActualizar.setEnabled(false);
        btnAprobar.setEnabled(false);
        btnRechazar.setEnabled(false);
        isOpen = true;
        CASO = new CasoDao().read(solicitud.getIdSolicitud());
        if (CASO != null) {
            fillCasoPane();
            fillBitacoraPane();
            fillObservacion();
        }

        this.remove(btnAprobar);
        this.remove(btnRechazar);
        this.remove(btnActualizar);
        EMPLEADO_USUARIO = null;
        DESKTOP = null;
    }

    /**
     *
     * @param empleado
     * @param desktop
     *
     * CASO SELECCIONADO POR UN EMPLEADO O UN PROGRAMADOR
     */
    public CasoActual(EmpleadoDto empleado, JDesktopPane desktop) {
        initComponents();
        DESKTOP = desktop;
        isOpen = true;
        EMPLEADO_USUARIO = empleado;
        if (EMPLEADO_USUARIO.getIdRol() == RolDto.PROGRAMADOR) {
            this.remove(btnAprobar);
            this.remove(btnRechazar);
        }
        if (EMPLEADO_USUARIO.getIdRol() == RolDto.EMPLEADO) {
            this.remove(btnActualizar);
        }
        CasoDao casoDao = new CasoDao();
        CasoDto caso = null;
        //Se filtran las solicitudes que pertenecen al departamento actual
        for (CasoDto c : casoDao.list()) {
            if (c.getIdCaso().startsWith(EMPLEADO_USUARIO.getIdDepartamento())
                    && c.getIdEstadoCaso() != EstadoCasoDto.FINALIZADO) {

                if (EMPLEADO_USUARIO.getIdRol() == RolDto.EMPLEADO) {
                    if (c.getIdProbador() == EMPLEADO_USUARIO.getIdEmpleado()) {
                        caso = c;
                        break;
                    }
                }

                if (EMPLEADO_USUARIO.getIdRol() == RolDto.PROGRAMADOR) {
                    if (c.getIdProgramador() == EMPLEADO_USUARIO.getIdEmpleado()) {
                        caso = c;
                        break;
                    }
                }
            }
        }

        CASO = caso;
        if (CASO != null) {
            fillCasoPane();
            fillBitacoraPane();
            fillObservacion();
        } 
    }

    private void fillCasoPane() {
        EstadoCasoDto estado = new EstadoCasoDao().read(CASO.getIdEstadoCaso());
        EmpleadoDao empleadoDao = new EmpleadoDao();
        EmpleadoDto programador = empleadoDao.read(CASO.getIdProgramador());
        EmpleadoDto probador = empleadoDao.read(CASO.getIdProbador());
        DateFormat df = new ConstantFormats().getDateFormat();

        txtIdCaso.setText(CASO.getIdCaso());
        txtEstado.setText(estado.getNombreEstadoCaso());
        txtInicio.setText(df.format(CASO.getFechaInicio()));
        txtEntrega.setText(df.format(CASO.getFechaEntrega()));
        if (CASO.getFechaPuestaProduccion() != null) {
            txtProduccion.setText(df.format(CASO.getFechaPuestaProduccion()));
        }
        txtProgramador.setText(programador.getNombreEmpleado());
        txtProbador.setText(probador.getNombreEmpleado());
        txtRequerimento.setText(CASO.getRequerimento());

        switch (CASO.getIdEstadoCaso()) {
            case EstadoCasoDto.FINALIZADO: {
                btnActualizar.setEnabled(false);
                btnAprobar.setEnabled(false);
                btnRechazar.setEnabled(false);
                break;
            }
            case EstadoCasoDto.ESPERA: {
                btnActualizar.setEnabled(false);
                btnAprobar.setEnabled(true);
                btnRechazar.setEnabled(true);
                break;
            }
            default: {
                btnActualizar.setEnabled(true);
                btnAprobar.setEnabled(false);
                btnRechazar.setEnabled(false);
                break;
            }
        }
    }

    private void fillBitacoraPane() {
        DateFormat df = new ConstantFormats().getDateFormat();
        BitacoraDto bitacora;
        List<BitacoraDto> bitacoras = new BitacoraDao().list(CASO.getIdCaso());
        if (!bitacoras.isEmpty()) {
            int i = bitacoras.size();
            bitacora = bitacoras.get(i - 1);
            progressBar.setValue(bitacora.getPorcentaje());
            lblPercent.setText(bitacora.getPorcentaje() + "%");
            txtFecha.setText(df.format(bitacora.getFechaAvance()));
            txtDescripcion.setText(bitacora.getDescripcionAvance());
        }
    }

    private void fillObservacion() {
        DateFormat df = new ConstantFormats().getDateFormat();
        ObservacionCasoDto observacion;
        List<ObservacionCasoDto> observaciones = new ObservacionCasoDao().list(CASO.getIdCaso());
        if (!observaciones.isEmpty()) {
            int i = observaciones.size();
            observacion = observaciones.get(i - 1);
            txtFecha1.setText(df.format(observacion.getFechaObservacionCaso()));
            txtDescripcion1.setText(observacion.getDescripcion());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtIdCaso = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtEstado = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtProgramador = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtProbador = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtInicio = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtRequerimento = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        txtProduccion = new javax.swing.JTextField();
        txtEntrega = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        lblPercent = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtFecha1 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtDescripcion1 = new javax.swing.JTextArea();
        btnRechazar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnAprobar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Casos");
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

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Caso"));

        jLabel2.setText("Caso");

        txtIdCaso.setEditable(false);

        jLabel3.setText("Estado");

        txtEstado.setEditable(false);

        jLabel4.setText("Programador");

        txtProgramador.setEditable(false);

        jLabel5.setText("Probador");

        txtProbador.setEditable(false);

        jLabel6.setText("Inicio");

        txtInicio.setEditable(false);

        jLabel8.setText("Requerimentos");

        txtRequerimento.setEditable(false);
        txtRequerimento.setColumns(20);
        txtRequerimento.setRows(5);
        jScrollPane1.setViewportView(txtRequerimento);

        jLabel7.setText("Puesta en producci[on");

        txtProduccion.setEditable(false);

        txtEntrega.setEditable(false);
        txtEntrega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEntregaActionPerformed(evt);
            }
        });

        jLabel10.setText("Entrega");

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
                                    .addComponent(jLabel2)
                                    .addComponent(txtIdCaso, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(txtEstado)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtProgramador, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(txtProbador, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel8))
                                .addGap(0, 19, Short.MAX_VALUE)))
                        .addGap(0, 19, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(txtInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(txtProduccion, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtProbador, txtProgramador});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtEntrega, txtInicio, txtProduccion});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdCaso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtProduccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProgramador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProbador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtEntrega, txtInicio, txtProduccion});

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Bitácora"));

        jLabel1.setText("Fecha");

        txtFecha.setEditable(false);

        jLabel9.setText("Avance");

        jLabel12.setText("Descripción");

        txtDescripcion.setEditable(false);
        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane2.setViewportView(txtDescripcion);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPercent))
                            .addComponent(jLabel12))
                        .addContainerGap(44, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(lblPercent))
                .addGap(8, 8, 8)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Observaciones"));

        jLabel13.setText("Fecha");

        txtFecha1.setEditable(false);

        jLabel14.setText("Descripción");

        txtDescripcion1.setEditable(false);
        txtDescripcion1.setColumns(20);
        txtDescripcion1.setRows(5);
        jScrollPane4.setViewportView(txtDescripcion1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(txtFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel14))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnRechazar.setText("Rechazar");
        btnRechazar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRechazarActionPerformed(evt);
            }
        });

        btnActualizar.setText("Actualizar bitácora");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnAprobar.setText("Aprobar");
        btnAprobar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAprobarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRechazar)
                .addGap(18, 18, 18)
                .addComponent(btnActualizar)
                .addGap(18, 18, 18)
                .addComponent(btnAprobar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnActualizar, btnAprobar, btnRechazar});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnRechazar)
                    .addComponent(btnActualizar)
                    .addComponent(btnAprobar))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jPanel1, jPanel4});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnActualizar, btnAprobar, btnRechazar});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        // TODO add your handling code here:
        isOpen = false;
    }//GEN-LAST:event_formInternalFrameClosed

    private void btnAprobarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAprobarActionPerformed
        // TODO add your handling code here:
        if (!AprobarCaso.isOpen) {
            AprobarCaso aprobar = new AprobarCaso(CASO, () -> {
                fillCasoPane();
                EMPLEADO_USUARIO.setIdEstadoEmpleado(EstadoEmpleadoDto.DISPONIBLE);
            });
            DESKTOP.add(aprobar);
            aprobar.setVisible(true);
        }
    }//GEN-LAST:event_btnAprobarActionPerformed

    private void btnRechazarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRechazarActionPerformed
        // TODO add your handling code here:
        if (!RechazarCaso.isOpen) {
            RechazarCaso rechazar = new RechazarCaso(CASO, () -> {
                fillCasoPane();
                fillObservacion();
            });
            DESKTOP.add(rechazar);
            rechazar.setVisible(true);
        }
    }//GEN-LAST:event_btnRechazarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        if (!ReportarBitacora.isOpen) {
            ReportarBitacora reportar = new ReportarBitacora(CASO, () -> {
                fillCasoPane();
                fillBitacoraPane();
            });
            DESKTOP.add(reportar);
            reportar.setVisible(true);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void txtEntregaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEntregaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEntregaActionPerformed
// </editor-fold>   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAprobar;
    private javax.swing.JButton btnRechazar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblPercent;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextArea txtDescripcion1;
    private javax.swing.JTextField txtEntrega;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtFecha1;
    private javax.swing.JTextField txtIdCaso;
    private javax.swing.JTextField txtInicio;
    private javax.swing.JTextField txtProbador;
    private javax.swing.JTextField txtProduccion;
    private javax.swing.JTextField txtProgramador;
    private javax.swing.JTextArea txtRequerimento;
    // End of variables declaration//GEN-END:variables
}
