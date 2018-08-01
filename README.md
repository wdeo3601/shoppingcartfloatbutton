# shoppingcartfloatbutton  
一个带小红点显示数量并且可控制小红点位置的购物车入口 button  

## 效果  
![效果图](https://github.com/wdeo3601/shoppingcartfloatbutton/blob/master/%E6%95%88%E6%9E%9C%E5%9B%BE.gif)![小红点动画](https://github.com/wdeo3601/shoppingcartfloatbutton/blob/master/%E5%B0%8F%E7%BA%A2%E7%82%B9%E5%8A%A8%E7%94%BB.gif?raw=true)  

## 集成步骤：  
1. 复制 ShoppiingCartFloatButton.kt 类文件  
1. 复制 values 目录下的 attrs.xml 文件  
1. 复制 values 目录下的 ids.xml 文件  
1. 复制 drawable-xhdpi 目录下的默认购物车图片  
1. 复制 drawable 目录下的小红点背景 shape  

## 使用方法  

1. 在布局文件中使用该控件  

     ```java
    <com.wdeo3601.shoppingcartfloatbutton.ShoppingCartFloatButton
            android:id="@+id/view"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:commodity_count="10"
            app:cart_view_width="30dp"
            app:cart_view_height="30dp"
            app:dot_above_float_margin_bottom="-23dp"
            app:dot_to_right_of_float_margin_left="-23dp"
            app:float_image_resource="@drawable/ic_shopping_cart"
            android:clipChildren="false"/>
      ```  

      * app:float_image_resource——设置购物车图片  
      * app:commodity_count——设置默认的购物车中商品数量  
      * app:cart_view_width——设置内部购物车图片 view 的宽度  
      * app:cart_view_height——设置内部购物车图片 view 的高度  
      * app:dot_above_float_margin_bottom——设置小红点左下角相对于购物车图片右上角的 Y 轴偏移量  
      * app:dot_to_right_of_float_margin_left——设置小红点左下角相对于购物车右上角的 X 轴偏移量  

1. 在代码中动态的设置购物车小红点数量  

      ```java
      val shopping = findViewById<ShoppingCartFloatButton>(R.id.view)
      shopping.setCommodityCount(12)
      ```
      提供多个方法可以在代码里更改控件的属性  
      * `fun getCommodityCount(): Int` 获取当前购物车入口控件中商品的数量  
      * `fun setCommodityCount(count: Int)` 设置小红点显示的商品数量
      * `fun setCommodityCountWithAnim(count: Int)`  设置小红点显示的商品数量，小红点 View 有一个短暂的缩放动画
      * `fun setCartViewSize(dpWidth: Int, dpHeight: Int)` 设置内部购物车 view 的尺寸，单位 dp  
      * `fun getDotBottomMargin(): Int` 获取小红点左下角相对于购物车图片右上角的 Y 轴偏移量，像素值 px  
      * `fun setDotBottomMargin(dp: Int)` 设置小红点左下角相对于购物车图片右上角的 Y 轴偏移量，单位 dp  
      * `fun getDotMarginStart(): Int` 获取小红点左下角相对于购物车右上角的 X 轴偏移量，单位 px  
      * `fun setDotMarginStart(dp: Int)` 设置小红点左下角相对于购物车右上角的 X 轴偏移量,单位 dp  
      * `fun setFloatImageResource(@DrawableRes resId: Int)` 设置购物车浮动按钮的 image

## 更新记录

**2018.7.31**
    添加方法 `fun setCommodityCountWithAnim(count: Int)`，加入购物车时可以触发小红点动画
      
## 联系方式  

QQ：974826191  
CSDN：https://blog.csdn.net/captive_rainbow_  
email：wdeo3601@163.com  
希望共同探讨共同进步~~  
      

