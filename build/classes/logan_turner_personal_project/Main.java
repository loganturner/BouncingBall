/*
 * Computer Science 2
 * Personal Project
 * Logan Turner
 * Last Modified 04/23/2012
 *
 * Simulates the physics of a bouncing ball
 * with controls for gravity, elasticity and air resistance.
 */

package logan_turner_personal_project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;


public class Main extends JApplet implements ActionListener
{
    PhysicsArea area;

    Timer timer;

    JPanel controlPanel;
    JSlider gravitySlider;
    JSlider elasticitySlider;
    JSlider airResistanceSlider;
    JButton resetButton;

    public Main()
    {
        add(area = new PhysicsArea());

        // Controls for environmental factors

        controlPanel = new JPanel();
        controlPanel.setPreferredSize(new Dimension(400, 75));

        controlPanel.add(new JLabel("Gravity"));
        controlPanel.add(gravitySlider = new JSlider(0, 10));
        gravitySlider.setValue(7);

        controlPanel.add(new JLabel("Elasticity"));
        controlPanel.add(elasticitySlider = new JSlider(0, 10));
        elasticitySlider.setValue(8);

        controlPanel.add(new JLabel("Air Resistance"));
        controlPanel.add(airResistanceSlider = new JSlider(0, 10));
        airResistanceSlider.setValue(2);

        controlPanel.add(resetButton = new JButton("Reset"));
        resetButton.addActionListener(area);
        
        add(controlPanel, BorderLayout.SOUTH);

        timer = new Timer(50, this);

        timer.start();
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(new Main());
        frame.setSize(550, 600);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae)
    {
        area.update(gravitySlider.getValue(), elasticitySlider.getValue() / 10.0, 1 - airResistanceSlider.getValue() / 10.0);
    }
}
