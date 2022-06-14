package Client.GUI.Admin;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JDialog;

import Client.startFrame;

public class adminFrame extends javax.swing.JFrame {

    /**
     * Creates new form adminFrame
     */
    public adminFrame() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setTitle("POKLADNA - ADMIN");
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        adminLabel = new javax.swing.JLabel();
        kategorieButton = new javax.swing.JButton();
        produktyButton = new javax.swing.JButton();
        zavritButton = new javax.swing.JButton();
        historieButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        adminLabel.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        adminLabel.setText("Administrace");

        kategorieButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        kategorieButton.setText("Editace kategorií");
        kategorieButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    kategorieButtonMouseClicked(evt);
                } catch (MalformedURLException | RemoteException | NotBoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        kategorieButton.setVisible(false);

        produktyButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        produktyButton.setText("Editace produktů");
        produktyButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                produktyButtonMouseClicked(evt);
            }
        });

        zavritButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        zavritButton.setText("ZAVŘÍT");
        zavritButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                zavritButtonMouseClicked(evt);
            }
        });

        historieButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        historieButton.setText("Historie objednávek");
        historieButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                historieButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                javax.swing.GroupLayout.Alignment.TRAILING,
                layout.createSequentialGroup().addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(adminLabel).addGap(162, 162, 162))
                .addGroup(layout.createSequentialGroup().addGroup(layout
                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup().addGap(18, 18, 18).addComponent(kategorieButton)
                                .addGap(35, 35, 35).addComponent(produktyButton).addGap(18, 18, 18)
                                .addComponent(historieButton))
                        .addGroup(layout.createSequentialGroup().addGap(203, 203, 203).addComponent(zavritButton,
                                javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addGap(38, 38, 38).addComponent(adminLabel).addGap(110, 110, 110)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(produktyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(kategorieButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(historieButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                        .addComponent(zavritButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap()));

        pack();
    }// </editor-fold>

    private void kategorieButtonMouseClicked(java.awt.event.MouseEvent evt) throws MalformedURLException, RemoteException, NotBoundException {

    }

    private void produktyButtonMouseClicked(java.awt.event.MouseEvent evt) {
        editaceProduktuFrame editace = new editaceProduktuFrame();
        editace.setVisible(true);
        this.dispose();
    }

    private void zavritButtonMouseClicked(java.awt.event.MouseEvent evt) {
        startFrame start = new startFrame();
        start.setVisible(true);
        this.dispose();
    }

    private void historieButtonMouseClicked(java.awt.event.MouseEvent evt) {
        historieProduktuDialog historie = new historieProduktuDialog(this, true);
        historie.setVisible(true);
    }

    // Variables declaration - do not modify
    private javax.swing.JLabel adminLabel;
    private javax.swing.JButton kategorieButton;
    private javax.swing.JButton produktyButton;
    private javax.swing.JButton zavritButton;
    private javax.swing.JButton historieButton;
    // End of variables declaration
}
