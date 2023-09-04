package com.nagpal.shivam.workout.manager.helpers

import com.nagpal.shivam.workout.manager.dtos.request.ReorderRequestDto
import com.nagpal.shivam.workout.manager.models.OrderedBaseModel

interface IReorderHelper {
    fun <T : OrderedBaseModel> reorderItems(
        reorderRequestDto: ReorderRequestDto,
        dbItems: List<T>,
        idSelector: (T) -> Long,
    )
}
