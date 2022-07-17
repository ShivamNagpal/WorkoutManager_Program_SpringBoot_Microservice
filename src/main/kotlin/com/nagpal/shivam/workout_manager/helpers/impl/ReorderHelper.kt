package com.nagpal.shivam.workout_manager.helpers.impl

import com.nagpal.shivam.workout_manager.dtos.request.ReorderRequestDto
import com.nagpal.shivam.workout_manager.enums.ResponseMessage
import com.nagpal.shivam.workout_manager.exceptions.ResponseException
import com.nagpal.shivam.workout_manager.helpers.IReorderHelper
import com.nagpal.shivam.workout_manager.models.OrderedBaseModel
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
            val responseMessage = ResponseMessage.REORDER_ENTRIES_COUNT_MISMATCH
            throw ResponseException(HttpStatus.BAD_REQUEST, responseMessage.messageCode, responseMessage.getMessage())
        }
        dbItemsIdsSet.forEach {
            if (!itemsIdsHashSet.contains(it)) {
                val responseMessage = ResponseMessage.REORDER_ENTRIES_VALUE_MISMATCH
                throw ResponseException(
                    HttpStatus.BAD_REQUEST,
                    responseMessage.messageCode,
                    responseMessage.getMessage()
                )
            }
        }

        reorderRequestDto.items!!.forEachIndexed { index, id ->
            itemsMappedById[id]!!.order = index + 1
        }
    }
}
