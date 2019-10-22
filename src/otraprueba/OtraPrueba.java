package otraprueba;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

public class OtraPrueba extends JFrame {

    static int reinas = 0;
    int count = 0;
    int anchoAlto = 50;
    int margen = 25;
    int espacio = 50;
    JPanel jpanel = (JPanel) this.getContentPane();
    JLabel ex = new JLabel();
    JLabel label[] = new JLabel[reinas];
    JLabel tablero[][] = new JLabel[reinas][reinas];
    Border border = BorderFactory.createLineBorder(Color.black, 1);

    public static void main(String[] args) {
        reinas = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad de reinas"));
        OtraPrueba op = new OtraPrueba();
        op.setBounds(0, 0, (60 * reinas), (60 * reinas));
        op.setVisible(true);
        op.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public OtraPrueba() {
        for (int i = 0; i < label.length; i++) {
            label[i] = new JLabel();
            // r + r + r + r
            label[i].setBounds(margen + (espacio * i), margen, anchoAlto, anchoAlto);
            label[i].setText("Q" + (i + 1));
            label[i].setForeground(Color.red);
            label[i].setBorder(border);
            label[i].setHorizontalAlignment(SwingConstants.CENTER);
            label[i].addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent evt) {
                    arrastreReina(evt);
                }
            });
            label[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent evt) {
                    movimientoReina(evt);
                }
            });
            jpanel.add(label[i]);
        }

        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                tablero[i][j] = new JLabel();
                tablero[i][j].setBounds(margen + (espacio * i), margen + (espacio * j), anchoAlto, anchoAlto);
                tablero[i][j].setBorder(border);

                if ((i % 2 == 0) == (j % 2 == 0)) {
                    tablero[i][j].setBackground(Color.white);
                } else {
                    tablero[i][j].setBackground(Color.black);
                }

                tablero[i][j].setOpaque(true);
                tablero[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                jpanel.add(tablero[i][j]);
            }
        }

        ex.setBounds(margen, margen, anchoAlto, anchoAlto);
        jpanel.add(ex);
    }

    public void arrastreReina(MouseEvent evt) {
        if (evt.getSource() instanceof JLabel) {
            ((JLabel) evt.getSource()).setLocation(posicion(evt)[0], posicion(evt)[1]);
        }
    }

    public void movimientoReina(MouseEvent evt) {
        if (evt.getSource() instanceof JLabel) {
            count++;
            System.out.println("Movimientos: " + count);
        }
    }

    public int[] posicion(MouseEvent evt) {
        int newX, newY;

        // Variables de entorno X
        String conX = Integer.toString(evt.getXOnScreen());
        String ultimoX = conX.substring(conX.length() - 2, conX.length());

        int primerosMenosUltimosX = Integer.parseInt(ultimoX);

        // Variables de entorno Y
        String conY = Integer.toString(evt.getYOnScreen());
        String ultimoY = conY.substring(conY.length() - 2, conY.length());

        int primerosMenosUltimosY = Integer.parseInt(ultimoY);

        if (primerosMenosUltimosX < 25) {
            newX = 25 + (evt.getXOnScreen() - 50 - primerosMenosUltimosX);
        } else if (primerosMenosUltimosX < 75) {
            newX = 75 + (evt.getXOnScreen() - 50 - primerosMenosUltimosX);
        } else {
            newX = 25 + ((evt.getXOnScreen() - 50 + 100) - primerosMenosUltimosX);
        }

        if (primerosMenosUltimosY < 25) {
            newY = 25 + (evt.getYOnScreen() - 50 - primerosMenosUltimosY);
        } else if (primerosMenosUltimosY < 75) {
            newY = 75 + (evt.getYOnScreen() - 50 - primerosMenosUltimosY);
        } else {
            newY = 25 + ((evt.getYOnScreen() - 50 + 100) - primerosMenosUltimosY);
        }

        int retorno[] = {newX, newY};

        return retorno;
    }

}
