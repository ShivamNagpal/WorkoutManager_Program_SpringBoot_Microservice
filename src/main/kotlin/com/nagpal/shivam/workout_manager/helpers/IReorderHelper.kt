package com.nagpal.shivam.workout_manager.helpers

import com.nagpal.shivam.workout_manager.dtos.request.ReorderRequestDto
import com.nagpal.shivam.workout_manager.models.OrderedBaseModel

interface IReorderHelper {
    fun <T : OrderedBaseModel> reorderItems(
        reorderRequestDto: ReorderRequestDto,
        dbItems: List<T>,
        idSelector: (T) -> Long
    )
}
