package de.hdm_stuttgart.mi.sd1.weather;

/**
 * Commonly used project methods.
 */
public class Common {
  /**
   * Print error message to stderr and exit with error code 1..
   * @param msg The message to be displayed.
   */
  static public void exitOnError(final String msg) {
    System.err.println("Exit on fatal error: " + msg);
    System.exit(1);
  }
}