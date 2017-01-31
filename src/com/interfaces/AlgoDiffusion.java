package com.interfaces;

/**
 * Interface for the different aglo
 */
public interface AlgoDiffusion {
    /**
     * Configure.
     *
     * @param s the s
     */
    void configure(Capteur s);

    /**
     * Execute.
     */
    void execute();
}
