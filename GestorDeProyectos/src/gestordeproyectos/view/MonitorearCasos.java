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
import gestordeproyectos.dto.CasoDto;
import gestordeproyectos.dto.EmpleadoDto;
import gestordeproyectos.dto.EstadoCasoDto;
import gestordeproyectos.dto.RolDto;
import gestordeproyectos.util.ConstantFormats;
import java.text.DateFormat;
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
public class MonitorearCasos extends javax.swing.JInternalFrame {

    /**
     * Creates new form VerSolicitudes
     */
    public static boolean isOpen = false;

    private final EmpleadoDto EMPLEADO_USUARIO;
    private final List<EmpleadoDto> EMPLEADOS;
    private final CasoDao CASO_DAO;
    private final List<CasoDto> CASOS;
    private final HashMap<Integer, String> ESTADOS_CASO;
    private final List<String> CASO_CON_BITACORA;
    private final List<String> CASO_CON_OBSERVACION;
    private final String[] COLUMNAS;
    private final int TODO = 0;
    private final Consumer<CasoDto> VER_OBSERVACIONES;
    private final Consumer<CasoDto> VER_BITACORA;
    private int filtroEstado;
    private Object[][] datos;
    private CasoDto caso;

    public MonitorearCasos(EmpleadoDto empleado,
            Consumer<CasoDto> observacionesFunc,
            Consumer<CasoDto> bitacoraFunc) {
        initComponents();
        isOpen = true;
        this.VER_OBSERVACIONES = observacionesFunc;
        this.VER_BITACORA = bitacoraFunc;
        filtroEstado = TODO;
        EMPLEADO_USUARIO = empleado;
        EMPLEADOS = new EmpleadoDao().list();
        BitacoraDao bDao = new BitacoraDao();
        ObservacionCasoDao oDao = new ObservacionCasoDao();
        CASO_CON_OBSERVACION = new LinkedList<>();
        CASO_CON_BITACORA = new LinkedList<>();
        CASO_DAO = new CasoDao();
        CASOS = new ArrayList<>();
        //Se filtran las solicitudes que pertenecen al departamento actual
        CASO_DAO.list().forEach(c -> {
            if (c.getIdCaso().startsWith(EMPLEADO_USUARIO.getIdDepartamento())) {
                CASOS.add(c);
            }
        });

        //x -> return boolean;
        if (EMPLEADO_USUARIO.getIdRol() == RolDto.EMPLEADO) {
            CASOS.removeIf(
                    c -> c.getIdProbador() != EMPLEADO_USUARIO.getIdEmpleado()
            );
        }

        if (EMPLEADO_USUARIO.getIdRol() == RolDto.PROGRAMADOR) {
            CASOS.removeIf(
                    c -> c.getIdProgramador() != EMPLEADO_USUARIO.getIdEmpleado()
            );
        }

        CASOS.forEach(
                c -> {
                    if (bDao.list(c.getIdCaso()).size() > 0) {
                        CASO_CON_BITACORA.add(c.getIdCaso());
                    }
                    
                    if (oDao.list(c.getIdCaso()).size() > 0) {
                        CASO_CON_OBSERVACION.add(c.getIdCaso());
                    }
                }
        );

        //Se obtienen los estados de caso y se ingresan a un HashMap
        ESTADOS_CASO = new HashMap<>();
        new EstadoCasoDao().list().forEach(es -> ESTADOS_CASO.put(
                es.getIdEstadoCaso(), es.getNombreEstadoCaso())
        );
        String[] cols = {"N° de Solicitud", "Caso", "Programador",
            "Probador", "Estado", "fecha de entrega",
            "Puesta en producción", "Requerimentos"};
        COLUMNAS = cols;
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        radTodo = new javax.swing.JRadioButton();
        radDesarrollo = new javax.swing.JRadioButton();
        radDevuelto = new javax.swing.JRadioButton();
        radFinalizado = new javax.swing.JRadioButton();
        radVencido = new javax.swing.JRadioButton();
        radEspera = new javax.swing.JRadioButton();
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
        txtEntrega = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtProduccion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtRequerimento = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnObservaciones = new javax.swing.JButton();
        btnBitacora = new javax.swing.JButton();

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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtro"));

        jLabel1.setText("Estado");

        radGroupEstado.add(radTodo);
        radTodo.setSelected(true);
        radTodo.setText("Todo");
        radTodo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        radTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radTodoActionPerformed(evt);
            }
        });

        radGroupEstado.add(radDesarrollo);
        radDesarrollo.setText("Desarrollo");
        radDesarrollo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        radDesarrollo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radDesarrolloActionPerformed(evt);
            }
        });

        radGroupEstado.add(radDevuelto);
        radDevuelto.setText("Devuelto");
        radDevuelto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        radDevuelto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radDevueltoActionPerformed(evt);
            }
        });

        radGroupEstado.add(radFinalizado);
        radFinalizado.setText("Finalizado");
        radFinalizado.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        radFinalizado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radFinalizadoActionPerformed(evt);
            }
        });

        radGroupEstado.add(radVencido);
        radVencido.setText("Vencido");
        radVencido.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        radVencido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radVencidoActionPerformed(evt);
            }
        });

        radGroupEstado.add(radEspera);
        radEspera.setText("Espera");
        radEspera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radEsperaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(radTodo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(radEspera))
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(radDesarrollo)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(radFinalizado))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(radDevuelto)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(radVencido)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radTodo)
                    .addComponent(radEspera))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radDesarrollo)
                    .addComponent(radFinalizado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radDevuelto)
                    .addComponent(radVencido))
                .addGap(22, 22, 22))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Caso"));

        jLabel2.setText("Caso");

        txtIdCaso.setEditable(false);

        jLabel3.setText("Estado");

        txtEstado.setEditable(false);

        jLabel4.setText("Programador");

        txtProgramador.setEditable(false);

        jLabel5.setText("Probador");

        txtProbador.setEditable(false);

        jLabel6.setText("Entrega");

        txtEntrega.setEditable(false);

        jLabel7.setText("Puesta en producción");

        txtProduccion.setEditable(false);

        jLabel8.setText("Requerimentos");

        txtRequerimento.setEditable(false);
        txtRequerimento.setColumns(20);
        txtRequerimento.setRows(5);
        jScrollPane1.setViewportView(txtRequerimento);

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
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtProgramador, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(txtProbador, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel8)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(txtIdCaso, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(txtEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(txtProduccion, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtProbador, txtProgramador});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtEntrega, txtProduccion});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdCaso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtProduccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProgramador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProbador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
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

        btnObservaciones.setText("Observaciones");
        btnObservaciones.setEnabled(false);
        btnObservaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObservacionesActionPerformed(evt);
            }
        });

        btnBitacora.setText("Bitácora");
        btnBitacora.setEnabled(false);
        btnBitacora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBitacoraActionPerformed(evt);
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
                        .addComponent(btnObservaciones)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBitacora))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnBitacora, btnObservaciones});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(btnBitacora)
                            .addComponent(btnObservaciones)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int fila = jTable1.rowAtPoint(evt.getPoint());

        if (fila > -1) {
            //Se obtiene la solicitud mediante su id
            caso = CASO_DAO.read((String) datos[fila][1]);
            //se llenan los campos de lectura
            txtIdCaso.setText((String) datos[fila][1]);
            txtProgramador.setText((String) datos[fila][2]);
            txtProbador.setText((String) datos[fila][3]);
            txtEstado.setText((String) datos[fila][4]);
            txtEntrega.setText((String) datos[fila][5]);
            txtProduccion.setText((String) datos[fila][6]);
            txtRequerimento.setText((String) datos[fila][7]);
        }
        if (caso != null) {
            //Se activan los botones dependiendo del estado de solicitud
            btnBitacora.setEnabled(CASO_CON_BITACORA.contains(caso.getIdCaso()));
            btnObservaciones.setEnabled(CASO_CON_OBSERVACION.contains(caso.getIdCaso()));
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        // TODO add your handling code here:
        isOpen = false;
    }//GEN-LAST:event_formInternalFrameClosed

    private void btnObservacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObservacionesActionPerformed
        // TODO add your handling code here:
        //Ejecuta el consumer con el id de la solicitud ejecutada
        VER_OBSERVACIONES.accept(caso);
    }//GEN-LAST:event_btnObservacionesActionPerformed

    // <editor-fold defaultstate="collapsed" desc="RadioButton action">           
    private void radTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radTodoActionPerformed
        // TODO add your handling code here:
        filtroEstado = TODO;
        fillTable();
    }//GEN-LAST:event_radTodoActionPerformed

    private void radDesarrolloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radDesarrolloActionPerformed
        // TODO add your handling code here:
        filtroEstado = EstadoCasoDto.DESARROLLO;
        fillTable();
    }//GEN-LAST:event_radDesarrolloActionPerformed

    private void radDevueltoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radDevueltoActionPerformed
        // TODO add your handling code here:
        filtroEstado = EstadoCasoDto.DEVUELTO;
        fillTable();
    }//GEN-LAST:event_radDevueltoActionPerformed

    private void radEsperaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radEsperaActionPerformed
        // TODO add your handling code here:
        filtroEstado = EstadoCasoDto.ESPERA;
        fillTable();
    }//GEN-LAST:event_radEsperaActionPerformed

    private void radFinalizadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radFinalizadoActionPerformed
        // TODO add your handling code here:
        filtroEstado = EstadoCasoDto.FINALIZADO;
        fillTable();
    }//GEN-LAST:event_radFinalizadoActionPerformed

    private void radVencidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radVencidoActionPerformed
        // TODO add your handling code here:
        filtroEstado = EstadoCasoDto.VENCIDO;
        fillTable();
    }//GEN-LAST:event_radVencidoActionPerformed

    private void btnBitacoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBitacoraActionPerformed
        // TODO add your handling code here:
        VER_BITACORA.accept(caso);
    }//GEN-LAST:event_btnBitacoraActionPerformed
