package ru.skillbranch.devintensive.models

class Bender(
		var status: Status = Status.NORMAL,
		var question: Question = Question.NAME) {
	
	fun askQuestion(): String = question.question
	
	fun listenAnswer(answer: String): Pair<String, Triple<Int, Int, Int>> {
		return if (question.answers.contains(answer.toLowerCase())) {
			"Отлично - это правильный ответ!" to status.color
		} else {
			"Это не правильный ответ!" to status.color
		}
	}
	
	enum class Status(val color: Triple<Int, Int, Int>){
		NORMAL(Triple(255,255,255)),
		WARNING(Triple(255,120,0)),
		DANGER(Triple(255,60,60)),
		CRITICAL(Triple(255,255,0))
	}
	
	enum class Question(val question: String, val answers: List<String>){
		NAME("Как меня зовут?", listOf("bender", "бендер")),
		PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")),
		MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")),
		BDAY("Когда меня создали?", listOf("2993")),
		SERIAL("Мой серийный номер?", listOf("2716057")),
		IDLE("На этом все, вопросов больше нет", listOf())
	}
}