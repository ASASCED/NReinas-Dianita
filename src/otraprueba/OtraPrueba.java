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
    int espaciado = 50;
    JPanel jpanel = (JPanel) this.getContentPane();
    JLabel ex = new JLabel();
    JLabel label[] = new JLabel[reinas];
    JLabel tablero[][] = new JLabel[reinas][reinas];
    Border border = BorderFactory.createLineBorder(Color.black, 1);

    public static void main(String[] args) {
        reinas = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad de Reinas: "));
        OtraPrueba op = new OtraPrueba();
        op.setBounds(0, 0, (60 * reinas), (60 * reinas));
        op.setVisible(true);
        op.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public OtraPrueba() {
        for (int i = 0; i < label.length; i++) {
            label[i] = new JLabel();
            label[i].setBounds(margen + (espaciado * i), margen, anchoAlto, anchoAlto);
            label[i].setText("Q" + (i + 1));
            label[i].setForeground(Color.red);
            label[i].setBorder(border);
            label[i].setHorizontalAlignment(SwingConstants.CENTER);
            label[i].addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent evt) {
                    myDraggingMethod(evt);//reemplaza a los metodos j1 j2 .... j8MouseDragged
                }
            });
            label[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent evt) {
                    myPressedMethod(evt);
                }
            });
            jpanel.add(label[i], null);
        }

        //        Inicializa el panel.
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                tablero[i][j] = new JLabel();
                tablero[i][j].setBounds(margen + (espaciado * j), margen + (espaciado * i), anchoAlto, anchoAlto);
                tablero[i][j].setBorder(border);
                tablero[i][j].setBackground(Color.red);

                if ((i % 2 == 0) == (j % 2 == 0)) {
                    tablero[i][j].setBackground(Color.white);
                } else {
                    tablero[i][j].setBackground(Color.black);
                }

                tablero[i][j].setOpaque(true);
                tablero[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                jpanel.add(tablero[i][j], null);
            }
        }

        // Creamos un pequeño label extra para que imprima el resto correctamente
        ex.setBounds(margen, margen, anchoAlto, anchoAlto);
        ex.setBorder(border);
        ex.setBackground(Color.red);
        jpanel.add(ex, null);
    }

    public void myDraggingMethod(MouseEvent evt) {
        if (evt.getSource() instanceof JLabel) {
            ((JLabel) evt.getSource()).setLocation(newPosition(evt)[0], newPosition(evt)[1]);
        }
    }

    public void myPressedMethod(MouseEvent evt) {
        if (evt.getSource() instanceof JLabel) {
            System.out.println("Movimientos: " + count);
            count++;
        }
    }

    public int[] newPosition(MouseEvent evt) {
        int newX, newY;
        if ((evt.getXOnScreen() - 50) <= 25) {
            newX = 25;
        } else if ((evt.getXOnScreen() - 50) <= 75) {
            newX = 75;
        } else if ((evt.getXOnScreen() - 50) <= 125) {
            newX = 125;
        } else if ((evt.getXOnScreen() - 50) <= 175) {
            newX = 175;
        } else if ((evt.getXOnScreen() - 50) <= 225) {
            newX = 225;
        } else if ((evt.getXOnScreen() - 50) <= 275) {
            newX = 275;
        } else if ((evt.getXOnScreen() - 50) <= 325) {
            newX = 325;
        } else if ((evt.getXOnScreen() - 50) <= 375) {
            newX = 375;
        } else {
            newX = 375;
        }

        if ((evt.getYOnScreen() - 50) <= 25) {
            newY = 25;
        } else if ((evt.getYOnScreen() - 50) <= 75) {
            newY = 75;
        } else if ((evt.getYOnScreen() - 50) <= 125) {
            newY = 125;
        } else if ((evt.getYOnScreen() - 50) <= 175) {
            newY = 175;
        } else if ((evt.getYOnScreen() - 50) <= 225) {
            newY = 225;
        } else if ((evt.getYOnScreen() - 50) <= 275) {
            newY = 275;
        } else if ((evt.getYOnScreen() - 50) <= 325) {
            newY = 325;
        } else if ((evt.getYOnScreen() - 50) <= 375) {
            newY = 375;
        } else {
            newY = 375;
        }

        int retorno[] = {newX, newY};

        return retorno;
    }

}
