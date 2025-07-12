package View;

import Crontroller.EstadoController;

public class GerenciarEstado extends javax.swing.JFrame {
    
    private EstadoController controller = new EstadoController();

    public GerenciarEstado() {
        initComponents();
        setLocationRelativeTo(null); //Posicionar a tela no senrtro do monitor
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelEstado = new javax.swing.JPanel();
        labelDescricao = new javax.swing.JLabel();
        txtDescricao = new javax.swing.JTextField();
        labelSigla = new javax.swing.JLabel();
        txtSigla = new javax.swing.JTextField();
        botaoCadastrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GERENCIAR ESTADO");
        setResizable(false);

        painelEstado.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Estado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        labelDescricao.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelDescricao.setText("DESCRIÇÃO:");

        txtDescricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescricaoActionPerformed(evt);
            }
        });

        labelSigla.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelSigla.setText("SIGLA:");

        botaoCadastrar.setFont(new java.awt.Font("Segoe UI Semibold", 3, 12)); // NOI18N
        botaoCadastrar.setText("CADASTRAR");
        botaoCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelEstadoLayout = new javax.swing.GroupLayout(painelEstado);
        painelEstado.setLayout(painelEstadoLayout);
        painelEstadoLayout.setHorizontalGroup(
            painelEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEstadoLayout.createSequentialGroup()
                .addGroup(painelEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelEstadoLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(painelEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelEstadoLayout.createSequentialGroup()
                                .addComponent(labelSigla, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtSigla, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(painelEstadoLayout.createSequentialGroup()
                                .addComponent(labelDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(painelEstadoLayout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addComponent(botaoCadastrar)))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        painelEstadoLayout.setVerticalGroup(
            painelEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEstadoLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(painelEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDescricao)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(painelEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSigla)
                    .addComponent(txtSigla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(75, 75, 75)
                .addComponent(botaoCadastrar)
                .addContainerGap(178, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(painelEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(painelEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDescricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescricaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescricaoActionPerformed

    private void botaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarActionPerformed
        
        controller.cadastrarEstrado(txtDescricao.getText(),txtSigla.getText());
        
        dispose();
        
        new GerenciarEstado();setVisible(true);
        
        
    }//GEN-LAST:event_botaoCadastrarActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GerenciarEstado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCadastrar;
    private javax.swing.JLabel labelDescricao;
    private javax.swing.JLabel labelSigla;
    private javax.swing.JPanel painelEstado;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtSigla;
    // End of variables declaration//GEN-END:variables
}
