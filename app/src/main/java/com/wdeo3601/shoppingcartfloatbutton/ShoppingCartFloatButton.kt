package com.wdeo3601.shoppingcartfloatbutton

import android.content.Context
import android.graphics.Color
import android.support.annotation.DrawableRes
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView

/**
 * Created by wendong on 2018/7/22 0022.
 * Email:       wdeo3601@163.com
 * Description:显示商品数量的购物车按钮
 */
class ShoppingCartFloatButton : RelativeLayout {

    private lateinit var ivShoppingCartBg: ImageView
    private lateinit var tvCommodityCount: TextView

    private var mCommodityCount = 0
    @DrawableRes
    private var mFloatImageResource: Int = -1
    private var mCartViewWidth = 0
    private var mCartViewHeight = 0
    private var mDotBottomMargin = 0
    private var mDotMarginStart = 0

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        val obtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.ShoppingCartFloatButton)
        mFloatImageResource = obtainStyledAttributes.getResourceId(R.styleable.ShoppingCartFloatButton_float_image_resource, R.drawable.ic_shopping_cart)
        mCartViewWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ShoppingCartFloatButton_cart_view_width, AppUtil.INSTANCE.dp2px(44f))
        mCartViewHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ShoppingCartFloatButton_cart_view_height, AppUtil.INSTANCE.dp2px(44f))
        mDotBottomMargin = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ShoppingCartFloatButton_dot_above_float_margin_bottom, AppUtil.INSTANCE.dp2px(-23f))
        mDotMarginStart = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ShoppingCartFloatButton_dot_to_right_of_float_margin_left, AppUtil.INSTANCE.dp2px(-23f))
        mCommodityCount = obtainStyledAttributes.getInteger(R.styleable.ShoppingCartFloatButton_commodity_count, 0)
        obtainStyledAttributes.recycle()

        init()
    }

    private fun init() {
        ivShoppingCartBg = ImageView(context)
        val ivParams = RelativeLayout.LayoutParams(mCartViewWidth
                , mCartViewHeight)
        ivParams.addRule(RelativeLayout.CENTER_IN_PARENT)
        ivShoppingCartBg.layoutParams = ivParams
        ivShoppingCartBg.id = R.id.iv_shopping_cart_bg
        ivShoppingCartBg.setImageResource(mFloatImageResource)

        addView(ivShoppingCartBg)

        tvCommodityCount = TextView(context)
        val tvParams = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        tvParams.addRule(RelativeLayout.RIGHT_OF, ivShoppingCartBg.id)
        tvParams.addRule(RelativeLayout.ABOVE, ivShoppingCartBg.id)
        tvParams.bottomMargin = mDotBottomMargin
        tvParams.marginStart = mDotMarginStart
        tvCommodityCount.layoutParams = tvParams
        tvCommodityCount.minWidth = AppUtil.INSTANCE.dp2px(18f)
        tvCommodityCount.minHeight = AppUtil.INSTANCE.dp2px(18f)
        tvCommodityCount.setTextColor(Color.WHITE)
        tvCommodityCount.textSize = 9f
        tvCommodityCount.gravity = Gravity.CENTER
        tvCommodityCount.setBackgroundDrawable(resources.getDrawable(R.drawable.shape_red_circle))
        tvCommodityCount.maxLines = 1
        tvCommodityCount.ellipsize = TextUtils.TruncateAt.END

        addView(tvCommodityCount)

        setCommodityCount(mCommodityCount)
    }

    /**
     * 获取当前购物车中商品数量
     */
    fun getCommodityCount(): Int {
        return mCommodityCount
    }

    /**
     * 设置内部购物车 view 的尺寸
     */
    fun setCartViewSize(dpWidth: Int, dpHeight: Int) {
        mCartViewWidth = AppUtil.INSTANCE.dp2px(dpWidth.toFloat())
        mCartViewHeight = AppUtil.INSTANCE.dp2px(dpHeight.toFloat())
        ivShoppingCartBg.layoutParams.width = mCartViewWidth
        ivShoppingCartBg.layoutParams.height = mCartViewHeight
        ivShoppingCartBg.requestLayout()
    }

    fun getDotBottomMargin(): Int {
        return AppUtil.INSTANCE.pt2px(context, mDotBottomMargin.toFloat())
    }

    fun setDotBottomMargin(dp: Int) {
        mDotBottomMargin = AppUtil.INSTANCE.dp2px(dp.toFloat())
        (tvCommodityCount.layoutParams as RelativeLayout.LayoutParams).bottomMargin = mDotBottomMargin
        requestLayout()
    }

    fun getDotMarginStart(): Int {
        return AppUtil.INSTANCE.pt2px(context, mDotMarginStart.toFloat())
    }

    fun setDotMarginStart(dp: Int) {
        mDotMarginStart = AppUtil.INSTANCE.dp2px(dp.toFloat())
        (tvCommodityCount.layoutParams as RelativeLayout.LayoutParams).marginStart = mDotMarginStart
        requestLayout()
    }

    /**
     * 设置购物车浮动按钮的 image
     */
    fun setFloatImageResource(@DrawableRes resId: Int) {
        if (!::ivShoppingCartBg.isInitialized) {
            throw UninitializedPropertyAccessException("lateinit View ivShoppingCartBg is Uninitialized")
        }
        ivShoppingCartBg.setImageResource(resId)
    }

    /**
     * 设置小红点显示的商品数量
     */
    fun setCommodityCount(count: Int) {
        setCommodityCount(count, false)
    }

    /**
     * 设置小红点显示的商品数量(有动画)
     */
    fun setCommodityCountWithAnim(count: Int) {
        setCommodityCount(count, true)
    }

    private fun setCommodityCount(count: Int, doAnim: Boolean) {
        if (!::tvCommodityCount.isInitialized) {
            throw UninitializedPropertyAccessException("lateinit View tvCommodityCount is Uninitialized")
        }

        mCommodityCount = if (count < 0) {
            0
        } else {
            count
        }

        if (mCommodityCount == 0) {
            tvCommodityCount.visibility = View.INVISIBLE
        } else {
            tvCommodityCount.visibility = View.VISIBLE
            setRealCommodityCount(mCommodityCount, doAnim)
        }
    }

    private fun setRealCommodityCount(intCount: Int, doAnim: Boolean) {
        val strCount =
                if (intCount > 99) {
                    tvCommodityCount.textSize = 9f
                    "99+"
                } else {
                    if (intCount > 9) {
                        tvCommodityCount.textSize = 11f
                    } else {
                        tvCommodityCount.textSize = 13f
                    }
                    "$intCount"
                }

        tvCommodityCount.text = strCount

        if (doAnim) {
            val scaleAnimation = ScaleAnimation(1f, 1.1f, 1f, 1.1f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f)
            scaleAnimation.duration = 200
            scaleAnimation.repeatMode = ScaleAnimation.REVERSE
            scaleAnimation.repeatCount = 1
            tvCommodityCount.startAnimation(scaleAnimation)
        }
    }
}