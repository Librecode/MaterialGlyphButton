package org.librecode.glyphbutton.util

import android.content.Context
import android.graphics.Typeface
import androidx.core.content.res.ResourcesCompat
import org.librecode.glyphbutton.R

object GlyphFont {
    private var ICONS_FONT: Typeface? = null

    fun getTypeface(context: Context): Typeface? {
        if(ICONS_FONT == null) {
            ICONS_FONT = ResourcesCompat.getFont(context, R.font.material_icons)
        }

        return ICONS_FONT
    }
}
