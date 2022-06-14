package Client.GUI.Admin;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Arrays;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import Shared.Objednavky;
import Shared.data.Produkt;

public class editaceProduktuFrame extends javax.swing.JFrame {
String[] objednavkaVypis = new String[0];
        /**
         * Creates new form editaceProduktuFrame
         */
        public editaceProduktuFrame() {
                try {
                        initComponents();
                } catch (RemoteException | MalformedURLException | NotBoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
        }

        /**
         * This method is called from within the constructor to initialize the form.
         * WARNING: Do NOT modify this code. The content of this method is always
         * regenerated by the Form Editor.
         * @throws RemoteException
         * @throws NotBoundException
         * @throws MalformedURLException
         */
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">
        private void initComponents() throws RemoteException, MalformedURLException, NotBoundException {

                editaceProduktuLabel = new javax.swing.JLabel();
                pridatButton = new javax.swing.JButton();
                editaceButton = new javax.swing.JButton();
                jScrollPane1 = new javax.swing.JScrollPane();
                produktyList = new javax.swing.JList<>();
                zpetButton = new javax.swing.JButton();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                editaceProduktuLabel.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
                editaceProduktuLabel.setText("Editace produktu");

                pridatButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
                pridatButton.setText("Přidat produkt");
                pridatButton.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                pridatButtonMouseClicked(evt);
                        }
                });

                editaceButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
                editaceButton.setText("Editace produktu");
                editaceButton.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                editaceButtonMouseClicked(evt);
                        }
                });

                jScrollPane1.setToolTipText("");

                produktyList.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
                jScrollPane1.setViewportView(produktyList);

                zpetButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
                zpetButton.setText("ZPĚT DO MENU");
                zpetButton.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                zpetButtonMouseClicked(evt);
                        }
                });

                Objednavky produkt = (Objednavky) Naming.lookup("rmi://jskola:12345/objednavka");

                int pomocna = 0;

                for (Produkt pro : produkt.getProdukty()) {
                        objednavkaVypis = Arrays.copyOf(objednavkaVypis, objednavkaVypis.length + 1);
                        objednavkaVypis[pomocna] = pro.getNazevKategorie() + " | " + pro.getNazevPolozky() + " - " + pro.getCena() + " Kč";
                        pomocna++;
                }

                pomocna = 0;

                produktyList.setModel(new javax.swing.AbstractListModel<String>() {
                        // String[] strings = { "Zatím nebyla vytvořena žádná objednávka!" };
                        public int getSize() {
                                return objednavkaVypis.length;
                        }

                        public String getElementAt(int i) {
                                return objednavkaVypis[i];
                        }
                });

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(editaceProduktuLabel,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, 323,
                                                                Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                .addComponent(editaceButton,
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                323, Short.MAX_VALUE)
                                                                .addComponent(pridatButton,
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE))
                                                .addComponent(zpetButton, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 483,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap()));
                layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addComponent(editaceProduktuLabel)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(pridatButton,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                35,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(editaceButton,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                35,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(zpetButton,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                39,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(8, 8, 8))
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 363,
                                                                Short.MAX_VALUE))
                                                .addContainerGap()));

                pack();
                System.gc();
        }// </editor-fold>

        private void pridatButtonMouseClicked(java.awt.event.MouseEvent evt) {
                pridaniProduktuFrame pridani = new pridaniProduktuFrame();
                pridani.setVisible(true);
                this.dispose();
        }

        private void editaceButtonMouseClicked(java.awt.event.MouseEvent evt) {
                zmenaProduktuFrame zmena = new zmenaProduktuFrame(produktyList.getSelectedIndex() + 1);
                zmena.setVisible(true);
                this.dispose();
        }

        private void zpetButtonMouseClicked(java.awt.event.MouseEvent evt) {
                adminFrame afr = new adminFrame();
                afr.setVisible(true);
                this.dispose();
        }

        // Variables declaration - do not modify
        private javax.swing.JButton editaceButton;
        private javax.swing.JLabel editaceProduktuLabel;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JButton pridatButton;
        private javax.swing.JList<String> produktyList;
        private javax.swing.JButton zpetButton;
        // End of variables declaration
}
