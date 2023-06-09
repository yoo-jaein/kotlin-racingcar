package calculator

class Calculator(
    private val input: String
) {
    init {
        validate()
    }

    fun run(): Double {
        return calculate()
    }

    private fun validate() {
        if (input.isEmpty()) {
            throw IllegalArgumentException("입력값이 비어있습니다.")
        }
    }

    private fun calculate(): Double {
        val operators = extractOperators()
        val numbers = extractNumbers()

        var current = numbers[0]

        for (index in 0 until numbers.size - 1) {
            current = operators[index].calculate(current, numbers[index + 1])
        }

        return current
    }

    private fun extractNumbers(): List<Double> {
        val inputWithoutBlank = input.replace(" ", "")
        val numberStrings = inputWithoutBlank.split(*arrayOf("+", "-", "*", "/"))
        return numberStrings.map(String::toDouble)
    }

    private fun extractOperators(): List<Operator> {
        val inputWithoutBlank = input.replace(" ", "")
        val operatorStrings = inputWithoutBlank.filterNot(Char::isDigit)
        return operatorStrings.map(Operator::of)
    }
}
