package org.otus
/**
 * В main применен вызов DSL функции testRunner с вынесенной за скобки в лямбду функцией
 * функция testRunner определена нами заранее в Dsl.kt
 * testRunner сначала создает экземпляр класса testRunner и затем вызывает функцию из лямбды
 * Окружив контекстом Класса testRunner вызываем его функцию runTest
 * передавая ей анализируемый на before after класс
 */
fun main() {
    val test = Test()
    val runner = TestRunnerImpl<Test>()

    runner.runTest(test) { test.isEvenTest() }
    runner.runTest(test) { test.isOddTest() }
    runner.runTest(test) { test.nonNullTest() }
    runner.runTest(test) { test.isNullTest() }

/**
 * Пример вызова DSL
 */
    testRunner {
        runTest(Test()) { test.isEvenTest() }
    }

    testRunner {
        runTest(BeforeTwiceAndAfterTwiceTestClass()) { println("§ invoke test BeforeTwiceAndAfterTwiceTestClass RUNNING") }
    }


    testRunner {
        runTest(BeforeAndAfterTestClass()) { println("§ invoke test BeforeAndAfterTestClass RUNNING") }
    }

    testRunner {
        runTest(AfterOnlyTestClass()) { println("§ invoke test AfterOnlyTestClass RUNNING") }
    }

    testRunner {
        runTest(BeforeOnlyTestClass()) { println("§ invoke test BeforeOnlyTestClass RUNNING") }
    }

    testRunner {
        runTest(EmptyTestClass()) { println("§ invoke test EmptyTestClass RUNNING") }
    }

    println("### finish main ###")
}

