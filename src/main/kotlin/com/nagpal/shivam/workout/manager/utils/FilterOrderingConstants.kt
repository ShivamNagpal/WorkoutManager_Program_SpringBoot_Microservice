package com.nagpal.shivam.workout.manager.utils

import org.springframework.boot.web.servlet.filter.OrderedFilter

object FilterOrderingConstants {
    const val CORS_FILTER_ORDER = OrderedFilter.HIGHEST_PRECEDENCE
    const val REQUEST_ATTRIBUTION_FILTER_ORDER = OrderedFilter.HIGHEST_PRECEDENCE + 1
    const val HTTP_LOGGING_FILTER_ORDER = OrderedFilter.HIGHEST_PRECEDENCE + 2
}
