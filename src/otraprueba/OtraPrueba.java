package otraprueba;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.BorderFactory;
import javax.swing.*;
import javax.swing.border.Border;

public class OtraPrueba extends JFrame implements ActionListener {

    static int reinas = 0;
    String[][] algoritmo = new String[reinas][reinas];
    String[][] comprobacion = new String[reinas][reinas];
    int count = 0;
    int anchoAlto = 50;
    int margen = 25;
    int espacio = 50;
    JPanel jpanel = (JPanel) this.getContentPane();
    JLabel ex = new JLabel();
    JLabel label[] = new JLabel[reinas];
    JLabel tablero[][] = new JLabel[reinas][reinas];
    Border border = BorderFactory.createLineBorder(Color.black, 1);

    String[] parts;
    int x, y, cooX, cooY, newI, newJ;

    JButton boton1;

    public static void main(String[] args) {
        reinas = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad de reinas"));
        OtraPrueba op = new OtraPrueba();
        op.setBounds(0, 0, (60 * reinas), (60 * reinas));
        op.setVisible(true);
        op.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public OtraPrueba() {
        // Declaracion de reinas
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
                public void mouseReleased(MouseEvent evt) {
                    arregloTablero(evt);
                }

                @Override
                public void mousePressed(MouseEvent evt) {
                    valoresIniciales(evt);
                }
            });
            jpanel.add(label[i]);

            for (int j = 0; j < algoritmo.length; j++) {
                if (i == 0) {
                    algoritmo[i][j] = Integer.toString((margen + (espacio * j))) + "|" + Integer.toString(margen);
                } else {
                    algoritmo[i][j] = "0|0";
                }
            }
        }

        // Declaracion del tablero
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

        // Declaracion del boton
        setLayout(null);
        boton1 = new JButton("Finalizar");
        boton1.setBounds(25, 3, 90, 20);
        jpanel.add(boton1);
        boton1.addActionListener(this);

        ex.setBounds(margen, margen, anchoAlto, anchoAlto);
        jpanel.add(ex);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boton1) {
            for (int i = 0; i < comprobacion.length; i++) {
                for (int j = 0; j < comprobacion.length; j++) {
                    if (algoritmo[i][j].equals("0|0")) {
                        comprobacion[i][j] = "*";
                    } else {
                        comprobacion[i][j] = "+";
                    }
                }
            }

            imprimirArreglo(comprobacion);

            int contUno = 0;
            int contDos = 0;
            int contTres = 0;

            int diagS, diagI;

            for (int i = 0; i < comprobacion.length; i++) {
                for (int j = 0; j < comprobacion.length; j++) {
                    // Comprobacion de filas
                    if (comprobacion[i][j].equals("+")) {
                        contUno++;
                        if (contUno == 2) {
                            JOptionPane.showMessageDialog(null, "Existe más de una reina en una fila");
                            break;
                        }
                    }
                    
                    // Comprobacion de columnas
                    if (comprobacion[j][i].equals("+")) {
                        contDos++;
                        if (contDos == 2) {
                            JOptionPane.showMessageDialog(null, "Existe más de una reina en una columna");
                            break;
                        }

                        // Comprobacion de diagonales
                        diagS = j - 1;
                        diagI = j + 1;

                        if (diagS < 0) {
                            diagS = 0;
                        } else if (diagI >= comprobacion.length) {
                            diagI = (comprobacion.length - 1);
                        }

                        System.out.println("S/I: " + diagS + "/" + diagI);

                        if (i < comprobacion.length - 1) {
                            if (comprobacion[diagS][i + 1].equals("+") || comprobacion[diagI][i + 1].equals("+")) {
                                JOptionPane.showMessageDialog(null, "En alguna diagnoal de las reinas existe mas de una reina");
                                contTres = 2;
                                break;
                            }
                        }
                    }
                }
                if (contUno == 2 || contDos == 2 || contTres == 2) {
                    break;
                }
                contUno = 0;
                contDos = 0;
                contTres = 0;
            }
            
            if (contUno == 0 && contDos == 0 && contTres == 0) {
                JOptionPane.showMessageDialog(null, "Felicidades, completaste el juego de N Reinas");
            }
        }
    }

    public void arrastreReina(MouseEvent evt) {
        if (evt.getSource() instanceof JLabel) {
            ((JLabel) evt.getSource()).setLocation(posicion(evt)[0], posicion(evt)[1]);
        }
    }

    public void valoresIniciales(MouseEvent evt) {
        if (evt.getSource() instanceof JLabel) {
            this.newI = (posicion(evt)[1] - 25) / 50;
            this.newJ = (posicion(evt)[0] - 25) / 50;
            x = posicion(evt)[0];
            y = posicion(evt)[1];
            System.out.println("INI X/Y: " + this.newI + "/" + this.newJ);
            System.out.println("X/Y: " + x + "/" + y);
        }
    }

    public void arregloTablero(MouseEvent evt) {
        if (evt.getSource() instanceof JLabel) {
            int newX = (posicion(evt)[0] - x) / 50;
            int newY = (posicion(evt)[1] - y) / 50;

            System.out.println("pos X/Y: " + posicion(evt)[0] + "/" + posicion(evt)[1]);
            System.out.println("newX: " + newX + "\nnewY: " + newY);

            algoritmo[newI][newJ] = "0|0";
            System.out.println("newI/newJ: " + newI + "/" + newJ);
            algoritmo[newI + newY][newJ + newX] = Integer.toString(posicion(evt)[0]) + "|" + Integer.toString(posicion(evt)[1]);

            count++;
            System.out.println("Movimientos: " + count);
            imprimirArreglo(algoritmo);
        }
    }

    public void imprimirArreglo(String[][] arreglo) {
        for (int i = 0; i < arreglo.length; i++) {
            for (int j = 0; j < arreglo.length; j++) {
                System.out.print(arreglo[i][j] + "    ");
            }
            System.out.println("");
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
            newX = 25 + (evt.getXOnScreen() - espacio - primerosMenosUltimosX);
        } else if (primerosMenosUltimosX < 75) {
            newX = 75 + (evt.getXOnScreen() - espacio - primerosMenosUltimosX);
        } else {
            newX = 25 + ((evt.getXOnScreen() - espacio + 100) - primerosMenosUltimosX);
        }

        if (primerosMenosUltimosY < 25) {
            newY = 25 + (evt.getYOnScreen() - espacio - primerosMenosUltimosY);
        } else if (primerosMenosUltimosY < 75) {
            newY = 75 + (evt.getYOnScreen() - espacio - primerosMenosUltimosY);
        } else {
            newY = 25 + ((evt.getYOnScreen() - espacio + 100) - primerosMenosUltimosY);
        }

        int retorno[] = {newX, newY};

        return retorno;
    }

}