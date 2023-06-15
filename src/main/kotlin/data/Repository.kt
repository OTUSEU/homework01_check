package data

class TasksRepositoryMemory : TasksRepository() {

    val tasks = mutableListOf<Task>()

    fun nextId(): Int = tasks.maxByOrNull { it.id ?: 0 }?.id?.inc() ?: 1
    override fun getTasks(completed: Boolean?): List<Task> =
        tasks
            .toList()
            .filter { completed == null || it.completed == completed }

    /**
     * Было - внизу Стало - вверху + изменено по умолчанию 3 пункт меню на null
     * Получить полный список задач (completed = true по умолчанию) --> (completed = null по умолчанию)
     * Получить список активных задач (completed = false)
     * добавлено список законченных задач (completed = не было) --> (completed = true)
     * В задачах в БД:
     * completed = true - законченная задача
     * completed = false - активная задача
     */
//    override fun getTasks(completed: Boolean): List<Task> {
//        var filteredTasks = tasks.toList()
//        if (!completed) filteredTasks = filteredTasks.filter { !it.completed }
//        return filteredTasks
//    }
//

    override fun addTask(task: Task): Int {
        val id = nextId()
        tasks.add(task.copy(id = id))
        return id
    }

    override fun deleteTask(id: Int) {
        tasks.removeAll { it.id == id }
    }

    override fun completeTask(id: Int) {
        tasks.first { it.id == id }.completed = true
    }

    override fun uncompleteTask(id: Int) {
        tasks.first { it.id == id }.completed = false
    }
}
