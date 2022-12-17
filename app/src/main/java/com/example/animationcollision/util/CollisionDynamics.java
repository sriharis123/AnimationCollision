package com.example.animationcollision.util;

import com.example.animationcollision.state.Ball;
import com.example.animationcollision.state.Wall;

public class CollisionDynamics {

    private static long collisionElapsedThreshold = 100;

    public static boolean wallCollisionDynamics(Wall w, Ball b) { // angle of impact = angle of release
        if (b.getPrevCollision() == w && System.currentTimeMillis() - b.getCollisionTime() < collisionElapsedThreshold) return false;
        boolean collided = false;
        if (Math.abs(w.getStartX() - b.getCenterX()) < b.getRadius()) { //left
            b.setVelocity(b.getAngle() + 2 * (90 - b.getAngle()), b.getSpeed());
            collided = true;
        } else if (Math.abs(w.getStartY() - b.getCenterY()) < b.getRadius()) { //top
            b.setVelocity(b.getAngle() - 2 * (b.getAngle()), b.getSpeed());
            collided = true;
        } else if (Math.abs(b.getCenterX() - w.getEndX()) < b.getRadius()) { //right
            b.setVelocity(b.getAngle() + 2 * (90 - b.getAngle()), b.getSpeed());
            collided = true;
        } else if (Math.abs(b.getCenterY() - w.getEndY()) < b.getRadius()) { //bottom
            b.setVelocity(b.getAngle() - 2 * (b.getAngle()), b.getSpeed());
            collided = true;
        }
        if (collided) {
            b.setPrevCollision(w);
            return true;
        }
        return false;
    }

    public static boolean ballCollisionDynamics(Ball a, Ball b) {
        if (b.getPrevCollision() == a && a.getPrevCollision() == b &&
                System.currentTimeMillis() - b.getCollisionTime() < collisionElapsedThreshold)
            return false;
        double xchange = b.getCenterX() - a.getCenterX(), ychange = b.getCenterY() - a.getCenterY();
        double dist = Math.sqrt(Math.pow(xchange, 2) + Math.pow(ychange, 2));
        if (dist < a.getRadius() + b.getRadius()) {
            double temp = a.getSpeed();
            a.setSpeed(b.getSpeed());
            b.setSpeed(temp);
            b.setAngle(Math.toDegrees(Math.atan2(-ychange, xchange)));
            xchange = a.getCenterX() - b.getCenterX();
            ychange = a.getCenterY() - b.getCenterY();
            a.setAngle(Math.toDegrees(Math.atan2(-ychange, xchange)));
            b.setPrevCollision(a);
            a.setPrevCollision(b);
            return true;
        }
        return false;
    }
}
