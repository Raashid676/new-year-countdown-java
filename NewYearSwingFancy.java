import javax.swing.*;
import java.awt.*;

public class NewYearSwingFancy {

    static int count = 10;
    static int fontSize = 100;
    static boolean grow = true;

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("New Year Countdown");
            frame.setSize(900, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);

            JLabel background = new JLabel(new ImageIcon("background.gif"));
            background.setLayout(new GridBagLayout());

            JLabel text = new JLabel(String.valueOf(count)) {

                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);

                    Graphics2D g2 = (Graphics2D) g;
                    g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

                    GradientPaint gp = new GradientPaint(
                            0, 0, new Color(255, 215, 0),
                            getWidth(), getHeight(), new Color(255, 0, 128)
                    );
                    g2.setPaint(gp);
                    g2.setFont(getFont());

                    FontMetrics fm = g2.getFontMetrics();
                    int x = (getWidth() - fm.stringWidth(getText())) / 2;
                    int y = (getHeight() + fm.getAscent()) / 2;

                    g2.drawString(getText(), x, y);
                }
            };

            text.setOpaque(false);
            text.setFont(new Font("Arial Black", Font.BOLD, fontSize));

            background.add(text);
            frame.setContentPane(background);
            frame.setVisible(true);

            Timer timer = new Timer(1000, e -> {

                if (grow) {
                    fontSize += 6;
                    if (fontSize >= 120) grow = false;
                } else {
                    fontSize -= 6;
                    if (fontSize <= 90) grow = true;
                }

                if (count > 0) {
                    text.setText(String.valueOf(count));
                    text.setFont(new Font("Arial Black", Font.BOLD, fontSize));
                    count--;
                } else {
                    text.setText("HAPPY NEW YEAR");
                    text.setFont(new Font("Arial Black", Font.BOLD, 70));
                    ((Timer) e.getSource()).stop();
                }

                text.repaint();
            });

            timer.start();
        });
    }
}
