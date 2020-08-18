package training;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
//from w  w  w .ja  v  a  2  s  .  c  om
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Main extends JFrame {
  public Main() {
    getContentPane().setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    JPanel panel1 = new JPanel();
    Border eBorder = BorderFactory.createEtchedBorder();
    panel1.setBorder(BorderFactory.createTitledBorder(eBorder, "70pct"));
    gbc.gridx = gbc.gridy = 0;
    gbc.gridwidth = gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.anchor = GridBagConstraints.NORTHWEST;
    gbc.weightx = gbc.weighty = 70;
    getContentPane().add(panel1, gbc);
    JPanel panel2 = new JPanel();
    panel2.setBorder(BorderFactory.createTitledBorder(eBorder, "30pct"));
    gbc.gridy = 1;
    gbc.weightx = 30;
    gbc.weighty = 30;
    gbc.insets = new Insets(2, 2, 2, 2);
    getContentPane().add(panel2, gbc);
    pack();
  }
  public static void main(String[] args) {
    new Main().setVisible(true);
  }
}