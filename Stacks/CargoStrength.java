/**
 * This class represents the enum of a Cargo object, if the object is either
 * FRAGILE, MODERATE, or STURDY. FRAGILE objects can only allow other FRAGILE
 * objects stack above it, MODERATE objects can have other MODERATE objects and
 * FRAGILE objects stack above it, and STURDY objects can have all objects
 * stack above it.
 *
 * @author Alan George
 * ID: 114484206
 * Recitation #: 03
 */

public enum CargoStrength {
    FRAGILE,
    MODERATE,
    STURDY
}
