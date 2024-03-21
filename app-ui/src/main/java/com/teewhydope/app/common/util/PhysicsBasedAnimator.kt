package com.teewhydope.app.common.util

import android.view.View
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import io.reactivex.rxjava3.subjects.PublishSubject

/**
 * Provides set of functions which apply physics-based motion to provided views with a help of [SpringAnimation] framework.
 */
class PhysicsBasedAnimator {

    companion object {

        private const val SPRING_DAMPING_RATIO = 0.7f

        /**
         * Animates view's movement from bottom up
         */
        fun showViewFromBottomUp(
            view: View,
            translationOffset: Float = 0f,
            animProgressObservable: PublishSubject<Any>? = null
        ) {
            val startPosition =
                getViewHeight(view) - translationOffset // Start position offset ( relative to view's current position )
            val finalPosition =
                0f - translationOffset // Final position relative to view's actual position
            getSpringAnimation(
                DynamicAnimation.TRANSLATION_Y,
                view,
                startPosition,
                finalPosition,
                SpringForce.STIFFNESS_LOW,
                SPRING_DAMPING_RATIO
            )
                .addEndListener { _, _, _, _ -> animProgressObservable?.onNext(Unit) }
                .start()
        }

        /**
         * Creates and returns [SpringAnimation] instance
         *
         * @param view to animate
         * @param startPosition start value for the animation
         * @param finalPosition the final position of the spring to be created
         * @param stiffness non-negative stiffness constant of a spring. Sets the stiffness of a spring animation
         * @param dampingRatio describes how oscillations in a system decay over time. It should be non-negative
         */
        private fun getSpringAnimation(
            animationType: DynamicAnimation.ViewProperty,
            view: View,
            startPosition: Float,
            finalPosition: Float,
            stiffness: Float,
            dampingRatio: Float
        ): SpringAnimation {
            if (view.visibility != View.VISIBLE) view.visibility = View.VISIBLE
            return SpringAnimation(view, animationType, finalPosition).apply {
                spring.stiffness = stiffness
                spring.dampingRatio = dampingRatio
            }.setStartValue(startPosition)
        }

        private fun getViewHeight(view: View?): Float {
            view?.let { return view.height.toFloat() }
            return 0f
        }
    }
}