package org.otus

import kotlin.reflect.full.declaredFunctions

class TestRunnerImpl<T : Any> : TestRunner<T> {

    /**
     * Внутри класса переопределите метод runTest
     * Сделанный и настроенный  runTest вызывается из main:
     * runTest(BeforeTwiceAndAfterTwiceTestClass()) { println("§ BeforeTwiceAndAfterTwiceTestClass RUNNING") }
     * и передается класс в котором сидят запускаемые функции тестов (в т.ч. м.б. before* и after*):
     * BeforeTwiceAndAfterTwiceTestClass(), BeforeAndAfterTestClass, AfterOnlyTestClass, BeforeOnlyTestClass, EmptyTestClass
     * на место функции test сюда main передает println("§...")
     */
    override fun  runTest(steps: T, test: () -> Unit) {
        // чтобы передавать null в эту функцию должен быть параметр c ?
        // иначе этот  when пустой: null не пропустит Котлин
//        val mSteps = when (steps) {
//            null -> throw NullPointerException("Class with callbacks is required!")
//            else -> steps
//        }


//        val mStepsFunctions = mSteps::class.declaredFunctions
        // Старайтесь не допускать дублирования кода
//        mStepsFunctions.filter { kFunction -> kFunction.name.contains("before") }
//            .forEach { it.call(mSteps) }
        steps.beforeAfter("before")

        try {
           // test.invoke()
            test()  // короче
        } catch (e: AssertionError) {
            e.printStackTrace()
        }

        steps.beforeAfter("after")

//        mStepsFunctions.filter { kFunction -> kFunction.name.contains("after") }
//            .forEach { it.call(mSteps) }
//

    }

    /**
     * Функция расширения для Generic вызывает и before и after
     */
    private fun T.beforeAfter(beforeAfter: String) = this::class.declaredFunctions.filter { kFunction -> kFunction.name.contains(beforeAfter) }
        .forEach { it.call(this) }
}