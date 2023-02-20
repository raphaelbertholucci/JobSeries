package com.bertholucci.home.helpers

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

class RecyclerViewMatcher(private val id: Int, private val parentId: Int? = null) {

    fun atPositionOnView(position: Int, targetViewId: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            private var childView: View? = null

            override fun describeTo(description: Description) {
                val descriptionId = id.toString()
                description.appendText("with id: $descriptionId")
            }

            override fun matchesSafely(item: View): Boolean {
                if (childView == null) {
                    val viewId =
                        if (parentId != null) item.rootView.findViewById<ViewGroup>(parentId)
                        else item.rootView

                    val recyclerView = viewId.findViewById<RecyclerView>(id)
                    childView = recyclerView.findViewHolderForAdapterPosition(position)?.itemView
                }

                return if (targetViewId == -1) item == childView
                else {
                    val targetView = childView?.findViewById<View>(targetViewId)
                    item == targetView
                }
            }
        }
    }

    companion object {
        fun withRecyclerView(recyclerViewId: Int, parentId: Int? = null) =
            RecyclerViewMatcher(recyclerViewId, parentId)
    }
}