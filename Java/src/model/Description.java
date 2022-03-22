package model;

import java.io.Serializable;

/**
 * Represents description. Is either functional or non functional.
 * @author Group 6 - 1Y ICT A2020
 * @version 1.0 - December 2020
 * @since 1.0
 */
abstract public class Description implements Serializable
{

  private boolean isFunctional;

  /**
   * One-argument constructor.
   * @param isFunctional Decides if the description will be functional
   */
  public Description(boolean isFunctional) //nonFunctional
  {
      this.isFunctional = isFunctional;

  }

  /**
   * Gets the description
   * @return A String array containing the description. Three strings if its functional, one if its non functional.
   */
  public abstract String[] getDescription();

  /**
   * Says if the description is functional.
   * @return True if the description is functional. False if its non functional.
   */
  public boolean isFunctional()
  {
    return isFunctional;
  }
}
