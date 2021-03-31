package com.vc19003337.betterweatherapp.Models;

public class Wind
{
    private double speed;
    public int deg;

    public Wind() { }

    public double getSpeed()
    {
        return speed;
    }

    public void setSpeed(int speed)
    {
        this.speed = speed;
    }

    public int getDeg()
    {
        return deg;
    }

    public void setDeg(int deg)
    {
        this.deg = deg;
    }
}
