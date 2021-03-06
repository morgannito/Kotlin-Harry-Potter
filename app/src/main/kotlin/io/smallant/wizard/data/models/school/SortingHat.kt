package io.smallant.wizard.data.models.school

import io.smallant.wizard.data.models.characters.Student
import io.smallant.wizard.data.models.houses.HogwartsHouse

object SortingHat {
    fun sort(student: Student, defaultHouse: HogwartsHouse? = null) {
        if(defaultHouse != null)
            student.house = defaultHouse
        else
            student.house = HogwartsHouse.HOUSES.random()
    }
}