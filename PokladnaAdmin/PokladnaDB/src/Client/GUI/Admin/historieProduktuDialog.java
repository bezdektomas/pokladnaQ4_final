package Client.GUI.Admin;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import Client.Tiskarna;
import Shared.Objednavky;
import Shared.list.ObjednavkySeznam;
import Shared.Uloziste;
import Shared.data.Objednavka;
import Shared.data.Produkt;

import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Arrays;

public class historieProduktuDialog extends JDialog {
    private JDialog frame;

    String[] objednavkaVypis = new String[0];

    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> objednavkaSelectBox;
    private javax.swing.JButton tiskZnovuButton;
    private javax.swing.JTextArea vypisObjednavky;
    private javax.swing.JButton zpetButton;

    public historieProduktuDialog(java.awt.Frame parent, boolean modal) {

        super(parent, modal);
        frame = this;
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setTitle("POKLADNA - HISTORIE OBJEDNÁVEK");
        try {
            initComponents();
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void initComponents() throws MalformedURLException, RemoteException, NotBoundException {

        jLabel1 = new javax.swing.JLabel();
        objednavkaSelectBox = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        vypisObjednavky = new javax.swing.JTextArea();
        tiskZnovuButton = new javax.swing.JButton();
        zpetButton = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("HISTORIE OBJEDNÁVEK");

        objednavkaSelectBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        objednavkaSelectBox
                .setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Načte se z databáze.. někdy" }));
        objednavkaSelectBox.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zmenaObjednavky(evt);
            }
        });

        Objednavky produkt = (Objednavky) Naming.lookup("rmi://jskola:12345/objednavka");
        int pomocna = 0;

        for (Objednavka pro : produkt.getObjednavkyCisla()) {
            objednavkaVypis = Arrays.copyOf(objednavkaVypis, objednavkaVypis.length + 1);
            objednavkaVypis[pomocna] = "Objednávka " + pro.getId();
            pomocna++;
        }

        tiskZnovuButton.setEnabled(false);

        objednavkaSelectBox.setModel(new DefaultComboBoxModel<>(objednavkaVypis));

        vypisObjednavky.setEditable(false);
        vypisObjednavky.setFont(new java.awt.Font("Consolas", 0, 18));
        jScrollPane1.setViewportView(vypisObjednavky);

        tiskZnovuButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tiskZnovuButton.setText("VYTISKNOUT ZNOVU");
        tiskZnovuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tiskZnovuButton(evt);
            }
        });

        tiskZnovuButton.setVisible(false);

        zpetButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        zpetButton.setText("ZPĚT DO HLAVNÍ NABÍDKY");
        zpetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zpetButton(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jLabel1,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup().addGap(41, 41, 41)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(objednavkaSelectBox, 0,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jScrollPane1)
                                                .addComponent(tiskZnovuButton, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(zpetButton, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(37, 37, 37)))
                        .addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addGap(19, 19, 19).addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(objednavkaSelectBox, javax.swing.GroupLayout.PREFERRED_SIZE, 49,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 349,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tiskZnovuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(zpetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        pack();
        try {
            zmena();
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void zmenaObjednavky(java.awt.event.ActionEvent evt) {
        try {
            zmena();
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void zmena() throws MalformedURLException, RemoteException, NotBoundException {


            String vybrane = objednavkaSelectBox.getSelectedItem().toString();
            int vyber = Integer.parseInt(vybrane.replaceAll("[^0-9]", ""));

            Objednavky produkt = (Objednavky) Naming.lookup("rmi://jskola:12345/objednavka");

            int celkovaCena = 0;


            String objednavkaVypis = "\n\nVýpis objednávky:\n\n";

            for (Produkt pro : produkt.getObejdnavkaId(vyber)) {
                celkovaCena += pro.getCena();
                objednavkaVypis += pro.getNazevPolozky() + " - " + pro.getCena() + " Kč\n";

            }

            objednavkaVypis += "\nCelková cena: " + celkovaCena + " Kč";
            vypisObjednavky.setText(objednavkaVypis);
        }


    private void tiskZnovuButton(java.awt.event.ActionEvent evt) {

        Tiskarna pr = Tiskarna.getTiskarna();
        pr.ucetenka(ObjednavkySeznam.ziskat(objednavkaSelectBox.getSelectedIndex()),
                objednavkaSelectBox.getSelectedIndex());

        JOptionPane.showMessageDialog(this,
                "Objednávka " + (objednavkaSelectBox.getSelectedIndex() + 1) + " úspěšně vytisknuta!", "Tisk",
                JOptionPane.INFORMATION_MESSAGE);

    }

    private void zpetButton(java.awt.event.ActionEvent evt) {
        this.dispose();
    }
}
