import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MapView extends JPanel {
    static final int PORT = 5005;     // deve coincidere con MAP_PORT in Floor3D
    static final int SIZE = 20;       // metà lato del pavimento, come in Floor3D
    static final int RANGE = 25;      // unità visibili dal centro verso ogni lato
    static final int PX = 600;
    static final double SC = PX / (2.0 * RANGE);

    volatile double x, y, z, yaw, face;
    volatile boolean received = false;

    MapView() {
        setPreferredSize(new Dimension(PX, PX));
        Thread t = new Thread(this::listen, "udp");
        t.setDaemon(true);
        t.start();
    }

    void listen() {
        try (DatagramSocket s = new DatagramSocket(PORT, InetAddress.getLoopbackAddress())) {
            byte[] buf = new byte[256];
            while (true) {
                DatagramPacket p = new DatagramPacket(buf, buf.length);
                s.receive(p);
                String[] v = new String(p.getData(), 0, p.getLength()).trim().split(" ");
                x = Double.parseDouble(v[0]);
                y = Double.parseDouble(v[1]);
                z = Double.parseDouble(v[2]);
                yaw = Double.parseDouble(v[3]);
                face = Double.parseDouble(v[4]);
                received = true;
                repaint();
            }
        } catch (Exception e) {
            System.err.println("Impossibile ascoltare sulla porta " + PORT + ": " + e.getMessage());
        }
    }

    double sx(double wx) { return PX / 2.0 + wx * SC; }
    double sy(double wz) { return PX / 2.0 - wz * SC; }      // z in avanti = verso l'alto

    protected void paintComponent(Graphics g0) {
        super.paintComponent(g0);
        Graphics2D g = (Graphics2D) g0;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(new Color(0x101018));
        g.fillRect(0, 0, PX, PX);

        for (int i = -SIZE; i < SIZE; i++)
            for (int j = -SIZE; j < SIZE; j++) {
                g.setColor(((i + j) & 1) == 0 ? new Color(0xDCDCDC) : new Color(0x4A5A78));
                g.fillRect((int) Math.round(sx(i)), (int) Math.round(sy(j + 1)),
                        (int) Math.ceil(SC), (int) Math.ceil(SC));
            }

        double px = sx(x), py = sy(z);
        // cono di visione (60° come la camera di Floor3D), orientato secondo yaw
        double theta = Math.toDegrees(Math.PI / 2 - yaw);
        double r = 6 * SC;
        g.setColor(new Color(255, 200, 0, 90));
        g.fill(new Arc2D.Double(px - r, py - r, 2 * r, 2 * r, theta - 30, 60, Arc2D.PIE));

        // personaggio e direzione in cui è rivolto
        g.setColor(new Color(0xE8803A));
        g.fill(new Ellipse2D.Double(px - 7, py - 7, 14, 14));
        g.setColor(Color.BLACK);
        g.draw(new Ellipse2D.Double(px - 7, py - 7, 14, 14));
        g.setStroke(new BasicStroke(2.5f));
        g.drawLine((int) px, (int) py, (int) (px + Math.sin(face) * 16), (int) (py - Math.cos(face) * 16));
        g.setStroke(new BasicStroke(1f));

        g.setColor(new Color(0, 0, 0, 170));
        g.fillRect(0, 0, PX, 24);
        g.setColor(Color.WHITE);
        g.setFont(new Font("SansSerif", Font.PLAIN, 13));
        g.drawString(received
                ? String.format(java.util.Locale.US, "x %.1f   z %.1f   quota %.1f   (su = avanti con yaw 0)", x, z, y)
                : "In attesa di Floor3D sulla porta " + PORT + "...", 10, 17);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame("Floor3D - Mappa");
            f.add(new MapView());
            f.pack();
            f.setResizable(false);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setLocation(40, 40);
            f.setVisible(true);
        });
    }
}
