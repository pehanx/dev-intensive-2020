package ru.skillbranch.devintensive.ui.custom

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.util.AttributeSet
import ru.skillbranch.devintensive.utils.textBitmap

class AvatarImageView @JvmOverloads constructor(ctx: Context,
                                                attrs: AttributeSet? = null,
                                                defStyleAttr: Int = 0,
                                                defStyleRes: Int = 0
) : CircleImageView(ctx, attrs, defStyleAttr, defStyleRes) {

    private var initials: String = "??"
    override fun getBitmapFromDrawable(): Bitmap? {
        return super.getBitmapFromDrawable() ?: textBitmap(width, height, initials, Color.GREEN)

    }

    fun setInitials(newInitials: String) {
        initials = newInitials
        invalidate()
    }
}