import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Floor3D extends JPanel implements ActionListener {
    static final int W = 480, H = 300, SCALE = 2;
    static final int SIZE = 20;          // il pavimento va da -SIZE a +SIZE su x e z
    static final double EYE = 1.4, DIST = 5, DT = 0.016, F = W * 0.866;

    // astronauta: {x locale, y, z locale, raggio, segno oscillazione}
    static final double[][] P = {
        {-0.15, 0.10, 0.04, 0.15,  1}, { 0.15, 0.10, 0.04, 0.15, -1},   // stivali
        {-0.15, 0.33, 0.00, 0.16,  1}, { 0.15, 0.33, 0.00, 0.16, -1},   // gambe
        { 0.00, 0.62, 0.00, 0.32,  0}, { 0.00, 0.90, 0.00, 0.30,  0},   // busto
        { 0.00, 0.82,-0.32, 0.22,  0}, { 0.00, 0.80, 0.30, 0.08,  0},   // zaino, pannello
        {-0.40, 0.84, 0.00, 0.12, -1}, {-0.42, 0.64, 0.00, 0.11, -1},   // braccio e mano sx
        { 0.40, 0.84, 0.00, 0.12,  1}, { 0.42, 0.64, 0.00, 0.11,  1},   // braccio e mano dx
        { 0.00, 1.30, 0.00, 0.34,  0}, { 0.00, 1.30, 0.17, 0.20,  0}    // casco, visiera
    };
    static final int[] COL = {
        0x6B6B78, 0x6B6B78, 0xF2F2F2, 0xF2F2F2, 0xF2F2F2, 0xF2F2F2, 0xB8BCC8, 0xE8803A,
        0xF2F2F2, 0xD8D8E0, 0xF2F2F2, 0xD8D8E0, 0xFAFAFA, 0x1E3A6E
    };

    double x = 0, y = 0, z = 0, vy = 0, yaw = 0, pitch = 0, face = 0, phase = 0, swing = 0;
    double camX, camY, camZ;
    boolean fly = false, onGround = true, third = true;
    final Set<Integer> keys = new HashSet<>();
    final BufferedImage img = new BufferedImage(W, H, BufferedImage.TYPE_INT_RGB);
    final int[] px = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();

    Floor3D() {
        setPreferredSize(new Dimension(W * SCALE, H * SCALE));
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int k = e.getKeyCode();
                if (!keys.add(k)) return;               // ignora l'auto-repeat
                if (k == KeyEvent.VK_F) fly = !fly;
                if (k == KeyEvent.VK_V) third = !third;
                if (k == KeyEvent.VK_SPACE && !fly && onGround) { vy = 8; onGround = false; }
            }
            public void keyReleased(KeyEvent e) { keys.remove(e.getKeyCode()); }
        });
        MouseAdapter m = new MouseAdapter() {
            int lx, ly;
            public void mousePressed(MouseEvent e) { lx = e.getX(); ly = e.getY(); }
            public void mouseDragged(MouseEvent e) {
                yaw += (e.getX() - lx) * 0.005;
                pitch = clamp(pitch - (e.getY() - ly) * 0.005);
                lx = e.getX(); ly = e.getY();
            }
        };
        addMouseListener(m);
        addMouseMotionListener(m);
        new javax.swing.Timer(16, this).start();
    }

    static double clamp(double p) { return Math.max(-1.5, Math.min(1.5, p)); }

    // ---------- logica ----------
    public void actionPerformed(ActionEvent ev) {
        if (keys.contains(KeyEvent.VK_LEFT)) yaw -= 1.8 * DT;
        if (keys.contains(KeyEvent.VK_RIGHT)) yaw += 1.8 * DT;
        if (keys.contains(KeyEvent.VK_UP)) pitch = clamp(pitch + 1.2 * DT);
        if (keys.contains(KeyEvent.VK_DOWN)) pitch = clamp(pitch - 1.2 * DT);

        double mx = 0, mz = 0, s = Math.sin(yaw), c = Math.cos(yaw);
        if (keys.contains(KeyEvent.VK_W)) { mx += s; mz += c; }
        if (keys.contains(KeyEvent.VK_S)) { mx -= s; mz -= c; }
        if (keys.contains(KeyEvent.VK_D)) { mx += c; mz -= s; }
        if (keys.contains(KeyEvent.VK_A)) { mx -= c; mz += s; }
        double len = Math.hypot(mx, mz);
        boolean run = keys.contains(KeyEvent.VK_SHIFT);
        if (len > 0) {
            double sp = (run ? 8 : 4) * DT / len;
            x += mx * sp;
            z += mz * sp;
            double d = Math.atan2(mx, mz) - face;           // il personaggio si gira verso la direzione
            while (d > Math.PI) d -= 2 * Math.PI;
            while (d < -Math.PI) d += 2 * Math.PI;
            face += d * Math.min(1, 12 * DT);
            phase += (run ? 8 : 4) * DT * 2.2;
        }
        swing = (len > 0 && onGround) ? Math.sin(phase) * 0.2 : 0;

        boolean over = Math.abs(x) <= SIZE && Math.abs(z) <= SIZE;
        if (fly) {
            if (keys.contains(KeyEvent.VK_SPACE)) y += 4 * DT;
            if (keys.contains(KeyEvent.VK_C)) y -= 4 * DT;
            y = Math.max(y, 0);
            vy = 0;
        } else {
            vy -= 25 * DT;
            y += vy * DT;
            if (over && y <= 0) { y = 0; vy = 0; onGround = true; }
            else onGround = false;
            if (y < -30) { x = 0; z = 0; y = 0; vy = 0; }   // caduto dal bordo: respawn
        }

        if (third) {                                         // camera orbitale dietro al personaggio
            double cpp = Math.cos(pitch);
            camX = x - Math.sin(yaw) * cpp * DIST;
            camY = Math.max(0.15, y + 1.0 - Math.sin(pitch) * DIST);
            camZ = z - Math.cos(yaw) * cpp * DIST;
        } else { camX = x; camY = y + EYE; camZ = z; }
        repaint();
    }

    // ---------- rendering ----------
    void render() {
        double sp = Math.sin(pitch), cp = Math.cos(pitch), sy = Math.sin(yaw), cy = Math.cos(yaw);
        for (int j = 0; j < H; j++) {
            double up = H / 2.0 - j;
            for (int i = 0; i < W; i++) {
                double rt = i - W / 2.0;
                double dy = up * cp + F * sp, dz0 = F * cp - up * sp;
                double dx = rt * cy + dz0 * sy, dz = -rt * sy + dz0 * cy;
                int col;
                if (dy < 0 && camY > 0) {
                    double t = -camY / dy;
                    double hx = camX + t * dx, hz = camZ + t * dz;
                    if (Math.abs(hx) <= SIZE && Math.abs(hz) <= SIZE) {
                        boolean a = (((int) Math.floor(hx) + (int) Math.floor(hz)) & 1) == 0;
                        double dist = t * Math.sqrt(dx * dx + dy * dy + dz * dz);
                        col = mix(a ? 0xDCDCDC : 0x4A5A78, 0x9FB8D8, Math.min(1, dist / 60));
                    } else col = 0x101018;
                } else if (dy >= 0) {
                    double e = dy / Math.sqrt(dx * dx + dy * dy + dz * dz);
                    col = mix(0x9FB8D8, 0x2E6FD0, Math.min(1, e * 2));
                } else col = 0x101018;
                px[j * W + i] = col;
            }
        }
    }

    static int mix(int a, int b, double t) {
        int r = (int) (((a >> 16) & 255) * (1 - t) + ((b >> 16) & 255) * t);
        int g = (int) (((a >> 8) & 255) * (1 - t) + ((b >> 8) & 255) * t);
        int bl = (int) ((a & 255) * (1 - t) + (b & 255) * t);
        return (r << 16) | (g << 8) | bl;
    }

    // proietta un punto del mondo sullo schermo: {i, j, profondità}, null se dietro la camera
    double[] project(double wx, double wy, double wz) {
        double sp = Math.sin(pitch), cp = Math.cos(pitch), sy = Math.sin(yaw), cy = Math.cos(yaw);
        double rx = wx - camX, ry = wy - camY, rz = wz - camZ;
        double x1 = rx * cy - rz * sy, z1 = rx * sy + rz * cy;
        double up = ry * cp - z1 * sp, fwd = ry * sp + z1 * cp;
        if (fwd < 0.2) return null;
        return new double[]{W / 2.0 + x1 * F / fwd, H / 2.0 - up * F / fwd, fwd};
    }

    void drawShadow(Graphics2D g) {
        if (Math.abs(x) > SIZE || Math.abs(z) > SIZE) return;
        int n = 20;
        int[] xs = new int[n], ys = new int[n];
        for (int k = 0; k < n; k++) {
            double a = 2 * Math.PI * k / n;
            double[] s = project(x + 0.5 * Math.cos(a), 0, z + 0.5 * Math.sin(a));
            if (s == null) return;
            xs[k] = (int) (s[0] * SCALE);
            ys[k] = (int) (s[1] * SCALE);
        }
        g.setColor(new Color(0, 0, 0, 90));
        g.fillPolygon(xs, ys, n);
    }

    void drawAstronaut(Graphics2D g) {
        double sf = Math.sin(face), cf = Math.cos(face);
        List<double[]> out = new ArrayList<>();            // {profondità, i, j, raggio, indice}
        for (int k = 0; k < P.length; k++) {
            double[] p = P[k];
            double lz = p[2] + p[4] * swing;
            double[] s = project(x + cf * p[0] + sf * lz, y + p[1], z - sf * p[0] + cf * lz);
            if (s != null) out.add(new double[]{s[2], s[0], s[1], p[3] * F / s[2], k});
        }
        out.sort((a, b) -> Double.compare(b[0], a[0]));    // dal più lontano al più vicino
        for (double[] o : out) {
            int rgb = COL[(int) o[4]];
            float cx = (float) (o[1] * SCALE), cy = (float) (o[2] * SCALE), r = (float) (o[3] * SCALE);
            if (r < 1) continue;
            g.setPaint(new RadialGradientPaint(new Point2D.Float(cx, cy), r,
                    new Point2D.Float(cx - r * 0.35f, cy - r * 0.35f), new float[]{0f, 1f},
                    new Color[]{new Color(mix(rgb, 0xFFFFFF, 0.5)), new Color(mix(rgb, 0x000000, 0.35))},
                    MultipleGradientPaint.CycleMethod.NO_CYCLE));
            g.fill(new java.awt.geom.Ellipse2D.Float(cx - r, cy - r, 2 * r, 2 * r));
            g.setColor(new Color(0, 0, 0, 70));
            g.draw(new java.awt.geom.Ellipse2D.Float(cx - r, cy - r, 2 * r, 2 * r));
        }
    }

    protected void paintComponent(Graphics g0) {
        super.paintComponent(g0);
        Graphics2D g = (Graphics2D) g0;
        render();
        g.drawImage(img, 0, 0, W * SCALE, H * SCALE, null);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (third) { drawShadow(g); drawAstronaut(g); }
        g.setColor(Color.BLACK);
        g.setFont(new Font("SansSerif", Font.PLAIN, 13));
        g.drawString("WASD muovi | frecce o trascina il mouse: guarda | SPAZIO salta | SHIFT corri | F volo (SPAZIO su, C giù) | V camera", 10, 18);
        g.drawString(String.format("x %.1f  y %.1f  z %.1f  %s", x, y, z, fly ? "[volo]" : ""), 10, 36);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame("Floor3D");
            Floor3D g = new Floor3D();
            f.add(g);
            f.pack();
            f.setResizable(false);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
            g.requestFocusInWindow();
        });
    }
}
