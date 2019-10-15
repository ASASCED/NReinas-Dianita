package otraprueba;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

public class OtraPrueba extends JFrame {

    JPanel jpanel = (JPanel) this.getContentPane();
    JLabel label[] = new JLabel[8];
    Border border = BorderFactory.createLineBorder(Color.black, 1);

    public static void main(String[] args) {
        OtraPrueba op = new OtraPrueba();
        op.setBounds(0, 0, 500, 500);
        System.out.println("Te quiero Diana :D");
        System.out.println("Y yo te quiero a ti <3");
        System.out.println("Gracias por todo");
        System.out.println("De nada Diana no hay de que comi siempre");
        op.setVisible(true);
        op.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public OtraPrueba() {
//        Inicializa el panel.
        for (int i = 0; i < label.length; i++) {
            label[i] = new JLabel();
            label[i].setBounds(25 + (50 * i), 25, 30, 30);
            label[i].setText("Q" + (i + 1));
            label[i].setBorder(border);
            label[i].setHorizontalAlignment(SwingConstants.CENTER);
            label[i].addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent evt) {
                    myDraggingMethod(evt);//reemplaza a los metodos j1 j2 .... j8MouseDragged
                }
            });
            jpanel.add(label[i], null);
        }
    }

    public void myDraggingMethod(MouseEvent evt) {
        if (evt.getSource() instanceof JLabel) {
            ((JLabel) evt.getSource()).setLocation(newPosition(evt)[0], newPosition(evt)[1]);
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
