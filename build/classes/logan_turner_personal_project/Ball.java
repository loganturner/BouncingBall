/*
 * Logan Turner
 * Computer Science 2
 * Personal Project
 */

package logan_turner_personal_project;

import java.awt.Color;
import java.util.ArrayList;

/**
 * @author loganturner
 */
public class Ball
{
    public int radius;
    public Color color;

    public double xPosition;
    public double yPosition;

    // Values in pixels per frame
    public double xVelocity;
    public double yVelocity;

    // Keeps track of bounce peaks
    public ArrayList<Double> records;

    public Ball(int radius, Color color)
    {
        this.radius = radius;
        this.color = color;

        xVelocity = 0;
        yVelocity = 0;

        reset(550);
    }

    // Applies gravitational acceleration and bouncing, plus looks for new records
    public void updatePhysics(double gravity, double elasticity, double airResistance, int height, int width)
    {
        double oldVelocity = 0;

        if (touchesFloor(height))
        {
            yVelocity = -1 * elasticity * Math.abs(yVelocity);
            yPosition = height - 2 * radius;
        } else if(touchesCeiling())
        {
            yVelocity = elasticity * Math.abs(yVelocity);
            yPosition = 0;
        } else oldVelocity = yVelocity;

        yVelocity += gravity;
        yPosition += yVelocity * airResistance;

        xPosition += xVelocity * airResistance;

        if (oldVelocity < 0 && yVelocity >= 0)
        {
            records.add(yPosition);
        }
    }

    private boolean touchesFloor(int height)
    {
        return (yPosition > height - 2 * radius);
    }

    private boolean touchesCeiling()
    {
        return (yPosition < 0);
    }

    // Reset position, velocity, and height records
    public void reset(int width)
    {
        xPosition = width / 2 - radius;
        xVelocity = 0;
        yPosition = radius;
        yVelocity = 0;

        records = new ArrayList<Double>();
        records.add(yPosition);
    }
}
