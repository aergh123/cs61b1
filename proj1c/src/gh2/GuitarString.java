package gh2;

// TODO: maybe more imports

import deque.ArrayDeque61B;
import deque.Deque61B;

//Note: This file will not compile until you complete the Deque61B implementations
public class GuitarString<T> extends ArrayDeque61B<T> {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor
    Deque61B<Double> arrayDeque;
    /* Buffer for storing sound data. */
    // TODO: uncomment the following line once you're ready to start this portion
    // private Deque61B<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        // TODO: Initialize the buffer with capacity = SR / frequency. You'll need to
        //       cast the result of this division operation into an int. For
        //       better accuracy, use the Math.round() function before casting.
        //       Your should initially fill your buffer with zeros.
        int capacity= (int) Math.round(SR/frequency)+1;
        arrayDeque=new ArrayDeque61B<>(capacity);
        arrayDeque.init(0.0,capacity);
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        // TODO: Dequeue everything in buffer, and replace with random numbers
        //       between -0.5 and 0.5. You can get such a number by using:
        //       double r = Math.random() - 0.5;
        //
        //       Make sure that your random numbers are different from each
        //       other. This does not mean that you need to check that the numbers
        //       are different from each other. It means you should repeatedly call
        //       Math.random() - 0.5 to generate new random numbers for each array index.
        for (int i=0;i<arrayDeque.length();i++){
          double r = Math.random() - 0.5;
          arrayDeque.addFirst(r);
        }

    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        // TODO: Dequeue the front sample and enqueue a new sample that is
        //       the average of the two multiplied by the DECAY factor.
        //       **Do not call StdAudio.play().**
       int first=arrayDeque.front();
       Double arrayItem1=(arrayDeque.get((first+1)%arrayDeque.length()));
       Double arrayItem2 =(arrayDeque.get((first+2)%arrayDeque.length()));
       arrayDeque.removeFirst();
       arrayDeque.addLast(DECAY*(arrayItem1+arrayItem2)/2);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        // TODO: Return the correct thing.
        int first=arrayDeque.front();
        Double returnItem=arrayDeque.get((first+1)%arrayDeque.length());
        if(returnItem==null){
            return 0;
        }
        return returnItem;
    }
}
    // TODO: Remove all comments that say TODO when you're done.
