package three.stone.serialization;

import java.io.*;

public class PersistentAnimation implements Serializable, Runnable{
    transient private Thread animator;
    private int animationSpeed;
    public PersistentAnimation(int animationSpeed)
    {
        this.animationSpeed = animationSpeed;
        startAnimation();
    }

    private void writeObject(ObjectOutputStream out) throws IOException
    {
        out.defaultWriteObject();
    }
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
    {
        // our "pseudo-constructor"
        in.defaultReadObject();
        // now we are a "live" object again, so let's run rebuild and start
        startAnimation();
    }
    private void startAnimation()
    {
        animator = new Thread(this);
        animator.start();
    }

    public void run() {
        while(true) {
            System.out.println("thread start");
            try {
                Thread.sleep(9999999L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        PersistentAnimation animation = new PersistentAnimation(10);
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("animation.ser"));
            out.writeObject(animation);

            ObjectInputStream in = new ObjectInputStream(new FileInputStream("animation.ser"));
            PersistentAnimation persistentAnimation = (PersistentAnimation) in.readObject();
            System.out.println("speed: " + persistentAnimation.animationSpeed);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
