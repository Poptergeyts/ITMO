package organization

import kotlinx.serialization.Serializable

/**
 * Coordinates.
 *
 * A class symbolizing a coordinates X and Y.
 */
@Serializable
class Coordinates {
    private val x: Double
    private val y: Double

    constructor(_x: Double, _y: Double) {
        x = _x
        y = if(_y <= 498) _y
            else throw RuntimeException("Greater than 498")
    }

    /**
     * Overrided to output information about coordinates.
     */
    override fun toString(): String {
        return "{X: $x, Y: $y}"
    }
}