// </editor-fold>   

    private void fillTable() {
        DateFormat df = new ConstantFormats().getDateFormat();
        int i = 0;
        List<CasoDto> filteredList = new LinkedList<>();
        //si está seleccionado todo simplemente se agregan todas las solicitudes
        if (filtroEstado == TODO) {
            filteredList.addAll(CASOS);
        } else {
            //se agregan todas las solicitudes que coinciden con el filtro
            CASOS.forEach(s -> {
                if (s.getIdEstadoCaso() == filtroEstado) {
                    filteredList.add(s);
                }
            });
        }
        datos = new Object[filteredList.size()][8];
        //se llenan los datos
        for (CasoDto c : filteredList) {
            datos[i][0] = c.getIdSolicitud();
            datos[i][1] = c.getIdCaso();
            for (EmpleadoDto e : EMPLEADOS) {

                if (c.getIdProgramador() == e.getIdEmpleado()) {
                    datos[i][2] = e.getNombreEmpleado();
                }

                if (c.getIdProbador() == e.getIdEmpleado()) {
                    datos[i][3] = e.getNombreEmpleado();
                }

                if (datos[i][2] != null && datos[i][3] != null) {
                    break;
                }

            }
            datos[i][4] = ESTADOS_CASO.get(c.getIdEstadoCaso());
            datos[i][5] = df.format(c.getFechaEntrega());
            datos[i][6] = c.getFechaPuestaProduccion() == null
                    ? ""
                    : df.format(c.getFechaPuestaProduccion());
            datos[i][7] = c.getRequerimento();
            i++;
        }
        //se llena la tabla
        DefaultTableModel dtm = new DefaultTableModel(datos, COLUMNAS);
        jTable1.setModel(dtm);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBitacora;
    private javax.swing.JButton btnObservaciones;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JRadioButton radDesarrollo;
    private javax.swing.JRadioButton radDevuelto;
    private javax.swing.JRadioButton radEspera;
    private javax.swing.JRadioButton radFinalizado;
    private javax.swing.ButtonGroup radGroupEstado;
    private javax.swing.JRadioButton radTodo;
    private javax.swing.JRadioButton radVencido;
    private javax.swing.JTextField txtEntrega;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtIdCaso;
    private javax.swing.JTextField txtProbador;
    private javax.swing.JTextField txtProduccion;
    private javax.swing.JTextField txtProgramador;
    private javax.swing.JTextArea txtRequerimento;
    // End of variables declaration//GEN-END:variables
}
