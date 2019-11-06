package otraprueba;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.*;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.*;
import javax.swing.border.Border;

public class OtraPrueba extends JFrame implements ActionListener {

    String[][] algoritmo = new String[reinas][reinas], comprobacion = new String[reinas][reinas];
    String[] parts;

    static short reinas = 0, count = 0, anchoAlto = 50, margen = 25, espacio = 50, newI, newJ, x, y, cooX, cooY;

    JPanel jpanel = (JPanel) this.getContentPane();
    JLabel ex = new JLabel();
    JLabel label[] = new JLabel[reinas];
    JLabel tablero[][] = new JLabel[reinas][reinas];
    Border border = BorderFactory.createLineBorder(Color.black, 1);

    JButton boton1;

    String path = "queen.png";
    URL url = this.getClass().getResource(path);
    ImageIcon icon = new ImageIcon(url);

    public static void main(String[] args) {
        reinas = Short.parseShort(JOptionPane.showInputDialog(null, "Ingrese la cantidad de reinas"));
        OtraPrueba op = new OtraPrueba();
        op.setBounds(0, 0, (60 * reinas), (60 * reinas));
        op.setVisible(true);
        op.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public OtraPrueba() {
        // Declaracion de reinas
        for (short i = 0; i < label.length; i++) {
            label[i] = new JLabel();
            // r + r + r + r
            label[i].setBounds(margen + (espacio * i), margen, anchoAlto, anchoAlto);
            label[i].setIcon(icon);
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

            for (short j = 0; j < algoritmo.length; j++) {
//                if (i == 0) {
//                    algoritmo[i][j] = Integer.toString((margen + (espacio * j))) + "|" + Integer.toString(margen);
//                } else {
//                    algoritmo[i][j] = "0|0";
//                }

                algoritmo[i][j] = (i == 0) ? Integer.toString((margen + (espacio * j))) + "|" + Integer.toString(margen) : "0|0";
                // variable = (condicion) ? primerValor : segundoValor;
                // operadores ternarios
            }
        }

        // Declaracion del tablero
        for (short i = 0; i < tablero.length; i++) {
            for (short j = 0; j < tablero.length; j++) {
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
            for (short i = 0; i < comprobacion.length; i++) {
                for (short j = 0; j < comprobacion.length; j++) {
//                    if (algoritmo[i][j].equals("0|0")) {
//                        comprobacion[i][j] = "*";
//                    } else {
//                        comprobacion[i][j] = "+";
//                    }

                    comprobacion[i][j] = algoritmo[i][j].equals("0|0") ? "*" : "+";
                }
            }

            imprimirArreglo(comprobacion);

            short contUno = 0, contDos = 0, contTres = 0;

            short diagS, diagI;

            for (short i = 0; i < comprobacion.length; i++) {
                for (short j = 0; j < comprobacion.length; j++) {
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
                        diagS = (short) (j - 1);
                        diagI = (short) (j + 1);

                        if (diagS < 0) {
                            diagS = 0;
                        } else if (diagI >= comprobacion.length) {
                            diagI = (short) (comprobacion.length - 1);
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
            OtraPrueba.newI = (short) ((posicion(evt)[1] - 25) / 50);
            OtraPrueba.newJ = (short) ((posicion(evt)[0] - 25) / 50);
            x = (short) posicion(evt)[0];
            y = (short) posicion(evt)[1];
            System.out.println("INI X/Y: " + OtraPrueba.newI + "/" + OtraPrueba.newJ);
            System.out.println("X/Y: " + x + "/" + y);
        }
    }

    public void arregloTablero(MouseEvent evt) {
        if (evt.getSource() instanceof JLabel) {
            short newX = (short) ((posicion(evt)[0] - x) / 50);
            short newY = (short) ((posicion(evt)[1] - y) / 50);

            System.out.println("pos X/Y: " + posicion(evt)[0] + "/" + posicion(evt)[1]);
            System.out.println("newX: " + newX + "\nnewY: " + newY);

            System.out.println("newI/newJ: " + newI + "/" + newJ);
            System.out.println("newX/newY: " + newX + "/" + newY);

            if (newX != 0 || newY != 0) {
                algoritmo[newI][newJ] = "0|0";
                algoritmo[newI + newY][newJ + newX] = (newY * 50) + "|" + (newX * 50);
            }

            count++;
            System.out.println("Movimientos: " + count);
            imprimirArreglo(algoritmo);
        }
    }

    public void imprimirArreglo(String[][] arreglo) {
        for (String[] arreglo1 : arreglo) {
            for (short j = 0; j < arreglo.length; j++) {
                System.out.print(arreglo1[j] + "    ");
            }
            System.out.println("");
        }
    }

    public short[] posicion(MouseEvent evt) {
        JLabel label = (JLabel) evt.getSource();
        Point labelLocation = label.getLocation();

        short x = (short) (labelLocation.x + evt.getX());
        short y = (short) (labelLocation.y + evt.getY());

        // Obtener numero de casilla x, y
        x = (short) ((x - margen) / espacio);
        y = (short) ((y - margen) / espacio);

        // No posicionar fuera del tablero
        x = (short) Math.min(Math.max(0, x), reinas - 1);
        y = (short) Math.min(Math.max(0, y), reinas - 1);

        // Revertir posicion exacta de casilla
        x = (short) ((x * espacio) + margen);
        y = (short) ((y * espacio) + margen);

        return new short[]{x, y};
    }

}
