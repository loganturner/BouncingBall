/*
 * Logan Turner
 * Computer Science 2
 * Personal Project
 */

package logan_turner_personal_project;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 *
 * @author loganturner
 */
public class PhysicsArea extends JPanel implements ActionListener
{
    Ball ball;

    public PhysicsArea()
    {
        ball = new Ball(50, Color.RED);
    }

    public void update(double gravity, double elasticity, double airResistance)
    {
        ball.updatePhysics(gravity, elasticity, airResistance, 400, 300);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        // Draw the ball
        g.setColor(ball.color);
        g.fillOval((int)ball.xPosition, (int)ball.yPosition, ball.radius*2, ball.radius*2);

        // Draw the ground
        g.setColor(Color.BLACK);
        g.fillRect(0, 400, 1000, 1000);
        
        // Draw height record lines
        for (double i : ball.records)
        {
            g.drawLine(50, (int)i, 500, (int)i);
        }
    }

    public void actionPerformed(ActionEvent ae)
    {
        ball.reset(550);
    }
}
