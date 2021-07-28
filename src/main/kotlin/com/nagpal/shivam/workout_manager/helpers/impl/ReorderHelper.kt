package com.nagpal.shivam.workout_manager.helpers.impl

import com.nagpal.shivam.workout_manager.dtos.request.ReorderRequestDto
import com.nagpal.shivam.workout_manager.exceptions.ResponseException
import com.nagpal.shivam.workout_manager.helpers.IReorderHelper
import com.nagpal.shivam.workout_manager.models.OrderedBaseModel
import com.nagpal.shivam.workout_manager.utils.ErrorMessages
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class ReorderHelper : IReorderHelper {

    override fun <T : OrderedBaseModel> reorderItems(
        reorderRequestDto: ReorderRequestDto,
        dbItems: List<T>,
        idSelector: (T) -> Long
    ) {
        val itemsIdsHashSet = HashSet(reorderRequestDto.items)
        val itemsMappedById = dbItems.associateBy { idSelector(it) }
        val dbItemsIdsSet = itemsMappedById.keys
        if (dbItemsIdsSet.size != itemsIdsHashSet.size) {
            throw ResponseException(HttpStatus.BAD_REQUEST, ErrorMessages.REORDER_ENTRIES_COUNT_MISMATCH)
        }
        dbItemsIdsSet.forEach {
            if (!itemsIdsHashSet.contains(it)) {
                throw ResponseException(HttpStatus.BAD_REQUEST, ErrorMessages.REORDER_ENTRIES_VALUE_MISMATCH)
            }
        }

        reorderRequestDto.items!!.forEachIndexed { index, id ->
            itemsMappedById[id]!!.order = index + 1
        }
    }
}
