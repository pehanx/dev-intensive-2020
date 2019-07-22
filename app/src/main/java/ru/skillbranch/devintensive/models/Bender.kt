package ru.skillbranch.devintensive.models

import android.util.Log

class Bender(
		var status: Status = Status.NORMAL,
		var question: Question = Question.NAME) {
	
	fun askQuestion(): String = question.question
	
	fun listenAnswer(answer: String): Pair<String, Triple<Int, Int, Int>> {
		Log.d("Bender", "Answer: $answer")
		return if (question.answers.contains(answer.trim().toLowerCase())) {
			question = question.nextQuestion()
			"Отлично - ты справился!\n${question.question}" to status.color
		} else {
			if (status == Status.NORMAL) {
				question = Question.NAME
				"Это неправильный ответ. Давай все по новой\n${question.question}"
			} else {
				"Это неправильный ответ!\n${question.question}"
			} to status.color
		}
	}
	
	enum class Status(val color: Triple<Int, Int, Int>){
		NORMAL(Triple(255,255,255)),
		WARNING(Triple(255,120,0)),
		DANGER(Triple(255,60,60)),
		CRITICAL(Triple(255,255,0));
		
		fun nextStatus(): Status {
			return if (ordinal < values().lastIndex) {
				values()[ordinal + 1]
			} else {
				values()[0]
			}
		}
	}
	
	enum class Question(val question: String, val answers: List<String>){
		NAME("Как меня зовут?", listOf("bender", "бендер")) {
			override fun nextQuestion(): Question {
				return PROFESSION
			}
		},
		PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")) {
			override fun nextQuestion(): Question {
				return MATERIAL
			}
		},
		MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")) {
			override fun nextQuestion(): Question {
				return BDAY
			}
		},
		BDAY("Когда меня создали?", listOf("2993")) {
			override fun nextQuestion(): Question {
				return SERIAL
			}
		},
		SERIAL("Мой серийный номер?", listOf("2716057")) {
			override fun nextQuestion(): Question {
				return IDLE
			}
		},
		IDLE("На этом все, вопросов больше нет", listOf()) {
			override fun nextQuestion(): Question {
				return IDLE
			}
		};
		
		abstract fun nextQuestion(): Question
	}
}