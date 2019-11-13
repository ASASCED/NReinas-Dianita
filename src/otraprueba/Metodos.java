package otraprueba;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Metodos {

    Vector vPrincipal = new Vector();

    public void guardar(Jugador unJugador) {
        vPrincipal.addElement(unJugador);
    }

    public void guardarArchivo(Jugador jugador) {
        try {
            FileWriter fw = new FileWriter("puntaje.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.print(jugador.getNombre());
            pw.print("|" + jugador.getPuntuacion());
            pw.println();
            pw.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public DefaultTableModel listaJugadores() {
        Vector cabeceras = new Vector();

        cabeceras.addElement("NOMBRE");
        cabeceras.addElement("PUNTUACION");

        DefaultTableModel tabla = new DefaultTableModel(cabeceras, 0);

        try {
            FileReader fr = new FileReader("puntaje.txt");
            BufferedReader br = new BufferedReader(fr);
            String d;

            while ((d = br.readLine()) != null) {
                StringTokenizer dato = new StringTokenizer(d, "|");
                Vector x = new Vector();
                while (dato.hasMoreTokens()) {
                    x.addElement(dato.nextToken());
                }
                tabla.addRow(x);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return tabla;
    }

}
