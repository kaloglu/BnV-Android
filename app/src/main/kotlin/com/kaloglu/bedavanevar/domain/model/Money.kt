package com.kaloglu.bedavanevar.domain.model

import java.io.Serializable
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

class Money private constructor(b: BigDecimal) : Serializable {

    private var value = BigDecimal.ZERO

    /**
     * Check if Money value less than zero
     *
     * @return true if Money value less than zero
     */
    val isZero: Boolean
        get() = value.compareTo(BigDecimal.ZERO) == 0

    /**
     * Check if Money value less than zero
     *
     * @return true if Money value less than zero
     */
    val isLessThanZero: Boolean
        get() = value.compareTo(BigDecimal.ZERO) < 0

    init {
        value = b
    }

    fun getValue(): Double {
        return value.toDouble()
    }

    /**
     * Add Money value into current object and update
     *
     * @return Updated Money object
     */
    fun add(money: Money): Money {
        value = value.add(money.value)
        return this
    }

    /**
     * Subtract Money value from current object and update
     *
     * @return Updated Money object
     */
    fun subtract(money: Money): Money {
        value = value.subtract(money.value)
        return this
    }

    /**
     * Create new Money object by multiply with an integer value
     *
     * @return New Money object with result of multiply operation
     */
    fun multiply(`val`: Int): Money {
        return multiply(BigDecimal(`val`))
    }

    /**
     * Create new Money object by multiply with a long value
     *
     * @return New Money object with result of multiply operation
     */
    fun multiply(`val`: Long): Money {
        return multiply(BigDecimal(`val`))
    }

    /**
     * Create new Money object by multiply with a double value
     *
     * @return New Money object with result of multiply operation
     */
    fun multiply(`val`: Double): Money {
        return multiply(BigDecimal(`val`))
    }

    /**
     * Create new Money object by multiply with a big decimal value
     *
     * @return New Money object with result of multiply operation
     */
    fun multiply(`val`: BigDecimal): Money {
        return of(value.multiply(`val`))
    }

    /**
     * Create new Money object by divide with an integer value
     *
     * @return New Money object with result of divide operation
     */
    fun divide(`val`: Int): Money {
        return divide(BigDecimal(`val`))
    }

    /**
     * Create new Money object by divide with a long value
     *
     * @return New Money object with result of long operation
     */
    fun divide(`val`: Long): Money {
        return divide(BigDecimal(`val`))
    }

    /**
     * Create new Money object by divide with a double value
     *
     * @return New Money object with result of divide operation
     */
    fun divide(`val`: Double): Money {
        return divide(BigDecimal(`val`))
    }

    /**
     * Create new Money object by divide with a big decimal value
     *
     * @return New Money object with result of divide operation
     */
    fun divide(`val`: BigDecimal): Money {
        return of(value.divide(`val`, RoundingMode.HALF_EVEN))
    }

    override fun equals(other: Any?): Boolean {
        return other!!.hashCode() == hashCode()
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    /**
     * Stringify Money value
     *
     * @return String value of Money (000.000,00 TL)
     */
    override fun toString(): String {
        return decimalFormat.format(value)
    }

    /**
     * Create Money Format Builder to custom string UI_DATE_FORMAT
     *
     * @return Created Money Format Builder object
     */
    fun toFormatBuilder(): MoneyFormatBuilder {
        return MoneyFormatBuilder(this)
    }

    fun clone(): Money {
        return of(value)
    }

    /**
     * Check if Money value has penny (or kuruş) value
     *
     * @return true if Money has penny (or kuruş) value
     */
    fun hasPenny(): Boolean {
        return value.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) != 0
    }

    inner class MoneyFormatBuilder internal constructor(internal var money: Money) {

        internal var withSign = false
        internal var withPenny = false
        internal var alwaysPenny = false

        /**
         * Add TL sign into UI_DATE_FORMAT
         *
         * @return Updated Money Format Builder object
         */
        fun sign(): MoneyFormatBuilder {
            this.withSign = true
            return this
        }

        /**
         * Add decimal values (penny or kuruş) into UI_DATE_FORMAT
         *
         * @return Updated Money Format Builder object
         */
        fun penny(alwaysPenny: Boolean): MoneyFormatBuilder {
            this.withPenny = true
            this.alwaysPenny = alwaysPenny
            return this
        }

        /**
         * Stringify Money value with Money Format Builder
         *
         * @return String value of Money
         */
        override fun toString(): String {
            val formatted: String
            if (alwaysPenny) {
                formatted = withPennyDecimalFormat.format(money.value)
            } else if (withPenny) {
                if (money.hasPenny())
                    formatted = withPennyDecimalFormat.format(money.value)
                else
                    formatted = withoutPennyDecimalFormat.format(money.value)
            } else {
                formatted = withoutPennyDecimalFormat.format(money.value)
            }

            return if (withSign)
                "$formatted TL"
            else
                formatted
        }


        private val withPennyDecimalFormat = DecimalFormat("#,##0.00", decimalFormatSymbols)
        private val withoutPennyDecimalFormat = DecimalFormat("#,##0", decimalFormatSymbols)
    }

    companion object {

        val ZERO = of(0)

        private val decimalFormatSymbols = provideDecimalFormatSymbols()

        private val decimalFormat = DecimalFormat("#,##0.00 TL", decimalFormatSymbols)

        /**
         * Create Money object with zero value
         *
         * @return Created Money object with zero value
         */
        fun zero(): Money {
            return ZERO.clone()
        }

        /**
         * Create Money object from a string value
         *
         * @return Created Money object
         */
        fun of(amount: String): Money {
            return of(BigDecimal(amount))
        }

        /**
         * Create Money object from an integer value
         *
         * @return Created Money object
         */
        fun of(amount: Int): Money {
            return of(BigDecimal(amount))
        }

        /**
         * Create Money object from a long value
         *
         * @return Created Money object
         */
        fun of(amount: Long): Money {
            return of(BigDecimal(amount))
        }

        /**
         * Create Money object from a double value
         *
         * @return Created Money object
         */
        fun of(amount: Double): Money {
            return of(BigDecimal(amount))
        }


        /**
         * Create Money object from a big decimal value
         *
         * @return Created Money object
         */
        fun of(amount: BigDecimal): Money {
            return Money(amount.setScale(2, RoundingMode.HALF_EVEN))
        }

        private fun provideDecimalFormatSymbols(): DecimalFormatSymbols {
            val symbols = DecimalFormatSymbols()
            symbols.decimalSeparator = ','
            symbols.groupingSeparator = '.'
            return symbols
        }
    }

}