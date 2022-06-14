package Client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Client.GUI.Admin.adminFrame;
import Shared.Objednavky;
import Shared.data.Objednavka;
import Shared.list.ObjednavkySeznam;

public class startFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private javax.swing.JButton novaButton;
    private javax.swing.JButton adminButton;
    private javax.swing.JButton ukoncitButton;
    private javax.swing.JLabel jLabel1;

    public startFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("POKLADNA v2");
        initComponents();
    }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        novaButton = new javax.swing.JButton();
        adminButton = new javax.swing.JButton();
        ukoncitButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("POKLADNA V2");


        adminButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        adminButton.setText("ADMIN");

        adminButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminButtonActionPerformed(evt);
            }
        });

        ukoncitButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ukoncitButton.setText("UKONČIT");

        ukoncitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ukoncitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(adminButton, javax.swing.GroupLayout.PREFERRED_SIZE, 190,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80,
                                                Short.MAX_VALUE)
                                        .addComponent(ukoncitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 190,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(51, 51, 51)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addGap(27, 27, 27).addComponent(jLabel1).addGap(70, 70, 70)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(ukoncitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(adminButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54,
                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)));

        pack();
    }

    private void adminButtonActionPerformed(java.awt.event.ActionEvent evt) {
        adminFrame afr = new adminFrame();
        afr.setVisible(true);
        this.dispose();
    }

    private void ukoncitButtonActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }
}
