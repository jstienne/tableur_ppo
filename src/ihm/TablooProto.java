package ihm;

/*
 * TablooProto.java requires no other files.
 * 
 */
import java.awt.Color;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

public class TablooProto extends JPanel {

    // Fourni: ne rien changer.
    public TablooProto() {
        super(new GridLayout(1, 0));

        // modele de donnees
        // cf. plus loin la inner classe MyTableModel a modifier...
        MyTableModel tableModel = new MyTableModel();

        // la JTable et ses parametres
        JTable table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(1000, 500));
        table.setGridColor(Color.BLACK);
        table.setShowGrid(true);

        // on ajoute un scroll
        JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // parametrage de la 1ere ligne = noms des colonnes
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        // parametrage de la 1ere colonne consacree a la numerotation des lignes
        TableColumn tm = table.getColumnModel().getColumn(0);
        tm.setPreferredWidth(tm.getPreferredWidth() * 2 / 3);
        tm.setCellRenderer(new PremiereColonneSpecificRenderer(Color.LIGHT_GRAY));

    }

    // Inner class pour changer l'aspect de la premiere colonne consacree a la numerotation des lignes
    // Fourni: ne rien changer.
    class PremiereColonneSpecificRenderer extends DefaultTableCellRenderer {

        Color couleur;

        public PremiereColonneSpecificRenderer(Color couleur) {
            super();
            this.couleur = couleur;
            this.setHorizontalAlignment(JLabel.CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            cell.setBackground(couleur);
            return cell;
        }
    }

    // Inner class pour etablir la connexion entre la JTable graphique et un modele de donnees.
    // Pour nous le modele de donnees sera une grille du noyau de representation et de calcul
    // construite et sauvegardee par serialisation comme precedemmment.
    // Dans ce prototype exemple, le modele de donnees est une simple matrice de String "en dur".
    // Il faudra le remplacer par une connexion a une telle grille.
    class MyTableModel extends AbstractTableModel {

        // TODO
        // remplacer ce tableau en dur du prototype par la grille serialisee:
        // noyau.Grille calc;
        String[][] calc;

        MyTableModel() {
            // TODO: remplacer cette initialisation par le chargement de la grille serialisee
            calc = new String[this.getRowCount()][this.getColumnCount()];
            for (int ligne =0; ligne < calc.length; ligne++)
                for (int colonne=0; colonne < calc[ligne].length; colonne++)
                    calc[ligne][colonne] = "";
        }

        @Override
        // Standard: doit retourner le nbre de colonnes de la JTable
        public int getColumnCount() {
            // TODO: remplacer par le nbre de colonnes de la grille
            // + 1 pour la colonne 0 consacrée aux numeros de ligne)
            return 10;
        }

        @Override
        // Standard: doit retourner le nbre de lignes de la JTable
        public int getRowCount() {
            // TODO: remplacer par le nbre de lignes de la grille
            return 10;
        }

        // Standard: doit renvoyer le nom de la colonne a afficher en tete
        // Fourni: ne rien changer.
        @Override
        public String getColumnName(int col) {
            if (col == 0) {
                return ""; // colonne consacrée aux numeros de ligne
            } else {
                return "" + (char) ((int) ('A') + col - 1);
            }
        }

        // Utilitaire interne fourni (ne rien changer)
        // Retourne le nom d'une case a partir de ses coordonnees dans la JTable.
        String getNomCase(int row, int col) {
            return this.getColumnName(col) + String.valueOf(row + 1); // row commence a 0
        }

        @Override
        // Standard: doit renvoyer le contenu a afficher de la case correspondante
        public Object getValueAt(int row, int col) {
            if (col == 0) {
                // Fourni: ne rien changer.
                // en colonne 0 : numeros de lignes
                return "" + String.valueOf(row + 1);
            } else {
                // TODO: remplacer par le contenu + la valeur
                // de la case de nom getNomCase(row, col)
                // dans la grille (comme dans la figure 1 du sujet).
                return "" + calc[row][col - 1];
            }
        }

        // Standard.
        // Fourni: ne rien changer.
        @Override
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        // Standard: determine si une case est editable ou non.
        // Fourni: ne rien changer.
        // Seules les cases de la 1er colonne ne le sont pas
        // (consacrees a la numerotation des lignes)
        @Override
        public boolean isCellEditable(int row, int col) {
            if (col < 1) {
                return false; // col 0 consacree a la numerotation des lignes (non editable)
            } else {
                return true;
            }
        }


        // Standard: l'utilisateur a entré une valeur dans une case,
        // mettre a jour le modèle de donnees connecte.
        // L'utilisateur a modifie une case.
        // Si c'est une valeur numerique (sinon ne rien faire)
        // - modifier la case correspondante dans la grille si cette case existe
        // - ajouter la case correspondante dans la grille
        @Override
        public void setValueAt(Object value, int row, int col) {

            // TODO remplacer par le code correspondant
            if (value instanceof String) {
                calc[row][col - 1] = (String) value;
            }
            // Ne pas modifier :
            // mise a jour automatique de l'affichage suite a la modification
            fireTableCellUpdated(row, col);
        }
    }
    // Fin de la inner class MyTableModel

    // Exécution de l'interface graphique a partir d'un terminal.
    // TODO: parametrer le tout par un fichier de grille serialisee.
    public static void main(String[] args) {
        // TODO: parametrer le tableur par un fichier de grille serialisee
        // a charger comme modele de donnees.
        TablooProto tableur = new TablooProto();

        // Creation de l'application et lancement
        // Fourni: ne rien changer.
        JFrame frame = new JFrame("TABLOO");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tableur.setOpaque(true);
        frame.setContentPane(tableur);
        frame.pack();
        frame.setVisible(true);

    }
}
